package renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;
import elements.LightSource;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * Created by yona on 17/05/2017.
 */
public class Render {

    private scene.Scene _scene;
    private ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;
    // ***************** Constructors ********************** //
    public Render(ImageWriter imageWriter, scene.Scene scene)
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
<<<<<<< Updated upstream

                    _imageWriter.writePixel(j, i, calcColor(closestPoint.en, closestPoint.get(closestPoint), ray));
=======
                    _imageWriter.writePixel(j, i, calcColor(closestPoint.g);

>>>>>>> Stashed changes
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
        Point3D P0 = _scene.getCamera().getP0();
        Point3D minDistancePoint = null;

        for (Point3D point: intersectionPoints.get())
            if (P0.distance(point) < distance)
                minDistancePoint = new Point3D(point);
        distance = P0.distance(point);
        return minDistancePoint;
    }
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
    private Color addColors(Color a, Color b){



}
