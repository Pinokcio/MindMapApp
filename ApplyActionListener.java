import java.awt.event.*;
import javax.swing.*;

public class ApplyActionListener implements ActionListener {
	private String word[];
	public void actionPerformed(ActionEvent e) {
		apply();
	}
	public void apply(){
		splitText();
		if(word[0] == null) {
			JOptionPane.showMessageDialog(null, "노드를 입력하세요", "빈 노드 오류", JOptionPane.ERROR_MESSAGE);
		}
		else {
			MNode.nodeArray.clear();
			MNode.recentParent.clear();
			for(int i = 0; i < word.length; i++) {
				new MNode(word[i], i);
				if(MNode.getErrorCnt()!=0) {
					break;
				}
			}
			if(MNode.getErrorCnt()==0)
				new Draw().drawLabel();;
			MNode.setErrorCnt(0);
			MNode.print();
		}
	}
	public void splitText() {
		String fullText = Frame.textField.getText();
		word = fullText.split("\n");
		if(word[0].equals(""))
			word[0] = null;
	}
}
