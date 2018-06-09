import javax.swing.*;
import java.util.Collections;
import java.awt.*;
import java.util.ArrayList;

public class Draw extends JComponent {
	static int mindPaneWidth = (int)(Frame.drawField.getSize().getWidth()/2);
	static int mindPaneHeight = (int)(Frame.drawField.getSize().getHeight()/2);
	public void drawLabel() {
		Frame.drawField.removeAll();
		for(int i = 1; i < MNode.nodeArray.size(); i++) {
			if(!MNode.nodeArray.get(i).isSetted()) {
				setPosition(i); // x, y ��ǥ�� �����Ǿ����� ���� ��� �ʵ忡 �׸��� ���� ��ǥ ��������, �ٸ� root�� ��� �����̱⿡ ���� x!
			}
		}
		drawLabelBounds();
		for(int i = 0; i < MNode.nodeArray.size(); i++) {
			Frame.drawField.add(MNode.nodeArray.get(i).getLabel());
		}
		Frame.drawMind();
	}
	public void setPosition(int i) {
		//Frame.drawField.set
		//root������ ������ ����� ��ġ ����
		MNode rootNode = MNode.nodeArray.get(0);
		int zeroX = rootNode.getX();
		int zeroY = rootNode.getY();
		
		ArrayList<Integer> cntChild = new ArrayList<Integer>();// ������ ������ �ֻ��� ��������� childnode������ ����.
		ArrayList<Integer> childNum = new ArrayList<Integer>();//��������尡 ���° child�������� �ֻ�����尡 ���° child�������� ����
		ArrayList<Double> angle = new ArrayList<Double>();
		int r;
		double theta = 0;
		MNode tmpNode;
		MNode nodeForChildNum;
		int k=0;
		int cnt;
		tmpNode = MNode.nodeArray.get(i);
		
		while(tmpNode.getParent()!=null) {
			cnt = 0;
			cntChild.add(tmpNode.getCntChildren());
			childNum.add(k,cnt);
			nodeForChildNum = tmpNode.getParent().getFirstChild();
			if(nodeForChildNum==tmpNode)
				childNum.set(k, cnt);
			else {
				cnt++;
				while(nodeForChildNum.getNextSibling()!=tmpNode) {
					nodeForChildNum = nodeForChildNum.getNextSibling();
					cnt++;
				}
				childNum.set(k,cnt);
			}
			k++;
			tmpNode = tmpNode.getParent();
			}
		childNum.add(0);
		cntChild.add(tmpNode.getCntChildren());
		Collections.reverse(cntChild);
		Collections.reverse(childNum);
			
		if(cntChild.get(0)==1) {
			angle.add(360.0/2);
		}
		else
			angle.add(360.0/cntChild.get(0));
		theta = angle.get(0)*childNum.get(1);
			
		for(int j = 1; j < cntChild.size()-1; j++) {
			angle.add(angle.get(j-1) / (cntChild.get(j)+1));
			theta = theta - angle.get(j-1)/2 + angle.get(j)*(childNum.get(j+1)+1);
		}
		
		r = 100*MNode.nodeArray.get(i).getRank();
		MNode.nodeArray.get(i).setX(zeroX+(int)(r*Math.cos(Math.toRadians(theta))));
		MNode.nodeArray.get(i).setY(zeroY+(int)(r*Math.sin(Math.toRadians(theta))));
		
		childNum.clear();
		cntChild.clear();
		angle.clear();
	}
	public void paintComponent(Graphics g) {
		g.translate(MNode.nodeArray.get(0).getCenterX(), MNode.nodeArray.get(0).getCenterY());
	}
	public void drawLabelBounds() {
		for(int i = 0; i < MNode.nodeArray.size(); i++) {
	         MNode.nodeArray.get(i).getLabel().setBounds(MNode.nodeArray.get(i).getX(), MNode.nodeArray.get(i).getY(), MNode.nodeArray.get(i).getWidth(), MNode.nodeArray.get(i).getHeight());
	      }
	   }
	}

/*//�ǳڿ� ���̺� �÷��� ���ε����ο� �ø��Ÿ� �� �ڵ� ���
JPanel p3 = new JPanel();
p3.setBounds(gap,gap*2+h,w,h);
p3.setBorder(new LineBorder(Coloed, 5));
p3.setLayout(new BorderLayout());
p3.add(new JLabel("LineBorder.rr(Color.red,5)",JLabel.CENTER));
add(p3);*/