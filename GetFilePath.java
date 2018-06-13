import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

public class GetFilePath {
	static private JFileChooser chooser;
	private String filePath;
	
	GetFilePath(String type){
		chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files","xml");
		chooser.setFileFilter(filter);
	
		int returnVal = 0;
		if(type.equals("open"))
			returnVal = chooser.showOpenDialog(null);
		else if(type.equals("save"))
			returnVal = chooser.showSaveDialog(null);
		
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
			filePath = null;
			return;
		}
		filePath = chooser.getSelectedFile().getPath();
	}
	public String returnFilePath() {
		return filePath;
	}
	static public void setFilePath(File file) {
		chooser.setSelectedFile(file);
	}
}