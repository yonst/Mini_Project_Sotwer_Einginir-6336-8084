package geometries;

import elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

/**
 * Created by yona on 10/05/2017.
 */
public class SphereTest {
    @Test
    public void testIntersectionPoints() throws Exception {
        final int WIDTH = 3;
        final int HEIGHT = 3;
        Ray[][] rays = new Ray [HEIGHT][WIDTH];
        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));
        Sphere sphere = new Sphere(1, new Point3D(0.0, 0.0, -3.0));
        Sphere sphere2 = new Sphere(10, new Point3D(0.0, 0.0, -3.0));
// Only the center ray intersect the sphere in two locations
        List<Point3D> intersectionPointsSphere = new ArrayList<Point3D>();
// The sphere encapsulates the view plane - all rays intersect with the sphere once
        List<Point3D> intersectionPointsSphere2 = new ArrayList<Point3D>();
        System.out.println("Camera:\n" + camera);
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                rays[i][j] = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                List<Point3D> rayIntersectionPoints = sphere.FindIntersections(rays[i][j]);
                List<Point3D> rayIntersectionPoints2 = sphere2.FindIntersections(rays[i][j]);
                for (Point3D iPoint : rayIntersectionPoints)
                    intersectionPointsSphere.add(iPoint);
                for (Point3D iPoint : rayIntersectionPoints2)
                    intersectionPointsSphere2.add(iPoint);
            }
        }
            assertTrue(intersectionPointsSphere. size() == 2);
            assertTrue(intersectionPointsSphere2.size() == 9);
            System.out.println("Intersection Points:");
            for (Point3D iPoint: intersectionPointsSphere)
            {
                assertTrue(iPoint.compareTo(new Point3D(0.0, 0.0, -2.0)) == 0 ||
                        iPoint.compareTo(new Point3D(0.0, 0.0, -4.0)) == 0);
                System.out.println(iPoint);
            }

    }
    @Test
    public void testIntersectionPoints2() throws Exception {
        Point3D p = new Point3D(new Point3D(200, 50, 0));
        Sphere s = new Sphere(180.0, p);
        Ray r = new Ray(new Point3D(0,0,0) ,new Vector(100,80,150));
        List<Point3D> l = new ArrayList<Point3D>();
        l = s.FindIntersections(r);

    }
    //***********david cohen tests***************//
    /************** Sphere test
     * @throws Exception ******************/
    @Test
    public void Test16() throws Exception{

        final int WIDTH  = 3;
        final int HEIGHT = 3;

        System.out.println("Test16: Sphere intersection test");

        Ray[][] rays = new Ray [HEIGHT][WIDTH];

        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));

        Sphere sphere1 = new Sphere(1, new Point3D(0.0, 0.0, -3.0));
        Sphere sphere2 = new Sphere(10, new Point3D(0.0, 0.0, -3.0));

        // Only the center ray intersect the sphere in two locations
        List<Point3D> intersectionPointsSphere1 = new ArrayList<Point3D>();

        // The sphere encapsulates the view plane - all rays intersect with the sphere once
        List<Point3D> intersectionPointsSphere2 = new ArrayList<Point3D>();

        System.out.println("Camera:\n" + camera);

        for (int i = 0; i < HEIGHT; i++){
            for (int j = 0; j < WIDTH; j++){

                rays[i][j] = camera.constructRayThroughPixel(
                        WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);

                List<Point3D> rayIntersectionPoints1 = sphere1.FindIntersections(rays[i][j]);
                List<Point3D> rayIntersectionPoints2 = sphere2.FindIntersections(rays[i][j]);

                for (Point3D iPoint: rayIntersectionPoints1)
                    intersectionPointsSphere1.add(iPoint);

                for (Point3D iPoint: rayIntersectionPoints2)
                    intersectionPointsSphere2.add(iPoint);

            }
        }

        assertTrue(intersectionPointsSphere1.size() == 2);
        assertTrue(intersectionPointsSphere2.size() == 9);

        System.out.println("Intersection Points:");
        for (Point3D iPoint: intersectionPointsSphere1)
        {

            assertTrue(iPoint.compareTo(new Point3D(0.0, 0.0, -2.0)) == 0 ||
                    iPoint.compareTo(new Point3D(0.0, 0.0, -4.0)) == 0);
            System.out.println(iPoint);
        }
    }
    @Test
    public void Test17() throws Exception
    {
        System.out.print("Test17: Sphere getNormal test: ");
        Sphere sphere = new Sphere(10, new Point3D(0.0, 0.0, -3.0));
        Vector normal = sphere.getNormal(new Point3D(5.0,5.0,-3.0));
        System.out.println(normal);
        assertEquals("Bad normal! ", normal.toString(),"(0.71, 0.71, 0.00)");
    }
}
