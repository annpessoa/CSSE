
/*
 * This class is used to track the perormance of the Seahawks
 * The class will also be able to find the min, max, and median of three numbers
 * 
 * @author Anneliese Pessoa 
 * @version (a version number or a date)
 */
public class SeaHawksTracker
{
    // instance variables 
    private int firstScore;
    private int secondScore;
    private int thirdScore;

    /*
     * Constructor for objects of class SeaHawksTracker
     */
    public SeaHawksTracker()
    {
        // initialise instance variables
        firstScore = 0;
        secondScore = 0;
        thirdScore = 0;
    }

    /*
     * This method returns the value of firstScore
     */
    public int getFirstScore(){
        return firstScore; //return the value of firstScore
    }

    /*
     * This method returns the value of secondScore
     */
    public int getSecondScore(){
        return secondScore; //return the value of secondScore
    }

    /*
     * This method returns the value of thirdScore
     */
    public int getThirdScore(){
        return thirdScore; //return the value of thirdScore
    }

    /*
     * This method sets the value of firstScore
     */
    public void setFirstScore(int score){
        firstScore = score; //set the firstScore value to score
    }

    /*
     * This method sets the value of secondScore
     */
    public void setSecondScore(int score){
        secondScore = score; //set the secondScore value to score
    }

    /*
     * This method sets the value of thirdScore
     */
    public void setThirdScore(int score){
        thirdScore = score; //set the thirdScore value to score
    }

    /*
     * This method returns the minimum of three integers
     */
    public int min(int a, int b, int c){
        if(a <= b && a <= c){
            return a; //return int a if it is the min numeber of the three
        }else if(b <= a && b <= c){
            return b;//return int b if it is the min numeber of the three
        }
        return c; //return int c if it is the min numeber of the three
    }

    /*
     * This method returns the maximum of three integers
     */
    public int max(int a, int b, int c){
        if(a >= b && a >= c){
            return a; //return int a if it is the max numeber of the three
        }else if(b >= a && b >= c){
            return b; //return int b if it is the max numeber of the three
        }
        return c; //return int c if it is the max numeber of the three
    }

    /*
     * This method returns the median of three integers
     */
    public int mid(int a, int b, int c){
        if( b > a && a > c || c > a && a > b){
            return a; //return int a if it is the mid numeber of the three
        }else if(a > b && b > c || c > b && b > a){
            return b; //return int b if it is the mid numeber of the three
        }return c;
    }

    /*
     * This method returns a string printout of the three variables in ascending order
     */
    public String toString(){
        String min = "The least of the SeaHawks' scores was:" + min(firstScore,secondScore,thirdScore); //get the min score
        String mid = "The middle of the SeaHawks' scores was:" + mid(firstScore,secondScore,thirdScore); //get the mid score
        String max = "The greatest of the SeaHawks' scores was:" + max(firstScore,secondScore,thirdScore); //get the max score
        return min + "\n" + mid + "\n" + max + "\n"; //return the min max and min of all three scores as a string
    }

    /*
     * This method executes SeaHawksTracker
     */
    public static void main(String[] args) {
        SeaHawksTracker stats = new SeaHawksTracker(); //create a new SeaHawksTracker object
        stats.setFirstScore(22); //set the first score
        stats.setSecondScore(11); //set the second score
        stats.setThirdScore(27); //set the third score

        System.out.println("---------Min, Mid, & Max----------");
        System.out.println("The largest is:" + stats.max(3,5,1)); //print out the max of the three numbers
        System.out.println("The middle is:" + stats.mid(3,5,1)); //print out the mid of the three numebrs
        System.out.println("The smallest is:" + stats.min(3,5,1)); //print out the min of the three numbers

        System.out.println("\n---------Report of Scores--------");
        System.out.println(stats.toString());	//print out the score report
    }

}
