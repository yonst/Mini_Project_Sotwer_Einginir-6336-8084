package primitives;

/**
 * Created by Moishe on 04/04/2017.
 */
public class Ray {
    // Point of origin
    private Point3D _POO;
    // Ray direction
    private Vector _direction;
    // ***************** Constructors ********************** //
    public Ray()
    {};
    public Ray(Ray ray){
        this(ray._POO,ray._direction);
    }
    public Ray(Point3D poo, Vector direction)
    {
        _POO=new Point3D(poo);
        _direction=new Vector(direction);

    }
    // ***************** Getters/Setters ********************** //

    public void set_POO(Point3D _POO) {
        this._POO = _POO;
    }

    public Point3D get_POO() {
        return _POO;

    }

    public void set_direction(Vector _direction) {
        this._direction = _direction;
    }

    public Vector get_direction() {
        return _direction;
    }


}


