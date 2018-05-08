//CSS 161
//  Skeleton file for use with basic numerical analysis, such as: 
//  Are numbers even? Odd? How many evens? What is the median of a set?
//
//  Skeleton Level: Medium.  
//		You need to uncomment and fill-in functions below to complete
//		this driver.  You also need to uncomment lines in main to 
//		invoke the functions you've defined.
//
// edited by Anneliese Pessoa

public class Exercise2 {

    public static void main(String[] args) {	
        int[] data = {1,2,3,4,5,6,9};  //note that these are already sorted for you

        System.out.println("Is the first array element even? " + isEven(data[0]));
        System.out.println("Is the third element even? " + isEven(data[0]));

        System.out.println("Number of evens in data: " + numberOfEvens(data));
        System.out.println("Number of odds in data: " + numberOfOdds(data));

        System.out.println("Median value: " + median(data));
    }

    public static boolean isEven(int input) 	  {//build first
        if(input%2 == 0){
            return true;
        }
        return false;
    }

    //todo:
    public static int numberOfEvens(int[] input)   {//must use isEven 
        int numEvens = 0;
        //goes through the array input
        for(int i = 0; i < input.length; i++){
            //uses the isEven function to test if each number is even
            if(isEven(input[i])){
                numEvens++; //increment count variable if an even number is found
            }
        }
        return numEvens;
    }
    //todo:
    public static int numberOfOdds(int[] input) 	  {//must use numberOfEvens()
        //finds number of odd numbers in the array by subtracting the number of evens from the total
        int numOdds = 0;
        numOdds = input.length - numberOfEvens(input);
        return numOdds;
    }
    
    public static double median(int[] input){
        //return median if the number of ints in input is odd
        if(!isEven(input.length)){
            return input[input.length/2];
        }
        //return median if the number of ints in input is even
        return ((double)input[input.length/2] + (double)input[(input.length/2)-1])/2;
    }
}