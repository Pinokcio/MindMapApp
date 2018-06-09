import java.awt.event.*;

public class SaveAsActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		new SaveActionListener().createFileAndSaveXml();
	}
}	