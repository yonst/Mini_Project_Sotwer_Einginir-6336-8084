package Elements;
import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;
import primitives.Point2D;
import primitives.Coordinate;
import primitives.Material;

import java.util.Map;

/**
 * Created by Moishe on 04/04/2017.
 */

public class Camera{

    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;
    // ***************** Constructors ********************** //
    public Camera(){}
    public Camera (Camera camera){}
    public Camera (Point3D PO, Vector vUp, Vector vTo){}
    public Camera ( Map<String, String> attributes){}

// ***************** Getters/Setters ********************** //
    public Vector get_vUp(){return _vUp;}
    public void set_vUp(Vector vUp) {this._vUp=vUp;}
    public Vector get_vTo(){return _vTo;}
    public void set_vTo(Vector vTo){this._vTo=vTo;}
    public Point3D getP0(){return _P0;}


    public void setP0(Point3D P0){this._P0=P0;}
    public Vector get_vRight(){return _vRight;}
    // ***************** Administration ********************** //
    public String toString(){return "";}
    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight)
    { Ray a=new Ray();
    return a;
    }

}
