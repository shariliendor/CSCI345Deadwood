public interface Display {
    default void displayMessage(String message) {}

    default void displayWelcome() {}

    default void displayInvalidInput(String message) {}

    default void displayPlayerTurn(Player player) {}

    default void displayPlayerInfo(Player player) {}

    default void displayRoomInfo(Room room) {}

    default void displayGameState(Board board, Player[] players) {}

    default void displayStandings(Player[] players) {}

    default void displayUpdatedRank(int newRank) {}

    // uml says weird parameters, james if you remember what the parameters should be, please change this lol
    default void displayActOutcome(boolean success, int dollarsEarned, int creditsEarned, int shotsLeft) {}

    default void displayRehearseOutcome() {}

    default void displayMoveOutcome(Room room) {}

    // were gonna have to come up with something else for the winnings because
    // java doesn't do tuples and we cant have multiple data types in one list :(
    default void displayWrapOutcome(Set set, int scenesLeft) {}

    default void announceWinner(Player player) {}
}
