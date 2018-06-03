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
	WriteXml(String gfp){
		try 
		{
			String[] gfpStr = gfp.split("\\\\");
			String fileName = gfpStr[gfpStr.length-1];
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			
			Element filePathElmnt = doc.createElement(fileName);
			doc.appendChild(filePathElmnt);
			
			for(int i=0; i<MNode.nodeArray.size(); i++) 
			{ 
				Element node = doc.createElement("node");
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
			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "euc-KR");
			transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		
			DOMSource source = new DOMSource(doc);		
			StreamResult result = new StreamResult(new File(gfp+".xml"));
			transformer.transform(source, result);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}

