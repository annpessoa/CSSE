import java.util.Scanner;
import java.io.*;
/**
 * Write a description of class Diamonds here.
 * 
 * @author Anneliese Pessoa 
 * @version (a version number or a date)
 */

public class Diamonds
{
    public static void main(String args[])throws IOException{
        //read configurations of the symbol from a file
        Scanner sc = new Scanner(new File("config.txt"));
        int size = sc.nextInt(); //get the size of the logo
        int numLogos = sc.nextInt(); //get the number of logos
        char char1 = sc.next().charAt(0); //get the first character used to draw the logo
        char char2 = sc.next().charAt(0); //get the second character used to draw the logo
        sc.close(); //close the file

        //draw the top of the top diamond
        for(int y = 0; y < size; y++){
            for(int i = 0; i < numLogos; i++){
                //draws each line of the top of the diamond numLogos amount of times
                drawBorder(size, y, char1, char2);
                diamondTop(size, y, char1, char2);
                drawBorder(size, y, char1, char2);
            }
            System.out.println();
        }
        
        //draw the bottom of the top diamond
        for(int y = 0; y < size; y++){
            for(int i = 0; i < numLogos; i++){
                //draws each line of the bottom of the diamond numLogos amount of times
                drawBorder(size, y, char1, char2);
                diamondBottom(size, y, char1, char2);
                drawBorder(size, y, char1, char2);
            }
            System.out.println();
        }

        //draw the bottom part of the logo
        for(int y = 0; y < size; y++){
            for(int i = 0; i < numLogos; i++){
                //draws each line of the bottom rhombuses numLogos amount of times
                rhombus1(size, y, char1, char2);
                rhombus2(size, y, char1, char2);
            }
            System.out.println();
        }
    }

    public static void drawLine(int numChars, char letter){
        //prints a line of the given character
        for(int i = 0; i < numChars; i++){
            System.out.print(letter);
        }
    }

    public static void triangle1(int size, int y, char char1, char char2){
        drawLine(size-y, char1); //draws a triangle with char1 that has a longer side on top and decreases vertically
        drawLine(y, char2); //draws a triangle next to the first triangle with char2 that has the point on top
    }

    public static void triangle2(int size, int y, char char1, char char2){
        drawLine(y, char1); //draws a triangle with char1 with the point on top 
        drawLine(size-y, char2); //draws a triangle next to the first triangle with char2 with the longer side on top
    }

    public static void diamondTop(int size, int y, char char1, char char2){
        triangle1(size, y, char2, char1); //draws the left half of the top of the diamond
        triangle2(size, y, char1, char2); //draws the right half of the top of the diamond
    }

    public static void diamondBottom(int size, int y, char char1, char char2){
        triangle2(size, y, char2, char1); //draws the left half of the bottom of the diamond
        triangle1(size, y, char1, char2); //draws the right half of the bottom of the diamond
    }

    public static void rhombus1(int size, int y, char char1, char char2){
        //draws a rhombus that leans to the right
        triangle1(size, y, char2, char1); //first triangle of the rhombus
        triangle1(size, y, char1, char1); //square in the middle of the rhombus
        triangle1(size, y, char1, char2); //second triangle of the rhombus
    }

    public static void rhombus2(int size, int y, char char1, char char2){
        //draws a rhombus that leans to the left
        triangle2(size, y, char2, char1); //first triangle of the rhombus
        triangle2(size, y, char1, char1); //square int he middle of the rhombus
        triangle2(size, y, char1, char2); // second triangle of the rhombus
    }

    public static void drawBorder(int size, int y, char char1, char char2){
        //creates a box for the borders on the sides of the diamonds
        triangle1(size*2, y, char2, char2);
    }
}