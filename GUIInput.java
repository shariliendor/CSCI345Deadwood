import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class GUIInput implements Input {
    private final JFrame frame;
    private JLayeredPane interfacePane;

    public GUIInput(JFrame frame) {
        this.frame = frame;
    }

    public void setInterfacePane(JLayeredPane interfacePane) {
        this.interfacePane = interfacePane;
    }

    @Override
    public int getNumPlayers() {
        while (true) {
            String input = JOptionPane.showInputDialog(frame, "Enter the number of players (2–8):");
            try {
                int num = Integer.parseInt(input);
                if (num >= 2 && num <= 8) return num;
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(frame, "Invalid input. Enter a number between 2 and 8.");
        }
    }

    @Override
    public String getPlayerName(int playerNum) {
        return JOptionPane.showInputDialog(frame, "Enter name for player " + playerNum + ":");
    }

    @Override
    public String selectAction(String[] actions) {
        return selectOption("action", actions);
    }

    @Override
    public Room selectRoom(String[] rooms) {
        String roomName = selectOption("room", rooms);
        return Room.getRoom(roomName);
    }

    @Override
    public Role selectRole(Role[] roles) {
        String[] roleNames = Arrays.stream(roles).map(Role::getName).toArray(String[]::new);
        String selectedRoleName = selectOption("role", roleNames);
        for (Role role : roles) {
            if (role.getName().equals(selectedRoleName)) return role;
        }
        return null;
    }

    @Override
    public Upgrade chooseUpgrade(Upgrade[] upgrades) {
        String[] options = new String[upgrades.length];

        for (int i = 0; i < upgrades.length; i++) {
            options[i] = "Rank " + upgrades[i].getRank() +
                    " (" + upgrades[i].getAmount() + " " + upgrades[i].getCurrency() + "s)";
        }

        String optionSelected = selectOption("upgrade", options);

        // this is really dumb but it's better than trying to parse the string lol
        // basically I loop through the options until i find selectedOption
        // and I return the upgrade at that index
        for (int i = 0; i < options.length; i++) {
            if (options[i].equals(optionSelected)) {
                return upgrades[i];
            }
        }
        return null;
    }

    private String selectOption(String toSelect, String[] options) {
        clear(interfacePane);
        interfacePane.setLayout(new FlowLayout());
        interfacePane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        frame.revalidate();
        frame.repaint();

        JLabel prompt = new JLabel("Select a(n) " + toSelect + ":");
        interfacePane.add(prompt);

        final String[] selected = new String[1];

        for (String option : options) {
            JButton button = new JButton(option);
            button.addActionListener((ActionEvent e) -> selected[0] = option);
            interfacePane.add(button);
        }

        frame.revalidate();
        frame.repaint();

        while (selected[0] == null) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }

        clear(interfacePane);
        return selected[0];
    }

    private void clear(JLayeredPane pane) {
        pane.removeAll();
        pane.revalidate();
        pane.repaint();
    }
}
