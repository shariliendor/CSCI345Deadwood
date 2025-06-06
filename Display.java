import java.util.HashMap;

public interface Display {
    void displayWelcome();

    void displayInvalidInput(String message);

    void displayPlayerTurn(Player player);

    void displayPlayerInfo(Player player);

    void displayRoomInfo(Room room);

    void displayRoleInfo(Role role);

    void displayStandings(Player[] players);

    void displayPlayerLocations(Player[] players);

    void displayUpdatedRank(int newRank);

    void displayUpgradeCosts();

    void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft);

    void displayRehearseOutcome(Role role);

    void displayMoveOutcome(Room room);

    void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft);

    void announceWinner(Player player);

    void displayGameEnd(Player[] players);

    void displayTakeRoleOutcome(Role role);

    default void showRoomImage(Room room) {}

    default void displayDaysLeft(int daysLeft) {}
}
