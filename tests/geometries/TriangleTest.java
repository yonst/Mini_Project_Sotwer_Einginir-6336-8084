package geometries;

import org.junit.Test;

import static org.junit.Assert.*;
import Elements.Camera;
import org.junit.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by Moishe on 12/05/2017.
 */
public class TriangleTest {
    @Test
    public void getNormal() throws Exception {

    }

    @Test
    public void findIntersections() throws Exception {

    }
    /*** Triangle test ***/
    @Test
    public void TriangleIntersectionPointsTest(){
        final int WIDTH = 3;
        final int HEIGHT = 3;
        Ray[][] rays = new Ray [HEIGHT][WIDTH];
        Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
                new Vector (0.0, 1.0, 0.0),
                new Vector (0.0, 0.0, -1.0));
        Triangle triangle = new Triangle(new Point3D( 0, 1, -2),
                new Point3D( 1, -1, -2),
                new Point3D(-1, -1, -2));
        Triangle triangle2 = new Triangle(new Point3D( 0, 10, -2),
                new Point3D( 1, -1, -2),
                new Point3D(-1, -1, -2));
        List<Point3D> intersectionPointsTriangle = new ArrayList<Point3D>();
        List<Point3D> intersectionPointsTriangle2 = new ArrayList<Point3D>();
        System.out.println("Camera:\n" + camera);
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                rays[i][j] = camera.constructRayThroughPixel
                        (WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
                List<Point3D> rayIntersectionPoints = triangle. FindIntersections(rays[i][j]);
                List<Point3D> rayIntersectionPoints2 = triangle2.FindIntersections(rays[i][j]);
                for (Point3D iPoint: rayIntersectionPoints)
                    intersectionPointsTriangle.add(iPoint);
                for (Point3D iPoint: rayIntersectionPoints2)
                    intersectionPointsTriangle2.add(iPoint);
            }
        }
        assertTrue(intersectionPointsTriangle. size() == 1);
        assertTrue(intersectionPointsTriangle2.size() == 2);
        System.out.println("Intersection Points:");
        for (Point3D iPoint: intersectionPointsTriangle)
            System.out.println(iPoint);
        System.out.println("--");
        for (Point3D iPoint: intersectionPointsTriangle2)
            System.out.println(iPoint);
    }
}