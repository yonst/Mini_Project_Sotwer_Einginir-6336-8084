package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 * Created by Moishe on 04/04/2017.
 */

//this class represent the point of light
public class PointLight extends Light implements LightSource{

    //three constant that influence in the light
    Point3D _position;
    double _Kc, _Kl, _Kq;
    // ***************** Constructors ********************** //
    //constructor that receive color ,point3D and the tree constant
    public PointLight(Color color, Point3D position,
                      double kc, double kl, double kq){
        super(color);
        this._position = new Point3D(position);
        this._Kc = kc;
        this._Kl = kl;
        this._Kq = kq;
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point){
        double d = _position.distance(point);
        double denominator = _Kc + _Kl*d + _Kq*Math.pow(d, 2);
        Color intensity = this.getIntensity();
        int r = Math.min(255,(int)(intensity.getRed()/denominator));
        int g = Math.min(255,(int)(intensity.getGreen()/denominator));
        int b = Math.min(255,(int)(intensity.getBlue()/denominator));
        return new Color(r,g,b);
    }
    public Vector getL(Point3D point){
        Vector L = new Vector(point, _position);
        L.normalize();
        return L;
    }

}
