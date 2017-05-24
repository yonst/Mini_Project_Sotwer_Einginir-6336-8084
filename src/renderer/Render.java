package renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;
import elements.LightSource;
import geometries.Geometry;
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
                Ray ray = new Ray();
                ray = _scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),j,i,
                        _scene.getScreenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight());

                Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<>(getSceneRayIntersections(ray));

                if(intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else
                {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry,Point3D> entry;
                    Iterator<Entry<Geometry, Point3D>> it = closestPoint.entrySet().iterator();
                    entry = it.next();
                    _imageWriter.writePixel(j, i, calcColor(entry.getKey(), closestPoint.get(entry.getKey()), ray));
                }

            }
        }
    }
    /*private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {

    }*/
    public void printGrid(int interval)
    {

    }
    /*public void writeToImage(){

    }*/

 private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
      return  addColors(_scene.getAmbientLight().getIntensity(), geometry.getEmmission());
    }
    /*private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){

    } // Recursive
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){

    }
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){

    }
    private boolean occluded(LightSource light, Point3D point, Geometry geometry){

    }*/
    /*private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity){

    }
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity){

    }*/
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
        Map<Geometry, List<Point3D>> sceneRayIntersectPions = new HashMap<>();
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();

        while (geometries.hasNext()){
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            sceneRayIntersectPions.put(geometry, geometryIntersectionPoints);
        }
        return sceneRayIntersectPions;
    }
    private Color addColors(Color a, Color b) {
        return new Color(a.getRGB() + b.getRGB());
    }
}
