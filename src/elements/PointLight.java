package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 * Created by Moishe on 04/04/2017.
 */


public class PointLight extends Light implements LightSource{

    Point3D _position;
    double _Kc, _Kl, _Kq;
    // ***************** Constructors ********************** //
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
        return new Color((int)(this.getIntensity().getRGB()/ (_Kc + _Kl*d + _Kq*Math.pow(d,2))));
    }
    public Vector getL(Point3D point){
        Vector L = new Vector(point, _position);
        L.normalize();
        return L;
    }

}
