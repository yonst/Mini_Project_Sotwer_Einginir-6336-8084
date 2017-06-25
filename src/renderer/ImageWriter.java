package renderer;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
/**
 * Created by yona on 17/05/2017.
 */
//this class writes the image on a file, and executes the writing on each pixel
public class ImageWriter {
    private int _imageWidth;
    private int _imageHeight;
    private int _Ny, _Nx;
    // the name of the file which is on the output
    final String PROJECT_PATH = System.getProperty("user.dir");
    //writes the picture on the image at once
    private BufferedImage _image;
    // the name of the image in the project.
    private String _imageName;

    // ***************** Constructors ********************** //
    /*************************************************
     * regular- Constructor
     * PARAMETERS
     * image-writer, width, height, Nx, Ny.
     * MEANING
     * this is a regular- constructor which initializes the parameters
     * image writer.
     **************************************************/
    public ImageWriter(String imageName, int width, int height,
                       int Ny, int Nx) {
        _Nx = Nx;
        _Ny = Ny;
        //the size of the image
        _imageWidth = width;
        _imageHeight = height;
        _imageName = imageName;
        _image = new BufferedImage(_imageWidth, _imageHeight,
                BufferedImage.TYPE_INT_RGB);
    }
    /*************************************************
     * Copy- Constructor
     * PARAMETERS
     * image-writer variable.
     * MEANING
     * this is a regular- constructor which initializes the parameters
     * image writer.
     **************************************************/

    public ImageWriter(ImageWriter imageWriter) {
        //number of pixel.
        _Nx = imageWriter._Nx;
        _Ny = imageWriter._Ny;
        _imageWidth = imageWriter.getWidth();
        _imageHeight = imageWriter.getHeight();
        _imageName = imageWriter._imageName;
        _image = new BufferedImage(_imageWidth, _imageHeight,
                BufferedImage.TYPE_INT_RGB);
    }

    // ***************** Getters/Setters ********************** //
    /*************************************************
     * Getter
     * PARAMETERS
     * none
     * MEANING
     * Gets the imageWidth size.
     **************************************************/
    public int getWidth() {
        return _imageWidth;
    }
    /*************************************************
     * Getter
     * PARAMETERS
     * none
     * MEANING
     * Gets the imageHeight size.
     **************************************************/

    public int getHeight() {
        return _imageHeight;
    }
    /*************************************************
     * Getter
     * PARAMETERS
     * none
     * MEANING
     * Gets the Ny size, num of pixel's.
     **************************************************/

    public int getNy() {
        return _Ny;
    }
    /*************************************************
     * Getter
     * PARAMETERS
     * none
     * MEANING
     * Gets the Nx size, num of pixel's.
     **************************************************/

    public int getNx() {
        return _Nx;
    }
    /*************************************************
     * Setter
     * PARAMETERS
     * Ny
     * MEANING
     * Sets the Ny size, num of pixel's.
     **************************************************/

    public void setNy(int _Ny) {
        this._Ny = _Ny;
    }
    /*************************************************
     * Getter
     * PARAMETERS
     * Nx
     * MEANING
     * sets the Nx size, num of pixel's.
     **************************************************/

    public void setNx(int _Nx) {
        this._Nx = _Nx;
    }

    // ***************** Operations ******************** //
    /*************************************************
     * FUNCTION
     * writeToimage
     * PARAMETERS
     * none
     * RETURN VALUE
     * void
     * MEANING
     * this function writes the image on a file which is
     * defined in the class by a string value.
     **************************************************/
    public void writeToimage() {
        File outFile = new File(PROJECT_PATH + "/" + _imageName + ".jpg");
        try {
            ImageIO.write(_image, "jpg", outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*************************************************
     * FUNCTION
     * writePixel
     * PARAMETERS
     * xIndex, yIndex, r, red, g, green, b, blue.
     * RETURN VALUE
     * void
     * MEANING
     * it gives the color of each pint on the view plane
     * which is the picture we create
     **************************************************/
    public void writePixel(int xIndex, int yIndex, int r, int g, int b) {
        int rgb = new Color(r, g, b).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);
    }

    /*************************************************
     * FUNCTION
     * writePixel
     * PARAMETERS
     * xIndex, yIndex, r, red, g, green, b, blue.
     * RETURN VALUE
     * void
     * MEANING
     * it gives the color of each pint on the view plane
     * which is the picture we create, and calls the calculater of the colors function from
     * the renderer.
     **************************************************/
    public void writePixel(int xIndex, int yIndex, int[] rgbArray) {
        int rgb = new Color(rgbArray[0], rgbArray[1], rgbArray[2]).getRGB();
        _image.setRGB(xIndex, yIndex, rgb);
    }
    public void writePixel(int xIndex, int yIndex, Color color) {
        _image.setRGB(xIndex, yIndex, color.getRGB());
    }

}
