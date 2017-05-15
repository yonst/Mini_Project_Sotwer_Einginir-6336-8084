package Elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import java.awt.Color;
import java.util.Map;
import primitives.Point2D;
import primitives.Point3D;
import primitives.Vector;
import primitives.Coordinate;
import primitives.Material;

public abstract class LightSource {


    public abstract Color getIntensity(Point3D point);
            public abstract Vector getL(Point3D point); // light to point vector

}


