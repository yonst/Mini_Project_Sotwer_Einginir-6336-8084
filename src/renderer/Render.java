package renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import elements.Light;
import elements.LightSource;
import geometries.FlatGeometry;
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
        //printGrid(1);
        _imageWriter.writeToimage();
    }

    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
        Point3D rayPoint = ray.get_POO();
        Point3D minDistancePoint = null;
        Map.Entry<Geometry, List<Point3D>> entry;
        Iterator<Entry<Geometry, List<Point3D>>> it = intersectionPoints.entrySet().iterator();
        while (it.hasNext())
        {
            entry = it.next();
            for (Point3D point: intersectionPoints.get(entry.getKey())) {
                if (rayPoint.distance(point) < distance) {
                    minDistancePoint = new Point3D(point);
                    closestPoint = new HashMap<Geometry, Point3D>();
                    closestPoint.put(entry.getKey(), point);
                }
                distance = rayPoint.distance(point);
            }

        }
        if (closestPoint.isEmpty())
            return null;
        Entry<Geometry, Point3D> entry1;
        Iterator<Entry<Geometry, Point3D>> it1 = closestPoint.entrySet().iterator();
        entry1 = it1.next();
        return entry1;
    }

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
        return calcColor(geometry, point, ray, 0);
    }
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){

     if(level == RECURSION_LEVEL) return new Color(0,0,0);

        int diffuseR = 0;
        int diffuseG = 0;
        int diffuseB = 0;
        int specularR = 0;
        int specularG = 0;
        int specularB = 0;
        Iterator<LightSource> lights = _scene.getLightsIterator();
        Material material = geometry.getMaterial();
        Vector normal = geometry.getNormal(point);
        while (lights.hasNext())
        {
            LightSource light = lights.next();
            Vector L = light.getL(point);
            Color intensity = light.getIntensity(point);
            if (!occluded(light, point, geometry)) {
                Color diffuseColor = calcDiffusiveComp(material.getKd(),
                        new Vector(normal), L, intensity);
                diffuseR += diffuseColor.getRed();
                diffuseG += diffuseColor.getGreen();
                diffuseB += diffuseColor.getBlue();
                Color specularColor = calcSpecularComp(material.getKs(), new Vector(_scene.getCamera().getP0(), point), new Vector(normal),
                        L, geometry.getShininess(), intensity);
                specularR += specularColor.getRed();
                specularG += specularColor.getGreen();
                specularB += specularColor.getBlue();
            }
        }

        Point3D tmpPoo;
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);

        Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
        tmpPoo = reflectedRay.get_POO();
        tmpPoo.add(epsVector);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(new Ray(tmpPoo, reflectedRay.get_direction()));
        Color reflectedColor;
        Color reflectedLight = new Color(0,0,0);
        if(reflectedEntry != null) {
            reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = geometry.getMaterial().getKr();
            reflectedLight = new Color((int) (kr * reflectedColor.getRed()), (int) (kr * reflectedColor.getGreen()), (int) (kr * reflectedColor.getBlue()));
        }

        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        tmpPoo = refractedRay.get_POO();
        tmpPoo.add(epsVector);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(new Ray(tmpPoo, refractedRay.get_direction()));
        Color refractedColor;
        Color refractedLight = new Color(0,0,0);
        if (refractedEntry != null) {
            refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = geometry.getMaterial().getKt();
            refractedLight = new Color((int) (kt * refractedColor.getRed()), (int) (kt * refractedColor.getGreen()), (int) (kt * refractedColor.getBlue()));
        }

        int finalR = Math.min(255, geometry.getEmmission().getRed() + _scene.getAmbientLight().getIntensity().getRed() + diffuseR + specularR + reflectedLight.getRed() + refractedLight.getRed());
        int finalG = Math.min(255, geometry.getEmmission().getGreen() + _scene.getAmbientLight().getIntensity().getGreen() + diffuseG + specularG + reflectedLight.getGreen() + refractedLight.getGreen());
        int finalB = Math.min(255, geometry.getEmmission().getBlue() + _scene.getAmbientLight().getIntensity().getBlue() + diffuseB + specularB + reflectedLight.getBlue() + refractedLight.getBlue());


        return new Color(finalR, finalG, finalB);
    } // Recursive

    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){
        return new Ray(point, inRay.get_direction());
    }

    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){
        Vector R = new Vector(inRay.get_direction());
        normal.scale(2*inRay.get_direction().dotProduct(normal));
        R.subtract(normal);
        R.normalize();
        return new Ray(point, R);
    }

    private boolean occluded(LightSource light, Point3D point, Geometry geometry){
        Vector lightDirection = light.getL(point);
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry){
            intersectionPoints.remove(geometry);
        }
        for(Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet()){
            if(entry.getKey().getMaterial().getKt() == 0)
                return true;
        }
        return false;
    }

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
        double dotProductResult = normal.dotProduct(l);
        if(-1 * dotProductResult < 0) return new Color(0,0,0);
        int red = Math.min(255,(int)(kd *(-1)*dotProductResult * lightIntensity.getRed()));

        int green = Math.min(255,(int)(kd *(-1)*dotProductResult * lightIntensity.getGreen()));

        int blue = Math.min(255,(int)(kd *(-1)*dotProductResult * lightIntensity.getBlue()));

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
