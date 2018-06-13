import javax.swing.*;
import java.awt.event.*;
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
	private GetFilePath tmpGfp = null;
	
	OpenActionListener(){
		chooser = new JFileChooser();
	}
	public void actionPerformed(ActionEvent e) {
		try {
			tmpGfp = new GetFilePath("open");
			if(tmpGfp.returnFilePath()==null)
				return;
			new NewTextField().clearTextField();//열기에 성공했을 경우, 그 전에 있던 ArrayList 및 TextField를 정리.
			gfp = tmpGfp;
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

					NodeList xList = nodeElmnt.getElementsByTagName("x");
					Element xElmnt = (Element)xList.item(0);
					Node xNode = xElmnt.getFirstChild();
					
					NodeList yList = nodeElmnt.getElementsByTagName("y");
					Element yElmnt = (Element)yList.item(0);
					Node yNode = yElmnt.getFirstChild();
					
					NodeList widthList = nodeElmnt.getElementsByTagName("width");
					Element widthElmnt = (Element)widthList.item(0);
					Node widthNode = widthElmnt.getFirstChild();
					
					NodeList heightList = nodeElmnt.getElementsByTagName("height");
					Element heightElmnt = (Element)heightList.item(0);
					Node heightNode = heightElmnt.getFirstChild();
					
					NodeList colorList = nodeElmnt.getElementsByTagName("color");
					Element colorElmnt = (Element)colorList.item(0);
					Node colorNode = colorElmnt.getFirstChild();
					
					NodeList rankList = nodeElmnt.getElementsByTagName("rank");
					Element rankElmnt = (Element)rankList.item(0);
					Node rankNode = rankElmnt.getFirstChild();
					
					String text = textNode.getNodeValue();
					
					for(int j = 0 ; j < Integer.parseInt(rankNode.getNodeValue()); j++) {
						text = "\t" + text;
					}
					Frame.textField.append(text+"\n");
					
					new MNode(text, i);
					MNode.nodeArray.get(i).setX(Integer.parseInt(xNode.getNodeValue()));
					MNode.nodeArray.get(i).setY(Integer.parseInt(yNode.getNodeValue()));
					MNode.nodeArray.get(i).setWidth(Integer.parseInt(widthNode.getNodeValue()));
					MNode.nodeArray.get(i).setHeight(Integer.parseInt(heightNode.getNodeValue()));
					MNode.nodeArray.get(i).setColor(colorNode.getNodeValue());
					MNode.nodeArray.get(i).setRank(Integer.parseInt(rankNode.getNodeValue()));
				}
			}	
			Draw.drawLabel();
			Frame.pathLabel.setPathLabel(gfp.returnFilePath());
		}catch(Exception E) {
			E.printStackTrace();
		}
	}
}	