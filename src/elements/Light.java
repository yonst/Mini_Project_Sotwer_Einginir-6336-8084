package elements;

import java.awt.*;

/**
 * Created by Moishe on 04/04/2017.
 */

public abstract class Light {
    protected Color _color;
    // ***************** Constructors ********************** //
    public Light(){}
    public Light (Color color){
        _color = new Color(color.getRed(), color.getGreen(),color.getBlue());
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(){
        return new Color(_color.getRed(), _color.getGreen(),_color.getBlue());
    }
}
