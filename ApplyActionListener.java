import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ApplyActionListener implements ActionListener {
	private String word[];
	public void actionPerformed(ActionEvent e) {
		MNode.nodeArray.clear();
		splitText();
		
		if(word == null) {
			JOptionPane.showMessageDialog(null, "노드 입력하셈", "빈 노드 오류", JOptionPane.ERROR_MESSAGE);
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
