import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParser {
   public Deck parseDeck() {
       Deck deck = new Deck();
       try {
           File inputXmlFile = new File("cards.xml");
           DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
           DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
           Document xmldoc = docBuilder.parse(inputXmlFile);
           xmldoc.getDocumentElement().normalize();

           NodeList cardNodes = xmldoc.getElementsByTagName("card");

           for (int i = 0; i < cardNodes.getLength(); i++) {
               Element cardElement = (Element) cardNodes.item(i);

               String name = cardElement.getAttribute("name");
               String img = cardElement.getAttribute("img");
               int budget = Integer.parseInt(cardElement.getAttribute("budget"));

               Element sceneElement = (Element) cardElement.getElementsByTagName("scene").item(0);
               String sceneDescription = sceneElement.getTextContent().trim();

               NodeList partNodes = cardElement.getElementsByTagName("part");
               Role[] roles = new Role[partNodes.getLength()];

               for (int j = 0; j < partNodes.getLength(); j++) {
                   Element partElement = (Element) partNodes.item(j);
                   String roleName = partElement.getAttribute("name");
                   int level = Integer.parseInt(partElement.getAttribute("level"));

                   Element areaElement = (Element) partElement.getElementsByTagName("area").item(0);
                   int x = Integer.parseInt(areaElement.getAttribute("x"));
                   int y = Integer.parseInt(areaElement.getAttribute("y"));
                   int h = Integer.parseInt(areaElement.getAttribute("h"));
                   int w = Integer.parseInt(areaElement.getAttribute("w"));
                   Area area = new Area(x, y, h, w);

                   String line = partElement.getElementsByTagName("line").item(0).getTextContent();

                   roles[j] = new Role(roleName, level, line, area);
               }

               Card card = new Card(name, img, budget, sceneDescription, roles);
               deck.addCard(card);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return deck;
   }

   public Board parseBoard(String fileName) {
       // Placeholder
       return null;
   }
}