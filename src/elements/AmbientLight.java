package elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import java.awt.Color;
import java.util.Map;

public class AmbientLight extends Light{


    private final double _Ka = 0.1;
    // ***************** Constructors ********************** //
    public AmbientLight(){this(Color.darkGray);}
    public AmbientLight(AmbientLight aLight)
    {
        _color = new Color(aLight._color.getRGB());
    }
    public AmbientLight(int r, int g, int b)
    {
        super(new Color(r,g,b));
    }
    public AmbientLight(Color color){super(color);}
    public AmbientLight(Map<String, String> attributes)
    {

    }
    // ***************** Getters/Setters ********************** //
    public Color getColor()
    {
        return _color;
    }
    public void setColor(Color color){
        this._color = new Color (color.getRGB());
    }
    public double getKa(){
        return _Ka;
    }
    public Color getIntensity(){
        int r = Math.min(255,(int)(_color.getRed()*_Ka));
        int g = Math.min(255,(int)(_color.getGreen()*_Ka));
        int b = Math.min(255,(int)(_color.getBlue()*_Ka));
        return new Color(r,g,b);
    }
}
