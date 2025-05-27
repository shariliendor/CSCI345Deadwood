import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDisplay implements Display{
    private final int WIDTH = 1500, HEIGHT = 900;
    private JPanel boardPanel = new JPanel();
    private JPanel sidePanel = new JPanel();
    private JPanel dayPanel = new JPanel();
    private JPanel currPlayerPanel = new JPanel();
    private JPanel standingsPanel = new JPanel();
    private JPanel interfacePanel = new JPanel();

    public GUIDisplay(JFrame frame) {
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(boardPanel);
        boardPanel.setLocation(0, 0);
        boardPanel.setSize(4 * WIDTH / 5, HEIGHT);

        frame.add(sidePanel);
        sidePanel.setLocation(4 * WIDTH / 5, 0);
        sidePanel.setSize(WIDTH / 5, HEIGHT);

        sidePanel.add(dayPanel);
        dayPanel.setLocation(4 * WIDTH / 5, 0);
    }

    @Override
    public void displayWelcome() {
        // not used
    }

    @Override
    public void displayInvalidInput(String message) {
        // maybe for number of players
    }

    public void displayDaysLeft(int daysLeft) {
        // update days left panel
    }

    @Override
    public void displayPlayerTurn(Player player) {
        // calls displayPlayerInfo 'cause they are basically the same in our GUI implementation
        displayPlayerInfo(player);
    }

    @Override
    public void displayPlayerInfo(Player player) {
        // update currPlayer panel with stats of current player
    }

    @Override
    public void displayRoomInfo(Room room) {
        // not used in model
    }

    @Override
    public void displayRoleInfo(Role role) {
        // not used in model
    }

    @Override
    public void displayStandings(Player[] players) {
        // update standings panel
    }

    @Override
    public void displayPlayerLocations(Player[] players) {
        // each player has a spot in each room different from other players
        // put the player in their spot
    }

    @Override
    public void displayUpdatedRank(int newRank) {
        // update player sprites
    }

    @Override
    public void displayUpgradeCosts() {
        // already drawn on the board lol
    }

    @Override
    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {
        // add a label to the interface panel
        // click to continue
    }

    @Override
    public void displayRehearseOutcome(Role role) {

    }

    @Override
    public void displayMoveOutcome(Room room) {

    }

    @Override
    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {

    }

    @Override
    public void announceWinner(Player player) {
        // message to the interface panel
    }

    @Override
    public void displayGameEnd(Player[] players) {
        // announce winner in interface panel
    }

    @Override
    public void displayTakeRoleOutcome(Role role) {

    }
}
