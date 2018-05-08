
/*
 * This class deals with a point in one dimension
 * The class will be able to get and set the value x
 * 
 * @author Anneliese Pessoa 
 * @version (a version number or a date)
 */

public class Point1D
{
    // instance variables
    private int x;

    /*
     * Constructor for objects of class Point1D
     */
    public Point1D()
    {
        // initialise instance variables
        x = 0;
    }
    
    /*
     * return the value of x
     */
    public int getX(){
        return x; //return the x value
    }
    
    /*
     * set the value of x
     */
    public void setX(int numX){
        x = numX; //set x value to numX
    }
    
    /*
     * Execute Point1D
     */
    public static void main(String[] args){
        Point1D pt = new Point1D(); //create a new Point1D object
        pt.setX(10); //set x value to 10
        //print out object pt's x value
        System.out.println("The point's x value is: " + pt.getX());
    }
    
}
