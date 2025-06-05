public interface Input {
    int getNumPlayers();
    String getPlayerName(int playerNum);
    String selectAction(String[] action);
    Room selectRoom(String[] room);
    Role selectRole(Role[] roles);

    // defaulted so that specific upgrades can be chosen in the GUI
    default int chooseRank(int currRank, int maxRank) {return 0;}

    default Upgrade chooseUpgrade(Upgrade[] possibleUpgrades) {
        // get min and max ranks
        int minRank = Integer.MAX_VALUE;
        int maxRank = 0;

        for (Upgrade upgrade: possibleUpgrades) {
            if (upgrade.getRank() < minRank) {
                minRank = upgrade.getRank();
            }
            if (upgrade.getRank() > maxRank) {
                maxRank = upgrade.getRank();
            }
        }

        // choose a rank
        int newRank = chooseRank(minRank, maxRank);

        // return an upgrade that matches the new rank
        for (Upgrade upgrade: possibleUpgrades) {
            if (upgrade.getRank() == newRank) {
                return upgrade;
            }
        }
        return null;
    }
}
