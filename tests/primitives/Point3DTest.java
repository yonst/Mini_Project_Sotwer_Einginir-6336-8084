package primitives;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yona on 28/03/2017.
 */
public class Point3DTest {
    Point3D p1 = new Point3D(1.0,2.0,3.0);
    Point3D p2 = new Point3D(2.0,3.0,4.0);

    @Test
    public void getZ() throws Exception {
        
    }

    @Test
    public void set_z() throws Exception {

    }

    @Test
    public void compareTo() throws Exception {

    }

    /**@Test
    public void toString() throws Exception {

    }*/

    @Test
    public void add() throws Exception {
        Vector v = new Vector(p2);
        p1.add(v);
        assertEquals( 3.0,p1._x.getCoordinate(), 1E-5);
        assertEquals( 5.0,p1._y.getCoordinate(), 1E-5);
        assertEquals( 7.0,p1.getZ().getCoordinate(), 1E-5);
    }

    @Test
    public void substract() throws Exception {
        p2.Substract(p1);
        assertEquals( 1.0,p2._x.getCoordinate(), 1E-5);
        assertEquals( 1.0,p2._y.getCoordinate(), 1E-5);
        assertEquals( 1.0,p2.getZ().getCoordinate(), 1E-5);
    }

    @Test
    public void distance() throws Exception {

    }

}