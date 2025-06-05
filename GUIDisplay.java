import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUIDisplay implements Display {
    private final int WIDTH = 1200, HEIGHT = 900;
    private final JFrame frame;
    private final JLayeredPane boardPane, sidePane, dayPane, currPlayerPane, standingsPane, interfacePane;

    private final HashMap<Player, Integer> playerNumbers = new HashMap<>();
    private final HashMap<String, JLabel> roomImages = new HashMap<>();
    private final HashMap<Player, JLabel> playerIcons = new HashMap<>();
    private final HashMap<Set, JLabel[]> shotCounterIcons = new HashMap<>();

    private final JLabel boardImageLabel;

    private final String[] playerIconFiles = {
            "b1.png", "c1.png", "g1.png", "o1.png", "p1.png", "r1.png", "v1.png", "w1.png", "y1.png"
    };

    private final String[] playerIconPrefixes = {
            "b", "c", "g", "o", "p", "r", "v", "w", "y"
    };

    private Player lastActingPlayer;

    public void setLastActingPlayer(Player player) {
        this.lastActingPlayer = player;
    }

    public GUIDisplay(JFrame frame) {
        this.frame = frame;
        frame.setSize(WIDTH + 300, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        boardPane = getLayeredPane(WIDTH, HEIGHT);
        boardImageLabel = new JLabel(new ImageIcon("images/board.jpg"));
        boardImageLabel.setBounds(0, 0, WIDTH, HEIGHT);
        boardPane.add(boardImageLabel, JLayeredPane.DEFAULT_LAYER);
        frame.add(boardPane, BorderLayout.CENTER);

        sidePane = new JLayeredPane();
        sidePane.setPreferredSize(new Dimension(300, HEIGHT));
        sidePane.setLayout(new BoxLayout(sidePane, BoxLayout.Y_AXIS));
        frame.add(sidePane, BorderLayout.EAST);

        dayPane = createSideSection("Day");
        currPlayerPane = createSideSection("Active Player");
        standingsPane = createSideSection("Standings");
        interfacePane = createSideSection("Interface");

        frame.setVisible(true);
    }

    private JLayeredPane createSideSection(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(300, HEIGHT / 4));

        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(panel.getPreferredSize());
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        pane.setName(title);
        panel.add(pane);
        sidePane.add(panel);

        return pane;
    }

    private JLayeredPane getLayeredPane(int width, int height) {
        JLayeredPane pane = new JLayeredPane();
        pane.setPreferredSize(new Dimension(width, height));
        pane.setLayout(null);
        return pane;
    }

    private void setLabel(JLayeredPane pane, String str) {
        clear(pane);
        JLabel label = new JLabel("<html>" + str.replace("\n", "<br>") + "</html>");
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(label);
        pane.revalidate();
        pane.repaint();
    }

    private void clear(JLayeredPane pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }

    @Override
    public void displayWelcome() {
    }

    @Override
    public void displayInvalidInput(String message) {
    }

    @Override
    public void displayPlayerTurn(Player player) {
        displayPlayerInfo(player);
    }

    @Override
    public void displayPlayerInfo(Player player) {
        StringBuilder labelText = new StringBuilder("<html>");
        labelText.append("Name: ").append(player.getName()).append("<br>");
        labelText.append("Rank: ").append(player.getRank()).append("<br>");
        labelText.append("Points: ").append(player.getPoints()).append("<br>");
        if (player.hasRole()) {
            labelText.append("Role: ").append(player.getRole().getName()).append("<br>");
        } else {
            labelText.append("Role: None<br>");
        }
        HashMap<String, Integer> assets = player.getAssets();
        for (String currency : assets.keySet()) {
            labelText.append(currency).append("s: ").append(assets.get(currency)).append("<br>");
        }
        labelText.append("</html>");
        setLabel(currPlayerPane, labelText.toString());
    }

    @Override
    public void displayRoomInfo(Room room) {
    }

    @Override
    public void displayRoleInfo(Role role) {
    }

    @Override
    public void displayStandings(Player[] players) {
        if (playerNumbers.isEmpty()) {
            for (int i = 0; i < players.length; i++) {
                playerNumbers.put(players[i], i);
            }
        }

        StringBuilder standingsText = new StringBuilder("<html>");
        for (int i = 0; i < players.length; i++) {
            standingsText.append(i + 1).append(": ")
                    .append(players[i].getName())
                    .append(" (")
                    .append(players[i].getPoints() + players[i].getRank() * 5)
                    .append(" points)<br>");
        }
        standingsText.append("</html>");
        setLabel(standingsPane, standingsText.toString());
    }

    @Override
    public void displayPlayerLocations(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            JLabel iconLabel = playerIcons.get(player);
            boolean isNew = false;

            int rank = player.getRank();
            int iconRank = Math.min(rank, 6);
            String iconFile = "images/" + playerIconPrefixes[i] + iconRank + ".png";

            if (iconLabel == null) {
                iconLabel = new JLabel(new ImageIcon(iconFile));
                playerIcons.put(player, iconLabel);
                isNew = true;
            } else {
                iconLabel.setIcon(new ImageIcon(iconFile));
            }

            Room room = player.getLocation();
            Area area;
            int x, y;

            if (player.hasRole()) {
                Role role = player.getRole();
                if (room instanceof Set set && set.isOnCardRole(role)) {
                    area = room.getArea();
                    int offsetX = (i % 4) * 15;
                    int offsetY = (i / 4) * 15;
                    x = area.getX() + offsetX;
                    y = area.getY() + offsetY;
                } else {
                    area = role.getArea();
                    x = area.getX();
                    y = area.getY();
                }
            } else {
                area = room.getArea();
                int offsetX = (i % 4) * 15;
                int offsetY = (i / 4) * 15;
                x = area.getX() + offsetX;
                y = area.getY() + offsetY + 120;
            }

            iconLabel.setBounds(x, y, 40, 40);
            if (isNew) {
                boardPane.add(iconLabel, JLayeredPane.DRAG_LAYER);
            }
        }

        boardPane.revalidate();
        boardPane.repaint();
    }

    @Override
    public void displayUpdatedRank(int newRank) {
        setLabel(interfacePane, "Congratulations! You are now rank " + newRank + "!");
    }

    @Override
    public void displayUpgradeCosts() {
    }

    @Override
    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {
        StringBuilder labelText = new StringBuilder("<html>");
        labelText.append(success ? "Success!<br>" : "Failure...<br>");
        if (earnings.isEmpty()) {
            labelText.append("You didn't earn anything.<br>");
        } else {
            for (String currency : earnings.keySet()) {
                labelText.append("You earned ").append(earnings.get(currency)).append(" ").append(currency)
                        .append("(s).<br>");
            }
        }
        labelText.append("There are ").append(shotsLeft).append(" scenes left to shoot on this set.</html>");
        setLabel(interfacePane, labelText.toString());

        // 🛠️ Corrected to update based on the actual player’s location
        for (Player p : playerIcons.keySet()) {
            if (lastActingPlayer != null && lastActingPlayer.getLocation() instanceof Set set) {
                updateShotCounters(set);
            }
        }
    }

    public void updateShotCounters(Set set) {
        // Remove old icons
        if (shotCounterIcons.containsKey(set)) {
            for (JLabel label : shotCounterIcons.get(set)) {
                if (label != null) {
                    boardPane.remove(label);
                }
            }
        }

        int currentShots = set.getShotCounters();
        Area[] counterAreas = set.getShotCounterAreas();
        JLabel[] icons = new JLabel[counterAreas.length];

        for (int i = 0; i < currentShots && i < counterAreas.length; i++) {
            Area area = counterAreas[i];
            JLabel icon = new JLabel(new ImageIcon("images/shot.png"));
            icon.setBounds(area.getX(), area.getY(), area.getWidth(), area.getHeight());
            boardPane.add(icon, JLayeredPane.MODAL_LAYER);
            icons[i] = icon;
        }

        shotCounterIcons.put(set, icons);
        boardPane.revalidate();
        boardPane.repaint();
    }

    @Override
    public void displayRehearseOutcome(Role role) {
        setLabel(interfacePane, "You rehearsed. Practice chips on " + role.getName() + ": " + role.getPracticeChips());
    }

    @Override
    public void displayMoveOutcome(Room room) {
        showRoomImage(room);
    }

    @Override
    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {
        StringBuilder labelText = new StringBuilder("<html>");
        labelText.append(set.getName()).append(" has been wrapped!<br>");
        for (Player player : playerEarnings.keySet()) {
            labelText.append(player.getName()).append(" earned ").append(playerEarnings.get(player))
                    .append(" dollars.<br>");
        }
        labelText.append("<br>There are ").append(scenesLeft).append(" sets left to shoot.</html>");
        setLabel(interfacePane, labelText.toString());

        // Remove the card image from the board
        String setName = set.getName();
        if (roomImages.containsKey(setName)) {
            JLabel cardLabel = roomImages.get(setName);
            boardPane.remove(cardLabel);
            roomImages.remove(setName);
        }

        // Remove shot counter icons
        JLabel[] icons = shotCounterIcons.get(set);
        if (icons != null) {
            for (JLabel icon : icons) {
                if (icon != null) {
                    boardPane.remove(icon);
                }
            }
            shotCounterIcons.remove(set);
        }

        boardPane.revalidate();
        boardPane.repaint();
    }

    @Override
    public void announceWinner(Player player) {
        clear(interfacePane);
        setLabel(interfacePane,
                player.getName() + " wins with " + (player.getPoints() + player.getRank() * 5) + " points!");
    }

    @Override
    public void displayGameEnd(Player[] players) {
        displayStandings(players);
        announceWinner(players[0]);
    }

    @Override
    public void displayTakeRoleOutcome(Role role) {
        Player player = role.getPlayer();
        displayPlayerLocations(playerIcons.keySet().toArray(new Player[0]));

        Room location = player.getLocation();
        String type = (location instanceof Set set && set.isOnCardRole(role)) ? "On-card" : "Off-card";
        String text = "You took the " + type + " role: \"" + role.getName() + "\" (Level " + role.getLevel()
                + ")<br>Line: \"" + role.getLine() + "\"";
        setLabel(interfacePane, text);

        boardPane.revalidate();
        boardPane.repaint();
    }

    @Override
    public void showRoomImage(Room room) {
        if (room instanceof Set setRoom) {
            Card card = setRoom.getCard();
            if (card != null) {
                String setName = setRoom.getName();
                if (roomImages.containsKey(setName)) {
                    boardPane.remove(roomImages.get(setName));
                    roomImages.remove(setName);
                }

                ImageIcon icon = new ImageIcon("images/Cards/" + card.getImage());
                JLabel cardLabel = new JLabel(icon);
                Area area = room.getArea();
                cardLabel.setBounds(area.getX(), area.getY(), area.getWidth(), area.getHeight());
                boardPane.add(cardLabel, JLayeredPane.PALETTE_LAYER);
                roomImages.put(setName, cardLabel);
                boardPane.revalidate();
                boardPane.repaint();
            }
        }
    }

    public void initializeCardbacks(Set[] sets) {
        for (Set set : sets) {
            Area area = set.getArea();
            JLabel cardbackLabel = new JLabel(new ImageIcon("images/Cardback.png"));
            cardbackLabel.setBounds(area.getX(), area.getY(), area.getWidth(), area.getHeight());
            boardPane.add(cardbackLabel, JLayeredPane.PALETTE_LAYER);
            roomImages.put(set.getName(), cardbackLabel);
        }
        boardPane.revalidate();
        boardPane.repaint();
    }

    public JLayeredPane getInterfacePane() {
        return interfacePane;
    }
}
