import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDisplay implements Display{
    private final int WIDTH = 750, HEIGHT = 450;
    private final JLayeredPane boardPane, sidePane, dayPane, currPlayerPane, standingsPane, interfacePane;

    public GUIDisplay(JFrame frame) {
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        boardPane = getLayeredPane(4 * WIDTH / 5, HEIGHT);
        frame.add("Board", boardPane);
        boardPane.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));

        sidePane = getLayeredPane(WIDTH / 5, HEIGHT);
        frame.add("Side Pane", sidePane);
        sidePane.setLayout(new FlowLayout());

        dayPane = getLayeredPane(WIDTH / 5, HEIGHT / 30);
        dayPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        sidePane.add("Day", dayPane);

        currPlayerPane = getLayeredPane(WIDTH / 5, 5 * HEIGHT / 30);
        currPlayerPane.setBorder(BorderFactory.createTitledBorder("Active Player"));
        sidePane.add("Current Player", currPlayerPane);

        standingsPane = getLayeredPane(WIDTH / 5, 8 * HEIGHT / 30);
        standingsPane.setBorder(BorderFactory.createTitledBorder("Standings"));
        sidePane.add("Standings", standingsPane);

        interfacePane = getLayeredPane(WIDTH / 5, 15 * HEIGHT / 30);
        interfacePane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        sidePane.add("Interface", interfacePane);


        frame.pack();
    }

    private JLayeredPane getLayeredPane(int width, int height) {
        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(new Dimension(width, height));
        pane.setLayout(new FlowLayout());

        return pane;
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
        setLabel(dayPane, "Days Left: " + daysLeft);
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

        setLabel(currPlayerPane, labelText.toString());
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

        setLabel(standingsPane, standingsText.toString());
    }

    @Override
    public void displayPlayerLocations(Player[] players) {
        // each player has a spot in each room different from other players
        // put the player in their spot
    }

    @Override
    public void displayUpdatedRank(int newRank) {
        // update player sprites
        setLabel(interfacePane, "Congratulations! You are now rank " + newRank + "!");
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

        setLabel(interfacePane, labelText.toString());
    }

    @Override
    public void displayRehearseOutcome(Role role) {
        String labelText =
                "You have rehearsed. There are now " + role.getPracticeChips()
                + " practice chips on " + role.getName();
        setLabel(interfacePane, labelText);
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

        setLabel(interfacePane, labelText.toString());
    }

    @Override
    public void announceWinner(Player player) {
        setLabel(interfacePane, player.getName() + " wins with " + (player.getPoints() + player.getRank()*5) + " points!");
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

        setLabel(interfacePane, labelText.toString());
    }

    private void setLabel(JLayeredPane pane, String str) {
        clear(pane);
        JLabel label = new JLabel(str);
        pane.add(label);
    }

    private void clear(JLayeredPane pane) {
        while (pane.getComponentCount() > 0) {
            pane.remove(0);
        }
    }
}
