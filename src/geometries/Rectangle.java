package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yona on 11/06/2017.
 */


public class Rectangle extends Geometry implements FlatGeometry {
    // the Rectangle is build from 2 triangles
    private Triangle _tri1;
    private Triangle _tri2;

    // ***************** Constructors ********************** //

    /*************************************************
     * FUNCTION
     * Constructor
     * PARAMETERS
     * two point Up and down of the Rectangle
     * MEANING
     * this builds the Rectangle by using 2 triangle constructors
     **************************************************/
    public Rectangle(Point3D bottomLeft, Point3D topRight)
    {
        Point3D topLeft = new Point3D(bottomLeft.getX(), topRight.getY(), bottomLeft.getZ());
        Point3D bottomRight = new Point3D(topRight.getX(), bottomLeft.getY(), topRight.getZ());
        _tri1 = new Triangle(bottomLeft, topLeft, bottomRight);
        _tri2 = new Triangle(topLeft, topRight, bottomRight);
    }
    /*************************************************
     * FUNCTION
     * Constructor
     * PARAMETERS
     * List points, of 2 triangles, 4 is enough.
     * MEANING
     * this builds the Rectangle by using 2 triangle constructors
     **************************************************/
    public Rectangle (Point3D P1, Point3D P2, Point3D P3, Point3D P4)
    {
        _tri1 = new Triangle(P1, P2, P4);
        _tri2 = new Triangle(P2, P3, P4);
    }

    public Rectangle(Rectangle rec){
        this._tri1 = new Triangle(rec._tri1);
        this._tri2 = new Triangle(rec._tri2);
    }

    /*************************************************
     * FUNCTION
     * FindIntersections
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * List<Point3D> of Intersection with triangle
     * MEANING
     * This functions finds the intersection of the ray with the triangle
     * the way is by calculating the normals of 3 imaginary triangles
     * that go out of the triangle and checking if the normal of each one
     * of them is with the same sign
     **************************************************/
    @Override
    public List<Point3D> FindIntersections(Ray ray) {
        Point3D PO = new Point3D(ray.get_POO());//the start point of the ray

        Vector N = new Vector(this.getNormal(_tri1.getP1()));//N receive the normal vector of the triangle

        //plane receive the plane that is formed from the vector N(the normal of the triangle)
        // and on of the tree points of the triangle (in this case we choice the _p3)
        Plane plane = new Plane(N, _tri1.getP3());

        //intersection receive the points of the ray hurt the plane that we dDeclare above
        List<Point3D> intersection = triangleFindIntersectuon(_tri1, ray, new ArrayList<>(plane.FindIntersections(ray)));

        //if the ray not hurt any point in the plane than return the List with Null
        if (intersection.isEmpty()) {
            return triangleFindIntersectuon(_tri2, ray, new ArrayList<>(plane.FindIntersections(ray)));
        }
        else
            return intersection;
    }

    private List<Point3D> triangleFindIntersectuon(Triangle tri, Ray ray, List<Point3D> planeIntersection){
        //P_PO receive a vector that start in the start point of the ray and go until the point that ray hurt the plane
        Vector P_PO = new Vector(planeIntersection.get(0), ray.get_POO());

        //.............................the 3 vectors in the triangle.................................................
        Vector vec1 = new Vector(new Point3D(tri.getP1()),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p1 of the triangle
        Vector vec2 = new Vector(new Point3D(tri.getP2()),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p2 of the triangle
        Vector vec3 = new Vector(new Point3D(tri.getP3()),new Point3D(ray.get_POO()));//Vector between the start point of the ray and the point _p3 of the triangle
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
        if ((dotProduct1 >= 0 && dotProduct2 >= 0 && dotProduct3 >= 0) || (dotProduct1 <= 0 && dotProduct2 <= 0 && dotProduct3 <= 0))
            return planeIntersection;
     else {
        planeIntersection.clear();//if there is not point of intersection that return the List Null
        return planeIntersection;
    }


    }
    /*************************************************
     * FUNCTION
     * Gets Normal
     * PARAMETERS
     * Ray
     * RETURN VALUE
     * Vector
     * MEANING
     * The triangle has a plane, here we get the normal of this plane
     * we use here directly the getnormal function of the triangle
     **************************************************/
    @Override
    public Vector getNormal(Point3D point) {
        return _tri1.getNormal(point);
    }
}
