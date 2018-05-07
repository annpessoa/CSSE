import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/*
 * This is a game of Deal or No Deal! The functions of this game are slightly different than the game show however the game overall is fairly similar.
 * The user will be prompted to choose a case and will keep that case throughout the game. The player will continue to eliminate more and more cases
 * until they recieve an offer from a banker and accept it or until there are no cases left. At the end of the game instead of making the user choose
 * between the final case on the board and their personal case, a final cash offer is made from the banker and the user must choose to accept the offer
 * or keep their own case. 
 * 
 * @ Anneliese Pessoa 
 * @ 3/17/2016
 * 
 */
public class DealOrNoDeal
{
    //initialize the player's case
    private int playerCaseNum;
    private double playerCaseVal;
    //initialize Banker
    private Banker banker = new Banker();
    //initialize the cases
    private Case[][] cases = new Case[4][];
    //initialize cash values
    private double[] cash = new double[26];
    private int[] caseNums = new int[26];
    //initialize turn number
    private int turns = 0;
    //initialize the players choice in payout rate
    private String gameType = "option";
    //boolean value to set whether or not the game is being played
    private boolean stillPlaying = true;
    /*
     * This method runs the game
     */
    public void playGame(){
        //initialize the board
        initializeCases();
        //start the game
        Scanner inputFile = null;
        Scanner sc = new Scanner(System.in);
        printIntro(sc);
        //read from a file to get the payout rate that the user wants
        try {
            inputFile = new Scanner(new File(gameType + ".txt")); 
        } catch (FileNotFoundException e) {
            System.out.println("no file was found");
            System.exit(0);
        }    
        //set the values of the cases based upon the user input
        setCaseNums();
        setCashValues(inputFile);
        setCaseValues(); //set the values of each case
        System.out.println();
        printCases(); //print out the board
        getFirstCase(sc); //prompt player to choose the first briefcase
        while(stillPlaying){
            System.out.println();
            printCases(); //print out the board
            removeCase(sc); //ask the user to choose a case
            turns++; //add to the number of turns
            switch(turns){
                case 7: case 10: case 14: case 19: case 26: //calls the banker at turn numbers 7, 10, 14, 19, and 26
                callBanker(sc); //get a call from the banker with an offer
                break;
            }
            //end the game once all cases have been opened-the player has chosen to stick with the case they chose in the beginning
            if(turns == 26){
                stillPlaying = false; //end the game
                outroRejectOffer(); //print out the outro 
            }
        }
    }

    /*
     * This method initializes all cases to have randomly placed values.
     */
    public void initializeCases(){
        int rowLength = 6; //default length of the row of cases
        for(int i = 0; i < cases.length; i++){
            if(i == 0 || i == 3){ //if the row is the first or last one, have 6 cases in the row
                cases[i] = new Case[rowLength];
            }else{ //if the row is one of the middle two rows, have 7 cases in the row
                cases[i] = new Case[rowLength + 1];
            }
        }
    }

    /*
     * sets the case nums array to be numbers from 1-26
     */
    public void setCaseNums(){
        for(int i = 0; i < 26; i++){
            caseNums[i] = i + 1;
        }
    }
    
    /*
     * This method sets all cases to have a number and a money value inside.
     */
    public void setCaseValues(){
        int caseCount = 0; //used to get the value of each case from the caseNums and cash arrays
        //shuffle the arrays that contain the case numbers and cash values
        shuffleCaseNumbers();
        shuffleCashValues();
        //loop through entire 2D array
        for(int y = 0; y < cases.length; y++){
            for(int x = 0; x < cases[y].length; x++){
                cases[y][x] = new Case(caseNums[caseCount], cash[caseCount]); //create a new case with the values from the caseNums array and the cash array
                caseCount++; //increment the caseCount index
            }
        }
    }
    
    /*
     * This method reads from a file and gets the values of the briefcases
     */
    public void setCashValues(Scanner s){
        int index = 0;
        while(s.hasNext()){
            cash[index] = s.nextDouble();
            index++;
        }
    }
    
    /*
     * This method will take the array of case numbers and randomly shuffle them
     */
    public void shuffleCaseNumbers(){
        Random random = new Random(); //create a new random object
        for(int i  = 0; i < caseNums.length; i++){
            int randomIndex = random.nextInt(caseNums.length); //create a random index
            int temp = caseNums[i]; //create a temporary variable to contain the value at index i before it is switched with another value
            caseNums[i] = caseNums[randomIndex]; //set the original value at index i to the value at the randomly generated index
            caseNums[randomIndex] = temp; //replace the value at the randomly generated index with the value that was originally at index i
        }
    }
    
    /*
     * This method will take the array of cash values and randomly shuffle them
     */
    public void shuffleCashValues(){
        Random random = new Random(); //create a new random object
        for(int i  = 0; i < cash.length; i++){
            int randomIndex = random.nextInt(cash.length); //create a random index
            double temp = cash[i]; //create a temporary variable to contain the value at index i before it is switched with another value
            cash[i] = cash[randomIndex]; //set the original value at index i to the value at the randomly generated index
            cash[randomIndex] = temp; //replace the value at the randomly generated index with the value that was originally at index i
        }
    }

    /*
     * This method makes the number of the cases visible to the player but not the amount of money inside.
     */
    public void printCases(){
        //loop through each element in the 2D array and print out the number of each case
        for(int y = 0; y < cases.length; y++){
            for(int x = 0; x < cases[y].length; x++){
                //print out the number of the case only if the case has not been opened AND if the case is not the player's case
                if(!cases[y][x].isOpened() && cases[y][x].getNum() != playerCaseNum){
                    System.out.print("|" + cases[y][x].getNum() + "|"); //print out the number of each case
                }else{
                    System.out.print("| |");//print out a blank space if the case has been opened
                }
            }
            System.out.println();
        }
    }

    /*
     * This method will get the number and cash value of the player's chosen case.
     */
    public void getFirstCase(Scanner sc){
        System.out.print("Here are the briefcases. Enter the number of the case you want:");
        playerCaseNum = sc.nextInt(); //sets the players case number
        playerCaseVal = getCaseValue(playerCaseNum); //get the cash value of the player's case
        turns++;
    }

    /*
     * This method loops through the board and returns the cash value of the target case.
     */
    public double getCaseValue(int caseNum){
        for(int y = 0; y < cases.length; y++){ //loop through the y values
            for(int x = 0; x < cases[y].length; x++){ //loop through the x values
                if(cases[y][x].getNum() == caseNum){ //if the current case in the 2D array is the target num, return the cash value inside
                    return cases[y][x].getValue();
                }
            }
        }
        return 0.0;
    }

    /*
     * This method will allow the user to choose a case and see the value inside. It will only remove the case if
     * the case has not already been removed.
     */
    public void removeCase(Scanner sc){
        boolean hasRemovedCase = false;
        //loop until a case has been removed
        while(!hasRemovedCase){
            System.out.print("Choose a case: "); //promt user for a case number
            int caseNum = sc.nextInt(); //get the case number from the input
            if(!cases[getY(caseNum)][getX(caseNum)].isOpened()){
                openCase(caseNum); //open the case
                System.out.printf("The amount of money in this case was: %.2f", getCaseValue(caseNum)); //print out the value of the case
                System.out.println("\n");
                hasRemovedCase = true; //end the loop in this method
            }else{
                System.out.println("You have already chosen this case! Try again"); //tell the user that they need to enter valid input
            }
        }
    }
    
    /*
     * This method loops through the board and opens only the target case.
     */
    public void openCase(int caseNum){
        for(int y = 0; y < cases.length; y++){ //loop through y values
            for(int x = 0; x < cases[y].length; x++){ //loop through x values
                if(cases[y][x].getNum() == caseNum){//if the current case in the 2D array is the target num, open the case
                    cases[y][x].openCase();
                }
            }
        }
    }

    /*
     * This method will return the x index of the given case number.
     */
    public int getX(int caseNum){
        for(int y = 0; y < cases.length; y++){ //loop through the y values
            for(int x = 0; x < cases[y].length; x++){ //loop through the x values
                if(cases[y][x].getNum() == caseNum){
                    return x; //return x index of the case
                }
            }
        }
        return 0;
    }

    /*
     * This method will return the y index of the given case number.
     */
    public int getY(int caseNum){
        for(int y = 0; y < cases.length; y++){ //loop through the y values
            for(int x = 0; x < cases[y].length; x++){ //loop through the x values
                if(cases[y][x].getNum() == caseNum){
                    return y; //return y index of the case
                }
            }
        }
        return 0;
    }

    /*
     * This method will get an offer from the banker and takes into account whether or not the player chooses to accept the offer.
     */
    public void callBanker(Scanner sc){
        System.out.println("\nThe banker is calling in to make an offer"); //announce that the banker is going to make an offer
        banker.setOffer(cases, turns); //sets the current offer from the banker
        System.out.printf("Their offer is: $%.2f", banker.getOffer()); //print out the bankers offer

        boolean validInput = false; //boolean to keep track of whether or not the user's input was valid
        while(!validInput){
            System.out.println("\nDo you chose to accept the offer? (Y/N)"); //ask the user wheter or not they wish to accept the offer
            String acceptOrReject = sc.next(); //store the user input
            switch(acceptOrReject){
                case "Y": case "y": case "YES": case "YEs": case "Yes": case "yes":
                    outroAcceptOffer(); //print out the outro
                    validInput = true; //end the while loop in this method
                    stillPlaying = false; //end the game
                    break;
                case "N": case "n": case "NO": case "No": case "no":
                    System.out.println("You chose to reject the banker! Let's hope that was a good choice."); 
                    validInput = true; //end the while loop in this methd
                    break;
                default:
                    System.out.println("Your input was invalid try again");
                    break;
            }
        }
    }

    /*
     * This method prints out the intro welcoming the player to the game and explains the rules.
     */
    public void printIntro(Scanner sc){
        System.out.println("Welcome to Deal or No Deal!");
        System.out.println("In this game you will choose a single briefcase that will contain a cash value.");
        System.out.println("The goal is to eliminate cases until a cash offer is given to you by a banker.");
        System.out.println("If you choose to accept the banker's offer, the game is over.");
        System.out.println("However, if you reject their offer, you may continue to eliminate cases.");
        System.out.println("If you reject all of the banker's offers, you must choose to take home the case");
        System.out.println("you chose in the beginning, or you may take home the banker's final offer.");
        System.out.println();
        System.out.println("The highest amount of money you can win in this game is determined by you.");
        System.out.println("Here are your options for maximum payout (enter the letter in parenthesis as your choice):");
        System.out.println("(A) $1,000,000 \n(B) $1,500,000 \n(C) $2,000,000 \n(D) $2,500,000 \n(E) $3,000,000");
        gameType += sc.next().toUpperCase();
    }

    /*
     * This prints out the value the player has chosen to accept from the banker along with the value of
     * the player's case. 
     */
    public void outroAcceptOffer(){
        System.out.println("You accepted the offer from the banker!");
        System.out.printf("You get to walk away today $%.2f", banker.getOffer());
        System.out.println(" richer, congrats!");
    }

    /*
     * This prints out the value of the case the player has chosen at the beginning of the game.
     */
    public void outroRejectOffer(){
        System.out.println("You have rejected the final offer from the banker!");
        printPlayerCaseVal();
    }

    /*
     * This method prints out the value of the player's case
     */
    public void printPlayerCaseVal(){
        System.out.println("The amount of money in your case was $" + playerCaseVal);
    }
}
