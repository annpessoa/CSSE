
/*
 * This class creates each case as an object that contains the case number as an int, the cash value inside as
 * a double, and a boolean that determines whether or not the case has been opened.
 * 
 * @ Anneliese Pessoa
 * @ 3/17/2016
 */

public class Case
{
    // instance variables - replace the example below with your own
    private int caseNum; //number to identify each unique case
    private double cashValue; //the amount of money contained in the case
    private boolean opened; //determines whether the case has been opened

    /*
     * Default constructor for objects of class Case
     * Initializes all values to be zero or false
     */
    public Case()
    {
        //create an empty case
        caseNum = 0;
        cashValue = 0.0;
        opened = false;
    }
    
    /*
     * Parameterized constructor to set the values of the case number and cash value.
     */
    
    public Case(int num, double value){
        //create a case with new values
        setNum(num);
        setValue(value);
    }
    
    /*
     * This method sets the number of the case.
     */
    public void setNum(int num){
        caseNum = num; //change the value of caseNum
    }
    
    /*
     * This method sets the value of the contents of the case.
     */
    public void setValue(double value){
        cashValue = value; //change the cashValue
    }
    
    /*
     * This method is called when the player opens a case and sets the boolean value to true.
     */
    public void openCase(){
        opened = true; //open the case
    }
    
    /*
     * This method returns the number of the case.
     */
    public int getNum(){
        return caseNum;
    }
    
    /*
     * This method returns the amount of money inside of the case.
     */
    public double getValue(){
        return cashValue;
    }
    
    /*
     * This method returns whether or not the case has been opened or not.
     * True if the case has been opened and false if it hasn't.
     */
    public boolean isOpened(){
        return opened;
    }
}
