import javax.swing.*;
import java.util.Scanner;

public class Deadwood {
    public static void main(String[] args) {
        runGame("cards.xml", "board.xml");
    }

    public static void runGame(String deckFile, String boardFile) {
        XMLParser xmlParser = new XMLParser();
        Deck deck = xmlParser.parseDeck(deckFile);
        Board board = xmlParser.parseBoard(boardFile);

        JFrame frame = new JFrame("Deadwood");
        GUIInput input = new GUIInput(frame);
        GUIDisplay display = new GUIDisplay(frame);

        input.setInterfacePane(display.getInterfacePane());

        // Show card backs on all sets before gameplay begins
        display.initializeCardbacks(board.getSets());

        Controller controller = new Controller(input, display);
        SetupManager setupManager = new SetupManager(controller);

        int numPlayers = input.getNumPlayers();
        Player[] players = setupManager.createPlayers(numPlayers);
        int daysToPlay = setupManager.getDaysToPlay(numPlayers);

        GameManager gameManager = new GameManager(players, board, deck, controller, daysToPlay);
        gameManager.playGame();
    }

}
