import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTextField implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		clearTextField();
	}
	public void clearTextField() {
		Frame.textField.setText("");
		MNode.nodeArray.clear();
		MNode.recentParent.clear();
		OpenActionListener.gfp = null;
		Draw.drawLabel();
		Frame.pathLabel.setPathLabel(null);
		Frame.pathLabel.setBounds(0, 0, 0, 50);
	}
}