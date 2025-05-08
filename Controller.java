public class Controller {
    private Input input;
    private Display display;

    public Controller(Input input, Display display) {
        this.input = input;
        this.display = display;
    }

    public void displayMessage(String message) {
        display.displayMessage(message);
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

    public void displayGameState(Board board, Player[] players) {
        display.displayGameState(board, players);
    }

    public void displayStandings(Player[] players) {
        display.displayStandings(players);
    }

    public void displayUpdatedRank(int newRank) {
        display.displayUpdatedRank(newRank);
    }

    // uml says weird parameters, james if you remember what the parameters should be, please change this lol
    public void displayActOutcome(boolean success, int dollarsEarned, int creditsEarned, int shotsLeft) {
        display.displayActOutcome(success, dollarsEarned, creditsEarned, shotsLeft);
    }

    public void displayRehearseOutcome() {
        display.displayRehearseOutcome();
    }

    public void displayMoveOutcome(Room room) {
        display.displayMoveOutcome(room);
    }

    // were gonna have to come up with something else for the winnings because
    // java doesn't do tuples and we cant have multiple data types in one list :(
    public void displayWrapOutcome(Set set, int scenesLeft) {
        display.displayWrapOutcome(set, scenesLeft);
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

    public Role selectRole(String[] roles) {
        return input.selectRole(roles);
    }

    public int chooseRank() {
        return input.chooseRank();
    }

    public String selectOption(String[] options) {
        return input.selectOption(options);
    }
}
