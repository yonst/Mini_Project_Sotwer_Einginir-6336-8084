package primitives;

/**
 * Created by yona on 26/03/2017.
 */
public class Vector implements Comparable<Vector>{

    private Point3D Head;

    // ***************** Constructors ********************** //
    public Vector(){
        Head = new Point3D();
    }

    public Vector(Point3D head){
        Head = new Point3D(head);
    }

    public Vector(Vector vector){
        this(vector.Head);
    }

    public Vector(double xHead, double yHead, double zHead){
        Head = new Point3D(xHead, yHead, zHead);
    }

    public Vector(Point3D p1, Point3D p2){
        Head = new Point3D(p1._x.Subtract(p2._x), p1._y.Subtract(p2._y), p1.getZ().Subtract(p2.getZ()));
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
    public String toString() {
        return String.format("%.2f, %.2f, %.2f", Head._x, Head._y, Head.getZ());
    }

    @Override
    public int compareTo(Vector vector) {
        return this.getHead().compareTo(vector.getHead());
    }

    // ***************** Operations ******************** //
    public void add(Vector vector) {
        this.Head.add((Point2D) vector.getHead());
        this.Head.getZ().add(vector.getHead().getZ());
    }


 void subtract(Vector vector) {
     this.Head.Substract((Point2D) vector.getHead());
     this.Head.getZ().Subtract(vector.getHead().getZ());
    }

    public void scale(double scalingFactor) {
        this.Head._x.setCoordinate(this.Head._x.getCoordinate() * scalingFactor);
        this.Head._y.setCoordinate(this.Head._y.getCoordinate() * scalingFactor);
        this.Head.getZ().setCoordinate(this.Head.getZ().getCoordinate() * scalingFactor);
    }

    public Vector crossProduct(Vector vector) {
        return null;
    }

    public double length() {
        return 0;
    }

    public void normalize() // Throws exception if length = 0
    {

    }

    public double dotProduct(Vector vector) {
        return 0;
    }
}
