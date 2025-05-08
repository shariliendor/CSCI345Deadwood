public interface Input {
    // james if you could copy paste these methods into the controller that would be amazing
    default void getNumPlayers() {}

    default void getPlayerName(int name) {}

    default void selectAction(String[] action) {}

    default void selectRoom(String[] room) {}

    default void selectRole(String[] role) {}

    default void chooseRank() {}

    default void selectOption(String[] option) {}

}
