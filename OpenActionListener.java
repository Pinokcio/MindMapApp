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

public class OpenActionListener implements ActionListener{
	JFileChooser chooser;
	OpenActionListener(){
		chooser = new JFileChooser();
	}
	public void actionPerformed(ActionEvent e) {
		try {
			GetFilePath gfp = new GetFilePath();
			File file = new File(gfp.returnFilePath());
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(file);
			doc.getDocumentElement().normalize();
			
			for(int i = 1; i <= MNode.nodeArray.size(); i++) {	
				NodeList xmlList = doc.getElementsByTagName("node"+i);
				Node readXml = xmlList.item(i);
				
				if(readXml.getNodeType() == Node.ELEMENT_NODE) {
					Element nodeElmnt = (Element) xmlList;
					
					NodeList textList = nodeElmnt.getElementsByTagName("text");
					Element textElmnt = (Element)textList.item(0);
					Node textNode = textElmnt.getFirstChild();
					
					NodeList xList = nodeElmnt.getElementsByTagName("x");
					Element xElmnt = (Element)textList.item(0);
					Node xNode = textElmnt.getFirstChild();
					
					NodeList yList = nodeElmnt.getElementsByTagName("y");
					Element yElmnt = (Element)yList.item(0);
					Node yNode = yElmnt.getFirstChild();
					
					NodeList widthList = nodeElmnt.getElementsByTagName("width");
					Element widthElmnt = (Element)widthList.item(0);
					Node widthNode = widthElmnt.getFirstChild();
					
					NodeList heightList = nodeElmnt.getElementsByTagName("height");
					Element heightElmnt = (Element)heightList.item(0);
					Node heightNode = heightElmnt.getFirstChild();
					
					NodeList colorList = nodeElmnt.getElementsByTagName("text");
					Element colorElmnt = (Element)colorList.item(0);
					Node colorNode = colorElmnt.getFirstChild();
				}
			}	
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
}	

