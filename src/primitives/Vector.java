package primitives;

/**
 * Created by yona on 26/03/2017.
 */
//  this class represent a vector for 3 dimensions X, Y and Z
public class Vector implements Comparable<Vector>{

    private Point3D Head;

    // ***************** Constructors ********************** //
    //default constructor
    public Vector(){
        Head = new Point3D();
    }
//constructor that receive a 3D variable
    public Vector(Point3D head){
        Head = new Point3D(head);
    }
//constructor that receive a vector
    public Vector(Vector vector){
        this(vector.Head);
    }
//constructor that receive 3 double variable and put them to be the coordinates X, Y and Z for the head of the vector
    public Vector(double xHead, double yHead, double zHead){
        Head = new Point3D(xHead, yHead, zHead);
    }

    //construct that receive two point3D and put them to bem the coordinate X, Y and the
    //coordinate Z is the coordinate X minus the coordinate Y (Z=X-Y)
    public Vector(Point3D p1, Point3D p2){
        p1.Substract(p2);
        Head = new Point3D(p1);
    }

    // ***************** Getters/Setters ********************** //
    public void setHead(Point3D head) {
        Head = new Point3D(head);
    }

    public Point3D getHead() {
        return Head;
    }

    // ***************** Administration ******************** //
    @Override
    //this function return the coordinates X, Y and Z with a precision of 2 decimal digits
    public String toString() {
        return String.format("%.2f, %.2f, %.2f", Head._x, Head._y, Head.getZ());
    }

    @Override
    /**
     * this function compare if the 2 vectors are equal.
     if they equal the fuction return 0(zero), if they aren't the
     function thetur -1
     */
    public int compareTo(Vector vector) {
        return this.getHead().compareTo(vector.getHead());
    }

    // ***************** Operations ******************** //

    /**
     * this function add each coordinate with the coordinate of the second vector
     * this X with X,this Y with Y and this Z with Z
     * @param vector
     */
    public void add(Vector vector) {
        this.Head.add(vector);
    }

    /**
     * this function substract each coordinate with the coordinate of the second vector
     this X - X,this Y - Y and this Z - Z
     * @param vector
     */
 void subtract(Vector vector) {
     this.Head.Substract(vector.getHead());
    }

    public void scale(double scalingFactor) {
        this.Head._x.setCoordinate(this.Head._x.getCoordinate() * scalingFactor);
        this.Head._y.setCoordinate(this.Head._y.getCoordinate() * scalingFactor);
        this.Head.getZ().setCoordinate(this.Head.getZ().getCoordinate() * scalingFactor);
    }

    /**
     *  the cross product, a × b, is a vector that is perpendicular to both a and b and therefore normal to the plane containing them.
     *  it gets the correct vector a = (a1, b1, c1) and anouther vector b = (a2, b2, c2)
     * @param vector
     * @return new vector c = a x b = (b1*c2 - c1*b2, c1*a2 - a1*c2, a1*b2 - b1*a2)
     */
    public Vector crossProduct(Vector vector) {
        Vector v = new Vector(Head._y.getCoordinate()*vector.Head.getZ().getCoordinate() - Head.getZ().getCoordinate()*vector.Head._y.getCoordinate(),
                              Head.getZ().getCoordinate()*vector.Head._x.getCoordinate() - Head._x.getCoordinate()*vector.Head.getZ().getCoordinate(),
                              Head._x.getCoordinate()*vector.Head._y.getCoordinate() - Head._y.getCoordinate()*vector.Head._x.getCoordinate());
        return v;
    }

    /**
     *returns the length of the vector
     * @return  √(a^2 + b^2 + c^2)
     */
    public double length() {
        return Math.sqrt(Math.pow(Head._x.getCoordinate(),2) + Math.pow(Head._y.getCoordinate(),2)+Math.pow(Head.getZ().getCoordinate(),2));
    }

    /**
     * Makes this vector have a magnitude of 1, by u = u/||u|| (where u is the
     * correct vector and ||u|| is the norm (or length) of u).
     * Throws exception if length = 0
     */
    public void normalize()
    {
        if(this.length() == 0)
            throw new ArithmeticException("The length is 0");
        this.scale(1/this.length());
    }

    /**
     * Gets vector (a2, b2, c2) and the correct vector (a1, b1, c1)
     * @param vector
     * @return a1*a2 + b1*b2 + c1*c2
     */
    public double dotProduct(Vector vector) {
        return Head._x.getCoordinate()*vector.Head._x.getCoordinate() + Head._y.getCoordinate()*vector.Head._y.getCoordinate() + Head.getZ().getCoordinate()*vector.Head.getZ().getCoordinate();
    }
}
