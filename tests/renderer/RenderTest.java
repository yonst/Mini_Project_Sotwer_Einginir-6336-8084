package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import geometries.Rectangle;
import org.junit.Test;
import primitives.Material;
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
        blueTriangle.setEmmission(Color.white);
        Triangle greenTriangle = new Triangle(new Point3D(0, 170, -2), new Point3D(-170, 0, -2), new Point3D(-170, 170, -2));
        greenTriangle.setEmmission(Color.yellow);
        Triangle yellowTriangle = new Triangle(new Point3D(0, -170, -2), new Point3D(-170, 0, -2), new Point3D(-170, -170, -2));
        yellowTriangle.setEmmission(Color.darkGray);
        geometriesList.add(sphere);
        geometriesList.add(redTriangle);
        geometriesList.add(greenTriangle);
        geometriesList.add(yellowTriangle);
        geometriesList.add(blueTriangle);
        Scene scene = new Scene(new AmbientLight(Color.white), Color.black , camera, 1);
        scene.addGeometry(redTriangle);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
    }

    @Test
    public void testRenderImage1() throws Exception {
        Camera camera=new Camera(new Point3D(0.0, 0.0, 0.0), new Vector(0,1,0), new Vector(0, 0, -1));
        Scene scene = new Scene(new AmbientLight(255, 255, 255), new Color(0,0,0), camera, 100);
        /*Plane plane = new Plane(new Vector(0,1, 0), new Point3D(0.0, -130.0 , 0.0));
        plane.setShininess(200);
        plane.setEmmission(new Color(0,0,0));
        plane.setMaterial(new Material( 0, 0, 0, 0 ,0));*/
        Sphere sphere1 = new Sphere(900.0, new Point3D(0.0, 0.0 , -1300.0));
        sphere1.setShininess(35);
        sphere1.setEmmission(new Color(17,15,116));
        sphere1.setMaterial(new Material(1, 1, 0, 1,0));
        /*Triangle triangle = new Triangle(new Point3D(1000.0, -200.0, -600.0),new Point3D(-100.0, -200.0, -1000.0), new Point3D(400.0, 1000.0, -800.0));
        triangle.setShininess(1.0);
        triangle.setEmmission(new Color(0,0,0));
        triangle.setMaterial(new Material(0, 0, 1, 0 ,0));
        Sphere sphere2 = new Sphere(700.0, new Point3D(-200.0, 0.0 , -1500.0));
        sphere2.setShininess(35.0);
        sphere2.setEmmission(new Color(116,15,17));
        sphere2.setMaterial(new Material(0.5, 0.5, 0, 0.5,1));
        Sphere sphere3 = new Sphere(300.0, new Point3D(-200.0, 0.0 , -1500.0));
        sphere3.setShininess(35.0);
        sphere3.setEmmission(new Color(15,200,17));
        sphere3.setMaterial(new Material(1, 1, 0, 0,1));*/
        //scene.addGeometry(plane);
        scene.addGeometry(sphere1);
        /*scene.addGeometry(triangle);
        scene.addGeometry(sphere2);
        scene.addGeometry(sphere3);*/
        scene.addLight(new PointLight(new Color(255,50,50), new Point3D(200.0, 200.0, -20.0), 0.000004, 0.000004, 0.000002));
        //scene.addLight(new SpotLight(new Color(255, 50, 50), new Point3D(200.0, 200.0, -20.0), new Vector(-200.0, -200.0, -1280.0),0.002, 0.002, 0.001));
        ImageWriter imageWriter = new ImageWriter("SceneTestPoint", 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
    }

    @Test
    public void renderImagePointLightTest() throws Exception {

        Scene scene = new Scene(new AmbientLight(Color.darkGray),Color.black, new Camera(new Point3D(0,0,0), new Vector(0, 1, 0), new Vector(0, 0, -1)), 49);

        Sphere sphere=new Sphere(250, new Point3D(0,0,-300));
        sphere.setEmmission(Color.black);
        sphere.setMaterial(new Material(5,3, 0, 0,1));
        scene.addGeometry(sphere);

        PointLight pointLight=new PointLight(new Color(255, 48, 21),new Point3D(100,0,-0),0.1,0.01,0.000);
        //scene.addLight(pointLight);

        SpotLight spotLight = new SpotLight(Color.red,new Point3D(100,0,0), new Vector(-1,0,-1),0.01,0.01,0.001);
        scene.addLight(spotLight);
        ImageWriter imageWriter=new ImageWriter("renderImagePointLightTest", 500, 500, 500,500);

        Render Render=new Render(imageWriter, scene);

        Render.renderImage();

        // Render.printGrid(50);

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

   @Test
    public void RectangleTest() throws Exception {
       ImageWriter imageWriter = new ImageWriter("rectangleTest", 500, 500, 500, 500);
       Camera camera = new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1));
       Rectangle rectangleRed = new Rectangle(new Point3D(250, 250, -2), new Point3D(500, 500, -2));
       rectangleRed.setEmmission(Color.red);
       Rectangle rectangleGreen = new Rectangle(new Point3D(0,0,-2), new Point3D(250,250,-2));
       rectangleGreen.setEmmission(Color.green);
       Rectangle rectangleYellow = new Rectangle(new Point3D(-250,-250,-2), new Point3D(0,0,-2));
       rectangleYellow.setEmmission(Color.yellow);
       Rectangle rectangleBlue = new Rectangle(new Point3D(-500,-500,-2), new Point3D(-250,-250,-2));
       rectangleBlue.setEmmission(Color.blue);
       Scene scene = new Scene(new AmbientLight(Color.white), Color.black, camera, 1);
       scene.addGeometry(rectangleRed);
       scene.addGeometry(rectangleGreen);
       scene.addGeometry(rectangleYellow);
       scene.addGeometry(rectangleBlue);
       Render render = new Render(imageWriter, scene);
       render.renderImage();

   }
   @Test
    public void hours()throws Exception{
       ImageWriter imageWriter = new ImageWriter("hours", 1000,1000,1000,1000);
       Camera camera = new Camera(new Point3D(0,0,0),new Vector(0, 1, 0), new Vector(0, 0, -1));
       Triangle blueTriangle2 = new Triangle(new Point3D(-30, -119, -100), new Point3D(-90, -96, -100), new Point3D(-124, -122, -100));
       blueTriangle2.setEmmission(Color.green);
       //Rectangle rectangle1 = new Rectangle();
       Scene scene = new Scene(new AmbientLight(Color.darkGray),Color.black, new Camera(new Point3D(0,0,0), new Vector(0, 1, 0), new Vector(0, 0, -1)), 49);
       scene.addGeometry(blueTriangle2);
       Triangle redTriangle = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
       Triangle redTriangle2 = new Triangle(new Point3D(0, 170, -2), new Point3D(170, 0, -2), new Point3D(170, 170, -2));
       Render render = new Render(imageWriter, scene);
       render.renderImage();
   }
}