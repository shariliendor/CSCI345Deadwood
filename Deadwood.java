import java.util.Scanner;

public class Deadwood {
    public static void main(String[] args) {
        runGame("cards.xml", "board.xml");
    }

    public static void runGame(String deckFile, String boardFile) {
        XMLParser xmlParser = new XMLParser();
        Deck deck = xmlParser.parseDeck(deckFile);
        Board board = xmlParser.parseBoard(boardFile);

        Scanner sc = new Scanner(System.in);
        Input input = new TextInput(sc);
        Display display = new TextDisplay();
        Controller controller = new Controller(input, display);

        SetupManager setupManager = new SetupManager(controller);
        int numPlayers = controller.getNumPlayers();

        Player[] players = setupManager.createPlayers(numPlayers);
        int daysToPlay = setupManager.getDaysToPlay(numPlayers);

        GameManager gameManager = new GameManager(players, board, deck, controller, daysToPlay);

        gameManager.playGame();
    }
}
