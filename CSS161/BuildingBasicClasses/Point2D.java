
/*
 * This class deals with a point in two dimensions
 * The class will be able to get and set the values of x and y
 * 
 * @author Anneliese Pessoa 
 * @version (a version number or a date)
 */
public class Point2D
{
    // instance variables 
    private int x;
    private int y;

    /*
     * Constructor for objects of class Point2D
     */
    public Point2D()
    {
        // initialise instance variables
        x = 0;
        y = 0;
    }

    /*
     * This method returns the value of x
     */
    public int getX(){
        return x; // return the value of x
    }

    /*
     * This method returns the value of y
     */

    public int getY(){
        return y; //return the value of y
    }

    /*
     * This method sets the value of x
     */
    public void setX(int numX){
        x = numX; //set the x value to numX 
    }

    /*
     * This method sets the value of y
     */
    public void setY(int numY){
        y = numY; //set the y value to numY 
    }

    /*
     * This method describes the Point2D object as a String
     */
    public String toString(){
        //returns a string with the Point2D coordinates
        return "Point2D at (" + getX() + "," + getY() + ")";
    }
    
    /*
     * Execute Point2D 
     */
    public static void main(String[] args) {
        Point2D pt = new Point2D(); //create new Point2D object

        pt.setX(5); //set pt's x value
        pt.setY(2); //set pt's y value

        System.out.println("The 2D point's value is: " + pt.getX()); //print out pt's x value
        System.out.println("The 2D point's y value is: " + pt.getY()); //print out pt's y value

        System.out.println(pt.toString()); //print out the coordinates of pt
    }

}
