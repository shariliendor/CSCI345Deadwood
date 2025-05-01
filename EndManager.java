public class EndManager {
    private Player[] players;

    public EndManager(Player[] players) {
        this.players = players;
    }

    private int calcPoints(Player player) {
        return 0;
    }

    public Player calcWinner() {
        return this.players[0];
    }

    public void announceWinner(Player player) {

    }
}
