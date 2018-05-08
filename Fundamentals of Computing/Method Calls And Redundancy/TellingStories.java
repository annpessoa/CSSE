
/**
 * Write a description of class TellingStories here.
 * 
 * @Anneliese Pessoa (your name) 
 * @version (a version number or a date)
 */
public class TellingStories
{
    public static void main (String[] args){
        tellStoryOne(); //call the method to print story one
        System.out.println(); //space between the two stories
        tellStoryTwo();  //call the method to print story two
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
    
    public static void tellStoryOne(){
        System.out.println("Episode 1 : The Phantom Menace");
        intro(); //calls intro
        System.out.println("Turmoil has engulfed the Galactic Republic.");
        middle(); //calls middle
        System.out.println("The Supreme Chancellor has secretly dispatched two Jedi Knights to settle the conflict.");
        outro(); //calls outro
    }
    
    public static void tellStoryTwo(){
        System.out.println("Episode 7 : To Be Announced");
        intro();  //calls intro
        System.out.println("Han Solo discovers he can use both the Force and a whip, making him better than Luke.");
        middle(); //calls middle
        System.out.println("Can Solo pick up the pieces of the fragmented Republic and restore the Jedi Council?");
        outro(); //calls outro
    }
    
}
