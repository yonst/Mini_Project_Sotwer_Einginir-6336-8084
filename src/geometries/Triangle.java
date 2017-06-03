package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */
import primitives.Point3D;
import primitives.Vector;
import primitives.Ray;
import java.util.*;


//this class represent a triangle
public class Triangle extends Geometry implements FlatGeometry
{
    private Point3D _p1;//the first point of the triangle
    private Point3D _p2;//the second point of the triangle
    private Point3D _p3;//the third point of the triangle

    // ***************** Constructors ********************** //

    //default constructor
    public Triangle() {
        _p1 = new Point3D();
        _p2 = new Point3D();
        _p3 = new Point3D();
    }
    //constructor that receive tree points and Initializing the tree point of the triangle with their values
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        this._p1 = new Point3D(p1);
        this._p2 = new Point3D(p2);
        this._p3 = new Point3D(p3);
    }
    //copy constructor (receive a triangle and copy your values to ""this.Triangle"")
    public Triangle(Triangle copy) {
        this._p1 = new Point3D(copy._p1);
        this._p2 = new Point3D(copy._p2);
        this._p3 = new Point3D(copy._p3);
    }


    // ***************** Getters/Setters ********************** //
    public Point3D getP1() {
        return new Point3D(_p1);
    }//return a new Point3D with the value of this._p1
    public Point3D getP2() {
        return new Point3D(_p2);
    }//return a new Point3D with the value of this._p2
    public Point3D getP3() {return new Point3D(_p3);  }//return a new Point3D with the value of this._p3

    public void setP1(Point3D p1) {
        this._p1 = new Point3D(p1);
    }//set a value in the this._p1
    public void setP2(Point3D p2) {
        this._p2 = new Point3D(p2);
    }//set a value in the this._p2
    public void setP3(Point3D p3) {
        this._p3 = new Point3D(p3);
    }//set a value in the this._p3


    // ***************** Operations ******************** //

    /*************************************************
     * FUNCTION:
     * getNormal
     *
     * PARAMETERS:
     * Point3D
     *
     * RETURN VALUE:
     * the normal vector of the triangle
     *
     * MEANING:
     * This functions calculate the normal
     * vector of the triangle
     *
     * SEE ALSO
     * the function below: public List<Point3D> FindIntersections(Ray ray)
     **************************************************/
    public Vector getNormal(Point3D p) {
        Vector v1 = new Vector(_p1);
        v1.subtract(new Vector(_p2));
        Vector normal = new Vector(_p3);
        normal.subtract(new Vector(_p2));
        normal = normal.crossProduct(v1);
        normal.normalize();
        return normal;
    }
    /*************************************************
     * FUNCTION:
     * FindIntersections
     *
     * PARAMETERS:
     * Ray- the ray come from the camera
     *
     * RETURN VALUE:
     * A list with all the points that the ray hurt the triangle
     * if the ray not hurt any point in the triangle
     * the list will contain null
     *
     * MEANING:
     * This functions calculate all the points that the ray hurt the
     * triangle
     **************************************************/

   public List<Point3D> FindIntersections(Ray ray) {

        Point3D PO = new Point3D(ray.get_POO());//the start point of the ray

        Vector N = new Vector(this.getNormal(_p1));//N receive the normal vector of the triangle

       //plane receive the plane that is formed from the vector N(the normal of the triangle)
       // and on of the tree points of the triangle (in this case we choice the _p3)
        Plane plane = new Plane(N,_p3);

        //intersection receive the points of the ray hurt the plane that we dDeclare above
    ArrayList<Point3D> intersection = new ArrayList<>(plane.FindIntersections(ray));

    //if the ray not hurt any point in the plane than return the List with Null
        if (intersection.isEmpty()) {
            return intersection;
        }

       //P_PO receive a vector that start in the start point of the ray and go until the point that ray hurt the plane
       Vector P_PO = new Vector(intersection.get(0), ray.get_POO());

        //.............................the 3 vectors in the triangle.................................................
        Vector vec1 = new Vector(new Point3D(_p1),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p1 of the triangle
        Vector vec2 = new Vector(new Point3D(_p2),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p2 of the triangle
        Vector vec3 = new Vector(new Point3D(_p3),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p3 of the triangle
        //.............cross product between the V1 and V2 for the 3 sides of the triangle .........................
        Vector normal1 = new Vector(new Vector(vec1).crossProduct(new Vector(vec2)));//receive a normal through cross product between vec1 and vec2
        Vector normal2 = new Vector(new Vector(vec3).crossProduct(new Vector(vec1)));//receive a normal through cross product between vec3 and vec1
        Vector normal3 = new Vector(new Vector(vec2).crossProduct(new Vector(vec3)));//receive a normal through cross product between vec2 and vec3
        //........................to normalize the normal1, normal2 and normal3.....................................
        normal1.normalize();//Divide each coordinate by the length
        normal2.normalize();//Divide each coordinate by the length
        normal3.normalize();//Divide each coordinate by the length

        double dotProduct1 = new Vector(P_PO).dotProduct(normal1);//dot product between P_PO and normal1
        double dotProduct2 = new Vector(P_PO).dotProduct(normal2);//dot product between P_PO and normal2
        double dotProduct3 = new Vector(P_PO).dotProduct(normal3);//dot product between P_PO and normal3

       //if the result of the dot product above are the same sing (+ or -) this meaning that are point of intersection between the ray and the plane
        if ((dotProduct1 > 0 && dotProduct2 > 0 && dotProduct3 > 0) || (dotProduct1 < 0 && dotProduct2 < 0 && dotProduct3 < 0)) {
            return intersection;
        } else {
            intersection.clear();//if there is not point of intersection that return the List Null
            return intersection;
        }
    }

    @Override
    //we want this form of toSring
    public String toString() {
        return "p1:"+_p1.toString()+" "+"p2:"+_p2.toString()+" "+"p3:"+_p3.toString();
    }

}