import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SaveAsActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		GetFilePath gfp = new GetFilePath();
		if(gfp.returnFilePath()!=null)
			new WriteXml(gfp.returnFilePath());
	}
}	