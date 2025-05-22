import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDisplay implements Display{
    private final int WIDTH = 1500, HEIGHT = 900;
    private JFrame frame = new JFrame();
    private JPanel boardPanel = new JPanel();
    private JPanel sidePanel = new JPanel();
    private JPanel dayPanel = new JPanel();
    private JPanel currPlayerPanel = new JPanel();
    private JPanel standingsPanel = new JPanel();
    private JPanel interfacePanel = new JPanel();

    public GUIDisplay() {
        frame.setSize(WIDTH, HEIGHT);

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

    }

    @Override
    public void displayInvalidInput(String message) {

    }

    @Override
    public void displayPlayerTurn(Player player) {

    }

    @Override
    public void displayPlayerInfo(Player player) {

    }

    @Override
    public void displayRoomInfo(Room room) {

    }

    @Override
    public void displayRoleInfo(Role role) {

    }

    @Override
    public void displayStandings(Player[] players) {

    }

    @Override
    public void displayPlayerLocations(Player[] players) {

    }

    @Override
    public void displayUpdatedRank(int newRank) {

    }

    @Override
    public void displayUpgradeCosts() {

    }

    @Override
    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {

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

    }

    @Override
    public void displayGameEnd(Player[] players) {

    }

    @Override
    public void displayTakeRoleOutcome(Role role) {

    }
}
