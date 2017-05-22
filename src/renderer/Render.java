package renderer;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import elements.LightSource;
import geometries.Geometry;
import scene.Scene;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;


import javax.imageio.ImageWriter;

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
        for (:
             ) {
            
        }
    }
    // ***************** Operations ******************** //
    public void renderImage()
    {

    }
    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {

    }
    public void printGrid(int interval)
    {

    }
    public void writeToImage(){

    }
    private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {

    }
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){

    } // Recursive
    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){

    }
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){

    }
    private boolean occluded(LightSource light, Point3D point, Geometry geometry){

    }
    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity){

    }
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity){

    }
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints){

    }
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){

    }
    private Color addColors(Color a, Color b){

    }


}
