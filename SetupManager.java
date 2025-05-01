public class SetupManager {
    public SetupManager() {}

    public int getDaysToPlay(int numPlayers) {
        if (numPlayers <= 3) {
            return 3;
        }
        return 4;
    }

    public int getStartRank(int numPlayers) {
        if (numPlayers >= 7) {
            return 2;
        }
        return 1;
    }

    public int getStartCredits(int numPlayers) {
        if (numPlayers == 5) {
            return 2;
        } else if (numPlayers == 6) {
            return 4;
        }
        return 0;
    }

    public Player[] createPlayers(int numPlayers) {
        int startRank = getStartRank(numPlayers);
        int startCredits = getStartCredits(numPlayers);
        String name;

        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            name = "Player " + (i + 1); // could ask players for names instead
            Player newPlayer = new Player(name, startRank, startCredits);
            players[i] = newPlayer;
        }

        return players;
    }
}
