
/*
 * This class is meant to run the game of Deal or No Deal. The rules of the game are similar to the original game show
 * however the final choice the player makes is not to choose between their personal case and the case left on the board
 * but to choose between their personal case and the final offer the banker makes. 
 * 
 * @ Anneliese Pessoa 
 * @ 3/17/2016
 */
public class DealOrNoDealRunner{
    /*
     * This main method runs the game of Deal or No Deal
     */
    public static void main(String[] args){
        DealOrNoDeal game = new DealOrNoDeal(); //instantiate the Deal or No Deal Game
        game.playGame(); //play the game
    }
}
