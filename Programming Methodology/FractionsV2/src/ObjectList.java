/*
 * This program is meant to search through a text file and count up the number of unique fractions.
 * In this version, all fractions in the text file will be reduced in order to test equality.
 * To achieve this, the program includes multiple object lists that store Fractions, FractionCounters,
 * and a 2D array that stores individual numerators and denominators. The program reads the file, 
 * copies all values into the 2D array, copies these values into an ObjectList of Fractions, creates
 * another ObjectList to store unique fractions only, and counts all unique and equal values in a 
 * FractionCounter list. The list of unique fractions is then printed out along with the number of
 * occurrences in the file. 
 * 
 * @ Anneliese Pessoa 
 * @ 4/10/2016
 * 
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ObjectList {

	private Object[] objectList = new Object[2];
	private int numElements = 0;

	public static void main(String[] args) throws IOException {
		Scanner inputFile = new Scanner(new File("fractions.txt")); //create a scanner to read the file
		int fileSize = getFileSize(); //get the file size 

		int[][] fracArray = new int[2][fileSize]; //array to store numerator and denominator values
		fracArray = getFracArray(inputFile, fileSize);

		ObjectList fracList = new ObjectList(fileSize); //array of Fraction objects
		fracList = getFracList(fracArray);

		ObjectList singleFracArray = getSingleFracArray(fracList); //array of unique fraction objects

		ObjectList fractionCounter = new ObjectList(singleFracArray.size()); //list of FractionCounter objects
		fractionCounter = getCount(fracList, singleFracArray); //count all unique objects in the list
		
		//print out results from counting unique fractions
		for(int i = 0; i < fractionCounter.size(); i++){
			System.out.println(singleFracArray.get(i).toString() + fractionCounter.get(i).toString());
		}
		inputFile.close();
	}
	
	/*
	 * Default constructor.
	 */
	public ObjectList() {
	}

	/*
	 * Constructor that will initialize the object list with the given size.
	 */
	public ObjectList(int size) {
		objectList = new Object[size];
	}

	/*
	 * This method will return the index of the given target.
	 */
	public static int indexOf(ObjectList[] input, Object target) {
		int retVal = -1;
		for (int i = 0; i < input.length; i++) {
			if (input[i] != null && input[i].equals(target)) {
				retVal = i;
			}
		}
		return retVal;
	}

	/*
	 * This method will return whether or not the target object exists in the
	 * given list of Objects.
	 */
	public boolean contains(ObjectList list, Object target) {
		for (int i = 0; i < numElements; i++) { // loops through array searches for target object
			if (list.objectList[i].equals(target)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * This method will add the given object into the array of objects and
	 * double the size of the array if there is not enough space to add more
	 * objects.
	 */
	public void add(Object thatObject) {
		if (this.size() == objectList.length) {
			doubleSize();
		}
		objectList[numElements++] = thatObject;
	}

	/*
	 * This method creates an array double in size of the original array and
	 * copies all elements from the original array into the new array.
	 */
	private void doubleSize() {
		Object[] temp = new Object[this.size() * 2]; // temporary array to hold values before copying
		for (int i = 0; i < this.size(); i++) { // loop through the array and copy all values into temp
			temp[i] = objectList[i];
		}
		objectList = new Object[this.size() * 2]; // double the size of the original array
		objectList = temp; // copy all values into objectList
	}

	/*
	 * This method returns the size of the array of Objects.
	 */
	public int size() {
		return numElements;
	}

	/*
	 * This method returns the element at the given index.
	 */
	public Object get(int index) {
		return objectList[index];
	}

	/*
	 * This method returns a string of all values in the ObjectList.
	 */
	@Override
	public String toString() {
		String retVal = "";
		for (int i = 0; i < this.size(); i++) {
			if (objectList[i] != null) {
				retVal += objectList[i].toString() + "\n";
			}
		}
		return retVal;
	}

	/*
	 * This method will count the number of occurrences of each unique
	 *  fraction within the ObjectList.
	 */
	private static ObjectList getCount(ObjectList fracList, ObjectList singleFracArray) {
		ObjectList fracCount = new ObjectList(); //create a count array
		for (int i = 0; i < singleFracArray.size(); i++) { //loop through unique fractions
			if (singleFracArray.get(i) != null) { //only work with the fraction if a fraction is present
				//create a new FractionCounter object from the unique fraction list
				FractionCounter count = new FractionCounter((Fraction)singleFracArray.get(i)); 
				for (int j = 0; j < fracList.size(); j++) { //loop through original list of fractions
					//compare original list of fractions to unique list of fractions
					count.compareAndIncrement((Fraction) fracList.get(j)); 
				}
				fracCount.add(count); //add to the count array
			}
		}
		return fracCount;
	}

	/*
	 * This method creates a new list of unique fractions so that when the
	 * fractions are counted they will not be printed more than once.
	 */
	private static ObjectList getSingleFracArray(ObjectList fracList) {
		ObjectList singleFracArray = new ObjectList(); //create new ObjectList of fractions
		for (int i = 0; i < fracList.size(); i++) { //loop through the original fraction list
			boolean notUnique = false; //boolean to track whether the fraction has been added or not
			Fraction checkFrac = new Fraction((Fraction) fracList.get(i)); //get the fraction at index i
			for (int j = 0; j < singleFracArray.size(); j++) { //loop through the list to check for duplicates
				Fraction checkAgainst = new Fraction((Fraction) (singleFracArray.get(j))); //get the fraction at index j
				if (checkFrac.equals(checkAgainst)) { //test if the fractions are duplicates
					notUnique = true;
					break;
				}
			}
			if (!notUnique) { //only add the fraction to the list once
				singleFracArray.add(fracList.get(i));
			}
		}
		return singleFracArray;
	}

	/*
	 * This method generates an Object list of Fractions from a 2D array of
	 * integers.
	 */
	private static ObjectList getFracList(int[][] fracArray) {
		ObjectList list = new ObjectList(); //create a new object list to store fractions
		for (int i = 0; i < fracArray[0].length; i++) { //loop through the array
			Fraction frac = new Fraction(); //create a temporary fraction object
			frac.setNumerator(fracArray[0][i]); //set the numerator
			frac.setDenominator(fracArray[1][i]); //set the denominator
			frac.reduce(); //reduce the fraction
			list.add(frac); //add the reduced fraction to the list
		}
		return list;
	}

	/*
	 * This method creates a 2D array containing the numerator and denominator
	 * of each fraction.
	 */
	private static int[][] getFracArray(Scanner inputFile, int fileSize) {
		String current = "";
		int[][] retArray = new int[2][fileSize];
		int index = 0;
		while (inputFile.hasNextLine()) {
			current = inputFile.nextLine(); // get next fraction in the list
			String[] temp = new String[2];
			temp = current.split("/"); // temp variable to hold the num and denom of the fraction
			retArray[0][index] = Integer.parseInt(temp[0]); // get the numerator
			retArray[1][index] = Integer.parseInt(temp[1]); // get the denominator
			index++; // increment index
		}
		return retArray;
	}

	/*
	 * This method goes through the file and counts the number of lines in the
	 * file.
	 */
	private static int getFileSize() throws IOException {
		Scanner sc = new Scanner(new File("fractions.txt"));
		int count = 0;
		while (sc.hasNextLine()) {
			count++;
			sc.nextLine();
		}
		sc.close();
		return count;
	}
	
	

}
