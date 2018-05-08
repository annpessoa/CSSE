//CSS 161 Arrays
//  Skeleton file for use with basic list processing, such as: 
//  Shift elements left to make some room for a new element, or
//  Shift elements right to overwrite/remove a target element.
//  Optional challenge extends shifts to include rotates, but these
//  should reuse arrayShiftLeft() and right(), so do those first.
//
//  Examples:
//  shifing left    ==  {3,9,2,0,0,0} -> {9,2,0,0,0,0}
//  shifing right   ==  {9,2,0,0,0,0} -> {9,9,2,0,0,0}
//  rotate left     ==  {1,2,3} -> {2,3,1}
//  rotate right    ==  {1,2,3} -> {3,1,2}
//
//
//  Skeleton Level: Minimal.  
//      You need to call each function below from main; see comment stubs in main to start.
//      You need to define each function below; see comment stubs to get started.
//      
//  edited by Anneliese Pessoa
import java.util.Arrays;

public class Exercise4 {

    public static void main(String[] args) {
        int[] data = {3,9,2,0,0,0};  //a six-element array with only three values so far
        int[] data2 = {1,5,3,2,9,6};  //a six-element array filled with values.

        arrayShiftLeft(data,0); //shift numbers one spot to the left in data
        arrayShiftLeft(data2,0);//shift numbers one spot to the left in data2

       
        //print out each element in data here
        printArray(data);
        System.out.println();
        //print out each element in data2 here
        printArray(data2);
        System.out.println();
        
        //TODO: following the pattern above for arrayShiftLeft, 
        arrayShiftRight(data,0); //shift numbers one spot to the right in data
        arrayShiftRight(data2,0);//shift numbers one spot to the right in data2
        
        //print out each element in data here 
        printArray(data);
        System.out.println();
        //print out each element in data2 here
        printArray(data2);
        System.out.println();
        
        //test for rotateRight and rotateLeft
        int[] data3 = {1,2,3};
        int[] data4 = {1,2,3};
        
        rotateRight(data3); //rotate data3 to the right
        rotateLeft(data4); //rotate data4 to the left
        
        //print out each element in data3 here
        printArray(data3);
        System.out.println();
        //print out each element in data4 here
        printArray(data4);
        System.out.println();
        
        
    }

    public static void arrayShiftLeft(int[] input, int startIndex) {
        //shifts each int in the input array one index to the left from the start index
        for(int i = startIndex; i < input.length-1; i++) {  
            input[i] = input[i+1];
        }
    }

    public static void arrayShiftRight(int[] input, int startIndex) {
        //shifts each int in the input array one index to the right from the start index
        for(int i = input.length-1; i > startIndex; i--) {  
            input[i] = input[i-1];
        }
    }
    
    //optional - for extra challenge - 
    public static void rotateLeft(int[] input) { 
        int temp = input[0]; //create a temporary variable to store the number at index 0
        arrayShiftLeft(input, 0); //shift the array to the left
        input[input.length-1] = temp; //replace the final number in the array with the temporary variable
    }
    //hint: use arrayShiftLeft inside rotateLeft

    public static void rotateRight(int[] input) { 
        int temp = input[input.length-1];//create a temporary variable to store the last number in the input array
        arrayShiftRight(input, 0);//shift the array to the right
        input[0] = temp; //replace the first number in the array with the temporary variable
    }
    //hint: use arrayShiftRight inside rotateRight  
    
    //prints each element in the array
    public static void printArray(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + ",");
        }
    }
}