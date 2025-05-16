public interface Input {
    int getNumPlayers();
    String getPlayerName(int playerNum);
    String selectAction(String[] action);
    Room selectRoom(String[] room);
    Role selectRole(String[] role);
    int chooseRank();
}
