/**
 * This is my version of a Mountain factory. I followed the structure of the
 * TertiaryMountainFactory and came up with my own room names and descriptions.
 * I used this room name generator to get started with names:
 * http://www.seventhsanctum.com/generate.php?Genname=roomloc
 * 
 * This class will create 16 Mountain caves in addition to the root and treasure
 * caves. 
 * 
 * @author Anneliese Pessoa
 *
 */
public class MyMountainFactory extends MountainFactory {

	/*
	 * This method overrides the getMountainTop method in the MountainFactory
	 * superclass.
	 */
	@Override
	public MountainCave getMountainTop() {
		//this is the tree's root / the top of the mountain
		MountainCave root = new MountainCave("Mountain Top", "The air density here seems to indicate you're not far from the base of the mountain");
		
		//create the leaves of the tree
		MountainCave r4 = new MountainCave(root, "Alcove of Water", "This cave has a lot of water in it. Like, a LOT of water.");
		MountainCave r3 = new MountainCave(root, "Damned Coliseum", "This cave has a large coliseum in it. You're not sure how it fits in here.");
		MountainCave r2 = new MountainCave(root, "Gargoyles Cavern","This cave has a dozen gargoyles in it. They seem to be having some sort of meeting.");
		MountainCave r1 = new MountainCave(root, "Library of Lies", "This cave is just a school library.");

		//more leaves
		MountainCave r11 = new MountainCave(r1, "Mine of Amnesia", "This cave is empty. You forget why you walked inside.");
		MountainCave r12 = new MountainCave(r1, "Office of Statues", "This cave has a lot of statues inside.");
		MountainCave r13 = new MountainCave(r1, "Viper's Pantry","This cave has lots of food inside. Taking the viper's food is not suggested.");
		MountainCave r14 = new MountainCave(r1, "Cave of Illusions", "This cave is covered in optical illusions. It hurts your eyes.");

		//more leaves
		MountainCave r21 = new MountainCave(r2, "Gnome's Hold", "This cave contains many gnomethings you've never seen before.");
		MountainCave r22 = new MountainCave(r2, "Study of the Skeleton", "This cave is the skeleton's study. You've interrupted his study session.");
		MountainCave r23 = new MountainCave(r2, "Blue Asylum", "There is too much blue in this room");
		MountainCave r24 = new MountainCave(r3, "Damned Room of Snow", "It never stops snowing in this cave, even though it is in the middle of the mountain.");

		//surprise! more leaves
		MountainCave r31 = new MountainCave(r3, "Dangerous Cyclopss' Gymnasium", "The Cyclops is playing a game of basketball.");
		MountainCave r32 = new MountainCave(r3, "Library of Dishonor","This cave is another library. You wonder why there are so many libraries in this mountain.");
		MountainCave r33 = new MountainCave(r3, "Ruined Plaza", "It seems as though a monster destroyed the plaza. It was probably the Wumpus or something but who knows.");
		MountainCave r34 = new MountainCave(r4, "Asylum of Rain","You wonder why it's raining in the cave. You also wonder why you have no arrows.");

		//treasure room cave
		MountainCave r331 = new MountainCave(r34, "Treasure Room", "The golden scales are here!");

		//set r34 so that it is adjacent to the Treasure Room
		r34.setAdjacentToScales(true);
		//set the cave so that it has scales in it
		r331.setHasScales(true);

		return root;
	}

}
