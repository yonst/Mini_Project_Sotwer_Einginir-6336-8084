package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
public abstract class RadialGeometry extends Geometry{

    protected double _radius;

    public RadialGeometry(){}
    public RadialGeometry(double radius){this._radius = radius;}
    public double getRadius(){return this._radius;}
    public void setRadius(double radius){this._radius = radius;}

}
