public class TextDisplay implements Display {
    private final String welcomeMessage = "Welcome to Deadwood!";
    private final String invalidInputMessage = "Invalid input.";

    public TextDisplay() {

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWelcome() {
        displayMessage(welcomeMessage);
    }

    public void displayInvalidInput(String message) {
        displayMessage(invalidInputMessage + " " + message);
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

    public void displayRoomInfo(Room room) {
        System.out.println("Name: " + room.getName());
        System.out.println("Neighbors: " + join(room.getNeighbors(), ", "));
    }

    public void displayGameState(Board board, Player[] players) {}

    public void displayStandings(Player[] players) {}

    public void displayUpdatedRank(int newRank) {
        System.out.println("Congratulations! You are now rank " + newRank + "!");
    }

    // uml says weird parameters, james if you remember what the parameters should be, please change this lol
    public void displayActOutcome(boolean success, int dollarsEarned, int creditsEarned, int shotsLeft) {}

    public void displayRehearseOutcome() {}

    public void displayMoveOutcome(Room room) {}

    // were gonna have to come up with something else for the winnings because
    // java doesn't do tuples and we cant have multiple data types in one list :(
    public void displayWrapOutcome(Set set, int scenesLeft) {}

    public void announceWinner(Player player) {}

    public String join(String[] arr, String connector) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            str.append(arr[i] + connector);
        }
        str.append(arr[arr.length - 1]);

        return str.toString();
    }
}
