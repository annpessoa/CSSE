
/*
 * This class creates a banker that occasionally makes an offer to the player. The banker will return an offer based
 * on an equation that takes the average cash value of the cases on the board and multiplies that average by the number of turns.
 * That number will then be divided by 1000 to create a value that will most likely be less than the player's case.
 * 
 * @ Anneliese Pessoa 
 * @ 3/17/2016
 */
public class Banker
{
    // instance variables
    private double offer; //this double is the offer the banker will make
    private double averageValue; //this double will store the average value of all unopened cases
    private int numCases; //this will store the number of cases on the board
    
    public Banker()
    {
        // initialize instance variables
        offer = 0.0;
    }

    /*
     * This method returns the Banker's offer.
     */
    public double getOffer(){
        return offer;
    }
    
    /*
     * This method sets the offer that the banker will make based on the number of cases left unopened. 
     */
    public void setOffer(Case[][] cases, int turnNumber){
        averageValue = 0.0; //sets average value to 0
        numCases = 0; // sets the number of cases to 0
        //Loops through the board and adds the value of all the unopened cases to averageValue
        for(int y = 0; y < cases.length; y++){
            for(int x = 0; x < cases[y].length; x++){
                if(!cases[y][x].isOpened()){ 
                    averageValue += cases[y][x].getValue(); //add up values of the cases
                    numCases++; //increment number of cases
                }
            }
        }
        averageValue /= numCases; //get the average value of all the cases
        offer = averageValue * turnNumber / 1000; //multiply the average value by the turn number and divide by 100 to get the offer
    }
}
