package elements;

import java.awt.*;

/**
 * Created by Moishe on 04/04/2017.
 */
//an abstract class, defines the color and intensity of each class in the package
public abstract class Light {
    //color of the light in each class which extends the class
    protected Color _color;
    // ***************** Constructors ********************** //
    /*************************************************
     * Default Constructor
     * PARAMETERS
     * none
     * MEANING
     * this is a default constructor which defines the color as white
     **************************************************/
    public Light(){}
    /*************************************************
     * Constructor
     * PARAMETERS
     * color
     * MEANING
     * Initializing the instense of color with green, blue, red.
     **************************************************/
    public Light (Color color){
        _color = new Color(color.getRed(), color.getGreen(),color.getBlue());
    }
    // ***************** Getters/Setters ********************** //
    public Color getIntensity(){
        return new Color(_color.getRed(), _color.getGreen(),_color.getBlue());
    }
}
