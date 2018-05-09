import java.util.ArrayList;
import java.util.Stack;

/**
 * This class was made to create a functional WumpusHunter by overriding the
 * getName and startAt methods. The getName function simply returns the
 * WumpusHunter's name while the startAt function will take the WumpusHunter
 * through the mountain until a treasure is found.
 * 
 * @author Anneliese Pessoa
 *
 */
public class StackBasedWumpusHunter extends WumpusHunter {
	// this is the name of the WumpusHunter
	private String name = "Cool Hunter Dude";
	// this creates a stack of MountainCave objects
	private Stack<MountainCave> data = new Stack<MountainCave>();

	/*
	 * This method overrides the getName method that is contained within the
	 * WumpusHunter superclass. This simply returns the name of the
	 * WumpusHunter.
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * This method will take the WumpusHunter through the mountain in search for
	 * the treasure. The method will go through the mountain until the treasure
	 * has been found. While the treasure has not been found, the method will
	 * add each room that has been entered into the action log and check if the
	 * scales are in an adjacent cave and also add it to the action log. If the
	 * scales are found, then the method will print out the path to the scales
	 * after announcing that the scales have been found. At that point, the
	 * boolean value that determines whether the while loop will continue will
	 * change and the loop stops.
	 */
	@Override
	public void startAt(MountainCave root) {
		// set the current MountainCave beginning at the root of the tree
		MountainCave current = root;
		// create boolean value to track whether or not the treasure has been
		// found
		boolean foundTreasure = false;
		while (!foundTreasure) {
			// add to the action log which caves the hunter is entering
			actionLog += "Entering the " + current.getCaveName() + "\n";
			if (current.isAdjacentToScales()) {
				// add to the action log if the hunter is adjacent a cave
				// that contains the scales
				actionLog += "We've neared the scales!\n";
			}
			if (current.hasScales()) {
				// test if the cave has scales inside of it
				foundTreasure = true; // set boolean to true to end the loop
				// add to the action log announcing the scales have been found
				actionLog += "We've found the scales!\n\n";
				// begin to print the path that the hunter took to find scales
				actionLog += "...The path is...\n\nStart at the Mountain Top\n";
				// create a new stack that will hold all cave names
				Stack<String> path = new Stack<String>();
				while (current != root) { // loop through the tree
					// push cave name onto the stack
					path.push(current.getCaveName());
					// move up the tree by getting the parent cave
					current = current.getParent();
				}
				while (!path.isEmpty()) { // loop through path stack
					// add to the action log each room that was visited
					actionLog += "and then visit the " + path.pop() + "\n";
				}
			} else {
				// create an ArrayList of currentChildren
				ArrayList<MountainCave> currentChildren = current.getChildren();
				// go through each cave in the ArrayList
				for (MountainCave cave : currentChildren) {
					data.push(cave); // push each cave onto the data stack
				}
				// set the current cave to the last element on the stack
				current = data.pop();
			}

		}
	}

}
