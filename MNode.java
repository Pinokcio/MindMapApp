import java.util.ArrayList;

public class MNode {
	static ArrayList<MNode> recentParent = new ArrayList<MNode>();
	static ArrayList<MNode> nodeArray = new ArrayList<MNode>();
	
	private int x, y;
	private int width, height;
	private String text;
	private String color;
	private int rank = 0;
	private MNode parent = null;
	private MNode firstChild = null;
	private MNode nextSibling = null;
	   
	public MNode (String text) {
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '\t') {
		            ++rank;
			}
		    else
		    	break;
		   	}
		    if(rank == 0) {
		    	parent = null;
		      }
		    else {
		    	parent = recentParent.get(rank-1);
		    	linkFirstChild();
		    	linkNextSibling();
		    }
		    recentParent.add(rank, this);
		    this.text = text.substring(rank);
		    
		    nodeArray.add(this);
		   	}
	
	public void randomPositon () {
		
	}
	public static void splitText() {
		String fullText = Frame.textField.getText();
		String word[] = fullText.split("\n");
		
		for(int i = 0; i < word.length; i++) {
			MNode node = new MNode(word[i]);
			nodeArray.add(i, node);
			}
		}
	public void linkFirstChild() {
		//지금 노드의 rank-1 == 마지막arr원소의 rank 
		MNode lastElem = nodeArray.get(nodeArray.size() - 1);
		if(this.rank - 1 == lastElem.rank)
			lastElem.firstChild = this;
	}
	public void linkNextSibling() {
		//MNode lastElem = nodeArray.get(nodeArray.size() - 1);
		for(int i = nodeArray.size() - 1; i > 0; i--) {
			if(nodeArray.get(i).rank == this.rank) {
				nodeArray.get(i).nextSibling = this;
				break;
			}
		}
	}
	public MNode getFirstChild() {
		return this.firstChild;
	}
	public MNode getNextSibling() {
		return this.nextSibling;
	}
	public int getRank() {
		return this.rank;
		}
	public MNode getParent() {
			return this.parent;
	}
	public String getText() {
		return this.text;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public String getColor() {
		return color;
	}	
}
