package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yona on 28/03/2017.
 */

public class Point3DTest {
    Point3D p1 = new Point3D(1.0,2.0,3.0);
    Point3D p2 = new Point3D(3.0,4.0,4.0);


    @Test
    public void compareTo() throws Exception {
        Point3D p3 = new Point3D(1.0,2.0,3.0);
        assertNotEquals(0, p1.compareTo(p2) );
        assertEquals(0,p3.compareTo(p3));



    }

    @Test
    public void toStringtest() throws Exception {
        Point3D p3 = new Point3D(1.319654,2.348566,3.946546);
        String s = p3.toStringtest();
        assertEquals("(1.32,2.35,3.95)",s);
    }

    @Test
    public void add() throws Exception {
        Vector v = new Vector(p2);
        p1.add(v);
        assertEquals( 4.0,p1._x.getCoordinate(), 1E-5);
        assertEquals( 6.0,p1._y.getCoordinate(), 1E-5);
        assertEquals( 7.0,p1.getZ().getCoordinate(), 1E-5);
    }

    @Test
    public void substract() throws Exception {
        p2.Substract(p1);
        assertEquals( 2.0,p2._x.getCoordinate(), 1E-5);
        assertEquals( 2.0,p2._y.getCoordinate(), 1E-5);
        assertEquals( 1.0,p2.getZ().getCoordinate(), 1E-5);
    }

    @Test
    public void distance() throws Exception {

       assertEquals(3,p1.distance(p2),1E-5);
    }


}