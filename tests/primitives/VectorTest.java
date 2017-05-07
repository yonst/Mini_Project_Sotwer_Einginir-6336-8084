package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yona on 28/03/2017.
 */
public class VectorTest {
    Vector v = new Vector(2.3,1.5,3.6);
    Point3D p3 = new Point3D(2.8, 3.9, 3.4);
    @Test
    public void setHead() throws Exception {
        Point3D p = new Point3D(1.0, 2.0, 3.0);
        v.setHead(p);
        assertEquals(v.getHead().compareTo(p), 0);
    }

    @Test
    public void getHead() throws Exception {
        setHead();
        Point3D p = new Point3D(v.getHead());
        Point3D p1 = new Point3D(1.0, 2.0, 3.0);
        assertEquals(p.compareTo(p1), 0);
    }

    @Test
    public void add() throws Exception {
        v = new Vector(2.3,1.5,3.6);
        Vector v1 = new Vector(p3);
        v.add(v1);
        assertEquals(v.getHead()._x.getCoordinate(), 2.8+2.3, 1E-5);
        assertEquals(v.getHead()._y.getCoordinate(), 1.5+3.9, 1E-5);
        assertEquals(v.getHead().getZ().getCoordinate(), 3.6+3.4, 1E-5);
    }

    @Test
    public void subtract() throws Exception {
        v = new Vector(2.3,1.5,3.6);
        Vector v1 = new Vector(p3);
        v.subtract(v1);
        assertEquals(v.getHead()._x.getCoordinate(), 2.3-2.8, 1E-5);
        assertEquals(v.getHead()._y.getCoordinate(), 1.5-3.9, 1E-5);
        assertEquals(v.getHead().getZ().getCoordinate(), 3.6-3.4, 1E-5);
    }

    @Test
    public void scale() throws Exception {
        Vector v = new Vector(1.55,2.32,3.14);
        Vector v1 = new Vector(7.75,11.6,15.7);
        v.scale(5);
        assertEquals(v.getHead()._x.getCoordinate(),v1.getHead()._x.getCoordinate(), 1E-5);
        assertEquals(v.getHead()._y.getCoordinate(),v1.getHead()._y.getCoordinate(), 1E-5);
        assertEquals(v.getHead().getZ().getCoordinate(),v1.getHead().getZ().getCoordinate(), 1E-5);
    }

    @Test
    public void crossProduct() throws Exception {
        Vector vv = new Vector(1,-7,1);
        Vector vv1 = new Vector(5,2,4);
        vv = vv.crossProduct(vv1);
        assertEquals(vv.getHead()._x.getCoordinate(), -30, 1E-5);
        assertEquals(vv.getHead()._y.getCoordinate(), 1, 1E-5);
        assertEquals(vv.getHead().getZ().getCoordinate(), 37, 1E-5);
    }

    @Test
    public void length() throws Exception {
        Vector v = new Vector(1,-7,1);
        double len=v.length();
        assertEquals(len, 7.14142, 1E-5);

    }

    @Test
    public void normalize() throws Exception {
        v.normalize();
        assertEquals(v.getHead()._x.getCoordinate(), 0.5079850199, 1E-5);
        assertEquals(v.getHead()._y.getCoordinate(), 0.3312945782, 1E-5);
        assertEquals(v.getHead().getZ().getCoordinate(), 0.7951069877, 1E-5);
        assertEquals(v.length(), 1, 1E-5);
    }

    @Test
    public void dotProduct() throws Exception {
        Vector v2 = new Vector(p3);
        assertEquals(2.3*2.8+1.5*3.9+3.6*3.4, v.dotProduct(v2),1E-5);
    }



}