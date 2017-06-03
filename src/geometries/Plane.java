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

//this class represent a plane
public class Plane extends Geometry implements FlatGeometry {
    private Vector _normal;//the vector that is part of the plane
    private Point3D _Q;//the point that is part of the plane

    // ***************** Constructors ********************** //

    //default constructor that Initializing the _normal and the Point3D with default values
    public Plane() {
        _normal = new Vector();
        _Q = new Point3D();
    }

    //copy constructor (receive one plane and copy your values to ""this.plane"")
    public Plane(Plane plane) {
        this(plane._normal, plane._Q);
    }

    public Plane(Vector normal, Point3D q) {
        this._normal = normal;
        this._Q = q;
    }

    // ***************** Getters/Setters ********************** //

    public Vector getNormal(Point3D point) {return new Vector(_normal);}//return a new Vector with the value of this._normal
    public Point3D getQ() {
        return new Point3D(_Q);
    }//return a new Point3D with the value of this._Q

    public void setNormal(Vector normal) {_normal = new Vector(normal);}//set a value in the this._normal
    public void setQ(Point3D d) {
        _Q = new Point3D(d);
    }//set a value in the this._Q



        // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION:
     * FindIntersections
     *
     * PARAMETERS:
     * Ray
     *
     * RETURN VALUE:
     * A list with all the points that the ray hurt the plane
     * if the ray not hurt any point in the plane
     * the list will contain null
     *
     * MEANING:
     * This functions calculate all the points that the ray hurt the palne
     *
     **************************************************/
  public List<Point3D> FindIntersections(Ray ray) {

        Point3D PO = new Point3D(ray.get_POO());
        Vector QO = new Vector(_Q);//QO receive the this._Q
        Vector N = new Vector(_normal);//N receive the this._normal
        Vector V = new Vector(ray.get_direction());//V receive the direction of the reay
        Vector PO_QO = new Vector(PO);
        PO_QO.subtract(QO);//PO_QO receive the vector between the start point of the ray and the plane

        //this code below is responsible to verify if is a point of intersection between the ray that come from the camera and the plane
        ArrayList intersectionPoint = new ArrayList();//a list that contain rhe intersection points
        double a = N.dotProduct(PO_QO);// part of the formula that verify if exist an intersection
        double b = _normal.dotProduct(ray.get_direction()); //part of the formula that verify if exist an intersection
        if (b!=0)// part of the formula that verify if exist an intersection
        {
        double t = -(a / b);// part of the formula that verify if exist an intersection

        //the code below is part of the formula that verify if exist an intersection
        if (t > 0) {
            V.scale(t);
            Point3D po = new Point3D(PO);
            po.add(V);

            intersectionPoint.add(po);
        }}
        return intersectionPoint;
    }
}

