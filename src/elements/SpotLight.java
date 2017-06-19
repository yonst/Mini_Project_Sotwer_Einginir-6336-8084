package elements;

import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

/**
 * Created by Moishe on 04/04/2017.
 */

public class SpotLight extends PointLight{
    private Vector _direction;
    // ***************** Constructor ********************** //
    public SpotLight(Color color, Point3D position, Vector direction,
                     double kc, double kl, double kq){
        super(color, position, kc, kl, kq);
        _direction = new Vector(direction);
        _direction.normalize();
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point){
        double d = this._position.distance(point);
        double numerator = _direction.dotProduct(this.getL(point));
        double denominator = _Kc + _Kl*d + _Kq*Math.pow(d, 2);
        double tmp = numerator/denominator;
        Color intensity = this.getIntensity();
        int red = Math.min(255,(int) (intensity.getRed()*tmp));
        red = Math.max(0,red);
        int green = Math.min(255,(int) (intensity.getGreen()*tmp));
        green = Math.max(0,green);
        int blue = Math.min(255,(int) (intensity.getBlue()*tmp));
        blue = Math.max(0,blue);
        return new Color(red, green, blue);
    }
}
