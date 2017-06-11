package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yona on 11/06/2017.
 */
public class Rectangle extends Geometry implements FlatGeometry {
    private Triangle _tri1;
    private Triangle _tri2;

    public Rectangle(Point3D bottomLeft, Point3D topRight)
    {
        Point3D topLeft = new Point3D(bottomLeft.getX(), topRight.getY(), bottomLeft.getZ());
        Point3D bottomRight = new Point3D(topRight.getX(), bottomLeft.getY(), topRight.getZ());
        _tri1 = new Triangle(bottomLeft, topLeft, bottomRight);
        _tri2 = new Triangle(topLeft, topRight, bottomRight);
    }

    public Rectangle(Rectangle rec){
        this._tri1 = new Triangle(rec._tri1);
        this._tri2 = new Triangle(rec._tri2);
    }

    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        List<Point3D> list = new ArrayList<Point3D>();
        list = _tri1.FindIntersections(ray);
        if(list.isEmpty())
            list = _tri2.FindIntersections(ray);
        return list;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return _tri1.getNormal(point);
    }
}
