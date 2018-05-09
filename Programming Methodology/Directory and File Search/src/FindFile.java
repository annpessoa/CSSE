import java.io.File;

/**
 * This class will take in a max number of files and find a target file in a
 * directory that max number of times. This class will search for the files
 * recursively by going through all files within the starting directory. If
 * another directory is found, the class will search through that directory as
 * well. When a match for target is found, the file is added to the files array
 * and the file paths are stored in a String array. This class searches for the
 * target until the max number is found or there are no more files left in the
 * directory.
 * 
 * @author Anneliese Pessoa
 *
 */
public class FindFile {
	private int max;
	private int count;
	private int index;
	private File[] files;
	private String[] retVal;

	/*
	 * Constructor that sets up the maximum number of files to search for and
	 * sets up the array that will contain all of the found files.
	 */
	public FindFile(int maxFiles) {
		max = maxFiles; // set maximum number of files
		files = new File[max]; // set up the File array
		retVal = new String[max]; //set up String array
	}

	/*
	 * This method will do a linear search through the input directory and
	 * search for a target file.
	 */
	public void directorySearch(String target, String dirName) {
		File dir = new File(dirName); // create a file from the directory name
		if (!dir.isDirectory()) { // make sure the directory name is valid
			// throw an exception if the dirName is not a valid directory
			throw new FileException("Invalid directory!");
		}
		// create a list of files to contain all files in the directory
		File[] listOfFiles = dir.listFiles();
		// loop through all files in the directory
		for (int i = 0; i < listOfFiles.length; i++) {
			if (getCount() > max) {
				// throw an exception if exceeded the max number of files
				throw new FileException("Exceeded max number of files");
			}
			if (count == max) {
				// throw an exception if the max number of files was found
				throw new FileException("Found max number of files");
			}
			if (listOfFiles[i].isFile()) {
				// if the next element is the target, add it to the file array
				if (listOfFiles[i].getName().equals(target)) {
					files[index] = listOfFiles[i].getAbsoluteFile();
					index++;
					count++;
				}
			} else {
				// if the next element is the target, add it to the file array
				if (listOfFiles[i].getName().equals(target)) {
					files[index] = listOfFiles[i].getAbsoluteFile();
					index++;
					count++;
				}
				// call the method again to search through the new directory
				directorySearch(target, listOfFiles[i].toString());
			}
		}
	}

	/*
	 * This method will return the number of matches in the directory for
	 * target.
	 */
	public int getCount() {
		return count;
	}

	/*
	 * This method will return a String array containing the file paths of all
	 * found target files in the files array.
	 */
	public String[] getFiles(int index) {
		// loop through all elements in the files array
		if(index < max){
			if (files[index] != null) {
				// add file paths to the String array if not null
				retVal[index] = files[index].toString();
				return getFiles(++index);
			}
		}
		return retVal;
	}

}
