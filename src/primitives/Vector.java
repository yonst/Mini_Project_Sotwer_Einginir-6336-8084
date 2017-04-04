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
    public String toString() {
        return String.format("%.2f, %.2f, %.2f", Head._x, Head._y, Head.getZ());
    }

    @Override
    public int compareTo(Vector vector) {
        return this.getHead().compareTo(vector.getHead());
    }

    // ***************** Operations ******************** //
    public void add(Vector vector) {
        this.Head.add(vector);
    }


 void subtract(Vector vector) {
     this.Head.Substract(vector.getHead());
    }

    public void scale(double scalingFactor) {
        this.Head._x.setCoordinate(this.Head._x.getCoordinate() * scalingFactor);
        this.Head._y.setCoordinate(this.Head._y.getCoordinate() * scalingFactor);
        this.Head.getZ().setCoordinate(this.Head.getZ().getCoordinate() * scalingFactor);
    }

    public Vector crossProduct(Vector vector) {
        Vector v = new Vector(Head._y.getCoordinate()*vector.Head.getZ().getCoordinate() - Head.getZ().getCoordinate()*vector.Head._y.getCoordinate(),
                              Head.getZ().getCoordinate()*vector.Head._x.getCoordinate() - Head._x.getCoordinate()*vector.Head.getZ().getCoordinate(),
                              Head._x.getCoordinate()*vector.Head._y.getCoordinate() - Head._y.getCoordinate()*vector.Head._x.getCoordinate());
        return v;
    }

    public double length() {
        return Math.sqrt(Math.pow(Head._x.getCoordinate(),2) + Math.pow(Head._y.getCoordinate(),2)+Math.pow(Head.getZ().getCoordinate(),2));
    }

    public void normalize() // Throws exception if length = 0
    {
        if(this.length() == 0)
            throw new Exception("ddd");
        this.scale(1/this.length());
    }

    public double dotProduct(Vector vector) {
        return 0;
    }
}
