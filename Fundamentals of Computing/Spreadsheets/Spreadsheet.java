import java.util.Scanner;
import java.text.DecimalFormat;
/*
 * Spreadsheet Skeleton File
 * For use with the Spreadsheet HW in 161, this assignment has you build methods
 * that do work on 2-dimensional arrays and produce calculations similar to what
 * you would find in Excel. 
 *
 * Don't rename these methods, or change their input or output values - use this
 * template to get you started.  You are free to add additional methods and data
 * as you see fit.
 *
 * Edited by Anneliese Pessoa
 *
 */

public class Spreadsheet{

     //Declaring variables as static globals is usually bad design. Do *NOT* use this strategy in future assignments.
    public static double[][] spreadsheet;
    public static int size = 2;
    public static DecimalFormat spreadsheetFormat = new DecimalFormat("0.00");
    
    /*
     * This method executes the Spreadsheets class
     */
    public static void main(String[] args) {
        //need some scanner data here       
        int input;
        initializeSpreadsheet(); //create a 2D array filled with 0.0
        

        while(true) {
            printSpreadsheet();
            Scanner sc = new Scanner(System.in);
            System.out.println("(1) Input (2) Row Sum (3) Col Sum (4) Row Ave (5) Col Ave (6) Sum (7) Average (8) Quit");
            input = sc.nextInt();
            //get the next operation from the user
            switch(input) { 
            case 1: 
                System.out.println("Enter integers X, Y, and double Z value: "); //ask user to input data
                int x = sc.nextInt();//get spreadsheet coordinate x
                int y = sc.nextInt();//get spreadsheet coordinate y
                double z = sc.nextDouble();//get double value that will be put into the array
                inputData(x, y, z); //set index x,y to the value z
                break;
            case 2: 
                System.out.println("Enter Row: ");//ask user for a row to sum
                int row = sc.nextInt(); //get the row
                System.out.print("Sum : "); //print out the sum of numbers in the given row
                System.out.printf("%.2f", sumRow(row));
                break;
            case 3: 
                System.out.println("Enter Column: ");//ask user for a column to sum
                int col = sc.nextInt(); //get the column
                System.out.print("Sum : ");//print out the sum of numbers in the given column
                System.out.printf("%.2f", sumCol(col));
                break;
            case 4: 
                System.out.println("Enter Row: ");//ask user for a row to average
                int inputRow = sc.nextInt();//get the row
                System.out.print("Average : ");//print out the average of numbers in the given row
                System.out.printf("%.2f", rowAve(inputRow));
                break;
            case 5: 
                System.out.println("Enter Column: ");//as user for a columm to average
                int inputCol = sc.nextInt();//get the col
                System.out.print("Average : ");//print out the average of numbers in the given column
                System.out.printf("%.2f", colAve(inputCol));
                break;
            case 6:
                System.out.print("Sum : ");//print the sum of all numbers in the array
                System.out.printf("%.2f", sumTotal());
                break;
            case 7: 
                System.out.print("Average : ");//print the average of all numbers in the array
                System.out.printf("%.2f", average());
                break;
            case 8:
                quit();
                break;
                 default:
                System.out.println("Unrecognized command. Please try again!");
            }
            System.out.println();
        }   
    }
   
    /*
     * This method initializes the spreadsheet to have length and width height
     * Initailizes each value int he 2D array to be 0.0
     */
    public static void initializeSpreadsheet(){
        spreadsheet = new double[size][size]; //initialize the spreadsheet array
        for(int y = 0; y < size; y++){ //loop through y values
            for(int x = 0; x < size; x++){ //loop through x value
                spreadsheet[y][x] = 0.0; //initialize value at index y, x to 0.0
            }
        }
    }
   
    /*
     * Set the value of the user input index x and y to the input double value z
     */
    public static void inputData(int x, int y, double value){
        spreadsheet[y][x] = value; //set index y, x to the input value from the user
    }   
    
    /*
     * This method returns the sum of all values in the spreadsheet 
     */
    public static double sumTotal() {
        double sum = 0; //initialize sum value
        for(int i = 0; i < spreadsheet.length; i++){ //loop through each column in the spreadsheet
            sum += sumRow(i); //add the sum of each row
        }
        return sum;
    }
    
    /*
     * This method returns the sum of all values in the user input target row
     */
    public static double sumRow(int targetRow) {
        double sum = 0; //initialize sum value
        for(int i = 0; i < size; i++){ //loop through the target row
            sum += spreadsheet[targetRow][i]; //add each number in the target row
        }
        return sum; 
    }
    
    /*
     * This method returns the sum of all values in the user input target col
     */
    public static double sumCol(int targetCol) {
        double sum = 0; //initialize sum value
        for(int i = 0; i < spreadsheet.length; i++){ //loop through the target column
            sum += spreadsheet[i][targetCol]; //add each number in the target col
        }
        return sum;
    }
    /*
     * This method returns the average of all values in the user input target row
     */
    public static double rowAve(int targetRow) {
        return (double)(sumRow(targetRow)/spreadsheet.length); //return the sum of the row divided by the row's length
    }
    
    /*
     * This method returns the average of all values in the user input target column
     */
    public static double colAve(int targetCol) {
        return (double)(sumCol(targetCol)/spreadsheet.length); //return the sum of the colum divided by the column's length
    }
    
    /*
     * This method returns the average of all values in the spreadsheet
     */
    public static double average() {
        return sumTotal()/(spreadsheet.length * spreadsheet.length); //retrun the total sum of numbers divided by the total number of values
    }
    
    /*
     * This method will quit the program when the user inputs 8
     */
    public static void quit() {
        //exit the program
        System.out.println("Good bye!");
        System.exit(0);
    }

    /*
     * This method loops through the entire spreadsheet and prints out each value
     */
    public static void printSpreadsheet(){
        //loop through the spreadsheet and print out each value at y,x
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                System.out.print("|");
                System.out.printf("%.2f",  spreadsheet[y][x]);
                System.out.print("|");
            }
            System.out.println();
        }
    }
}

