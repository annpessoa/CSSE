/**
 * This class serves as a driver for the FindFile class. Calls the
 * directorySearch method in FindFile to find the max number of files to find.
 * Catches any FileExceptions and prints out a message. Prints to the console
 * all locations in which the target file was found. If the file was not found,
 * then a message is printed onto the console stating that it was not found.
 * 
 * @author Anneliese Pessoa
 *
 */
public class Driver {

	/*
	 * Main method that tests the FindFile class.
	 */
	public static void main(String[] args) {
		int max = 2; // change maximum number of files to find here
		FindFile fileSearch = new FindFile(max); // create new FindFile object
		// change fileName and filePath with these variables
		String fileName = "foo.java"; // variable to store target file name
		String filePath = "C:"; // variable to store the beginning file path

		try {
			// call method to search for the target file
			fileSearch.directorySearch(fileName, filePath);
		} catch (FileException e) {
			// catch any runtime exceptions and print onto the console
			System.err.println(e.getMessage());
		}

		// print results
		System.out.println();
		// specify what file the class is searching for
		System.out.println("Looking for: " + fileName);
		// go through each instance in which the file was found
		for (String name : fileSearch.getFiles(0)) {
			System.out.println("Found at " + name); // print out each file path
		}
		// test if any files were found
		if (fileSearch.getFiles(0).length == 0) {
			// print to the console that the file was not found
			System.out.println("File not found");
		}
	}
}
