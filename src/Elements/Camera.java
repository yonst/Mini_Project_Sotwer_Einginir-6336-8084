package Elements;

/**
 * Created by Moishe on 04/04/2017.
 */

public class Camera{
/**
    //Eye point of the camera
    private Point3D _P0;
    private Vector _vUp;
    private Vector _vTo;
    //Should be calculated as the cross product if vUp and vTo
    private Vector _vRight;
    // ***************** Constructors ********************** //
    public Camera(){}
    public Camera (Camera camera(;
            public Camera (Point3D P0, Vector vUp, Vector vTo(;
                           public Camera (Map<String, String> attributes(;

// ***************** Getters/Setters ********************** //
                           public Vector get_vUp)(;
    public void set_vUp(Vector vUp(;
            public Vector get_vTo)(;
    public void set_vTo(Vector vTo(;
            public Point3D getP0)(;
    public void setP0(Point3D P0);
    public Vector get_vRight)(;
    // ***************** Administration ********************** //
    public String toString)(;
    // ***************** Operations ******************** //
    public Ray constructRayThroughPixel (int Nx, int Ny,
                                         double x, double y,
                                         double screenDist,
                                         double screenWidth,
                                         double screenHeight);
*/
}
