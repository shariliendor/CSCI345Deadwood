import java.util.Scanner;

public class TextInput implements Input{
    Scanner sc;

    public TextInput(Scanner sc) {
        this.sc = sc;
    }

    public int getNumPlayers() {
        System.out.print("Enter the number of players: ");
        return Integer.parseInt(sc.nextLine());
    }

    public String getPlayerName(int playerNum) {
        System.out.print("Enter name for player " + playerNum + ": ");
        return sc.nextLine();
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
