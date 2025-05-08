public class GameManager {
    private final Player[] players;
    private final Board board;
    private int daysLeft;
    private int currentPlayer;

    public GameManager(Player[] players, Board board, Deck deck, int days) {
        this.players = players;
        this.board = board;
        this.daysLeft = days;
        currentPlayer = (int) (Math.random() * players.length) - 1;
    }

    public void playGame() {
        while (daysLeft > 0) {
            playDay();
        }
    }

    public void playDay() {
        while(board.getScenesToShoot() > 1) {
            nextTurn();
        }
        daysLeft --;
    }

    public void wrap(Set set) {

    }

    public void nextTurn() {
        players[currentPlayer].takeTurn();
        currentPlayer ++;
        currentPlayer %= players.length;
    }

    public Player calcWinner() {
        return players[0];
    }
}
