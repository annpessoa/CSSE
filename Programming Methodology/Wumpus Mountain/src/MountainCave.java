import java.util.ArrayList;
/*
 * 
 * MountainCave.java
 *   This class is built for this exercise only, and has some serious
 *   flaws "by design" just so we could traverse the tree directly from our WumpusHunter code
 *   For a better design, we would have separate classes for the tree and internal nodes  
 *   (rather than packing them together here), and we'd never return private aliases for our nodes
 *   And the list goes on...
 * 
 */

public class MountainCave {
	//node structure pointers
	private MountainCave parent = null;
	private ArrayList<MountainCave> children = new ArrayList<MountainCave>();
	//end node
	
	//MountainCave data items
	private String caveMessage = null;
	private String caveName = null;
	
	private boolean isAdjacentToScales=false;
	private boolean hasWumpusScales=false;
	//end MountainCave
	
	
	public void setParent(MountainCave theParent) {
		parent = theParent;
		parent.addChild(this);
	}
	
	public MountainCave getParent() {
		return parent;
	}
	
	public boolean hasParent() {
		//return (parent == null);
		
		if(parent == null) {
			return false;
		}
		return true;
	}
	public String getCaveName() {
		return caveName;
	}
	
	public ArrayList getChildren() {
		return children;
	}
	
	public void addChild(MountainCave next) {
		if( next != null ) {
			children.add(next);
		}
	}
	
	public String getCaveMessage() {
		return caveMessage;
	}

	public void setCaveMessage(String caveName) {
		this.caveName = caveName;
	}

	public boolean hasScales() {
		return hasWumpusScales;
	}

	public void setHasScales(boolean hasWumpusScales) {
		this.hasWumpusScales = hasWumpusScales;
	}

	public boolean isAdjacentToScales() {
		return isAdjacentToScales;
	}

	public void setAdjacentToScales(boolean adjacentToScales) {
		this.isAdjacentToScales = adjacentToScales;
	}
	

	public MountainCave(String name){
		this(name,null);
	}	
	
	public MountainCave(MountainCave theParent, String name, String message) {
		this(name, message);
		if( theParent != null) {
			parent = theParent;
			parent.addChild(this);
		}
	}
	
	public MountainCave(String name, String message) {
		caveName = name;
		caveMessage = message;
	}

}