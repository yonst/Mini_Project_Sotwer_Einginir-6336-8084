package com.company;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

import elements.*;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.*;
import scene.Scene;


public class App {


    public static void main(String[] args) throws Exception {
        new App();
    }

    public App() throws Exception {
        JFrame janela = new JFrame("evento");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 600);
        janela.setLocationRelativeTo(null);
        janela.setLayout(null);
        janela.setBackground(Color.GREEN);


        janela.setResizable(false);

        //label
        JLabel title = new JLabel("Picture Builder");
        title.setBounds(170, 0, 170, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 25));

        janela.add(title);

//....................................................................check box..................................
        JCheckBox Directional_Light = new JCheckBox();
        Directional_Light.setText("Directional Light     angle(0-180)");
        Directional_Light.setBounds(10, 50, 210, 40);

        JCheckBox Point_Light = new JCheckBox();
        Point_Light.setText("Point Light");
        Point_Light.setBounds(10, 100, 210, 40);

        JCheckBox Spot_Light = new JCheckBox();
        Spot_Light.setText("Spot Light");
        Spot_Light.setBounds(10, 150, 140, 40);

        janela.add(Directional_Light);
        janela.add(Point_Light);
        janela.add(Spot_Light);

        //........................................................Text Field..................................
        JTextField D_Light = new JTextField();
        D_Light.setText("50");
        D_Light.setBounds(220, 60, 100, 20);

        //  JTextField P_Light = new JTextField();
        //P_Light.setText("50");
        // P_Light.setBounds(220, 110, 100, 20);

        JTextField S_Light1 = new JTextField();
        S_Light1.setText("255");
        S_Light1.setBounds(180, 160, 40, 20);

        JTextField S_Light2 = new JTextField();
        S_Light2.setText("100");
        S_Light2.setBounds(225, 160, 40, 20);

        JTextField S_Light3 = new JTextField();
        S_Light3.setText("100");
        S_Light3.setBounds(270, 160, 50, 20);


        janela.add(D_Light);
        ///janela.add(P_Light);
        janela.add(S_Light1);
        janela.add(S_Light2);
        janela.add(S_Light3);

        //.......................................................................label R G B ................................
        JLabel R = new JLabel("R");
        R.setFont(new Font("Serif", Font.PLAIN, 10));
        R.setBounds(193, 181, 10, 10);

        JLabel G = new JLabel("G");
        G.setFont(new Font("Serif", Font.PLAIN, 10));
        G.setBounds(240, 181, 10, 10);

        JLabel B = new JLabel("B");
        B.setFont(new Font("Serif", Font.PLAIN, 10));
        B.setBounds(290, 181, 10, 10);
//..........................................................................file name..............................
        JLabel File_Name = new JLabel("File Name");
        File_Name.setFont(new Font("Serif", Font.PLAIN, 15));
        File_Name.setBounds(270, 240, 100, 25);

        JTextField File_Text = new JTextField();
        File_Text.setText("The_amazind_picture.jpg");
        File_Text.setBounds(225, 265, 150, 20);

        janela.add(R);
        janela.add(G);
        janela.add(B);
        janela.add(File_Name);
        janela.add(File_Text);


        //.....................................................................//......................................
        //shadow test button
        JButton shadow_test = new JButton("Shadow Test");
        shadow_test.setBounds(10, 200, 120, 65);

        //recursive test button
        JButton recursive_test = new JButton("Recursive Test");
        recursive_test.setBounds(10, 300, 120, 65);

        //horse test button
        JButton horse_test = new JButton("Horse Test");
        horse_test.setBounds(10, 400, 120, 65);

        //exit button
        JButton exit = new JButton("Exit");
        exit.setBounds(350, 400, 120, 45);

        janela.add(shadow_test);
        janela.add(recursive_test);
        janela.add(horse_test);
        janela.add(exit);
        janela.setVisible(true);

        //events
        //shadow test (click)
        shadow_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("shadow test");

                int RR = Integer.parseInt(S_Light1.getText());
                int GG = Integer.parseInt(S_Light2.getText());
                int BB = Integer.parseInt(S_Light3.getText());

                //.............................................................................direction light seting

                double angle = Integer.parseInt(D_Light.getText());
                if (angle < 70) angle += 70;
                if (angle > 280) angle -= 70;
                double cosAngle = Math.cos(Math.toRadians(angle));
                double sinAngle = Math.sin(Math.toRadians(angle));
                Vector _direction = new Vector(-1, 0, 1);
                _direction.normalize();
                Vector X = new Vector((_direction.getHead().getX().getCoordinate() * (1 - cosAngle)),
                        (_direction.getHead().getY().getCoordinate() * cosAngle - _direction.getHead().getZ().getCoordinate() * sinAngle),
                        _direction.getHead().getZ().getCoordinate() * cosAngle + _direction.getHead().getY().getCoordinate() * sinAngle);
                X.normalize();


                //.................


                Scene scene = new Scene();
                Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000));
                sphere.setShininess(20);
                sphere.setEmmission(new Color(0, 0, 100));

                scene.addGeometry(sphere);

                Triangle triangle = new Triangle(new Point3D(3500, 3500, -2000),
                        new Point3D(-3500, -3500, -1000),
                        new Point3D(3500, -3500, -2000));

                Triangle triangle2 = new Triangle(new Point3D(3500, 3500, -2000),
                        new Point3D(-3500, 3500, -1000),
                        new Point3D(-3500, -3500, -1000));

                scene.addGeometry(triangle);
                scene.addGeometry(triangle2);


                   Vector spotVec = new Vector(X);


                if (Spot_Light.isSelected()) {
                    scene.addLight(new SpotLight(new Color(RR, GG, BB), new Point3D(200, 200, -100),

                            spotVec, 0, 0.000001, 0.0000005));
                }
                if (Directional_Light.isSelected()) {
                    scene.addLight(new DirectionalLight(new Color(RR, GG, BB), new Vector(X)));
                }
                if (Point_Light.isSelected()) {
                    scene.addLight(new PointLight(new Color(RR, GG, BB), new Point3D(-200, -200, -100),
                            0, 0.00001, 0.000005));
                }

                ImageWriter imageWriter = new ImageWriter(File_Text.getText(), 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);
                render.renderImage();
                render.writeToImage();
//....................................................................................to open the image.................................

                //..........................
                String path = File_Text.getText() + ".jpg";
                File file = new File(path);
                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JLabel labell = new JLabel(new ImageIcon(image));
                JFrame f = new JFrame();
                //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(labell);
                f.pack();
                f.setLocation(200, 200);
                f.setVisible(true);


            }


        });
        //recursive test (click)
        recursive_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("recursive test");
                Scene scene = new Scene();
                scene.setScreenDistance(300);

                Sphere sphere = new Sphere(300, new Point3D(0, 0, -1000));
                sphere.setShininess(20);
                sphere.setEmmission(new Color(0, 0, 100));
                sphere.setKt(0.5);
                scene.addGeometry(sphere);

                Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1000));
                sphere2.setShininess(20);
                sphere2.setEmmission(new Color(100, 20, 20));
                sphere2.setKt(0);
                scene.addGeometry(sphere2);

                Triangle triangle = new Triangle(new Point3D(2000, -1000, -1500),
                        new Point3D(-1000, 2000, -1500),
                        new Point3D(700, 700, -375));

                Triangle triangle2 = new Triangle(new Point3D(2000, -1000, -1500),
                        new Point3D(-1000, 2000, -1500),
                        new Point3D(-1000, -1000, -1500));

                triangle.setEmmission(new Color(20, 20, 20));
                triangle2.setEmmission(new Color(20, 20, 20));
                triangle.setKr(1);
                triangle2.setKr(0.5);
                scene.addGeometry(triangle);
                scene.addGeometry(triangle2);

                scene.addLight(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -150),
                        new Vector(-2, -2, -3), 0, 0.00001, 0.000005));

                ImageWriter imageWriter = new ImageWriter(File_Text.getText(), 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);

                render.renderImage();
                render.writeToImage();

                String path = File_Text.getText() + ".jpg";
                File file = new File(path);
                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JLabel labell = new JLabel(new ImageIcon(image));
                JFrame f = new JFrame();
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(labell);
                f.pack();
                f.setLocation(200, 200);
                f.setVisible(true);
            }
        });

        //.................................................................test horse..........................
        //horse test (click)
        horse_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileReader coordsFile, triCoords;
                try {

                    String line;
                    //read file of coordination of triangles
                    coordsFile = new FileReader("horsejava.txt");
                    //read file with the triangles information
                    triCoords = new FileReader("triforhorse.txt");
                    //put file in buffered file
                    BufferedReader bufCoordsFile = new BufferedReader(coordsFile);
                    BufferedReader bufTriCoords = new BufferedReader(triCoords);

                    //array for saving the points from file
                    ArrayList<Point3D> point3DList = new ArrayList<Point3D>();

                    //read first line from points file
                    line = bufCoordsFile.readLine();
                    //color to add to triangle
                    Color triColor = new Color(0, 0, 0);

                    //while its not the end of file
                    while (line != null) {
                        //split the line with " "(e.g. space) separator
                        String[] tmp = line.split(" ");
                        //create new point from the line
                        Point3D tmpPoint = new Point3D(Double.parseDouble(tmp[1]), Double.parseDouble(tmp[2]), Double.parseDouble(tmp[3]) - 150);
                        //add the point to point list
                        point3DList.add(tmpPoint);
                        //read next line
                        line = bufCoordsFile.readLine();
                    }

                    //read first line from triangle information file
                    String triLine = bufTriCoords.readLine();

                    //create scene
                    Scene scene = new Scene(new AmbientLight(255, 255, 255), Color.black, new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)), 50);
                    //while not the end of file(triangles file)
                    while (triLine != null) {
                        //for creating random color
                        int red, green, blue;
                        red = (int) (Math.random() * 40);
                        green = (int) (Math.random() * 40);
                        blue = (int) (Math.random() * 40);
                        triColor = new Color(red, green, blue);
                        //split line to spaces
                        String[] tmp = triLine.split(" ");
                        //create triangle from line info
                        Triangle tmpTriangle = new Triangle(point3DList.get(Integer.parseInt(tmp[1]) - 1), point3DList.get(Integer.parseInt(tmp[2]) - 1), point3DList.get(Integer.parseInt(tmp[3]) - 1), triColor);
                        tmpTriangle.setMaterial(new Material(2, 1, 1, 0, 1));
                        tmpTriangle.setShininess(150);
                        //add triangle to scene
                        scene.addGeometry(tmpTriangle);
                        //read next line
                        triLine = bufTriCoords.readLine();
                    }
                    scene.addGeometry(new Plane(new Vector(0, 1, 0), new Point3D(-300, -900, -300)));
                    scene.addLight(new PointLight(new Color(255, 255, 255), new Point3D(200, 300, 500),/* new Vector(900,500,-350),*/0.0002, 0.0002, 0.00001));
                    scene.addLight(new SpotLight(new Color(255, 255, 255), new Point3D(600, 900, 500), new Vector(1, 1, -3), 0.0002, 0.0002, 0.00001));
                    //scene.addLight(new DirectionalLight(new Color(30,30,30), new Vector(1,1,-3)));
                    Render render = new Render(new ImageWriter(File_Text.getText(), 1300, 1300, 1300, 1300), scene);
                    render.renderImage();
                    render.printGrid(100);

                } catch (Exception ee) {
                    System.out.print(ee.getMessage());
                }
                String path = File_Text.getText() + ".jpg";
                File file = new File(path);
                BufferedImage image = null;
                try {
                    image = ImageIO.read(file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                JLabel labell = new JLabel(new ImageIcon(image));
                JFrame f = new JFrame();
                // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.getContentPane().add(labell);
                f.pack();
                f.setLocation(200, 200);
                f.setVisible(true);


            }
        });
        //exit test (click)
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("exit");
                System.exit(0);

            }
        });

    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}
