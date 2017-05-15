package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.*;



public class Triangle extends Geometry implements FlatGeometry
{

    private Point3D _p1;
    private Point3D _p2;
    private Point3D _p3;

    public Point3D getP1() {
        return new Point3D(_p1);
    }

    public void setP1(Point3D p1) {
        this._p1 = new Point3D(p1);
    }

    public Point3D getP2() {
        return new Point3D(_p2);
    }

    public void setP2(Point3D p2) {
        this._p2 = new Point3D(p2);
    }

    public Point3D getP3() {
        return new Point3D(_p3);
    }

    public void setP3(Point3D p3) {
        this._p3 = new Point3D(p3);
    }

    public Triangle() {
        _p1 = new Point3D();
        _p2 = new Point3D();
        _p3 = new Point3D();
    }
    //בהנחה שהם לא על אותו ישר
//לקחים 2 זוגות משווים את השיפועים
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this._p1 = new Point3D(p1);
        this._p2 = new Point3D(p2);
        this._p3 = new Point3D(p3);
    }

    public Triangle(Triangle copy) {
        this._p1 = new Point3D(copy._p1);
        this._p2 = new Point3D(copy._p2);
        this._p3 = new Point3D(copy._p3);
    }

    // ***************** Operations ******************** //



    public Vector getNormal(Point3D p) {
        Vector v1 = new Vector(_p1);
        v1.subtract(new Vector(_p2));
        Vector normal = new Vector(_p3);
        normal.subtract(new Vector(_p2));
        normal = normal.crossProduct(v1);
        normal.normalize();
        return normal;
    }

   public List<Point3D> FindIntersections(Ray ray) {
        //
        //.....מצא את P0(נקודת המקור של הקרן)
        Point3D PO = new Point3D(ray.get_POO());
        //
        //...מצא את N( הווקטור הנורמל של המשולש)
        Vector N = new Vector(this.getNormal(_p1));
        //
        //............ .מצא את plane( המישור הנוצר ע"י N ו p3 _של המשולש)
        Plane plane = new Plane(N,_p3);
        //
        //.........מצא את נקודות החיתוך בין הקרן למישור. אם אין נקודות חיתוך - החזר רשימה ריקה
    ArrayList<Point3D> intersection = new ArrayList<>(plane.FindIntersections(ray));
        if (intersection.isEmpty()) {
            return intersection;
        }

        //................הגדר ווקטור P0_P בין P0 לבין נקודת החיתוך במישור
/*       Point3D testPoint = new Point3D(ray.get_POO());
       Point3D point_inter=new Point3D(intersection.get(0));
       Point3D temp=new Point3D(point_inter);
      // temp.subtract(testPoint);
       testPoint.subtract(temp);*/
       Vector P_PO = new Vector(intersection.get(0), ray.get_POO());




        //.............................the 3 vectors in the triangle.................................................
        Vector vec1 = new Vector(new Point3D(_p1),new Point3D(ray.get_POO()));
        Vector vec2 = new Vector(new Point3D(_p2),new Point3D(ray.get_POO()));
        Vector vec3 = new Vector(new Point3D(_p3),new Point3D(ray.get_POO()));
        //.............cross product between the V1 and V2 for the 3 sides of the triangle .........................
        Vector normal1 = new Vector(new Vector(vec1).crossProduct(new Vector(vec2)));
        Vector normal2 = new Vector(new Vector(vec3).crossProduct(new Vector(vec1)));
        Vector normal3 = new Vector(new Vector(vec2).crossProduct(new Vector(vec3)));
        //........................to normalize the normal1, normal2 and normal3.....................................
        normal1.normalize();
        normal2.normalize();
        normal3.normalize();
        //חשב את s( .מכפלה סקלרית בין P0_P לבין N בסימן מינוס()
        double dotProduct1 = new Vector(P_PO).dotProduct(normal1);
        double dotProduct2 = new Vector(P_PO).dotProduct(normal2);
        double dotProduct3 = new Vector(P_PO).dotProduct(normal3);

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