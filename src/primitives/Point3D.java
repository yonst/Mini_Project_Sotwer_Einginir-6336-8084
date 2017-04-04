package primitives;

/**
 * Created by Moishe on 26/03/2017.
 */
public class Point3D extends Point2D  {

    private Coordinate _z;

    // ***************** Constructors ********************** //
    public Point3D(){
        super();
        _z = new Coordinate();
    }

    public Point3D(Coordinate x ,Coordinate y , Coordinate z) {
        super(x,y);
        _z = new Coordinate(z);
    }

    public Point3D(double x, double y, double z){
        _x = new Coordinate(x);
        _y = new Coordinate(y);
        _z = new Coordinate(z);
    }

    public Point3D(Point3D P3){
        this(P3._x, P3._y, P3._z);
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getZ() {
        return _z;
    }

    public void set_z(Coordinate _z) {
        this._z = _z;
    }

    // ***************** Administration ******************** //
    public int compareTo(Point3D P3) {

        if (this._x.compareTo(P3._x) == 0 && this._y.compareTo(P3._y) == 0 && this._z.compareTo(P3._z) == 0) {
            return 0;
        }
        return  -1;
    }

    @Override
    public String toString() {
        return String.format("%.2f, %.2f, %.2f", _x, _y, _z);
    }

    // ***************** Operations ******************** //
    /**
     * Receive point3D and vector and add the point2D of vector head to p2 of this point3D
     * and add z of the vector to z of this point3D
     * @param vector
     */
    public void add(Vector vector){
        //Point2D temp = new Point2D(vector.getHead()._x, vector.getHead()._y);
        this.add((Point2D) vector.getHead());
        this._z.add(vector.getHead()._z);
    }

    /**
     * Receive two point3D and substract P2 of p3 from P2 of this point3D
     * and substract z of p3 from z of this point3D
     * @param P3
     */
    public void Substract(Point3D P3){
        Point2D temp = new Point2D(P3._x, P3._y);
        this.Substract(temp);
        this._z.Subtract(P3._z);
    }

    public double distance(Point3D point){
        return Math.sqrt(Math.pow(point._x.getCoordinate() - this._x.getCoordinate(),2) + (Math.pow(point._y.getCoordinate() - this._y.getCoordinate(),2)) + (Math.pow(point._z.getCoordinate() - this._z.getCoordinate(),2)));
    }
}

