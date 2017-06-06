package geometries;

/**
 * Created by Moishe on 04/04/2017.
 */

import primitives.Ray;
import primitives.Point3D;
import primitives.Vector;
import primitives.Point2D;
import primitives.Coordinate;
import primitives.Material;
import java.awt.Color;

import java.util.*;

//this class represent the components of the Geometric shapes
public abstract class Geometry {
    
    private Material _material = new Material();//The material of the geometric shape
    private double _nShininess = 1; // the Shininess of the geometric shape
    private Color _emmission = new Color(0, 0, 0); //the color of the geometric shape
    public abstract List<Point3D> FindIntersections (Ray ray);//list of intersection with ray that comes from the camera with the geometry
    public abstract Vector getNormal(Point3D point);//receive the normal of the geometry
    public double getShininess(){
        return _nShininess;
    }//returns the shininess of the geometry
    public Material getMaterial(){
        return new Material(_material);
    }//returns the material of it made the geometry
    public Color getEmmission(){
        return new Color(_emmission.getRGB());
    }//return the emission of the geometry
    public void setShininess(double n){
        _nShininess = n;
    }
    public void setMaterial(Material material){
        this._material = new Material(material);
    }
    public void setEmmission(Color emmission){
        _emmission = new Color(emmission.getRGB());
    }
    public void setKs(double ks){
        _material.setKs(ks);
    }
    public void setKd(double kd){
        _material.setKd(kd);
    }
    public void setKr(double Kr){
        _material.setKr(Kr);
    }
    public void setKt(double Kt){
        _material.setKt(Kt);
    }
}
