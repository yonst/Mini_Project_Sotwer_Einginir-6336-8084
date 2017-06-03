package elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import java.awt.Color;

import primitives.Point3D;
import primitives.Vector;

//this class represent the light source
public abstract class LightSource {


    public abstract Color getIntensity(Point3D point);   //
    public abstract Vector getL(Point3D point); // light to point vector

}


