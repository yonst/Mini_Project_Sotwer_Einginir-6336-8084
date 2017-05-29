package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Moishe on 04/04/2017.
 */

//this class represent a cylinder
public class Cylinder extends RadialGeometry{

    private Point3D _axisPoint;
    private Vector _axisDirection;
    // ***************** Constructors ********************** //
    //default constructor
    public Cylinder(){}
    //copy constructor (receive a cylinder and copy your values to ""this.cylinder"")
    public Cylinder(Cylinder cylinder){
        this._radius = cylinder._radius;
        this._axisPoint = new Point3D(cylinder._axisPoint);
        this._axisDirection = new Vector(cylinder._axisDirection);
    }
    //constructor that receive the radius ,a Point3D and a vector
    public Cylinder(double radius, Point3D axisPoint, Vector axisDirection){
        this._radius = radius;
        this._axisPoint = new Point3D(axisPoint);
        this._axisDirection = new Vector(axisDirection);
    }
    // ***************** Getters/Setters ********************** //
    public Point3D getAxisPoint(){
        return _axisPoint;
    }//return the _axisPoint
    public Vector getAxisDirection(){
        return _axisDirection;
    }//return the _axisDirection
    public void setAxisPoint(Point3D axisPoint){this._axisPoint = new Point3D(axisPoint);}//put a new value in the _axisPoint
    public void setAxisDirection(Vector axisDirection){//put a new value in the _axisDirection
        this._axisDirection = new Vector(axisDirection);
    }//
    /*************************************************
     * FUNCTION:
     * FindIntersections
     *
     * PARAMETERS:
     * Ray (the ray come from the camera)
     *
     * RETURN VALUE:
     * the list L
     *
     * MEANING:
     * This functions calculate the normal
     * vector of the triangle
     *
     **************************************************/
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray){
        List<Point3D> L = new ArrayList<Point3D>();
        return L;
    }
    public Vector getNormal(Point3D point){
        return new Vector(point);
    }}
