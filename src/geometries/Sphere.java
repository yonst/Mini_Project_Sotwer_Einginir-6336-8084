package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */

import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;
import primitives.Point2D;
import primitives.Coordinate;
import primitives.Material;

import java.util.*;

public class Sphere {

    private double _radius;
    private Point3D _center;
    // ***************** Constructors ********************** //
    public Sphere(){}
    public Sphere (Sphere sphere){this(sphere._radius,sphere._center);}
    public Sphere(double radius, Point3D center){this._radius=radius;this._center=center;}
    public Sphere(Map<String, String> attributes){}
    // ***************** Getters/Setters ********************** //
    public Point3D getCenter(){return _center;}
    public void setCenter(Point3D center){_center=center;}
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray){}
    public Vector getNormal(Point3D point){
        Vector normal=new Vector();
        Point3D help3D=this._center;
        help3D.Substract(point);
        normal.setHead(help3D);
        return normal;
    }

}
