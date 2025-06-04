import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDisplay implements Display{
    private final int WIDTH = 750, HEIGHT = 450;
    private final JFrame frame;
    private final JLayeredPane boardPane, sidePane, dayPane, currPlayerPane, standingsPane, interfacePane;

    private final HashMap<Player, Integer> playerNumbers = new HashMap<>();

    public GUIDisplay(JFrame frame) {
        this.frame = frame;
        frame.setSize(WIDTH, HEIGHT);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        boardPane = getLayeredPane(4 * WIDTH / 5, HEIGHT);
        boardPane.add(new JLabel(getScaledImage("images/board.jpg", 4 * WIDTH / 5, HEIGHT)));
        frame.getContentPane().add("Board", boardPane);

        sidePane = getLayeredPane(WIDTH / 5, HEIGHT);
        frame.getContentPane().add("Side Pane", sidePane);
        sidePane.setLayout(new FlowLayout());

        dayPane = getLayeredPane(WIDTH / 5, HEIGHT / 30);
        dayPane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        dayPane.setLayout(new FlowLayout());
        sidePane.add("Day", dayPane);

        currPlayerPane = getLayeredPane(WIDTH / 5, 8 * HEIGHT / 30);
        currPlayerPane.setBorder(BorderFactory.createTitledBorder("Active Player"));
        currPlayerPane.setLayout(new FlowLayout());
        sidePane.add("Current Player", currPlayerPane);

        standingsPane = getLayeredPane(WIDTH / 5, 10 * HEIGHT / 30);
        standingsPane.setBorder(BorderFactory.createTitledBorder("Standings"));
        standingsPane.setLayout(new FlowLayout());
        sidePane.add("Standings", standingsPane);

        interfacePane = getLayeredPane(WIDTH / 5, 9 * HEIGHT / 30);
        interfacePane.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        interfacePane.setLayout(new FlowLayout());
        sidePane.add("Interface", interfacePane);


        frame.pack();
    }

    private ImageIcon getScaledImage(String fileName, int width, int height) {
        ImageIcon imageIcon = new ImageIcon(fileName);
        Image image = imageIcon.getImage();
        Image scaledImg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg);
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
        StringBuilder labelText = new StringBuilder("<html>");
        labelText.append("Name: " + player.getName() + "<br>");
        labelText.append("Rank: " + player.getRank() + "<br>");
        labelText.append("Points: " + player.getPoints() + "<br>");

        if (player.hasRole()) {
            labelText.append("Role: " + player.getRole().getName() + "<br>");
        } else {
            labelText.append("Role: None" + "<br>");
        }

        HashMap<String, Integer> assets = player.getAssets();
        for (String currency: assets.keySet()) {
            labelText.append(currency + "s: " + assets.get(currency) + "<br>");
        }

        labelText.append("</html>");

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
        // fill player hashmap if not filled
        if (playerNumbers.isEmpty()) {
            for (int i = 0; i < players.length; i++) {
                playerNumbers.put(players[i], i);
            }
        }
        // update standings panel
        StringBuilder standingsText = new StringBuilder("<html>");

        for (int i = 0; i < players.length; i++) {
            standingsText.append((i + 1) + ": " + players[i].getName() + " (" +
                    (players[i].getPoints() + players[i].getRank() * 5) + " points)" + "<br>");
        }
        standingsText.append("</html>");

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
        setLabel(interfacePane, "<html><p>Congratulations! You are now rank " + newRank + "!</p></html>");
    }

    @Override
    public void displayUpgradeCosts() {
        // already drawn on the board lol
    }

    @Override
    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {
        // add a label to the interface panel
        // click to continue
        StringBuilder labelText = new StringBuilder("<html>");

        if (success) labelText.append("Success!" + "<br>");
        else labelText.append("Failure..." + "<br>");

        if(earnings.isEmpty()) {
            labelText.append("You didn't earn anything." + "<br>");
        } else {
            for (String currency: earnings.keySet()) {
                labelText.append("You earned " + earnings.get(currency) + " " + currency + "(s)." + "<br>");
            }
        }

        labelText.append("There are " + shotsLeft + " scenes left to shoot on this set.</p></html>");

        setLabel(interfacePane, labelText.toString());
    }

    @Override
    public void displayRehearseOutcome(Role role) {
        String labelText =
                "<html><p>You have rehearsed. There are now " + role.getPracticeChips()
                + " practice chips on " + role.getName() + "</p></html>";
        setLabel(interfacePane, labelText);
    }

    @Override
    public void displayMoveOutcome(Room room) {
        // move the current player to the specified room
        // need to get the current player somehow
    }

    @Override
    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {
        StringBuilder labelText = new StringBuilder("<html>");

        labelText.append(set.getName() + " has been wrapped!" + "<br>");

        for (Player player: playerEarnings.keySet()) {
            labelText.append(player.getName() + " earned " + playerEarnings.get(player) + " dollars." + "<br>");
        }

        labelText.append("<br>" + "There are " + scenesLeft + " sets left to shoot." + "</html>");

        setLabel(interfacePane, labelText.toString());
    }

    @Override
    public void announceWinner(Player player) {
        clear(interfacePane);
        setLabel(interfacePane,
                "<html><p>" +
                player.getName() + " wins with " + (player.getPoints() + player.getRank()*5) + " points!" +
                "</p></html>");
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
        frame.pack();
    }

    private void clear(JLayeredPane pane) {
        while (pane.getComponentCount() > 0) {
            pane.remove(0);
        }
    }
}
