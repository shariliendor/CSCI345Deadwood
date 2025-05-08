import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        XMLParser xmlParser = new XMLParser();
        Board board = xmlParser.parseBoard("board.xml");
        Deck deck = xmlParser.parseDeck("cards.xml");

        Scanner sc = new Scanner(System.in);
        Input input = new TextInput(sc);
        Display display = new TextDisplay();
        Controller controller = new Controller(input, display);

        SetupManager setupManager = new SetupManager(controller);
        int numPlayers = input.getNumPlayers();

        Player[] players = setupManager.createPlayers(numPlayers);
        int daysToPlay = setupManager.getDaysToPlay(numPlayers);

        GameManager gameManager = new GameManager(players, board, deck, daysToPlay);

        gameManager.playGame();
    }
}
