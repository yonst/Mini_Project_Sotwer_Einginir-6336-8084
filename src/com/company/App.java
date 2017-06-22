package com.company;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import javax.swing.*;

import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point3D;
import primitives.Vector;
import renderer.*;
import scene.Scene;


public class App {


    public static void main(String[] args) {
        new App();
    }

    public App() {
        JFrame janela = new JFrame("evento");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500, 600);
        janela.setLocationRelativeTo(null);
        janela.setLayout(null);
        janela.setBackground(Color.GREEN);

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
        Point_Light.setText("Point Light            itensity(0-100)");
        Point_Light.setBounds(10, 100, 210, 40);

        JCheckBox Spot_Light = new JCheckBox();
        Spot_Light.setText("Spot Light");
        Spot_Light.setBounds(10, 150, 140, 40);

        janela.add(Directional_Light);
        janela.add(Point_Light);
        janela.add(Spot_Light);

        //........................................................Text Field..................................
        JTextField D_Light = new JTextField();
        D_Light.setBounds(220, 60, 100, 20);

        JTextField P_Light = new JTextField();
        P_Light.setBounds(220, 110, 100, 20);

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
        janela.add(P_Light);
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
        File_Text.setText("The_amazind_picture");
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

                scene.addLight(new SpotLight(new Color(RR, GG, BB), new Point3D(200, 200, -100),
                        new Vector(-2, -2, -3), 0, 0.000001, 0.0000005));


                ImageWriter imageWriter = new ImageWriter(File_Text.getText(), 500, 500, 500, 500);

                Render render = new Render(imageWriter, scene);
                render.renderImage();
//....................................................................................to open the image.................................


                JFileChooser fileopen = new JFileChooser();
                int ret = fileopen.showDialog(null, "C:\\Users\\Moishe\\Documents\\projects\\Mini_Project_Sotwer_Einginir-6336-8084\\The_amazind_picture.jpg");
                if (ret == JFileChooser.APPROVE_OPTION) {

                    File file = fileopen.getSelectedFile();
                    ImageIcon icon = new ImageIcon(file.getPath());
                    //JLabel.setIcon(icon);
                   // janela.setIconImage(icon);
                }

                //..................................
                ////setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               // setTitle("Online First Person Shooter");

                ImageIcon image = new ImageIcon("C:\\Users\\Moishe\\Documents\\projects\\Mini_Project_Sotwer_Einginir-6336-8084\\The_amazind_picture.jpg");

               // ImageIcon icon = new ImageIcon(getClass().getResource("fish.png"));
                JLabel label2 = new JLabel(image);

                janela.add(label2);
                


                //..........................


            }
        });
        //recursive test (click)
        recursive_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("recursive test");
            }
        });
        //horse test (click)
        horse_test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("horse test");
            }
        });
        //exit test (click)
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("exit");
                // JFrame.EXIT_ON_CLOSE;
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
