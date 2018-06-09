import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class PathLabel extends JLabel{
	PathLabel() {
		this.setText(null);
		this.setFont(new Font("", Font.BOLD, 15));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBorder(new LineBorder(Color.ORANGE, 5));
		this.setBackground(new Color(-1));
		this.setOpaque(true);
		this.setBounds(this.getSize().width+300,this.getSize().height,295,50); //label위치가 drawfield에 맞게 변하도록 수정 필요.
	}
	public void setPathLabel(String path) {
		this.setText(path);
	}
}
