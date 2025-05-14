public class GameManager {
    private final Player[] players;
    private final Board board;
    private int daysLeft;
    private int currentPlayer;
    private Controller controller;

    public GameManager(Player[] players, Board board, Deck deck, Controller controller, int days) {
        this.players = players;
        this.board = board;
        this.daysLeft = days;
        currentPlayer = (int) (Math.random() * players.length);
        this.controller = controller;
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

    private void dealToSets() {
        // loop sets
        // draw a card and set set's card to the card drawn
    }

    public void wrap(Set set) {
        // follow rules
    }

    public void nextTurn() {
        players[currentPlayer].takeTurn(controller);
        currentPlayer ++;
        currentPlayer %= players.length;
    }

    public Player calcWinner() {
        return players[0];
    }
}
