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
{//color of background of the scene
    private Color _background;
    //the intensity of the ambient light
    private AmbientLight _ambientLight;
    //the collection of body/geometry on the scene
    private List<Geometry> _geometries = new ArrayList<Geometry>();
    // camera thats on the scene
    private Camera _camera;
    //screen distance of the camera from the scene
    private double _screenDistance;
    //list of all the lights
    private List<LightSource> _lights = new ArrayList<LightSource>();
    // name of the scene
    private String _sceneName = "scene";
    // ***************** Constructors ***********************//
    /*************************************************
     * Default- Constructor
     * PARAMETERS
     * none
     * MEANING
     * this is a d- constructor which initializes
     * the parameters of the scene.
     **************************************************/
    public Scene(){
        _background = new Color(0,0,0);
        _screenDistance = 100;
        _camera = new Camera();
        _ambientLight = new AmbientLight();
    }
    /*************************************************
     * Copy- Constructor
     * PARAMETERS
     * scene
     * MEANING
     * this is a copy- constructor which initializes
     * the parameters of the sphere and uses RadialGeomtry abstract
     * radius float var.
     **************************************************/
    public Scene (Scene scene){
    this._background = new Color(scene._background.getRGB());
    this._ambientLight = new AmbientLight(scene._ambientLight);
    this ._geometries = new ArrayList<Geometry>(scene._geometries);
    this._camera = new Camera(scene._camera);
    this._screenDistance = scene._screenDistance;
    this._lights = new ArrayList<LightSource>(scene._lights);
    this._sceneName = new String(scene._sceneName);
    }
    /*************************************************
     * Regular- Constructor
     * PARAMETERS
     * AmbientLight aLight, Color background,Camera camera, double screenDistance
     * MEANING
     * this is a d- constructor which initializes
     * the parameters of the scene.
     **************************************************/
    public Scene(AmbientLight aLight, Color background,
                 Camera camera, double screenDistance){
        this._ambientLight = new AmbientLight(aLight);
        this._background = new Color(background.getRGB());
        this._camera = new Camera(camera);
        this._screenDistance = screenDistance;
    }

/*************************************************
 * getter
 * PARAMETERS
 * none
 * RETURN VALUE
 * center, which is a point3d value and denotes the center of the sphere
 * MEANING
 * returns the center of a sphere
 **************************************************/

    /*************************************************
     * getter
     * PARAMETERS
     * none
     * RETURN VALUE
     * background color
     * MEANING
     * returns the background color
     **************************************************/
    // ***************** Getters/Setters ********************** //
    public Color getBackground(){
    return this._background;
    }
    /*************************************************
     * getter
     * PARAMETERS
     * none
     * RETURN VALUE
     * ambient light value
     * MEANING
     * returns the ambient light value
     **************************************************/
    public AmbientLight getAmbientLight(){
        return this._ambientLight;
    }
    /*************************************************
     * getter
     * PARAMETERS
     * none
     * RETURN VALUE
     * Camera
     * MEANING
     * returns the camera with all its parameters
     **************************************************/
    /*************************************************
     * getter
     * PARAMETERS
     * none
     * RETURN VALUE
     * astring scene name value
     * MEANING
     * returns the scene name value
     **************************************************/
    public Camera getCamera(){
        return new Camera(_camera);
    }
    public String getSceneName(){
        return _sceneName;
    }
    /*************************************************
     * getter
     * PARAMETERS
     * none
     * RETURN VALUE
     * double
     * MEANING
     * returns the screen distance value
     **************************************************/
    public double getScreenDistance(){
        return _screenDistance;
    }
    /*************************************************
     * setter
     * PARAMETERS
     * background color
     * RETURN VALUE
     * void
     * MEANING
     * sets the background color
     **************************************************/
    public void setBackground(Color _background){
        this._background = new Color(_background.getRGB());
    }
    /*************************************************
     * setter
     * PARAMETERS
     * ambientLight
     * RETURN VALUE
     * void
     * MEANING
     * sets the ambient light value
     **************************************************/
    public void setAmbientLight(AmbientLight ambientLight){
        this._ambientLight = new AmbientLight(ambientLight);
    }
    /*************************************************
     * setter
     * PARAMETERS
     * Camera
     * RETURN VALUE
     * void
     * MEANING
     * setes the camera parameters
     **************************************************/
    public void setCamera(Camera camera){
        this._camera = new Camera(camera);
    }
    /*************************************************
     * setter
     * PARAMETERS
     * scene name string
     * RETURN VALUE
     * void
     * MEANING
     * sets the scene name value
     **************************************************/
    public void setSceneName(String sceneNAme){
        this._sceneName = sceneNAme;
    }
    /*************************************************
     * setter
     * PARAMETERS
     * screenDistance
     * RETURN VALUE
     * void
     * MEANING
     * sets the screenDistance
     **************************************************/
    public void setScreenDistance(double screenDistance){
        this._screenDistance = screenDistance;
    }
    // ***************** Operations ******************** //
    /*************************************************
     * addGeometry
     * PARAMETERS
     *  geometry
     * RETURN VALUE
     * void
     * MEANING
     * lets you to add geometries to the scene
     **************************************************/
    public void addGeometry(Geometry geometry){
        this._geometries.add(geometry);
    }
    public Iterator<Geometry> getGeometriesIterator(){
        return _geometries.iterator();
    }
    /*************************************************
     * addLight
     * PARAMETERS
     *  light
     * RETURN VALUE
     * void
     * MEANING
     * lets you to add lights to the scene
     **************************************************/
    public void addLight(LightSource light){
        this._lights.add(light);
    }
    public Iterator<LightSource> getLightsIterator(){
        return _lights.iterator();
    }

}