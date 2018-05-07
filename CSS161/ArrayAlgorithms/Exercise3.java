//CSS 161 Arrays
//  Skeleton file for use with the well-ordering principle of integers, such as: 
//  Which is the least element? The greatest?  What is the range?
//
//  Skeleton Level: Weak.  
//      You need to define functions below to compile this driver.
//      You also need to uncomment some lines in main to invoke the functions
//      you've defined.  Finally, you'll need to add extra lines to main
//      to call the findMax() and findRange() functions.  Be sure you call
//      every function twice; once for each array defined in main below.
//
// edited by Anneliese Pessoa

public class Exercise3 {

    public static void main(String[] args) {
        int[] data = {50,100,20,45};
        int[] data2 = {-4,-1,-3,-10,-2};

        System.out.println("Min element in data is: " + findMin(data));
        System.out.println("Min element data2 is: " + findMin(data2));

        System.out.println("Max element in data is: " + findMax(data));
        System.out.println("Max element data2 is: " + findMax(data2));

        System.out.println("Range of data is: " + findRange(data));
        System.out.println("Range of data2 is: " + findRange(data2));
    }

    //todo:
    public static int findMin(int[] input){
        int min = (int)Double.POSITIVE_INFINITY;
        //goes through each int in the array input
        for(int i = 0; i < input.length; i++){
            //if the number at index i is less than the min, input[i] is now the min
            if(input[i] < min){
                min = input[i];
            }
        }
        return min;
    }

    //todo:
    public static int findMax(int[] input){
        int max = (int)Double.NEGATIVE_INFINITY;
        //goes through each int in the array input
        for(int i = 0; i < input.length; i++){
            //if the number at index i is greater than the max, input[i] is now the max
            if(input[i] > max){
                max = input[i];
            }
        }
        return max;
    }

    //todo:
    public static int findRange(int[] input){  //must reuse findMin() and findMax()
        //subtract the max number from the min number to get the range of numbers in the array
        return (int)Math.abs(findMax(input) - findMin(input));
    }
    
}