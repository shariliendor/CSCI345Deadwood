import javax.swing.*;
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
        setLabel(dayPanel, "Days Left: " + daysLeft);
    }

    @Override
    public void displayPlayerTurn(Player player) {
        // calls displayPlayerInfo 'cause they are basically the same in our GUI implementation
        displayPlayerInfo(player);
    }

    @Override
    public void displayPlayerInfo(Player player) {
        // update currPlayer panel with stats of current player
        StringBuilder labelText = new StringBuilder();
        labelText.append("Rank: " + player.getRank() + "\n");
        labelText.append("Points: " + player.getPoints() + "\n");

        if (player.hasRole()) {
            labelText.append("Role: " + player.getRole().getName() + "\n");
        } else {
            labelText.append("Role: None" + "\n");
        }

        HashMap<String, Integer> assets = player.getAssets();
        for (String currency: assets.keySet()) {
            labelText.append(currency + "s: " + assets.get(currency) + "\n");
        }

        setLabel(currPlayerPanel, labelText.toString());
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
        StringBuilder standingsText = new StringBuilder();

        for (int i = 0; i < players.length; i++) {
            standingsText.append((i + 1) + ": " + players[i].getName() + " (" + players[i].getPoints() + " points)" + "\n");
        }

        setLabel(standingsPanel, standingsText.toString());
    }

    @Override
    public void displayPlayerLocations(Player[] players) {
        // each player has a spot in each room different from other players
        // put the player in their spot
    }

    @Override
    public void displayUpdatedRank(int newRank) {
        // update player sprites
        setLabel(interfacePanel, "Congratulations! You are now rank " + newRank + "!");
    }

    @Override
    public void displayUpgradeCosts() {
        // already drawn on the board lol
    }

    @Override
    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {
        // add a label to the interface panel
        // click to continue
        StringBuilder labelText = new StringBuilder();

        if (success) labelText.append("Success!" + "\n");
        else labelText.append("Failure..." + "\n");

        if(earnings.isEmpty()) {
            labelText.append("You didn't earn anything." + "\n");
        } else {
            for (String currency: earnings.keySet()) {
                labelText.append("You earned " + earnings.get(currency) + " " + currency + "(s)." + "\n");
            }
        }

        labelText.append("There are " + shotsLeft + " scenes left to shoot on this set.");

        setLabel(interfacePanel, labelText.toString());
    }

    @Override
    public void displayRehearseOutcome(Role role) {
        String labelText =
                "You have rehearsed. There are now " + role.getPracticeChips()
                + " practice chips on " + role.getName();
        setLabel(interfacePanel, labelText);
    }

    @Override
    public void displayMoveOutcome(Room room) {
        // move the current player to the specified room
        // need to get the current player somehow
    }

    @Override
    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {
        StringBuilder labelText = new StringBuilder();

        labelText.append(set.getName() + " has been wrapped!" + "\n");

        for (Player player: playerEarnings.keySet()) {
            labelText.append(player.getName() + " earned " + playerEarnings.get(player) + " dollars." + "\n");
        }

        labelText.append("\n" + "There are " + scenesLeft + " sets left to shoot.");

        setLabel(interfacePanel, labelText.toString());
    }

    @Override
    public void announceWinner(Player player) {
        setLabel(interfacePanel, player.getName() + " wins with " + (player.getPoints() + player.getRank()*5) + " points!");
    }

    @Override
    public void displayGameEnd(Player[] players) {
        displayStandings(players);
        announceWinner(players[0]);
    }

    @Override
    public void displayTakeRoleOutcome(Role role) {
        Room location = role.getPlayer().getLocation();

        String takenType = "Unknown role type";
        if (location instanceof Set set) {
            takenType = set.isOnCardRole(role) ? "On-card role" : "Off-card role";
        }

        StringBuilder labelText = new StringBuilder();
        labelText.append("You have taken the " + takenType + ": \"" + role.getName() + "\" (Level " + role.getLevel() + ")\n");
        labelText.append("Line: \"" + role.getLine() + "\"");

        setLabel(interfacePanel, labelText.toString());
    }

    private void setLabel(JPanel panel, String str) {
        clear(panel);
        JLabel label = new JLabel(str);
        panel.add(label);
    }

    private void clear(JPanel panel) {
        while (panel.getComponentCount() > 0) {
            panel.remove(0);
        }
    }
}
