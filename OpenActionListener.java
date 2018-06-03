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
	static GetFilePath gfp = null;
	
	OpenActionListener(){
		chooser = new JFileChooser();
	}
	public void actionPerformed(ActionEvent e) {
		try {
			gfp  = new GetFilePath();
			File file = new File(gfp.returnFilePath());
			DocumentBuilderFactory docBuildFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuild = docBuildFact.newDocumentBuilder();
			Document doc = docBuild.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList xmlList = doc.getElementsByTagName("node");
			
			for(int i = 0; i < xmlList.getLength(); i++) {	
				
				Node readXml = xmlList.item(i);
				
				if(readXml.getNodeType() == Node.ELEMENT_NODE) {
					Element nodeElmnt = (Element) readXml;
					
					NodeList textList = nodeElmnt.getElementsByTagName("text");
					Element textElmnt = (Element)textList.item(0);
					Node textNode = textElmnt.getFirstChild();
					MNode.nodeArray.add(i,new MNode(textNode.getNodeValue()));
					
					NodeList xList = nodeElmnt.getElementsByTagName("x");
					Element xElmnt = (Element)xList.item(0);
					Node xNode = xElmnt.getFirstChild();
					MNode.nodeArray.get(i).setX(Integer.parseInt(xNode.getNodeValue()));
					
					NodeList yList = nodeElmnt.getElementsByTagName("y");
					Element yElmnt = (Element)yList.item(0);
					Node yNode = yElmnt.getFirstChild();
					MNode.nodeArray.get(i).setY(Integer.parseInt(yNode.getNodeValue()));
					
					NodeList widthList = nodeElmnt.getElementsByTagName("width");
					Element widthElmnt = (Element)widthList.item(0);
					Node widthNode = widthElmnt.getFirstChild();
					MNode.nodeArray.get(i).setWidth(Integer.parseInt(widthNode.getNodeValue()));
					
					NodeList heightList = nodeElmnt.getElementsByTagName("height");
					Element heightElmnt = (Element)heightList.item(0);
					Node heightNode = heightElmnt.getFirstChild();
					MNode.nodeArray.get(i).setHeight(Integer.parseInt(heightNode.getNodeValue()));
					
					NodeList colorList = nodeElmnt.getElementsByTagName("color");
					Element colorElmnt = (Element)colorList.item(0);
					Node colorNode = colorElmnt.getFirstChild();
					if(colorNode.getNodeValue()!=null) {
						MNode.nodeArray.get(i).setColor(colorNode.getNodeValue());
					}
					NodeList rankList = nodeElmnt.getElementsByTagName("rank");
					Element rankElmnt = (Element)rankList.item(0);
					Node rankNode = rankElmnt.getFirstChild();
					MNode.nodeArray.get(i).setRank(Integer.parseInt(rankNode.getNodeValue()));
					
					for(int j=0; j<MNode.nodeArray.get(i).getRank(); j++) {
						Frame.textField.append("\t");
					}
					Frame.textField.append(MNode.nodeArray.get(i).getText()+"\n");
				}
			}	
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
}	

