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

public class Plane {

    private Vector _normal;
    private Point3D _Q;
    // ***************** Constructors ********************** //
    public Plane(){}
    public Plane (Plane plane)
    {
        this(plane._normal,plane._Q);
    }
    public Plane (Vector normal, Point3D q)
    {
        this._normal = normal;
        this._Q = q;
    }
    // ***************** Getters/Setters ********************** //
    public Vector getNormal(Point3D point){return  _normal;}
    public Point3D getQ(){return _Q;}
    public void setNormal(Vector normal){this._normal=normal;}
    public void setQ(Point3D d){this._Q=d;}
    // ***************** Operations ******************** //
    public List<Point3D> FindIntersections(Ray ray){



    }

}
