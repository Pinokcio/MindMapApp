import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ChangeActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		try{
			LabelMove.node.setX(Integer.parseInt(Frame.xTF.getText()));
			LabelMove.node.setY(Integer.parseInt(Frame.yTF.getText()));
			LabelMove.node.setWidth(Integer.parseInt(Frame.wTF.getText()));
			LabelMove.node.setHeight(Integer.parseInt(Frame.hTF.getText()));
			LabelMove.node.setColor((Frame.colorTF.getText()));
		}catch(NumberFormatException ne) {
			JOptionPane.showMessageDialog(null, "������ ���ڸ� �Է����ּ���.", "�Է� ����", JOptionPane.ERROR_MESSAGE);
		}
		Draw.drawLabel();
	}
}