import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ResizeListener extends ComponentAdapter{
	public void componentResized(ComponentEvent e) {
		Frame.frameWidth = (int)e.getComponent().getBounds().getSize().getWidth();
		Frame.frameHeight = (int)e.getComponent().getBounds().getSize().getHeight();
		Frame.locateDivider();
	}
}
