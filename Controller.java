import java.util.HashMap;

public class Controller {
    private final Input input;
    private final Display display;

    public Controller(Input input, Display display) {
        this.input = input;
        this.display = display;
    }

    public void displayWelcome() {
        display.displayWelcome();
    }

    public void displayInvalidInput(String message) {
        display.displayInvalidInput(message);
    }

    public void displayPlayerTurn(Player player) {
        display.displayPlayerTurn(player);
    }

    public void displayPlayerInfo(Player player) {
        display.displayPlayerInfo(player);
    }

    public void displayRoomInfo(Room room) {
        display.displayRoomInfo(room);
    }

    public void displayRoleInfo(Role role) {
        display.displayRoleInfo(role);
    }

    public void displayStandings(Player[] players) {
        display.displayStandings(players);
    }

    public void displayPlayerLocations(Player[] players) {
        display.displayPlayerLocations(players);
    }

    public void displayUpdatedRank(int newRank) {
        display.displayUpdatedRank(newRank);
    }

    public void displayUpgradeCosts() {
        display.displayUpgradeCosts();
    }

    public void displayRehearseOutcome(Role role) {
        display.displayRehearseOutcome(role);
    }

    public void displayActOutcome(boolean success, int currencyEarned, String currency, int shotsLeft) {
        display.displayActOutcome(success, currencyEarned, currency, shotsLeft);
    }

    public void displayMoveOutcome(Room room) {
        display.displayMoveOutcome(room);
    }

    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {
        display.displayWrapOutcome(set, playerEarnings, scenesLeft);
    }

    public void displayEndGame(Player[] players) {
        display.displayGameEnd(players);
    }

    public void announceWinner(Player player) {
        display.announceWinner(player);
    }

    // input stuff
    public int getNumPlayers() {
        return input.getNumPlayers();
    }

    public String getPlayerName(int playerNum) {
        return input.getPlayerName(playerNum);
    }

    public String selectAction(String[] actions) {
        return input.selectAction(actions);
    }

    public Room selectRoom(String[] rooms) {
        return input.selectRoom(rooms);
    }

    public Role selectRole(Role[] roles) {
        return input.selectRole(roles);
    }

    public int chooseRank(int currRank, int maxRank) {
        return input.chooseRank(currRank, maxRank);
    }

    public void displayTakeRoleOutcome(Role role) {
        display.displayTakeRoleOutcome(role);
    }
}
