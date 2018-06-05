import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.util.ArrayList;

public class Draw extends JComponent {
	ArrayList<JLabel> label = new ArrayList<JLabel>();
	static int mindPaneWidth = (int)(Frame.drawField.getSize().getWidth()/2);
	static int mindPaneHeight = (int)(Frame.drawField.getSize().getHeight()/2);
	public Draw() {
		Frame.drawField.removeAll();
		//���̺� ��� �׽�Ʈ
		for(int i = 0; i < MNode.nodeArray.size(); i++)	 {
			JLabel tmp = new JLabel(MNode.nodeArray.get(i).getText(), SwingConstants.CENTER);
			label.add(i, tmp);
			label.get(i).setFont(new Font("", Font.BOLD, 20));
			label.get(i).setBorder(new LineBorder(Color.PINK, 5));
			label.get(i).setBackground(Color.WHITE);
			label.get(i).setOpaque(true);
			
			Frame.drawField.add(label.get(i));
		}
		setPosition();
		Frame.drawMind();
	}
	public void paintComponent(Graphics g) {
		g.translate(MNode.nodeArray.get(0).getCenterX(), MNode.nodeArray.get(0).getCenterY());
	}
	public void setPosition() {
		//Frame.drawField.set
		//root������ ������ ����� ��ġ ����
		MNode node = MNode.nodeArray.get(0);
		int zeroX = MNode.nodeArray.get(0).getX();
		int zeroY = MNode.nodeArray.get(0).getY();
		while(node.getFirstChild() != null) {
			int cntChildren = node.getCntChildren();
			double angle = 360.0 /cntChildren;
			node.getFirstChild().setX(zeroX+(int)(Math.cos(Math.toRadians(180))*180*node.getFirstChild().getRank()));
			node.getFirstChild().setY(zeroY+(int)(Math.sin(Math.toRadians(180))*180*node.getFirstChild().getRank()));
			for(int i = 1; i < cntChildren; i++) {
				double mAngle = 180-i*angle;
				if(mAngle < 0)
					mAngle = Math.abs(mAngle);
				node.getFirstChild().getNextSibling().setX(zeroX+(int)(Math.cos(Math.toRadians(mAngle))*180*node.getFirstChild().getRank()));
				node.getFirstChild().getNextSibling().setY(zeroY+(int)(Math.sin(Math.toRadians(mAngle))*180*node.getFirstChild().getRank()));
			}
			node = node.getFirstChild();
		}
		
		for(int i = 0; i < MNode.nodeArray.size(); i++) {
			label.get(i).setBounds(MNode.nodeArray.get(i).getX(), MNode.nodeArray.get(i).getY(), MNode.nodeArray.get(i).getWidth(), MNode.nodeArray.get(i).getHeight());
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