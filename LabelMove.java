import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.*;
import java.util.ArrayList;

public class LabelMove implements MouseListener, MouseMotionListener {
	private boolean isDragged;
	private int offX, offY;
	private ArrayList<MNode> nodeArray = new ArrayList<MNode>();
	public static MNode node;
	private String r, g, b, rgb, org;
	private Rectangle [] box = new Rectangle[8];
	private int boxOffX, boxOffY;
	private boolean isForSize;
	private Rectangle selectedBox;
	private boolean [] forBox = new boolean[8];
	
	LabelMove(ArrayList<MNode> nodeArray){
		this.nodeArray = nodeArray;
		isDragged = false;
		isForSize = false;
		for(int i = 0 ; i<box.length; i++)
			forBox[i] = false;
	}
	
	public void mousePressed(MouseEvent me) {
		for(int i = 0 ; i < nodeArray.size(); i++) {
			if(nodeArray.get(i).getLabel().getX() <= me.getX() && nodeArray.get(i).getLabel().getX() + nodeArray.get(i).getLabel().getWidth()>= me.getX() && nodeArray.get(i).getLabel().getY() <= me.getY() && nodeArray.get(i).getLabel().getY() + nodeArray.get(i).getLabel().getHeight() >= me.getY())
			{
				node = nodeArray.get(i);
				int x = node.getWidth()/5;
				int y = node.getHeight()/5;
				box[0] = new Rectangle(node.getX(), node.getY(), x, y);
				box[1] = new Rectangle(node.getX()+2*x, node.getY(), x, y);
				box[2] = new Rectangle(node.getX()+4*x, node.getY(), x, y);
				box[3] = new Rectangle(node.getX(), node.getY()+2*y, x, y);
				box[4] = new Rectangle(node.getX()+4*x, node.getY()+2*y, x, y);
				box[5] = new Rectangle(node.getX(), node.getY()+4*y, x, y);
				box[6] = new Rectangle(node.getX()+2*x,node.getY()+4*y, x, y);
				box[7] = new Rectangle(node.getX()+4*x, node.getY()+4*y, x, y);
				
				for(int j = 0; j < box.length; j++) {
					if(box[j].x <= me.getX() && box[j].x+box[j].width>= me.getX() && box[j].y <= me.getY() && box[j].y+box[j].height >= me.getY()) {
						selectedBox = box[j];
						boxOffX = me.getX() - selectedBox.x;
						boxOffY = me.getY() - selectedBox.y;
						isForSize = true;
						forBox[j] = true;
					}
				}
				offX = me.getX() - node.getLabel().getX();
				offY = me.getY() - node.getLabel().getY();
				
				org = node.getColor();
				
				r = Integer.toHexString(255-Integer.valueOf(node.getColor().substring( 0, 2 ), 16 ));
				while(r.length()!=2)
					r = "0"+r;
				g = Integer.toHexString(255-Integer.valueOf(node.getColor().substring( 2, 4 ), 16 ));
				while(g.length()!=2)
					g = "0"+g;
				b = Integer.toHexString(255-Integer.valueOf(node.getColor().substring( 4, 6 ), 16 ));
				while(b.length()!=2)
					b = "0"+b;
				rgb = r + g + b;
				
				Frame.textTF.setText(node.getText());
				Frame.xTF.setText(Integer.toString(node.getX()));
				Frame.yTF.setText(Integer.toString(node.getY()));
				Frame.wTF.setText(Integer.toString(node.getWidth()));
				Frame.hTF.setText(Integer.toString(node.getHeight()));
				Frame.colorTF.setText(node.getColor());
				node.getLabel().setBackground(new Color(Integer.parseInt(rgb,16)));
				
				isDragged = true;
			}	
		}
	}
	
	public void mouseReleased(MouseEvent me) {
		if(isDragged)
			node.getLabel().setBackground(new Color(Integer.parseInt(org,16)));
		
		isDragged = false;
		isForSize = false;
		for(int i = 0; i < box.length; i++)
			forBox[i] = false;
	}
	
	public void mouseDragged(MouseEvent me) {
		if(isDragged){	
			if(!isForSize) {
				node.getLabel().setLocation(me.getX() - offX, me.getY() - offY);
				node.setX(me.getX()-offX);
				node.setY(me.getY()-offY);
			}
			int x=0,y=0;
			if(isForSize) {
				if(forBox[0]) {
					x = node.getX()-(me.getX()-boxOffX);
					y = node.getY()-(me.getY()-boxOffY);
					node.setX(node.getX()-x);
					node.setY(node.getY()-y);
					node.setWidth(node.getWidth()+x);
					node.setHeight(node.getHeight()+y);
				}
				if(forBox[1]) {
					y = node.getY()-(me.getY()-boxOffY);
					node.setY(node.getY()-y);
					node.setHeight(node.getHeight()+y);	
				}
				if(forBox[2]) {
					x = (me.getX()-boxOffX) - (node.getX()+node.getWidth());
					y = node.getY()-(me.getY()-boxOffY);
					node.setY(node.getY()-y);
					node.setWidth(node.getWidth()+x+selectedBox.width);
					node.setHeight(node.getHeight()+y);	
				}
				if(forBox[3]) {
					x = node.getX()-(me.getX()-boxOffX);
					node.setX(node.getX()-x);
					node.setWidth(node.getWidth()+x);	
				}
				if(forBox[4]) {
					x = (me.getX()-boxOffX) - (node.getX()+node.getWidth());
					node.setWidth(node.getWidth()+x+selectedBox.width);
				}
				if(forBox[5]) {
					x = node.getX()-(me.getX()-boxOffX);
					y = (me.getY()-boxOffY) - (node.getY()+node.getHeight());
					node.setX(node.getX()-x);
					node.setWidth(node.getWidth()+x);
					node.setHeight(node.getHeight()+y+selectedBox.height);
				}
				if(forBox[6]) {
					y = (me.getY()-boxOffY) - (node.getY()+node.getHeight());
					node.setHeight(node.getHeight()+y+selectedBox.height);	
				}
				if(forBox[7]) {
					x = (me.getX()-boxOffX) - (node.getX()+node.getWidth());
					y = (me.getY()-boxOffY) - (node.getY()+node.getHeight());
					node.setWidth(node.getWidth()+x+selectedBox.width);
					node.setHeight(node.getHeight()+y+selectedBox.height);	
				}
				node.getLabel().setBounds(node.getX(),node.getY(),node.getWidth(),node.getHeight());
			}
			
			Frame.textTF.setText(node.getText());
			Frame.xTF.setText(Integer.toString(node.getX()));
			Frame.yTF.setText(Integer.toString(node.getY()));
			Frame.wTF.setText(Integer.toString(node.getWidth()));
			Frame.hTF.setText(Integer.toString(node.getHeight()));
			Frame.colorTF.setText(node.getColor());
			
			Frame.forPaneSize();
			Frame.locateDivider();
			
			Frame.MindMapPane.repaint();
		}
	}

	public void mouseMoved(MouseEvent me) {
	}
	public void mouseClicked(MouseEvent me) {
	}
	public void mouseEntered(MouseEvent me) {
	}
	public void mouseExited(MouseEvent me) {
	}
}