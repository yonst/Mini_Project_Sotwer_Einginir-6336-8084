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

public class Plane extends Geometry implements FlatGeometry {
    private Vector _normal;
    private Point3D _Q;

    // ***************** Constructors ********************** //
    public Plane() {
    }

    public Plane(Plane plane) {
        this(plane._normal, plane._Q);
    }

    public Plane(Vector normal, Point3D q) {
        this._normal = normal;
        this._Q = q;
    }

    // ***************** Getters/Setters ********************** //
    public Vector getNormal(Point3D point) {
        return new Vector(_normal);
    }

    public Point3D getQ() {
        return new Point3D(_Q);
    }

    public void setNormal(Vector normal) {
        _normal = new Vector(normal);
    }

    public void setQ(Point3D d) {
        _Q = new Point3D(d);
    }



        // ***************** Operations ******************** //
  public List<Point3D> FindIntersections(Ray ray) {
        Point3D PO = new Point3D(ray.get_POO());

        Vector QO = new Vector(_Q);
        Vector N = new Vector(_normal);
        Vector V = new Vector(ray.get_direction());
        Vector PO_QO = new Vector(PO);
        PO_QO.subtract(QO);


        ArrayList intersectionPoint = new ArrayList();
        double a = N.dotProduct(PO_QO);
        double b = _normal.dotProduct(ray.get_direction());
        if (b!=0)
        {
        double t = -(a / b);


        if (t > 0) {
            V.scale(t);
            Point3D po = new Point3D(PO);
            po.add(V);

            intersectionPoint.add(po);
        }}
        return intersectionPoint;





    }
}

