package renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import elements.Light;
import elements.LightSource;
import geometries.Geometry;
import primitives.Material;
import scene.Scene;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Created by yona on 17/05/2017.
 */
public class Render {

    private Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;
    // ***************** Constructors ********************** //
    public Render(ImageWriter imageWriter, Scene scene)
    {
       _imageWriter = new ImageWriter(imageWriter);
       this._scene = new Scene(scene);
    }
    // ***************** Operations ******************** //
    public void renderImage()
    {
        for (int i = 0; i < _imageWriter.getNx(); i++)
        {
            for (int j = 0; j < _imageWriter.getNy(); j++)
            {
                Ray ray;
                ray = new Ray(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),j,i,
                        _scene.getScreenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()));

                Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<>(getSceneRayIntersections(ray));

                if(intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else
                {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry,Point3D> entry;
                    Iterator<Entry<Geometry, Point3D>> it = closestPoint.entrySet().iterator();
                    entry = it.next();
                    _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray));
                }

            }
        }
        printGrid(1);
        _imageWriter.writeToimage();
    }
    /*private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {

    }*/
    public void printGrid(int interval)
    {
        for (int i = 0; i < _imageWriter.getHeight(); i++){
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if (i % 50 == 0 || j % 50 == 0 || i == 499 || j == 499)
                    _imageWriter.writePixel(j, i, 255, 255, 255);  // Black
            }
        }
    }
    /*public void writeToImage(){

    }*/

 private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
      int diffuseR = 0;
      int diffuseG = 0;
      int diffuseB = 0;
      int specularR = 0;
      int specularG = 0;
      int specularB = 0;
      Iterator<LightSource> lights = _scene.getLightsIterator();
      while (lights.hasNext())
      {
          LightSource light = lights.next();
          Color diffuseColor = new Color(calcDiffusiveComp(geometry.getMaterial().getKd(),
                  geometry.getNormal(point), light.getL(point), light.getIntensity(point)).getRGB());
          diffuseR += diffuseColor.getRed();
          diffuseG += diffuseColor.getGreen();
          diffuseB += diffuseColor.getBlue();
          Color specularColor = new Color(calcSpecularComp(geometry.getMaterial().getKs(), new Vector(_scene.getCamera().getP0(), point), geometry.getNormal(point)
                  ,light.getL(point), geometry.getShininess(), light.getIntensity(point)).getRGB());
          specularR += specularColor.getRed();
          specularG += specularColor.getGreen();
          specularB += specularColor.getBlue();
      }
      int finalR = Math.min(255, geometry.getEmmission().getRed() + _scene.getAmbientLight().getIntensity().getRed() + diffuseR + specularR);
      int finalG = Math.min(255, geometry.getEmmission().getGreen() + _scene.getAmbientLight().getIntensity().getGreen() + diffuseG + specularG);
      int finalB = Math.min(255, geometry.getEmmission().getBlue() + _scene.getAmbientLight().getIntensity().getBlue() + diffuseB + specularB);

      return new Color(finalR, finalG, finalB);
    }
    /*private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){

    } // Recursive
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){

    }
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){

    }
    private boolean occluded(LightSource light, Point3D point, Geometry geometry){

    }*/
    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity){
        Vector R = new Vector(l);
        normal.scale(2*l.dotProduct(normal));
        R.subtract(normal);
        v.normalize();
        R.normalize();
        int red = Math.min(255,(int)(lightIntensity.getRed() * ks * Math.pow(v.dotProduct(R), shininess)));
        red = Math.max(red, 0);
        int green = Math.min(255,(int)(lightIntensity.getGreen() * ks * Math.pow(v.dotProduct(R), shininess)));
        green = Math.max(green, 0);
        int blue = Math.min(255,(int)(lightIntensity.getBlue() * ks * Math.pow(v.dotProduct(R), shininess)));
        blue = Math.max(blue, 0);
        return new Color(red, green, blue);
    }
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity){
        int red = Math.min(255,(int)(kd *(-1)*normal.dotProduct(l) * lightIntensity.getRed()));
        red = Math.max(red, 0);
        int green = Math.min(255,(int)(kd *(-1)*normal.dotProduct(l) * lightIntensity.getGreen()));
        green = Math.max(green, 0);
        int blue = Math.min(255,(int)(kd *(-1)*normal.dotProduct(l) * lightIntensity.getBlue()));
        blue = Math.max(blue, 0);
        return new Color(red, green, blue);
    }
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
        Point3D P0 = _scene.getCamera().getP0();
        Point3D minDistancePoint = null;
        Map.Entry<Geometry, List<Point3D>> entry;
        Iterator<Entry<Geometry, List<Point3D>>> it = intersectionPoints.entrySet().iterator();
        while (it.hasNext())
        {
            entry = it.next();
            for (Point3D point: intersectionPoints.get(entry.getKey())) {
                if (P0.distance(point) < distance) {
                    minDistancePoint = new Point3D(point);
                    closestPoint = new HashMap<Geometry, Point3D>();
                    closestPoint.put(entry.getKey(), point);
                    }
                distance = P0.distance(point);
                }

            }
        return closestPoint;
    }


    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        Map<Geometry, List<Point3D>> sceneRayIntersectPions = new HashMap<Geometry, List<Point3D>>();
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();

        while (geometries.hasNext()){
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if(!geometryIntersectionPoints.isEmpty())
                sceneRayIntersectPions.put(geometry, geometryIntersectionPoints);
        }
        return sceneRayIntersectPions;
    }
    private Color addColors(Color a, Color b) {
        int red = Math.min(255, a.getRed() + b.getRed());
        int green = Math.min(255, a.getGreen() + b.getGreen());
        int blue = Math.min(255, a.getBlue() + b.getBlue());
        return new Color(red, green, blue);
    }
}
