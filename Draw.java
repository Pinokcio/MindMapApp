import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Draw {
	ArrayList<JLabel> label = new ArrayList<JLabel>();
	public Draw() {
		Frame.drawField.removeAll();
		//레이블 출력 테스트
		for(int i = 0; i < MNode.nodeArray.size(); i++) {
			JLabel tmp = new JLabel();
			tmp.setText(MNode.nodeArray.get(i).getText());
			
			label.add(i, tmp);
			Frame.drawField.add(label.get(i));
			label.get(i).setBounds(10*i, 10*i, 50, 50);
		}
		Frame.drawMind();
	}
}
