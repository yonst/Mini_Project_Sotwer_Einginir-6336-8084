package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yona on 28/03/2017.
 */
public class VectorTest {
    Vector v = new Vector();
    Point3D p3 = new Point3D(2.8, 3.9, 3);
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

   /*@Test
    public String toString() throws Exception {

    }*/

    @Test
    public void compareTo() throws Exception {

    }

    @Test
    public void add() throws Exception {
        Vector v1 = new Vector(p3);
        v.add(v1);
        assertEquals(v.getHead()._x.getCoordinate(), 2.8, 1E-5);
    }

    @Test
    public void subtract() throws Exception {

    }

    @Test
    public void scale() throws Exception {
        Vector v = new Vector(1.55,2.32,3.14);
        Vector v1 = new Vector(7.75,11.6,15.7);
        v.scale(5);
        assertEquals(v.getHead()._x.getCoordinate(),v1.getHead()._x.getCoordinate(), 0.00000000001);
        assertEquals(v.getHead()._y.getCoordinate(),v1.getHead()._y.getCoordinate(), 0.00000000001);
        assertEquals(v.getHead().getZ().getCoordinate(),v1.getHead().getZ().getCoordinate(), 0.00000000001);
    }

    @Test
    public void crossProduct() throws Exception {

    }

    @Test
    public void length() throws Exception {

    }

    @Test
    public void normalize() throws Exception {

    }

    @Test
    public void dotProduct() throws Exception {

    }

}