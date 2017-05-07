package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Point3D;
import primitives.Vector;

import java.util.*;

public class Triangle {

    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    // ***************** Constructors ********************** //
    public Triangle() {}
    public Triangle(Triangle triangle){this(triangle._p1,triangle._p2,triangle._p3);}
    public Triangle(Point3D p1, Point3D p2, Point3D p3){this._p1=p1;this._p2=p2;this._p3=p3;}
    public Triangle(Map<String, String> attributes){}
    // ***************** Getters/Setters ********************** //
    public Point3D getP1(){return _p1;}
    public Point3D getP2(){return _p2;}
    public Point3D getP3(){return _p3;}
    public void setP1(Point3D p1){this._p1=p1;}
    public void setP2(Point3D p2){this._p2=p2;}
    public void setP3(Point3D p3){this._p3=p3;}
    // ***************** Operations ******************** //
    public Vector getNormal(Point3D point){
        Vector vec1=new Vector();
        Vector vec2=new Vector();
        Vector normalVec = new Vector();
        Point3D help3D=new Point3D(_p1);
        help3D.subtract(_p3);
        vec1.setHead(help3D);
        help3D=new Point3D(_p2);
        help3D.subtract(_p3);
        vec2.setHead(help3D);
        normalVec = vec1.crossProduct(vec2);
        normalVec.normalize();
        return normalVec;
    }
    //public List<Point3D> FindIntersections(Ray ray) {



    }


