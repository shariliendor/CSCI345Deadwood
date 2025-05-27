import javax.swing.*;

public class GUIInput implements Input{
    private JPanel interfacePanel = new JPanel();

    public GUIInput(JFrame frame) {

    }
    @Override
    public int getNumPlayers() {
        // text input box
        // validate number between 2 and 8
        return 0;
    }

    @Override
    public String getPlayerName(int playerNum) {
        // text input box
        return null;
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
