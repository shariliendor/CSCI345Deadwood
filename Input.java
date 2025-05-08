public interface Input {
    // james if you could copy paste these methods into the controller that would be amazing
    default int getNumPlayers() {
        return 0;
    }

    default String getPlayerName(int playerNum) {
        return null;
    }

    default String selectAction(String[] action) {
        return null;
    }

    default Room selectRoom(String[] room) {
        return null;
    }

    default Role selectRole(String[] role) {
        return null;
    }

    default int chooseRank() {
        return 0;
    }

    default String selectOption(String[] option) {
        return null;
    }

}
