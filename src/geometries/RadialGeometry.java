package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
//this class is responsible for the Round shapes like sphere and cylinder
public abstract class RadialGeometry extends Geometry{

    protected double _radius;//the radius

    //default constructor
    public RadialGeometry(){}
    //constructor that receive a double and put him to be the value of _radius
    public RadialGeometry(double radius){this._radius = radius;}
    public double getRadius(){return this._radius;}//return the _radius
    public void setRadius(double radius){this._radius = radius;}//put a value in the _radius

}
