public class GameManager {
    private Player[] players;
    private Board board;
    private int daysLeft;
    private int currentPlayer;

    public GameManager(Player[] players, Board board, int days) {
        this.players = players;
        this.board = board;
        this.daysLeft = days;
        currentPlayer = (int) (Math.random() * players.length) - 1;
    }

    public void playDay() {
        // people take turns until one scene left to shoot
        daysLeft --;
    }

    public void wrap(Set set) {

    }

    public void nextTurn() {
        players[currentPlayer].takeTurn();
        currentPlayer ++;
        currentPlayer %= players.length;
    }
}
