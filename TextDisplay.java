public class TextDisplay implements Display {
    public TextDisplay() {

    }

    public void displayMessage(String message) {}

    public void displayWelcome() {}

    public void displayInvalidInput(String message) {}

    public void displayPlayerTurn(Player player) {}

    public void displayPlayerInfo(Player player) {}

    public void displayRoomInfo(Room room) {}

    public void displayGameState(Board board, Player[] players) {}

    public void displayStandings(Player[] players) {}

    public void displayUpdatedRank(int newRank) {}

    // uml says weird parameters, james if you remember what the parameters should be, please change this lol
    public void displayActOutcome(boolean success, int dollarsEarned, int creditsEarned, int shotsLeft) {}

    public void displayRehearseOutcome() {}

    public void displayMoveOutcome(Room room) {}

    // were gonna have to come up with something else for the winnings because
    // java doesn't do tuples and we cant have multiple data types in one list :(
    public void displayWrapOutcome(Set set, int scenesLeft) {}

    public void announceWinner(Player player) {}
}
