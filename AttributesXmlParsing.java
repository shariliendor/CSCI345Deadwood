import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class AttributesXmlParsing {
   public Deck parseDeck() {
      try {      	  
	     //Input the XML file
	     File inputXmlFile = new File("cards.xml");
	      
	     //creating DocumentBuilder
	     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	     DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
	     Document xmldoc = docBuilder.parse(inputXmlFile);
	      
	     //Getting the root element
	     System.out.println("Root element :" + xmldoc.getDocumentElement().getNodeName());
	     NodeList nList = xmldoc.getElementsByTagName("card");
	      
	     //Iterating through all the child elements of the root
	     for (int temp = 0; temp < nList.getLength(); temp++) {      
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
	        
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {     
               Element eElement = (Element) nNode;
               System.out.println("name : " + eElement.getAttribute("name"));
               System.out.println("img : " + eElement.getAttribute("img"));
               System.out.println("budget : " + eElement.getAttribute("budget"));
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}