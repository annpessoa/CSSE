/**
 * This class is meant to sort an array of integers using the QuickSort
 * algorithm. This algorithm picks a pivot point and all elements that are
 * smaller than the pivot are placed on one side of the array and elements
 * larger than the pivot point are placed on the other side of the array. Each
 * side is recursively sorted by applying the quick sort and then the sides are
 * combined into one sorted array.
 * 
 * @author Anneliese Pessoa
 *
 */
public class QuickSort extends Sort {

	/*
	 * This method will override the superclass sort method by using the
	 * QuickSort algorithm.
	 */
	@Override
	public void sort(int[] data, int first, int last) {
		if (first < last) { // compare first and last elements
			int pivot = selectPivot(data, first, last); // create a pivot point
			sort(data, first, pivot - 1); // sort data left of the pivot
			sort(data, pivot + 1, last); // sort data right of the pivot
		}

	}

	/*
	 * This method selects the pivot point
	 */
	public int selectPivot(int[] data, int low, int high) {
		int pivot = data[high]; // set pivot at end element
		int start = low - 1; // sets the start point
		// traverse the array
		for (int j = low; j <= high - 1; j++) {
			incrementComparisons(); // increment the amount of comparisons
			// test if element is smaller or equal to pivot
			if (data[j] <= pivot) {
				incrementComparisons();// increment the amount of comparisons
				start++; // increment start value
				swap(data, start, j); // swap the two elements
			}
		}
		swap(data, start + 1, high); //swap the last two elements
		return start + 1; 
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
