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
    ArrayList<Point3D> intersection=new ArrayList<>(plane.FindIntersections(ray));
        if (intersection.isEmpty()) {
            return intersection;
        }

        //................הגדר ווקטור P0_P בין P0 לבין נקודת החיתוך במישור
       Point3D testPoint = new Point3D(ray.get_POO());
       Point3D point_inter=new Point3D(intersection.get(0));
       Point3D temp=new Point3D(point_inter);
      // temp.subtract(testPoint);
       testPoint.subtract(temp);
       Vector P_PO = new Vector(testPoint);




        //.............................the 3 vector in the triangle.................................................
        Vector vec1 = new Vector(new Point3D(_p1),new Point3D(ray.get_POO()));
        Vector vec2 = new Vector(new Point3D(_p2),new Point3D(ray.get_POO()));
        Vector vec3 = new Vector(new Point3D(_p3),new Point3D(ray.get_POO()));
        //.............cross product between the V1 and V2 for the 3 sides of the triangle .........................
        Vector normal1 = new Vector(new Vector(vec1).crossProduct(new Vector(vec2)));
        Vector normal2 = new Vector(new Vector(vec1).crossProduct(new Vector(vec3)));
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
 /* public Vector triangleNormal(Point3D p1, Point3D p2, Point3D cameraPoo) {
      Vector temp=new Vector(cameraPoo);
      p1.subtract(temp);
      p2.subtract(temp);
      Vector vp1 = new Vector(p1);
      Vector vp2 = new Vector(p2);
      vp1 = vp1.crossProduct(vp2);
      vp1.normalize();
      return vp1;
  }


    public List<Point3D> FindIntersections(Ray ray) {
        Vector planeNormal = new Vector(this.getNormal(_p1));
        Plane planeOfTriangle = new Plane( planeNormal,_p3);
        ArrayList<Point3D> list2 = new ArrayList<>(planeOfTriangle.FindIntersections(ray));
        if (list2.isEmpty()) {
            return list2;
        }
        Point3D planeIntersectionPoint = list2.get(0);
        Vector normal1 = new Vector(triangleNormal(new Point3D(_p1), new Point3D(_p2), new Point3D(ray.get_POO())));
        Vector normal2 = new Vector(triangleNormal(new Point3D(_p2), new Point3D(_p3), new Point3D(ray.get_POO())));
        Vector normal3 = new Vector(triangleNormal(new Point3D(_p3), new Point3D(_p1), new Point3D(ray.get_POO())));

        Point3D testPoint = new Point3D(ray.get_POO());
        Vector temp=new Vector(planeIntersectionPoint);
        testPoint.subtract(temp);
        Vector testVector = new Vector(testPoint);

        double dotProduct1 = testVector.dotProduct(normal1);
        double dotProduct2 = testVector.dotProduct(normal2);
        double dotProduct3 = testVector.dotProduct(normal3);

        if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0) || (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
            return list2;
        } else {
            list2.clear();
            return list2;
        }
    }

    @Override
    public String toString() {
        return "p1:"+_p1.toString()+" "+"p2:"+_p2.toString()+" "+"p3:"+_p3.toString();
    }

}
*/





///////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*

public class Triangle extends Geometry implements FlatGeometry{

    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    public Point3D getP1() {
        return new Point3D(p1);
    }

    public void setP1(Point3D p1) {
        this.p1 = new Point3D(p1);
    }

    public Point3D getP2() {
        return new Point3D(p2);
    }

    public void setP2(Point3D p2) {
        this.p2 = new Point3D(p2);
    }

    public Point3D getP3() {
        return new Point3D(p3);
    }

    public void setP3(Point3D p3) {
        this.p3 = new Point3D(p3);
    }

    public Triangle() {
        p1 = new Point3D();
        p2 = new Point3D();
        p3 = new Point3D();
    }
    //בהנחה שהם לא על אותו ישר
//לקחים 2 זוגות משווים את השיפועים
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this.p1 = new Point3D(p1);
        this.p2 = new Point3D(p2);
        this.p3 = new Point3D(p3);
    }

    public Triangle(Triangle copy) {
        this.p1 = new Point3D(copy.p1);
        this.p2 = new Point3D(copy.p2);
        this.p3 = new Point3D(copy.p3);
    }


*/
/*
 this.setEmmission(new Color(copy.getEmmission().getRGB()));
        this.setnShininess(copy.getnShininess());
        this.setMaterial(new Material(copy.getMaterial()));*//*
*/
/*
*//*

*/
/*
*//*
*/
/*

*//*

*/
/*
*//*





    @Override
    public Vector getNormal(Point3D p) {
        Vector v1 = new Vector(p1);
        v1.subtract(new Vector(p2));
        Vector normal = new Vector(p3);
        normal.subtract(new Vector(p2));
        normal = normal.crossProduct(v1);
        normal.normalize();
        return normal;
    }

    public Vector triangleNormal(Point3D p1, Point3D p2, Point3D cameraPoo) {
        Vector temp=new Vector(cameraPoo);
        p1.subtract(temp);
        p2.subtract(temp);
        Vector vp1 = new Vector(p1);
        Vector vp2 = new Vector(p2);
        vp1 = vp1.crossProduct(vp2);
        vp1.normalize();
        return vp1;
    }

    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        Vector planeNormal = new Vector(this.getNormal(p1));
        Plane planeOfTriangle = new Plane( planeNormal,p3);
        ArrayList<Point3D> list2 = new ArrayList<>(planeOfTriangle.FindIntersections(ray));
        if (list2.isEmpty()) {
            return list2;
        }
        Point3D planeIntersectionPoint = list2.get(0);
        Vector normal1 = new Vector(triangleNormal(new Point3D(p1), new Point3D(p2), new Point3D(ray.get_POO())));
        Vector normal2 = new Vector(triangleNormal(new Point3D(p2), new Point3D(p3), new Point3D(ray.get_POO())));
        Vector normal3 = new Vector(triangleNormal(new Point3D(p3), new Point3D(p1), new Point3D(ray.get_POO())));

        Point3D testPoint = new Point3D(ray.get_POO());
        Vector temp=new Vector(planeIntersectionPoint);
        testPoint.subtract(temp);
        Vector testVector = new Vector(testPoint);

        double dotProduct1 = testVector.dotProduct(normal1);
        double dotProduct2 = testVector.dotProduct(normal2);
        double dotProduct3 = testVector.dotProduct(normal3);

        if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0) || (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
            return list2;
        } else {
            list2.clear();
            return list2;
        }
    }

    @Override
    public String toString() {
        return "p1:"+p1.toString()+" "+"p2:"+p2.toString()+" "+"p3:"+p3.toString();
    }

}

*/




