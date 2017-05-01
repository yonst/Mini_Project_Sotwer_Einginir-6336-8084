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
    public Camera (Camera camera){
        this(camera._P0,camera._vUp,camera._vTo);
    }
    public Camera (Point3D PO, Vector vUp, Vector vTo){
        this._P0= PO;
        this._vUp= vUp;
        this._vTo=vTo;
        this._vRight = vUp.crossProduct(vTo);
        _vUp.normalize();
        _vTo.normalize();
    }
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
    public String toString()
        {
            return "Vto: " + _vTo + "\n" + "Vup: " + _vUp + "\n" + "Vright:" + _vRight + ".";
        }

    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight)
    {
    Point3D Pc = new Point3D(_P0);
    Point3D p = new Point3D();
    double Rx = screenWidth/Nx;
    double Ry = screenHeight/y;
    Vector tempVtO = new Vector(_vTo);
    Vector tempVup = new Vector(_vUp);
    Vector tempVright = new Vector(_vRight);
    tempVtO.scale(screenDist);
    Pc.add(tempVtO);
    tempVright.scale((x-Nx/2)*Rx-Rx/2);
    tempVup.scale((y-Ny/2)*Ry-Ry/2);
    Pc.add(tempVright);
    Pc.Substract(tempVup.getHead());
    p = Pc;
    p.Substract(_P0);
    Ray R = new Ray(_P0, new Vector(p));
    return R;
    }

}
