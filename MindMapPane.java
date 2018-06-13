import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class MindMapPane extends JPanel{
   public void paintComponent(Graphics g) {
      super.paintComponent(g);

      for(int i = 0; i < MNode.nodeArray.size(); i++) {
         g.setColor(Color.BLACK);
         MNode.nodeArray.get(i).setCenterByVal();
         MNode.nodeArray.get(i).set4Points();
         if(i == 0)
			continue;
         MNode node = MNode.nodeArray.get(i);
         int centerX = node.getCenterX();
         int centerY = node.getCenterY();
         Point right = node.getRight();
         Point left = node.getLeft();
         Point up = node.getUp();
         Point down = node.getDown();

         //(원점이 왼쪽상단 일때)기울기가 양수인 직선 y = ax + b
         double y1, a1, b1;
         a1 = (double)node.getParent().getHeight() / node.getParent().getWidth();
         b1 = node.getParent().getY() - a1 * node.getParent().getX();
         y1 = a1 * centerX + b1;
         //기울기가 음수인 직선
         double y2, a2, b2;
         a2 = -1 * a1;
         b2 = node.getParent().getY() - a2 * (node.getParent().getX() + node.getParent().getWidth());
         y2 = a2 * centerX + b2;
         
         if(centerY <= y1 && centerY <= y2) {
        	 g.drawLine(down.x, down.y, node.getParent().getUp().x, node.getParent().getUp().y);
         }	
         else if(centerY >= y1 && centerY >= y2) {
        	 g.drawLine(up.x, up.y, node.getParent().getDown().x, node.getParent().getDown().y);
         }
         else if(centerY > y1 && centerY < y2) {
        	 g.drawLine(right.x, right.y, node.getParent().getLeft().x, node.getParent().getLeft().y);
         }
         else {
        	 g.drawLine(left.x, left.y, node.getParent().getRight().x, node.getParent().getRight().y);
         }
      }
   }
}