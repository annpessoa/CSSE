import java.util.Scanner;
import java.io.*;
/*
 * The purpose of this program is to count the number of occurrences of each unique fraction in a
 * text file. This program will read through the file, create an array of strings holding each fraction,
 * and then split the fraction's numerator and denominator and store it in a 2D array. Equality is tested
 * by comparing the numerators and denominators of two or more fractions.
 * 
 * @ Anneliese Pessoa 
 * @ 4/1/2016
 * 
 */
public class FractionsV1 {
	private String[] inputFractions = new String[100]; //String array to store all fractions
	private int numElements = 0; //integer to keep track of total number of fractions in the file
	
	/*
	 * This main method will read from the file and split the fractions into numerators and denominators.
	 * The numOccurences method is called in the end to print to the console the number of times
	 * each fraction occurs in the file.
	 */
	public static void main(String[] args) throws IOException{
		//open the file that contains the fractions
		Scanner inputFile = new Scanner(new File("fractions.txt")); 
		FractionsV1 frac = new FractionsV1();
		
		//add all fractions onto the String array
		while(inputFile.hasNext()){
			frac.add(inputFile.nextLine());
		}
		
		inputFile.close(); //close the file
		
		//create a 2D array to store numerators and denominators separately
		int[][] fractions = new int[frac.numElements][2]; 
		//loops through all of the fraction strings and adds them to the 2D array as integers
		for(int i = 0; i < frac.numElements; i++){
			//temporary string array to hold a single fraction's numerator and denominator
			String[] temp = frac.inputFractions[i].split("/"); 
			//store the numerator in the 2D array
			fractions[i][0] = Integer.parseInt(temp[0]); 
			//store the denominator in the 2D array
			fractions[i][1] = Integer.parseInt(temp[1]); 
		}
		
		//boolean array to keep track of fractions that have already been printed onto the console
		boolean[] isCounted = new boolean[frac.numElements]; 
		//print out the number of times each unique fraction is found in the file
		for(int y = 0; y < fractions.length; y++){
			//only print if the number has not been counted already
			if(!isCounted[y]){ 
			System.out.println(frac.inputFractions[y] + " has a count of " + numOccurrences(fractions, fractions[y][0], fractions[y][1], isCounted));
			}
		}
	}
	
	/*
	 * This function adds each fraction into the inputFractions array.
	 */
	public void add(String fraction){
		inputFractions[numElements] = fraction; //store the string in the array
		numElements = numElements + 1; //increment the number of elements in the file
	
	}
	
	/*
	 * This function counts the number of occurences of a fraction in the file and returns it as an integer.
	 */
	public static int numOccurrences(int[][] fractions, int num, int denom, boolean[] isCounted){
		int count = 0; //initialize count integer
		//loop through each fraction and compare it to the current fraction
		for(int i = 0; i < fractions.length; i++ ){
			//increment the count int if the numerator and denominator of the current fraction and 
			//another fraction in the list
			if(fractions[i][0] == num && fractions[i][1] == denom){
				count++; //increment count variable
				isCounted[i] = true; //the fraction has been counted
			}
		}
		return count;
	}
	
	
/*
 * Questions:
 * 
 * 1. Yes, it is possible to solve this problem without using arrays however it may be more time consuming.
 *    The least amount of variables needed to solve this without arrays would be the number of each
 *    numerator and each denominator.
 *    
 * 2. It is possible to solve this by using only one array. The data type of this array would be String and
 * 	  the fractions would be compared by using the equals function in the String class.
 * 
 * 3. It is also possible to nest classes like it is possible to nest loops. One example of this would be if
 *    a class was only used by one other class, it can be nested into the bigger class. Inner classes will also
 *    have access to variables in the larger class.
 *    
 * 4. Another way to solve the reduction problem would be to divide both the numerator and denominator by 2 if 
 *    both are even until they can no longer be divided further. The fraction can also be divided by other numbers
 *    but it would work best with prime numbers. This could take longer than Euclid's GCD algorithm.
 */
	

}
