import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.lang.StringBuffer;

public class GetFilePath {
	private JFileChooser chooser;
	public String filePath;
	GetFilePath(){
		chooser = new JFileChooser();

		FileNameExtensionFilter filter = new FileNameExtensionFilter("XML Files","xml");
		chooser.setFileFilter(filter);
	
		int returnVal = chooser.showSaveDialog(null);
		if(returnVal != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
			return;
		}	
		filePath = chooser.getSelectedFile().getPath();
	}
	public String returnFilePath() {
		return filePath;
	}
}
