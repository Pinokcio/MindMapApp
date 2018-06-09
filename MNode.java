import java.util.ArrayList;
import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MNode extends JLabel{
	static ArrayList<MNode> recentParent = new ArrayList<MNode>();
	static ArrayList<MNode> nodeArray = new ArrayList<MNode>();
	
	private static int errorCnt = 0;
	private int x, y;
	private int width = 60, height = 40;
	private int centerX, centerY;
	private String text;
	private Color color = new Color(-1);
	private int rank = 0;
	private MNode parent = null;
	private MNode firstChild = null;
	private MNode nextSibling = null;
	private JLabel label;
	private boolean isSettedX = false;
	private boolean isSettedY = false;
	
	public MNode (String text, int num) {
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '\t') {
				++rank;
			}
			else
				break;
		}
		if(rank == 0) {
			parent = null;
			setX(Draw.mindPaneWidth-50);	//�ʺ��� ���� ����
			setY(Draw.mindPaneHeight-30);	//������ ���� ����
			if(!recentParent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "TextField " + (num+1) + "��° �ٿ��� ������ �߻��Ͽ����ϴ�.\n���ϴ� RootNode�� �ϳ��� ������ �� �ֽ��ϴ�.", "Ʈ������ �˻��", JOptionPane.ERROR_MESSAGE);
				errorCnt++;
				return;
			}		
		}
		else { 
			if(recentParent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "TextField  " + (num+1) + "��° �ٿ��� ������ �߻��Ͽ����ϴ�.\nRootNode�� �Է����ּ���.", "Ʈ������ �˻��", JOptionPane.ERROR_MESSAGE);
				errorCnt++;
				return;
			}
			if(nodeArray.get(num-1).getRank() < rank && rank - nodeArray.get(num-1).getRank() >  1) {
				JOptionPane.showMessageDialog(null,"TextField " + (num+1) + "��° �ٿ��� ������ �߻��Ͽ����ϴ�.\n"+ text + "�� ParentNode�� �������� �ʽ��ϴ�.\n(= tabŰ �Է� ����)", "Ʈ������ �˻��", JOptionPane.ERROR_MESSAGE);
				errorCnt++;
				return;
			}
			else {
				parent = recentParent.get(rank-1);
				linkFirstChild();
				linkNextSibling();
			}
		}
		recentParent.add(rank, this);
		this.text = text.substring(rank);
		setLabel();	
		nodeArray.add(this);
	}
	public boolean isSetted() {
		return isSettedX && isSettedY;
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
	public Color getColor() {
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
		return countChildren();
	}
	public MNode getNextSibling() {
		return this.nextSibling;
	}
	public JLabel getLabel() {
		return this.label;
	}
	public static int getErrorCnt() {
		return errorCnt;
	}
	
	public void setX(int x) {
		this.isSettedX = true;
		this.x = x;
	}
	public void setY(int y) {
		this.isSettedY = true;
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
	public void setColor(int color) {
		this.color = new Color(color);
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
	public static void setErrorCnt(int cnt) {
		errorCnt = cnt;
	}
	public void setLabel() {
		this.label = new JLabel(this.text, SwingConstants.CENTER);
		this.label.setFont(new Font("", Font.BOLD, 20));
		this.label.setBorder(new LineBorder(Color.PINK, 5));
		this.label.setBackground(this.color);
		this.label.setOpaque(true);
		//this.label.addMouseListener(new MouseActionListener());
		//this.label.addMouseMotionListener(new MouseActionListener());
	}
	public void linkFirstChild() {
		//���� ����� rank-1 == ������arr������ rank 
		MNode lastElem = nodeArray.get(nodeArray.size() - 1);
		if(this.rank - 1 == lastElem.rank)
			lastElem.firstChild = this;
	}
	public int countChildren() {
		int cnt=0;
		MNode c = this.firstChild;
		if(c != null) {
			cnt++;
			while(c.nextSibling!=null) {
				c=c.nextSibling;
				cnt++;
			}
		}	
		return cnt;
	}
	public void linkNextSibling() {
		//MNode lastElem = nodeArray.get(nodeArray.size() - 1);
		for(int i = nodeArray.size() - 1; i > 0; i--) {
			if(nodeArray.get(i).rank == this.rank && nodeArray.get(i).getParent() == this.getParent()) {
				nodeArray.get(i).nextSibling = this;
				break;
			}
		}
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
			System.out.println("��ġ : "+nodeArray.get(i).getX()+", "+nodeArray.get(i).getY());
			System.out.println("-------------");
			System.out.println("nodeArray.size() : "+ nodeArray.size());
			System.out.println("isSetted : "+ nodeArray.get(i).isSetted());
		}
	}
	
}
