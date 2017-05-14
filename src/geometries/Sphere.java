package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */

import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;

import java.util.*;

public class Sphere extends RadialGeometry{

    private double _radius;
    private Point3D _center;

    // ***************** Constructors ********************** //
    public Sphere(){}
    public Sphere (Sphere sphere){this(sphere._radius,sphere._center);}
    public Sphere(double radius, Point3D center){this._radius=radius;this._center=center;}
    public Sphere(Map<String, String> attributes){}

    // ***************** Getters/Setters ********************** //
    public Point3D getCenter(){return  new Point3D(_center);}
    public void setCenter(Point3D center){_center =  new Point3D(center);}

    // ***************** Operations ******************** //

    /**
     *
     * @param ray
     * @return
     */
    public List<Point3D> FindIntersections(Ray ray) {
        ArrayList<Point3D> list =new ArrayList<Point3D>();
        Vector L = new Vector(_center ,ray.get_POO());
        Vector V = new Vector(ray.get_direction());
        double tm = L.dotProduct(V);
        double d = Math.sqrt(Math.pow(L.length(), 2) - Math.pow(tm, 2));
        if(d>_radius)return list;
        double th=Math.sqrt(Math.pow(_radius, 2) - Math.pow(d, 2));
        double t1 = tm - th;
        double t2 = tm + th;//המצלמה עלולה להמצא בנקודה אחרת ולכן בודקים את החיתוך עם כל נקודות השפה

        if(t1>=0){

            Point3D p1=new Point3D(ray.get_POO());
            Vector tempV = new Vector(V);
            tempV.scale(t1);
            p1.add(V);
            list.add(p1);}
        if(t2>=0){//למרות שאין טעם לבדוק את הנקודה הקודמת אם הנוכחיצ לא מתאימה כך הקוד יותר קריא
            Point3D p2=new Point3D(ray.get_POO());
            Vector tempV = new Vector(V);
            tempV.scale(t2);
            p2.add(V);
            list.add(p2);}
        return list;
    }
    public Vector getNormal(Point3D point){
        Vector normal=new Vector();
        Point3D help3D=this._center;
        help3D.subtract(point);
        normal.setHead(help3D);
        return normal;
    }

}
