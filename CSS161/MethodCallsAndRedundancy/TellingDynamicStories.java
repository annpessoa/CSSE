/**
 * Write a description of class TellingDynamicStories here.
 * 
 * @Anneliese Pessoa (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;

public class TellingDynamicStories
{
     public static void main (String[] args){
         //create variables
         String title = "";
         String content = "";
         String remainder = "";
         
         Scanner scanner = new Scanner(System.in);
         //prompt user for the Title
         System.out.println("Please enter the Title of the Story: ");
         title = scanner.nextLine();
         //prompt user for the content
         System.out.println("Please enter the content of the Story: ");
         content = scanner.nextLine();
         //prompt user for the Remainder
         System.out.println("Please enter the Remainder of the Story: ");
         remainder = scanner.nextLine();
         //space between user input and story
         System.out.println();
         //call tellDynamicStory to print the new story
         tellDynamicStory(title, content, remainder);
    }
    
    public static void intro(){
        System.out.println("A long time ago,");
        System.out.println("In a galaxy far, far away...");
    }
    
    public static void middle(){
        System.out.println("There was much strife and unrest.");
    }
    
    public static void outro(){
        System.out.println("<camera pans downward past stars as the string section enters>");
    }
    
    public static void tellDynamicStory(String title, String content, String remainder){
        System.out.println(title); //print user input Title
        intro(); //print intro
        System.out.println(content); //print user input content
        middle(); //print middle
        System.out.println(remainder); //print user input remainder
        outro(); //print outro
        
    }
    
}
