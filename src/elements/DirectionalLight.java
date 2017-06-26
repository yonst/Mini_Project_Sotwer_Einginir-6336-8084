package elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Vector;
import primitives.Point3D;
import java.awt.Color;

//this class represent the direction of the light
public class DirectionalLight extends Light implements LightSource {

    //the direction of the light
    private Vector _direction;
    // ***************** Constructors ********************** //
    //constructor that receive the color and the direction and put their values int into the this._color and this._direction
    public DirectionalLight(Color color, Vector direction)
    {
            _color = new Color(color.getRGB());
            _direction = new Vector(direction);
            _direction.normalize();
    }
    // ***************** Getters/Setters ********************** //
    //return the intensity of the color
    public Color getIntensity(Point3D point)
    {
        return this.getIntensity();
    }
    //return the direction of the light
    public Vector getDirection()
    {
        return new Vector(_direction);
    }
    //set a new value int the this._direction
    public void setDirection(Vector _direction)
    {
        _direction = new Vector(_direction);
    }
    //return the direction of the light
    public Vector getL(Point3D point)
    {
      return new Vector(_direction);
    }

}
