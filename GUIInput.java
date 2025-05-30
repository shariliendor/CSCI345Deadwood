import javax.swing.*;

public class GUIInput implements Input{
    private JFrame frame;

    public GUIInput(JFrame frame) {
        this.frame = frame;
    }
    @Override
    public int getNumPlayers() {
        while (true) {
            String input = JOptionPane.showInputDialog(frame, "Enter the number of players (2-8):");
            try {
                int num = Integer.parseInt(input);
                if (num >= 2 && num <= 8) {
                    return num;
                }
            } catch (NumberFormatException ignored) {}
            JOptionPane.showMessageDialog(frame, "Invalid input. Enter a number between 2 and 8.");
        }
    }

    @Override
    public String getPlayerName(int playerNum) {
        return JOptionPane.showInputDialog(frame, "Enter name for player " + playerNum + ":");
    }

    @Override
    public String selectAction(String[] action) {
        // make some buttons
        return null;
    }

    @Override
    public Room selectRoom(String[] room) {
        // make some buttons
        return null;
    }

    @Override
    public Role selectRole(Role[] roles) {
        // make some buttons
        return null;
    }

    @Override
    public int chooseRank(int currRank, int maxRank) {// add currency
        // button grid with unavailable upgrades grayed out or gone
        return 0;
    }
}
