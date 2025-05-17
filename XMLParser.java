import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class XMLParser {

    DocumentBuilderFactory dbFactory;

    public XMLParser() {
        dbFactory = DocumentBuilderFactory.newInstance();
    }

    public Deck parseDeck(String fileName) {
        Deck deck = new Deck();
        Document xmldoc = getNormalizedXMLDoc(fileName);
        assert xmldoc != null;

        NodeList cardNodes = xmldoc.getElementsByTagName("card");

        for (int i = 0; i < cardNodes.getLength(); i++) {
            Element cardElement = (Element) cardNodes.item(i);

            String name = cardElement.getAttribute("name");
            String img = cardElement.getAttribute("img");
            int budget = Integer.parseInt(cardElement.getAttribute("budget"));

            Element sceneElement = (Element) cardElement.getElementsByTagName("scene").item(0);
            String sceneDescription = sceneElement.getTextContent().trim();

            Role[] roles = parseRoles(cardElement);

            Card card = new Card(name, img, budget, sceneDescription, roles);
            deck.addCard(card);
        }

        return deck;
    }

    public Board parseBoard(String fileName) {
        Document xmldoc = getNormalizedXMLDoc(fileName);
        assert xmldoc != null;
        Element boardElement = (Element) xmldoc.getElementsByTagName("board").item(0);

        CastingOffice castingOffice = parseCastingOffice(boardElement);
        Room trailer = parseTrailer(boardElement);
        Set[] sets = parseSets(boardElement);

        return new Board(trailer, sets, castingOffice);
    }

    public CastingOffice parseCastingOffice(Element boardElement) {
        Element officeElement = (Element) boardElement.getElementsByTagName("office").item(0);

        String[] neighbors = parseNeighbors(officeElement);
        Area area = parseArea(officeElement);
        Upgrade[] upgrades = parseUpgrades(officeElement);

        return new CastingOffice(neighbors, area, upgrades);
    }

    public Room parseTrailer(Element boardElement) {
        Element trailerElement = (Element) boardElement.getElementsByTagName("trailer").item(0);

        String[] neighbors = parseNeighbors(trailerElement);
        Area area = parseArea(trailerElement);

        return new Room("trailer", neighbors, area);
    }

    public Set[] parseSets(Element boardElement) {
        NodeList setNodes = boardElement.getElementsByTagName("set");
        Set[] sets = new Set[setNodes.getLength()];

        for (int i = 0; i < setNodes.getLength(); i++) {
            Element setElement = (Element) setNodes.item(i);

            String name = setElement.getAttribute("name");
            String[] neighbors = parseNeighbors(setElement);
            Area area = parseArea(setElement);
            Area[] shotCounterAreas = parseShotCounterAreas(setElement);
            Role[] extraRoles = parseExtraRoles(setElement);

            sets[i] = new Set(name, neighbors, area, shotCounterAreas, extraRoles);
        }

        return sets;
    }

    private Document getNormalizedXMLDoc(String fileName) {
        try {
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

            File inputXmlFile = new File(fileName);
            Document xmldoc = docBuilder.parse(inputXmlFile);
            xmldoc.getDocumentElement().normalize();

            return xmldoc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Upgrade[] parseUpgrades(Element officeElement) {
        Element upgradesElement = (Element) officeElement.getElementsByTagName("upgrades").item(0);
        NodeList upgradeElements = upgradesElement.getElementsByTagName("upgrade");
        Upgrade[] upgrades = new Upgrade[upgradeElements.getLength()];

        for (int i = 0; i < upgrades.length; i++) {
            Element upgradeElement = (Element) upgradeElements.item(i);

            int level = Integer.parseInt(upgradeElement.getAttribute("level"));
            String currency = upgradeElement.getAttribute("currency");
            int amount = Integer.parseInt(upgradeElement.getAttribute("amt"));
            Area area = parseArea(upgradeElement);

            upgrades[i] = new Upgrade(level, currency, amount, area);
        }

        return upgrades;
    }

    private Role[] parseExtraRoles(Element element) {
        Element partsElement = (Element) element.getElementsByTagName("parts").item(0);
        if (partsElement == null) return new Role[0];

        return parseRoles(partsElement);
    }

    private Role[] parseRoles(Element parent) {
        NodeList partNodes = parent.getElementsByTagName("part");
        Role[] roles = new Role[partNodes.getLength()];

        for (int i = 0; i < roles.length; i++) {
            Element partElement = (Element) partNodes.item(i);
            roles[i] = parseRole(partElement);
        }

        return roles;
    }

    private Area[] parseShotCounterAreas(Element setElement) {
        Element takesElement = (Element) setElement.getElementsByTagName("takes").item(0);
        NodeList takeNodes = takesElement.getElementsByTagName("take");
        Area[] shotCounterAreas = new Area[takeNodes.getLength()];

        for (int i = 0; i < shotCounterAreas.length; i++) {
            Element takeElement = (Element) takeNodes.item(i);
            int number = Integer.parseInt(takeElement.getAttribute("number"));
            shotCounterAreas[number - 1] = parseArea(takeElement);
        }

        return shotCounterAreas;
    }

    private String[] parseNeighbors(Element element) {
        Element neighborsElement = (Element) element.getElementsByTagName("neighbors").item(0);
        NodeList neighborNodes = neighborsElement.getElementsByTagName("neighbor");
        String[] neighbors = new String[neighborNodes.getLength()];

        for (int i = 0; i < neighborNodes.getLength(); i++) {
            Element neighborElement = (Element) neighborNodes.item(i);
            neighbors[i] = neighborElement.getAttribute("name");
        }

        return neighbors;
    }

    private Area parseArea(Element element) {
        Element areaElement = (Element) element.getElementsByTagName("area").item(0);
        int x = Integer.parseInt(areaElement.getAttribute("x"));
        int y = Integer.parseInt(areaElement.getAttribute("y"));
        int w = Integer.parseInt(areaElement.getAttribute("w"));
        int h = Integer.parseInt(areaElement.getAttribute("h"));

        return new Area(x, y, w, h);
   }

   private Role parseRole(Element partElement) {
       String roleName = partElement.getAttribute("name");
       int level = Integer.parseInt(partElement.getAttribute("level"));
       Area area = parseArea(partElement);
       String line = partElement.getElementsByTagName("line").item(0).getTextContent();

       return new Role(roleName, level, line, area);
   }
}