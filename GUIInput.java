public class GUIInput implements Input{
    @Override
    public int getNumPlayers() {
        return 0;
    }

    @Override
    public String getPlayerName(int playerNum) {
        return null;
    }

    @Override
    public String selectAction(String[] action) {
        return null;
    }

    @Override
    public Room selectRoom(String[] room) {
        return null;
    }

    @Override
    public Role selectRole(Role[] roles) {
        return null;
    }

    @Override
    public int chooseRank(int currRank, int maxRank) {
        return 0;
    }
}
