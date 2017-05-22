package scene;

        import java.awt.Color;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import elements.AmbientLight;
        import elements.Camera;
        import elements.LightSource;
        import geometries.Geometry;

/**
 * Created by yona on 15/05/2017.
 */

public class Scene
{
    private Color _background;
    private AmbientLight _ambientLight;
    private List<Geometry> _geometries = new ArrayList<Geometry>();
    private Camera _camera;
    private double _screenDistance;
    private List<LightSource> _lights = new ArrayList<LightSource>();
    private String _sceneName = "scene";
    // ***************** Constructors ********************** //

    /**
     *
     */
    public Scene(){
        _background = new Color(0,0,0);
        _screenDistance = 100;
        _camera = new Camera();
    }
    public Scene (Scene scene){
    this._background = new Color(scene._background.getRGB());
    this._ambientLight = new AmbientLight(scene._ambientLight);
    this ._geometries = new ArrayList<Geometry>(scene._geometries);
    this._camera = new Camera(scene._camera);
    this._screenDistance = scene._screenDistance;
    this._lights = new ArrayList<LightSource>(scene._lights);
    this._sceneName = new String(scene._sceneName);
    }

    public Scene(AmbientLight aLight, Color background,
                 Camera camera, double screenDistance){
        this._ambientLight = new AmbientLight(aLight);
        this._background = new Color(background.getRGB());
        this._camera = new Camera(camera);
        this._screenDistance = screenDistance;
    }


    // ***************** Getters/Setters ********************** //
    public Color getBackground(){
    return this._background;
    }
    public AmbientLight getAmbientLight(){
        return this._ambientLight;
    }
    public Camera getCamera(){
        return new Camera(_camera);
    }
    public String getSceneName(){
        return _sceneName;
    }
    public double getScreenDistance(){
        return _screenDistance;
    }
    public void setBackground(Color _background){
        this._background = new Color(_background.getRGB());
    }
    public void setAmbientLight(AmbientLight ambientLight){
        this._ambientLight = new AmbientLight(ambientLight);
    }
    public void setCamera(Camera camera){
        this._camera = new Camera(camera);
    }
    public void setSceneName(String sceneNAme){
        this._sceneName = sceneNAme;
    }
    public void setScreenDistance(double screenDistance){
        this._screenDistance = screenDistance;
    }
    // ***************** Operations ******************** //
    public void addGeometry(Geometry geometry){
        this._geometries.add(geometry);
    }
    public Iterator<Geometry> getGeometriesIterator(){
        return _geometries.iterator();
    }
    public void addLight(LightSource light){
        this._lights.add(light);
    }
    public Iterator<LightSource> getLightsIterator(){
        return _lights.iterator();
    }

}