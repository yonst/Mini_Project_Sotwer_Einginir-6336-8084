package elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Vector;
import primitives.Point3D;
import java.awt.Color;

//this class represent the direction of the light
public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;
    // ***************** Constructors ********************** //
    public DirectionalLight(Color color, Vector direction)
    {
            _color = new Color(color.getRGB());
            _direction = new Vector(direction);

    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(Point3D point)
    {
        return this.getIntensity();
    }
    public Vector getDirection()
    {
        return new Vector(_direction);
    }
    public void setDirection(Vector _direction)
    {
        _direction = new Vector(_direction);
    }
    public Vector getL(Point3D point)
    {
      return new Vector(_direction);
    }

}
