package renderer;
import java.awt.Color;
import java.util.*;
import java.util.Map.Entry;

import elements.Light;
import elements.LightSource;
import elements.PointLight;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.Material;
import scene.Scene;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Created by yona on 17/05/2017.
 */
public class Render {
    //scene name parameter
    private Scene _scene;
    //image writer variable each writes the image
    private ImageWriter _imageWriter;
    //is a private value for reflection of light
    private final int RECURSION_LEVEL = 2;
    // ***************** Constructors ********************** //
    /*************************************************
     * regular- Constructor
     * PARAMETERS
     * image writer, scene
     * MEANING
     * this is a regular constructor which initializes
     * the parameters of the renderer
     **************************************************/
    public Render(ImageWriter imageWriter, Scene scene)
    {
       _imageWriter = new ImageWriter(imageWriter);
       this._scene = new Scene(scene);
    }
    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * renderImage
     * PARAMETERS
     * none
     * RETURN VALUE
     * void
     * MEANING
     * this is the main function in  the class,
     * he goes threw each point on the view plane with a ray
     * and checks the intersection with camera ray on the specific point
     * and writes all the intersections of the ray with all the geometries
     * and gets help from the raysceneintersection function, then
     * he checks which is the closes point and writes the right color with the
     * calccolor function who knows the color of each function.
     * if thtere is no intersection he writes on the image the backgrounde color.
     * SEE ALSO
     * writePixel, constructRayThroughPixel, getClosestPoint
     * closestPoint,  calcColor, getSceneRayIntersections.
     **************************************************/
    public void renderImage()
    {
        for (int i = 0; i < _imageWriter.getNx(); i++)
        {
            for (int j = 0; j < _imageWriter.getNy(); j++)
            {
                Ray ray;
                ray = new Ray(_scene.getCamera().constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(),j,i,
                        _scene.getScreenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()));

                if(i==255 && j==255)
                    System.out.print("111");
                Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<>(getSceneRayIntersections(ray));

                if(intersectionPoints.isEmpty())
                    _imageWriter.writePixel(j, i, _scene.getBackground());
                else
                {
                    Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
                    Map.Entry<Geometry,Point3D> entry;
                    Iterator<Entry<Geometry, Point3D>> it = closestPoint.entrySet().iterator();
                    entry = it.next();
                    _imageWriter.writePixel(j, i, calcColor(entry.getKey(), entry.getValue(), ray));
                }

            }
        }
        //printGrid(1);

    }

    private Entry<Geometry, Point3D> findClosesntIntersection(Ray ray)
    {
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);
        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
        Point3D rayPoint = ray.get_POO();
        Map.Entry<Geometry, List<Point3D>> entry;
        Iterator<Entry<Geometry, List<Point3D>>> it = intersectionPoints.entrySet().iterator();
        while (it.hasNext())
        {
            entry = it.next();
            for (Point3D point: entry.getValue()) {
                if (rayPoint.distance(point) < distance) {
                    closestPoint = new HashMap<Geometry, Point3D>();
                    closestPoint.put(entry.getKey(), point);
                    distance = rayPoint.distance(point);
                }
            }

        }
        if (closestPoint.isEmpty())
            return null;
        Entry<Geometry, Point3D> entry1;
        Iterator<Entry<Geometry, Point3D>> it1 = closestPoint.entrySet().iterator();
        entry1 = it1.next();
        return closestPoint.entrySet().iterator().next();
    }
    /*************************************************
     * FUNCTION
     * print grid
     * PARAMETERS
     * inerval
     * RETURN VALUE
     * void
     * MEANING
     * this function prins a grid on the view plane image. by using the length of the
     * interval.
     * SEE ALSO
     * writePixel.
     **************************************************/
    public void printGrid(int interval)
    {
        for (int i = 0; i < _imageWriter.getHeight(); i++){
            for (int j = 0; j < _imageWriter.getWidth(); j++) {

                if (i % 50 == 0 || j % 50 == 0 || i == 499 || j == 499)
                    _imageWriter.writePixel(j, i, 255, 255, 255);  // Black
            }
        }
    }
    public void writeToImage(){
        _imageWriter.writeToimage();
    }

    /*************************************************
     * FUNCTION
     * Calc color
     * PARAMETERS
     *  geometry, Point3D, in-ray, level
     * RETURN VALUE
     * color
     * MEANING
     * this function is the main function in the class, he uses all the sub-functions
     * to calculate the color of the point.
     * the function calculates the colors of the view plane, by adding
     * the ambient light and emission light.
     * and then calculates the point by taking in count the different lights on the scene.
     * he checks for shadows in the 'occluded' condition, if there is no geometry in the way of the light
     * the function calculates the diffusion and the specular light color (by using sub functions).
     * then he goes on and checks the refraction and reflection (by using sub functions).
     * if there is a reflection or a refraction a creats new rays and calculates by calling again the
     * calc-color function a recursion manner until we reach the level of recursion we set in advance.

     * the color of each function (light) is added to the l_point3D color value.
     * SEE ALSO
     * addColors, occluded, calcDiffusiveComp, constructRefractedRay,
     * findClosesntIntersection, calcSpecularComp
     **************************************************/
 private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
        return calcColor(geometry, point, ray, 0);
    }
    private Color calcColor(Geometry geometry, Point3D point, Ray inRay, int level){

     if(level == RECURSION_LEVEL) return new Color(0,0,0);

        int diffuseR = 0;
        int diffuseG = 0;
        int diffuseB = 0;
        int specularR = 0;
        int specularG = 0;
        int specularB = 0;
        Iterator<LightSource> lights = _scene.getLightsIterator();
        Material material = geometry.getMaterial();
        Vector normal = geometry.getNormal(point);
        while (lights.hasNext())
        {
            LightSource light = lights.next();
            Vector L = light.getL(point);
            Color intensity = light.getIntensity(point);
            if (!occluded(light, point, geometry)) {
                Color diffuseColor = calcDiffusiveComp(material.getKd(), new Vector(normal), L, intensity);
                diffuseR += diffuseColor.getRed();
                diffuseG += diffuseColor.getGreen();
                diffuseB += diffuseColor.getBlue();
                Color specularColor = calcSpecularComp(material.getKs(), new Vector(_scene.getCamera().getP0(), point), new Vector(normal),
                        L, geometry.getShininess(), intensity);
                specularR += specularColor.getRed();
                specularG += specularColor.getGreen();
                specularB += specularColor.getBlue();
            }
        }

        Ray reflectedRay = constructReflectedRay(normal, point, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosesntIntersection(reflectedRay);
        Color reflectedColor;
        Color reflectedLight = new Color(0,0,0);
        if(reflectedEntry != null) {
            reflectedColor = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
            double kr = material.getKr();
            reflectedLight = new Color((int) (kr * reflectedColor.getRed()), (int) (kr * reflectedColor.getGreen()), (int) (kr * reflectedColor.getBlue()));
        }

        Ray refractedRay = constructRefractedRay(geometry, point, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosesntIntersection(refractedRay);
        Color refractedColor;
        Color refractedLight = new Color(0,0,0);
        if (refractedEntry != null) {
            refractedColor = calcColor(refractedEntry.getKey(), refractedEntry.getValue(), refractedRay, level + 1);
            double kt = material.getKt();
            refractedLight = new Color((int) (kt * refractedColor.getRed()), (int) (kt * refractedColor.getGreen()), (int) (kt * refractedColor.getBlue()));
        }

        int finalR = Math.min(255, geometry.getEmmission().getRed() + _scene.getAmbientLight().getIntensity().getRed() + diffuseR + specularR + reflectedLight.getRed() + refractedLight.getRed());
        int finalG = Math.min(255, geometry.getEmmission().getGreen() + _scene.getAmbientLight().getIntensity().getGreen() + diffuseG + specularG + reflectedLight.getGreen() + refractedLight.getGreen());
        int finalB = Math.min(255, geometry.getEmmission().getBlue() + _scene.getAmbientLight().getIntensity().getBlue() + diffuseB + specularB + reflectedLight.getBlue() + refractedLight.getBlue());


        return new Color(finalR, finalG, finalB);
    } // Recursive

    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay){
        Vector N = geometry.getNormal(point);
        Vector direct = inRay.get_direction();
        double cosOi = N.dotProduct(direct);
        if (cosOi < 0) {
            N.scale(-2);
        }
        else{
            N.scale(2);
        }
        Point3D pointEps = new Point3D(point);
        pointEps.add(N);
        return new Ray(pointEps, direct);
    }
    /*************************************************
     * FUNCTION
     * constructRefractedRay
     * PARAMETERS
     *  Geometry geometry, Point3D point, Ray inRay
     * RETURN VALUE
     * RAY
     * MEANING
     * we create a new ray which is product of a refraction of an older ray
     * so we take the point of the hit of the old ray
     * we check if its a spere and then we change the direction, if not we use the same direction of
     * the old ray.
     * SEE ALSO
     * scale, add.
     **************************************************/
    private Ray constructReflectedRay(Vector normal, Point3D point, Ray inRay){
        Vector R = inRay.get_direction();
        normal.scale(2*inRay.get_direction().dotProduct(normal));
        R.subtract(normal);
        Vector epsV = new Vector(normal);
        if (normal.dotProduct(R) < 0) {
            epsV.scale(-2);
        }
        else {
            epsV.scale(2);
        }
        //Vector epsV = new Vector(EPS, EPS, EPS);
        Point3D eps = new Point3D(point);

        eps.add(epsV);
        R.normalize();
        return new Ray(eps, R);
    }
    /*************************************************
     * FUNCTION
     * occluded
     * PARAMETERS
     *  LightSource light, Point3D point, Geometry geometry
     * RETURN VALUE
     * boolean
     * MEANING
     * this function checks for shadow points, he creats a ray from the ray camera to the object and then reverses the
     * direction of the vector and check for any intersection with the scene ray intersection function.
     * in addition we check out the floating point problem and the flat geometry.
     * SEE ALSO
     *  getSceneRayIntersections, normilze
     **************************************************/
    private boolean occluded(LightSource light, Point3D point, Geometry geometry){
        Vector lightDirection = light.getL(point);
        // we want to check the vector from the ray of the camera to the light ray
        // this is the revers direction
        lightDirection.scale(-1);

        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector.scale(2);
        geometryPoint.add(epsVector);
        // create new ray
        Ray lightRay = new Ray(geometryPoint, lightDirection);
        // check for intersection points
        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(lightRay);

        // Flat geometry cannot self intersect
        if (geometry instanceof FlatGeometry){
            intersectionPoints.remove(geometry);
        }
        for(Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet()){
            if(entry.getKey().getMaterial().getKt() == 0)
                if (!(light instanceof PointLight) ||
                        (light instanceof PointLight && point.distance(entry.getValue().get(0)) < point.distance(((PointLight)light).getPosition())))
                    return true;
        }
        return false;
    }
    /*************************************************
     * FUNCTION
     * calcSpecularComp
     * PARAMETERS
     *  ks, vector v, normal, vector l, shininess, light intensity
     * RETURN VALUE
     * color
     * MEANING
     * this function calculates the specular light effect on the color.
     * he first normalizes the vector,
     * the l vector is a vector which gets from the light to the object
     * we use a calculation to get the R vector which is the the plumb of the L vector and hits the camera
     * then we multiply the VR exponently with shininess value and multiply with the light intensity and ks factor
     * SEE ALSO
     * normalize, dotproduct, subtraction.
     **************************************************/
    private Color calcSpecularComp(double ks, Vector v, Vector normal, Vector l, double shininess, Color lightIntensity){
        Vector R = new Vector(l);
        normal.scale(2*l.dotProduct(normal));
        R.subtract(normal);
        v.normalize();
        R.normalize();
        double KsVdotR = ks * Math.pow(v.dotProduct(R), shininess);
        int red = Math.min(255,(int)(lightIntensity.getRed() * KsVdotR));
        red = Math.max(red, 0);
        int green = Math.min(255,(int)(lightIntensity.getGreen() * KsVdotR));
        green = Math.max(green, 0);
        int blue = Math.min(255,(int)(lightIntensity.getBlue() * KsVdotR));
        blue = Math.max(blue, 0);
        return new Color(red, green, blue);
    }
    /*************************************************
     * FUNCTION
     * ccalc-DiffusiveComp
     * PARAMETERS
     *  kd, normal, vector l, light intensity
     * RETURN VALUE
     * color
     * MEANING
     * the function calculates the diffusion on the object, he uses an equation
     * which calculates the dot product between the L vector and the normal
     * then he multiplies the NL with the kd and the light-intensity
     * SEE ALSO
     * normalize, dot-product,  min.
     **************************************************/
    private Color calcDiffusiveComp(double kd, Vector normal, Vector l, Color lightIntensity){
        double kdNdotL  = kd * Math.abs(normal.dotProduct(l));

        int red = Math.min(255,(int)(kdNdotL * lightIntensity.getRed()));

        int green = Math.min(255,(int)(kdNdotL* lightIntensity.getGreen()));

        int blue = Math.min(255,(int)(kdNdotL * lightIntensity.getBlue()));

        return new Color(red, green, blue);
    }
    /*************************************************
     * FUNCTION
     * getClosestPoint
     * PARAMETERS
     * Map<Geometry, List<Point3D>> intersectionPoints
     * RETURN VALUE
     * Map<Geometry, List<Point3D>>
     * MEANING
     * this functions gives a map whith the geometry value, and the
     * closes point of intersection point.
     **************************************************/
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {

        double distance = Double.MAX_VALUE;
        Map<Geometry, Point3D> closestPoint = new HashMap<Geometry, Point3D>();
        Point3D P0 = _scene.getCamera().getP0();
        Map.Entry<Geometry, List<Point3D>> entry;
        Iterator<Entry<Geometry, List<Point3D>>> it = intersectionPoints.entrySet().iterator();
        while (it.hasNext())
        {
            entry = it.next();
            for (Point3D point: intersectionPoints.get(entry.getKey())) {
                if (P0.distance(point) < distance) {
                    closestPoint = new HashMap<Geometry, Point3D>();
                    closestPoint.put(entry.getKey(), point);
                    distance = P0.distance(point);
                    }
                }
            }
        return closestPoint;
    }

    /*************************************************
     * FUNCTION
     * getSceneRayIntersections
     * PARAMETERS
     * ray
     * RETURN VALUE
     * Map<Geometry, List<Point3D>>
     * MEANING
     * this functions gives a map of all the intersections of
     * geometries which intersect the ray
     **************************************************/
    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray){
        //Map key - geometric
        //value - a list of cut points
        Map<Geometry, List<Point3D>> sceneRayIntersectPions = new HashMap<Geometry, List<Point3D>>();
        //Iterator we can go through all the geometric shapes
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        //for each geometry finde intersection points
        while (geometries.hasNext()){
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.FindIntersections(ray);
            if(!geometryIntersectionPoints.isEmpty())
                //add geometryIntersectionPoints(list) to key geometry
                sceneRayIntersectPions.put(geometry, geometryIntersectionPoints);
        }
        return sceneRayIntersectPions;
    }
    /*************************************************
     * FUNCTION
     * Addcolors
     * PARAMETERS
     *  color a, color b
     * RETURN VALUE
     * color
     * MEANING
     * add the colors of tow ready color from calc color
     **************************************************/
    private Color addColors(Color a, Color b) {
        int red = Math.min(255, a.getRed() + b.getRed());
        int green = Math.min(255, a.getGreen() + b.getGreen());
        int blue = Math.min(255, a.getBlue() + b.getBlue());
        return new Color(red, green, blue);
    }
}
