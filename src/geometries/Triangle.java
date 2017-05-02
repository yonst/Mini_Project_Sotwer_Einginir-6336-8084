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
        Vector vecNormal1=new Vector();
        Vector vecNormal2=new Vector();
        Point3D help3D=new Point3D(_p1);
        help3D.Substract(_p3);
        vecNormal1.setHead(help3D);
        help3D=new Point3D(_p2);
        help3D.Substract(_p3);
        vecNormal2.setHead(help3D);
        vecNormal1.dotProduct(vecNormal2);
        return vecNormal1;
    }
    public List<Point3D> FindIntersections(Ray ray) {


    }
    }


