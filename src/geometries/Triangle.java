package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.*;

public class Triangle //extends Geometry implements FlatGeometry
{

    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    // ***************** Constructors ********************** //
    public Triangle() {
    }

    public Triangle(Triangle triangle) {
        this(triangle._p1, triangle._p2, triangle._p3);
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this._p1 = p1;
        this._p2 = p2;
        this._p3 = p3;
    }

    public Triangle(Map<String, String> attributes) {
    }

    // ***************** Getters/Setters ********************** //
    public Point3D getP1() {
        return _p1;
    }

    public Point3D getP2() {
        return _p2;
    }

    public Point3D getP3() {
        return _p3;
    }

    public void setP1(Point3D p1) {
        this._p1 = p1;
    }

    public void setP2(Point3D p2) {
        this._p2 = p2;
    }

    public void setP3(Point3D p3) {
        this._p3 = p3;
    }

    // ***************** Operations ******************** //


    public Vector getNormal() {
        Vector vec1 = new Vector();
        Vector vec2 = new Vector();
        Vector normalVec = new Vector();
        Point3D help3D = new Point3D(_p1);
        help3D.subtract(_p3);
        vec1.setHead(help3D);
        help3D = new Point3D(_p2);
        help3D.subtract(_p3);
        vec2.setHead(help3D);
        normalVec = vec1.crossProduct(vec2);
        normalVec.normalize();
        return normalVec;
    }

    public List<Point3D> FindIntersections(Ray ray) {
    //
    //.....מצא את P0(נקודת המקור של הקרן)
    Point3D PO=new Point3D(ray.get_POO());
    //
    //...מצא את N( הווקטור הנורמל של המשולש)
    Vector N= this.getNormal();
    //
    //............ .מצא את plane( המישור הנוצר ע"י N ו p3 _של המשולש)
    Plane plane=new Plane(N,PO);
    //
    //.........מצא את נקודות החיתוך בין הקרן למישור. אם אין נקודות חיתוך - החזר רשימה ריקה
    ArrayList<Point3D> intersection=new ArrayList<>(plane.FindIntersections(ray));
    Point3D point_inter=new Point3D(intersection.get(0));
        if (intersection.isEmpty()) {
            return intersection;
        }
    //
    //................הגדר ווקטור P0_P בין P0 לבין נקודת החיתוך במישור
    point_inter.subtract(PO);
    Vector P_PO=new Vector(point_inter);
    Vector copy_P_PO=new Vector(P_PO);
        //.............................the 3 vector in the triangle.................................................
        Vector vec1 = new Vector(new Point3D(ray.get_POO()),new Point3D(_p1));
        Vector vec2 = new Vector(new Point3D(ray.get_POO()),new Point3D(_p2));
        Vector vec3 = new Vector(new Point3D(ray.get_POO()),new Point3D(_p3));
        //.............cross product between the V1 and V2 for the 3 sides of the triangle .........................
        Vector normal1=new Vector(vec1).crossProduct(new Vector(vec2));
        Vector normal2=new Vector(vec1).crossProduct(new Vector(vec3));
        Vector normal3=new Vector(vec2).crossProduct(new Vector(vec3));
        //........................to normalize the normal1, normal2 and normal3.....................................
        normal1.normalize();
        normal2.normalize();
        normal3.normalize();
        //חשב את s( .מכפלה סקלרית בין P0_P לבין N בסימן מינוס()
        double dotProduct1 = copy_P_PO.dotProduct(normal1);
        double dotProduct2 = copy_P_PO.dotProduct(normal2);
        double dotProduct3 = copy_P_PO.dotProduct(normal3);
        /*אם כל 3 ה s הם בעלי אותו סימן )כולם חיוביים או כולם שליליים( זה אומר שהקרן פוגעת במשולש. -< הכנס
        את נקודת החיתוך במישור לרשימה.*/
        if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0) || (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
            return intersection;
        } else {
            intersection.clear();
            return intersection;
        }
    }

    @Override
    public String toString() {
        return "p1:"+_p1.toString()+" "+"p2:"+_p2.toString()+" "+"p3:"+_p3.toString();
    }



}


