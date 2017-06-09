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
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point){
        double d = this._position.distance(point);
        int red = Math.min(255,(int)((this.getIntensity().getRed()*(_direction.dotProduct(this.getL(point))))/(_Kc + _Kl*d + _Kq*Math.pow(d, 2))));
        int green = Math.min(255,(int)((this.getIntensity().getGreen()*(_direction.dotProduct(this.getL(point))))/(_Kc + _Kl*d + _Kq*Math.pow(d, 2))));
        int blue = Math.min(255,(int)((this.getIntensity().getBlue()*(_direction.dotProduct(this.getL(point))))/(_Kc + _Kl*d + _Kq*Math.pow(d, 2))));
        return new Color(red, green, blue);
    }
}
