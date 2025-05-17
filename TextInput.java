import java.util.Scanner;
import java.util.Arrays;

public class TextInput implements Input{
    Scanner sc;

    public TextInput(Scanner sc) {
        this.sc = sc;
    }

    public int getNumPlayers() {
        System.out.print("Enter the number of players: ");
        return getNumWithinRange(2, 8);
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

    public Role selectRole(Role[] roles) {
        String[] roleNames = Arrays.stream(roles)
                                   .map(Role::getName)
                                   .toArray(String[]::new);
        String selectedRoleName = selectOption("role", roleNames);
        for (Role role : roles) {
            if (role.getName().equals(selectedRoleName)) {
                return role;
            }
        }
        return null; 
    }

    public int chooseRank() {//james
        // ask for number between currRank +1 and max rank
        // validate with player
        // return rank
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
        boolean error = false;
        int input = 0;

        try {
            input = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            error = true;
        }

        while (input < min || input > max || error) {
            System.out.println("Invalid input. Enter a number between " + min + " and " + max);

            try {
                input = Integer.parseInt(sc.nextLine());
                error = false;
            } catch (NumberFormatException e) {
                error = true;
            }
        }

        return input;
    }

}
