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
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
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
	public MNode getFirstChild() {
		return this.firstChild;
	}
	public MNode getNextSibling() {
		return this.nextSibling;
	}
	public void linkFirstChild() {
		//���� ����� rank-1 == ������arr������ rank 
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
	public void setPositon() {
		
	}
	//�׽�Ʈ�� ����Լ�
	public static void print() {
		for(int i = 0; i < nodeArray.size(); i++) {
			System.out.println(nodeArray.get(i).getRank()+"���� "+nodeArray.get(i).getText());
			if(nodeArray.get(i).getParent() != null)
				System.out.println("�θ� : "+nodeArray.get(i).getParent().getText());
			if(nodeArray.get(i).getFirstChild() != null)
				System.out.println("ù�ڽ� : "+nodeArray.get(i).getFirstChild().getText());
			if(nodeArray.get(i).getNextSibling() != null)
				System.out.println("�������� : "+nodeArray.get(i).getNextSibling().getText());
			System.out.println("-------------");
		}
	}
	
}
