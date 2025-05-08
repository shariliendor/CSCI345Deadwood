import java.util.Scanner;

public class TextInput implements Input{
    Scanner sc;

    public TextInput(Scanner sc) {
        this.sc = sc;
    }

    public int getNumPlayers() {
        return 0;
    }

    public String getPlayerName(int playerNum) {
        return null;
    }

    public String selectAction(String[] actions) {
        return selectOption("action", actions);
    }

    public Room selectRoom(String[] rooms) {
        return null;
    }

    public Role selectRole(String[] roles) {
        return null;
    }

    public int chooseRank() {
        return 0;
    }// give possible ranks param

    public String selectOption(String toSelect, String[] options) {
        return null;
    }

}
