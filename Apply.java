import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Apply extends MouseAdapter {
	private String word[];
	public void mouseClicked(MouseEvent e) {
		MNode.nodeArray.clear();
		splitText();
		
		if(word == null) {
			JOptionPane.showMessageDialog(null, "��� �Է��ϼ�", "�� ��� ����", JOptionPane.ERROR_MESSAGE);
		}
		else {
			MNode.nodeArray.clear();
			for(int i = 0; i < word.length; i++) {
				MNode node = new MNode(word[i]);
			}
			new Draw();
			MNode.print();
		}
	}
	public void splitText() {
		String fullText = Frame.textField.getText();
		word = fullText.split("\n");
	}
}
