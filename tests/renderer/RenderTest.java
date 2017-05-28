package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.Test;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yona on 24/05/2017.
 */
public class RenderTest {
    @Test
    public void renderTest(){
        ImageWriter imageWriter = new ImageWriter("renderTest", 500, 500, 500, 500);
        Camera camera = new Camera(new Point3D(0,0,0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        List<Geometry> geometriesList = new ArrayList<Geometry>();
        Sphere sphere = new Sphere(50, new Point3D(0, 0, -2));
        sphere.setEmmission(new Color(20,20,20));
        Triangle redTriangle = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
        redTriangle.setEmmission(Color.red);
        Triangle blueTriangle = new Triangle(new Point3D(0, -170, -2), new Point3D(170, 0, -2), new Point3D(170, -170, -2));
        blueTriangle.setEmmission(Color.BLUE);
        Triangle greenTriangle = new Triangle(new Point3D(0, 170, -2), new Point3D(-170, 0, -2), new Point3D(-170, 170, -2));
        greenTriangle.setEmmission(Color.green);
        Triangle yellowTriangle = new Triangle(new Point3D(0, -170, -2), new Point3D(-170, 0, -2), new Point3D(-170, -170, -2));
        yellowTriangle.setEmmission(Color.yellow);
        geometriesList.add(sphere);
        geometriesList.add(redTriangle);
        geometriesList.add(greenTriangle);
        geometriesList.add(yellowTriangle);
        geometriesList.add(blueTriangle);
        Scene scene = new Scene(new AmbientLight(Color.white), Color.black , camera, geometriesList, 1);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
    }

   /* @Test
    public void renderTest2()
    {
        ImageWriter imageWriter = new ImageWriter("renderFlagTest", 500, 250, 500, 250);
        Camera camera = new Camera(new Point3D(0,0,0), new Vector(0, 1, 0), new Vector(0, 0, -1));
        List<Geometry> geometriesList = new ArrayList<Geometry>();
        Triangle blueTriangle = new Triangle(new Point3D(0, 125, -2), new Point3D(-125, 0, -2), new Point3D(-250, 250, -2));
        blueTriangle.setEmmission(Color.BLUE);
        Triangle blueTriangle2 = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
        Triangle redTriangle = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
        Triangle redTriangle2 = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
        geometriesList.add(blueTriangle);
        Scene scene = new Scene(new AmbientLight(Color.white), Color.black , camera, geometriesList, 1);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
    }*/

}