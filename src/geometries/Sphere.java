package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
public class Sphere {
    private Point3D _center;
    // ***************** Constructors ********************** //
    public Sphere();
    public Sphere (Sphere sphere);
    public Sphere(double radius, Point3D center);
    public Sphere(Map<String, String> attributes);
    // ***************** Getters/Setters ********************** //
    public Point3D getCenter();
    public void setCenter(Point3D center);
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray);
    public Vector getNormal(Point3D point);
}
