package primitives;

/**
 * Created by yona on 26/03/2017.
 */
public class Point2D implements Comparable<Point2D>  {

    protected Coordinate _x;
    protected Coordinate _y;

    // ***************** Constructors ********************** //
    public Point2D() {
        this._x = new Coordinate();
        this._y = new Coordinate();

    }

    public Point2D(Coordinate x, Coordinate y) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
    }

    public Point2D(Point2D point2D){
        this(point2D._x, point2D._y);
    }

    // ***************** Getters/Setters ********************** //
    public Coordinate getX() {
        return _x;
    }

    public Coordinate getY() {
        return _y;
    }

    public void setX(Coordinate _x) {
        this._x = _x;
    }

    public void setY(Coordinate _y) {
        this._y = _y;
    }

    // ***************** Administration ******************** //
    @Override
    public String toString() {
        return String.format("%.2f, %.2f", _x, _y);
    }

    @Override
    public int compareTo(Point2D point2D) {
        if(this._x == point2D._x && this._y == point2D._y)
            return 0;
        return -1;
    }

    /**
     * Receive two point2D and add _x of P2 to _x of this point2D
     * and add _y of P2 to _y of this point2D
     * @param point2D
     */
    public void add(Point2D point2D){
        this._x.add(point2D._x);
        this._y.add(point2D._y);
    }

    /**
     * Receive two point2D and substract _x of p2 from _x of this point2D
     * and substract _y of p2 from _y of this point2D
     * @param point2D
     */
    public void Substract(Point2D point2D){
        this._x.Subtract(point2D._x);
        this._y.Subtract(point2D._y);
    }
}
