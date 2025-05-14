import java.util.HashMap;

public class TextDisplay implements Display {
    public TextDisplay() {

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWelcome() {
        displayMessage("Welcome to Deadwood!");
    }

    public void displayInvalidInput(String message) {
        displayMessage("Invalid input. " + message);
    }

    public void displayPlayerTurn(Player player) {
        System.out.println("It is " + player.getName() + "'s turn.");
    }

    public void displayPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName());
        System.out.println("Rank: " + player.getRank());
        System.out.println("Points: " + player.getPoints());
        // print assets
        System.out.println("Location: " + player.getLocation().getName());

        if (player.hasRole()) {
            System.out.println("Role" + player.getRole().getName());
        }

    }

    public void displayUpgradeCosts() {
        // loop though upgrade cost hashmap
    }

    public void displayRoomInfo(Room room) {
        System.out.println("Name: " + room.getName());
        System.out.println("Neighbors: " + join(room.getNeighbors(), ", "));
    }

    //public void displayGameState(Board board, Player[] players) {}

    public void displayStandings(Player[] players) {
        // loop through players, sort by points
    }

    public void displayUpdatedRank(int newRank) {
        System.out.println("Congratulations! You are now rank " + newRank + "!");
    }

    public void displayActOutcome(boolean success, int currencyEarned, String currency, int shotsLeft) {
        if (success) System.out.println("Success!");
        else System.out.println("Failure...");
        System.out.println("You earned " + currencyEarned + " " + currency + "(s).");
        System.out.println("There are " + shotsLeft + " scenes left to shoot.");
    }

    public void displayRehearseOutcome() {
        System.out.println("You have rehearsed. Acting will now be more likely to succeed.");
    }

    public void displayMoveOutcome(Room room) {
        System.out.println("You are now in the " + room.getName() + ".");
    }

    // playerEarnings is a list of HashMaps with playerIndex(from gameManager), dollars, credits
    public void displayWrapOutcome(Set set, HashMap<String, Integer>[] playerEarnings, int scenesLeft) {}

    public void announceWinner(Player player) {
        System.out.println(player.getName() + "wins with " + player.getPoints() + "points!");
    }

    public String join(String[] arr, String connector) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            str.append(arr[i]).append(connector);
        }
        str.append(arr[arr.length - 1]);

        return str.toString();
    }
}
