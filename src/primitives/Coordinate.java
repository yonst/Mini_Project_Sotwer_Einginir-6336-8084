package primitives;

/**
 * Created by yona on 26/03/2017.
 */

/**
 * this class is the most basic class in the package of primitives
 */

public class Coordinate implements Comparable<Coordinate>{
    /**
     *   A variable that representing a coordinate
     */a
    private double _coordinate;
    // ***************** Constructors ********************** //
    public Coordinate(){
        _coordinate = 0;
    }
    public Coordinate(double coordinate) {
        this._coordinate = coordinate;
    }

    /**
     * copy constructor
     */
    public Coordinate(Coordinate coordinate){
        this._coordinate = coordinate._coordinate;
    }

    /**
     *  ***************** Getters/Setters ********************** //
      */

    public double getCoordinate() {
        return _coordinate;
    }

    public void setCoordinate(double coordinate1) {
        this._coordinate = coordinate1;
    }

    /**
     * ***************** Administration ******************** //
      */

    /**Comparable between two coordinates
     *
     * @param coordinate is object of coordinate
     * @return:
     * if the coordinates are equal returns 0
     * if co is bigger than returns -1
     * if co is smaller than returns 1
     */
    @Override

    public int compareTo(Coordinate coordinate) {
        if (this._coordinate < coordinate._coordinate)
            return -1;
        if(this._coordinate > coordinate._coordinate)
            return 1;
        return 0;
    }

    @Override
    /**
     * this function returns a string with the coordinate until 2 digit afeter the '.'
     */
    public String toString() {
        return String.format("(%1.2f)", _coordinate);
    }

    /**
     *  ***************** Operations ******************** //
      */

    /**
     * Receive two coordinates and add _coordinate of co to _coordinate of this coordinate
     * @param coordinate
     */
    public void add(Coordinate coordinate){
        this._coordinate += coordinate._coordinate;
    }

    /**
     * Receive two coordinates and subtract _coordinate of co from _coordinate of this coordinate
     * @param coordinate
     */
    public void subtract(Coordinate coordinate){
         this._coordinate -= coordinate._coordinate;
    }
}
