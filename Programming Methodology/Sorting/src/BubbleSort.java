/**
 * This class is meant to sort an array of integers using the Bubble Sort
 * algorithm. This method of sorting will traverse the array from the first
 * index to the index at position array.length - 1. The sort method will compare
 * two elements and swap them if the first element is greater than the second
 * element and so forth.
 * 
 * @author Anneliese Pessoa
 *
 */
public class BubbleSort extends Sort {

	/*
	 * This method will override the superclass sort method by using the bubble
	 * sort algorithm.
	 */
	@Override
	public void sort(int[] data, int first, int last) {
		int length = data.length; // set length integer
		for (int i = 0; i < length; i++) { // loop through the array
			// loop through unsorted elements
			for (int j = 0; j < length - 1 - i; j++) {
				incrementComparisons(); // increment the amount of comparisons
				if (data[j] > data[j + 1]) { // compare two adjacent elements
					// swap if the first element is larger than the second
					swap(data, j, j + 1); // swap the two elements
				}
			}

		}

	}

	/*
	 * Overriding the swap method isn't necessary if you build a swap method
	 * inside of the Sort superclass. This method can be used in both the
	 * BubbleSort and the QuickSort classes.
	 */
	/*
	 * @Override 
	 * public void swap(int[] data, int idx1, int idx2) { 
	 * 	int temp = data[idx1]; 
	 * 	data[idx1] = data[idx2]; 
	 * 	data[idx2] = temp; 
	 * }
	 */

}
