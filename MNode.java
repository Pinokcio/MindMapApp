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
	private Point right, left, up, down;
	private String text;
	private Color color = new Color(0xffffff);
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
			setX(Draw.mindPaneWidth-width/2);	//너비의 반절 빼기
			setY(Draw.mindPaneHeight-height/2);	//높이의 반절 빼기
			setCenterByVal();
			set4Points();
			if(!recentParent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "TextField " + (num+1) + "번째 줄에서 오류가 발생하였습니다.\n파일당 RootNode는 하나만 존재할 수 있습니다.", "트리구조 검사기", JOptionPane.ERROR_MESSAGE);
				errorCnt++;
				return;
			}		
		}
		else { 
			if(recentParent.isEmpty()) {
				JOptionPane.showMessageDialog(null, "TextField  " + (num+1) + "번째 줄에서 오류가 발생하였습니다.\nRootNode를 입력해주세요.", "트리구조 검사기", JOptionPane.ERROR_MESSAGE);
				errorCnt++;
				return;
			}
			if(nodeArray.get(num-1).getRank() < rank && rank - nodeArray.get(num-1).getRank() >  1) {
				JOptionPane.showMessageDialog(null,"TextField " + (num+1) + "번째 줄에서 오류가 발생하였습니다.\n"+ text + "의 ParentNode가 존재하지 않습니다.\n(= tab키 입력 오류)", "트리구조 검사기", JOptionPane.ERROR_MESSAGE);
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
	public Point getRight() {
		return this.right;
	}
	public Point getLeft() {
		return this.left;
	}
	public Point getUp() {
		return this.up;
	}
	public Point getDown() {
		return this.down;
	}
	public String getText() {
		return this.text;
	}
	public String getColor() {
		String r = Integer.toHexString(this.color.getRed());
		while(r.length()!=2)
			r = "0" + r;
		String g = Integer.toHexString(this.color.getGreen());
		while(g.length()!=2)
			g = "0" + g;
		String b = Integer.toHexString(this.color.getBlue());
		while(b.length()!=2)
			b = "0" + b;
		String rgb = r + g + b;
		return rgb;
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
		if(width < 30)
			width = 30; //최소 크기 설정
		this.width = width;
	}
	public void setHeight(int height) {
		if(height < 30)
			height = 30; // 최소 크기 설정
		this.height = height;
	}
	public void setCenterByVal() {
		this.centerX = (int)(this.x + (this.width/2.0));
		this.centerY = (int)(this.y + (this.height/2.0));
	}
	public void set4Points() {
		this.right = new Point(this.x + this.width, this.centerY);
		this.left = new Point(this.x, this.centerY);
		this.up = new Point(this.centerX, this.y);
		this.down = new Point(this.centerX, this.y + this.height);
	}
	public void setColor(String colorStr) {
		if(colorStr.length()>6) {
			JOptionPane.showMessageDialog(null, "Color 설정 시 6자리 16진수 숫자를 입력해주세요.","숫자 입력 오류",JOptionPane.ERROR_MESSAGE);
			return;
		}
		try{
			while(colorStr.length()!=6)
				colorStr = "0"+colorStr;
			this.color = new Color(
					Integer.valueOf( colorStr.substring( 0, 2 ), 16 ),
					Integer.valueOf( colorStr.substring( 2, 4 ), 16 ),
			        Integer.valueOf( colorStr.substring( 4, 6 ), 16 ) );
		}catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, "Color 설정 시 6자리 16진수 숫자를 입력해주세요.","숫자 입력 오류",JOptionPane.ERROR_MESSAGE);
		}
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
	}
	public void linkFirstChild() {
		//지금 노드의 rank-1 == 마지막arr원소의 rank 
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
}