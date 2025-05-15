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
        String roomName = selectOption("room", rooms);
        return Room.getRoom(roomName);
    }

    public Role selectRole(String[] roles) {
        // figure out how to turn a string into a role
        // maybe cards and sets have roleName hashMaps
        return null;
    }

    public int chooseRank() {
        // when user inputs a value, check if player can buy rank
        return 0;
    }// give possible ranks param

    private String selectOption(String toSelect, String[] options) {
        System.out.println("Select a(n) " + toSelect + ": ");

        for (int i = 0; i < options.length; i++) {
            System.out.println("[" + (i+1) + "] " + options[i]);
        }

        // user picked a number from 1 to size, but index needs to be one less
        int index = getNumWithinRange(1, options.length) - 1;

        return options[index];
    }

    private int getNumWithinRange(int min, int max) {
        int input = Integer.parseInt(sc.nextLine());

        while (input < min || input > max) {
            System.out.println("Invalid input. Enter a number between " + min + " and " + max);
            input = Integer.parseInt(sc.nextLine());
        }

        return input;
    }

}
