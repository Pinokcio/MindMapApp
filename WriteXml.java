import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
	
public class WriteXml 
{
	WriteXml(GetFilePath gfp){
		try 
		{
			String[] gfpStr = gfp.returnFilePath().split("\\\\");
			String fileName = gfpStr[gfpStr.length-1];
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			
			Element filePathElmnt = doc.createElement(fileName);
			doc.appendChild(filePathElmnt);
			
			for(int i=0; i<MNode.nodeArray.size(); i++) 
			{ 
				Element node = doc.createElement("node"+(i+1));
				filePathElmnt.appendChild(node);
				
				Element text = doc.createElement("text");
				text.appendChild(doc.createTextNode(MNode.nodeArray.get(i).getText()));
				node.appendChild(text);
				
				Element x = doc.createElement("x");
				x.appendChild(doc.createTextNode((Integer.toString(MNode.nodeArray.get(i).getX()))));
				node.appendChild(x);
				
				Element y = doc.createElement("y");
				y.appendChild(doc.createTextNode((Integer.toString(MNode.nodeArray.get(i).getY()))));
				node.appendChild(y);
				
				Element width = doc.createElement("width");
				width.appendChild(doc.createTextNode((Integer.toString(MNode.nodeArray.get(i).getWidth()))));
				node.appendChild(width);
				
				Element height = doc.createElement("height");
				height.appendChild(doc.createTextNode((Integer.toString(MNode.nodeArray.get(i).getHeight()))));
				node.appendChild(height);
				
				Element color = doc.createElement("color");
				color.appendChild(doc.createTextNode((MNode.nodeArray.get(i).getColor())));
				node.appendChild(color);
				
				Element rank = doc.createElement("rank");
				rank.appendChild(doc.createTextNode(Integer.toString((MNode.nodeArray.get(i).getRank()))));
				node.appendChild(rank);
				
				Element parent = doc.createElement("parent");
				if(MNode.nodeArray.get(i).getParent()!=null) {
					parent.appendChild(doc.createTextNode((MNode.nodeArray.get(i).getParent().getText())));
					node.appendChild(parent);
				}
				else {
					parent.appendChild(doc.createTextNode(null));
					node.appendChild(parent);
				}
				
				Element nextSibling = doc.createElement("nextSibling");
				if(MNode.nodeArray.get(i).getNextSibling()!=null) {
					nextSibling.appendChild(doc.createTextNode((MNode.nodeArray.get(i).getNextSibling().getText())));
					node.appendChild(nextSibling);
				}
				else {
					nextSibling.appendChild(doc.createTextNode(null));
					node.appendChild(nextSibling);	
				}
			}
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "euc-KR");
				transformer.setOutputProperty(OutputKeys.INDENT,"yes");
			
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(gfp.returnFilePath()+".xml"));
				transformer.transform(source, result);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

