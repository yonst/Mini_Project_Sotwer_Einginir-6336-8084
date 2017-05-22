package elements;

/**
 * Created by Moishe on 04/04/2017.
 */
import java.awt.Color;
import java.util.Map;

public class AmbientLight extends Light{


    private final double _Ka = 0.1;
    // ***************** Constructors ********************** //
    public AmbientLight(){}
    public AmbientLight(AmbientLight aLight)
    {
        _color = new Color(aLight._color.getRGB());
    }
    public AmbientLight(int r, int g, int b)
    {
        _color = new Color(r,g,b);
    }
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
        return new Color(_color.getRGB());
    }

}
