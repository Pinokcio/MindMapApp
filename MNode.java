import java.util.ArrayList;

public class MNode {
	static ArrayList<MNode> recentParent = new ArrayList<MNode>();
	static ArrayList<MNode> nodeArray = new ArrayList<MNode>();
	
	private int x, y;
	private int width = 100, height = 60;
	private int centerX, centerY;
	private String text;
	private String color = "07AVW67";
	private int rank = 0;
	private MNode parent = null;
	private MNode firstChild = null;
	private int cntChildren = 0;
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
			setX(Draw.mindPaneWidth-50);	//너비의 반절 빼기
			setY(Draw.mindPaneHeight-30);	//높이의 반절 빼기
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
	public int getCenterX() {
		return this.centerX;
	}
	public int getCenterY() {
		return this.centerY;
	}
	public String getText() {
		return this.text;
	}
	public String getColor() {
		return this.color;
	}
	public int getRank() {
		return this.rank;
	}
	public MNode getParent() {
		return this.parent;
	}
	public MNode getFirstChild() {
		return this.firstChild;
	}
	public int getCntChildren() {
		countChildren();
		return this.cntChildren;
	}
	public MNode getNextSibling() {
		return this.nextSibling;
	}
	/*
	public void setPosition() {

	}*/
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setCenterByVal() {
		this.centerX = (int)(this.x - (this.width/2.0));
		this.centerY = (int)(this.y - (this.height/2.0));
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setParent(MNode parent) {
		this.parent = parent;
	}
	public void setNextSibling(MNode nextSibling) {
		this.nextSibling = nextSibling;
	}
	public void setFirstChild(MNode firstChild) {
		this.firstChild = firstChild;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public void linkFirstChild() {
		//지금 노드의 rank-1 == 마지막arr원소의 rank 
		MNode lastElem = nodeArray.get(nodeArray.size() - 1);
		if(this.rank - 1 == lastElem.rank)
			lastElem.firstChild = this;
	}
	public void countChildren() {
		if(this.firstChild != null) {
			MNode c = this.firstChild;
			++this.cntChildren;
			while(c.nextSibling != null) {
				c = c.nextSibling;
				++this.cntChildren;
			}
		}	
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
	//테스트용 출력함수
	public static void print() {
		for(int i = 0; i < nodeArray.size(); i++) {
			System.out.println(nodeArray.get(i).getRank()+"순위 "+nodeArray.get(i).getText());
			if(nodeArray.get(i).getParent() != null)
				System.out.println("부모 : "+nodeArray.get(i).getParent().getText());
			if(nodeArray.get(i).getFirstChild() != null)
				System.out.println("첫자식 : "+nodeArray.get(i).getFirstChild().getText());
			if(nodeArray.get(i).getNextSibling() != null)
				System.out.println("다음형제 : "+nodeArray.get(i).getNextSibling().getText());
			System.out.println("위치 : "+nodeArray.get(i).getX()+", "+nodeArray.get(i).getY());
			System.out.println("-------------");
		}
	}
	
}
