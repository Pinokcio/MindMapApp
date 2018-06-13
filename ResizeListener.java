import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ResizeListener extends ComponentAdapter{
   JFrame frame;
   JComponent component;
   public ResizeListener(JFrame frame) {
      this.frame = frame;
   }
   public ResizeListener(JLabel label) {
      this.component = label;
   }
   public ResizeListener(JTextField text) {
      this.component = text;
   }
   public void componentResized(ComponentEvent e) {
      if(frame != null) {
         resizeFrame(e);
      }
      if(component != null) {
         resizeLabelNText();
      }
      
   }
   public void resizeFrame(ComponentEvent e) {
      Frame.frameWidth = (int)e.getComponent().getBounds().getSize().getWidth();
      Frame.frameHeight = (int)e.getComponent().getBounds().getSize().getHeight();
      Frame.locateDivider();
   }
   public void resizeLabelNText() {
      int i=0;
        while(true) {
            Font before = component.getFont();
            Font font = new Font(before.getName(), before.getStyle(), i);
            component.setFont(font);
            if(component.getPreferredSize().getWidth()>component.getWidth() || component.getPreferredSize().getHeight()>component.getHeight()) {
                font = new Font(before.getName(), before.getStyle(), i-1);
                component.setFont(font);
                break;
            }
            i++;    
        }
        System.out.println("½ÇÇà");
   }
}