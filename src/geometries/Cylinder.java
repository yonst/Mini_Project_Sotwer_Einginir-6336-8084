package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moishe on 04/04/2017.
 */
public class Cylinder extends RadialGeometry{

    private Point3D _axisPoint;
    private Vector _axisDirection;
    // ***************** Constructors ********************** //
    public Cylinder(){}
    public Cylinder(Cylinder cylinder){
        this._radius = cylinder._radius;
        this._axisPoint = new Point3D(cylinder._axisPoint);
        this._axisDirection = new Vector(cylinder._axisDirection);
    }
    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection){
        this._radius = radius;
        this._axisPoint = new Point3D(axisPoint);
        this._axisDirection = new Vector(axisDirection);
    }
    // ***************** Getters/Setters ********************** //
    public Point3D getAxisPoint(){
        return _axisPoint;
    }
    public Vector getAxisDirection(){
        return _axisDirection;
    }
    public void setAxisPoint(Point3D axisPoint){
        this._axisPoint = new Point3D(axisPoint);
    }
    public void setAxisDirection(Vector axisDirection){
        this._axisDirection = new Vector(axisDirection);
    }
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray){
        List<Point3D> l = new ArrayList<Point3D>();
        return l;
    }
    public Vector getNormal(Point3D point){
        return new Vector(point);
    }
}
