package primitives;

import org.junit.Test;

import static org.junit.Assert.*;
import static java.lang.System.out;
/**
 * Created by yona on 28/03/2017.
 */
public class VectorTest {
    /************************************** Point3D tests **************************************************************/
    @Test
    public void Test01() {
        out.println("Test01: Point3D compareTo");
        Point3D point3D = new Point3D(2.0, -7.5, 9.25);
        Point3D instance = new Point3D(2.0, -7.5, 9.25);
        int expResult = 0;
        int result = instance.compareTo(point3D);
        assertEquals(expResult, result);
    }

    @Test
    public void Test02() {
        out.println("Test02: Point3D toString");
        Point3D instance = new Point3D(1.123, 2.569, 3.999);
        String expResult = "(1.12, 2.57, 4.00)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    public void Test03() {
        out.println("Test03: Point3D add");
        Vector vector = new Vector(1.25, -2.0, 3.0);
        Point3D instance = new Point3D(4.75, -5.0, 6.0);
        instance.add(vector);
        assertTrue("Add failed! ", instance.compareTo(new Point3D(6.0, -7.0, 9.0)) == 0);
    }

    @Test
    public void Test04() {
        out.println("Test04: Point3D subtract");
        Vector vector = new Vector(1.0, 2.0, 3.0);
        Point3D instance = new Point3D(4.0, 5.0, 6.0);
        instance.subtract(vector);
        assertTrue("Substruct failed! ", instance.compareTo(new Point3D(3.0, 3.0, 3.0)) == 0);
    }

    @Test
    public void Test05() {
        out.println("Test05: Point3D distance");
        Point3D point = new Point3D(-20.5, 55, 9.25);
        Point3D instance = new Point3D(75, -10, -100);
        double expResult = 159.0;
        double result = instance.distance(point);
        assertEquals("Worng distance", expResult, result, 0.01);
    }
    /************************************** Vector tests **************************************************************/
    @Test
    public void Test06(){
        out.println("Test06: Vector Add test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);

        v1.add(v2);
        assertTrue(v1.compareTo(new Vector(0.0,0.0,0.0)) == 0);

        v2.add(v1);
        assertTrue(v2.compareTo(v2) == 0);
    }

    @Test
    public void Test07(){
        System.out.println("Test07: Vector Substruct test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);
        Vector v2 = new Vector(-1.0, -1.0, -1.0);

        v1.subtract(v2);
        assertTrue(v1.compareTo(new Vector(2.0,2.0,2.0)) == 0);

        v2.subtract(v1);
        assertTrue(v2.compareTo(new Vector(-3.0,-3.0,-3.0)) == 0);
    }

    @Test
    public void Test08(){
        System.out.println("Test08: Vector Scaling test");

        Vector v1 = new Vector(1.0, 1.0, 1.0);

        v1.scale(1);
        assertTrue(v1.compareTo(v1) == 0);

        v1.scale(2);
        assertTrue(v1.compareTo(new Vector(2.0,2.0,2.0)) == 0);

        v1.scale(-2);
        assertTrue(v1.compareTo(new Vector(-4.0,-4.0,-4.0)) == 0);
    }

    @Test
    public void Test09(){
        System.out.println("Test09: Vector Dot product test");


        Vector v1 = new Vector(3.5,-5,10);
        Vector v2 = new Vector(2.5,7,0.5);

        assertTrue(Double.compare(v1.dotProduct(v2), (8.75 + -35 + 5)) == 0);

    }

    @Test
    public void Test10() {
        System.out.println("Test10: Vector Length test");

        Vector v = new Vector(3.5,-5,10);
        assertTrue(v.length() ==  Math.sqrt(12.25 + 25 + 100));
    }

    @Test
    public void Test11(){
        System.out.printf("Test11: Vector Normalize test -> ");

        Vector v = new Vector(100,-60.781,0.0001);
        System.out.printf("Length = %f  ", v.length());
        v.normalize();
        System.out.printf("Length = %f\n", v.length());

        assertEquals("Incorrect length after normalize! ", 1, v.length(), 1e-10);

        v = new Vector(0,0,0);

        try {
            v.normalize();
            fail("Didn't throw divide by zero exception!");
        } catch (ArithmeticException e) {
            assertTrue(true);
        }

    }

    @Test
    public void Test12(){
        System.out.println("Test12: Vector Cross product test");

        Vector v1 = new Vector(3.5, -5.0, 10.0);
        Vector v2 = new Vector(2.5, 7, 0.5);
        Vector v3 = v1.crossProduct(v2);

        assertEquals("", 0, v3.dotProduct(v2), 1e-10);
        assertEquals("", 0, v3.dotProduct(v1), 1e-10);

        Vector v4 = v2.crossProduct(v1);
        v3.add(v4);
        assertEquals("", 0, v3.length(), 1e-10);
    }

/*    Vector v = new Vector(2.3,1.5,3.6);
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
*/


}