public class GameManager {
    private final Player[] players;
    private final Board board;
    private final Deck deck;
    private int daysLeft;
    private int currentPlayer;
    private final Controller controller;

    public GameManager(Player[] players, Board board, Deck deck, Controller controller, int days) {
        this.players = players;
        this.board = board;
        this.deck = deck;
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
        dealToSets();

        for (Player player: players) {
            player.setLocation(board.getTrailer());
        }

        while(board.getScenesToShoot() > 1) {
            nextTurn();
        }
        daysLeft --;
    }

    private void dealToSets() {
        for (Set set: board.getSets()) {
            set.setCard(deck.draw());
        }
    }

    public void wrap(Set set) {//chester
        // follow rules
        // loop players to see whos on set
    }

    public void nextTurn() {
        players[currentPlayer].takeTurn(controller);
        currentPlayer ++;
        currentPlayer %= players.length;
    }

    public Player calcWinner() {//chester
        // find player with most points
        return players[0];
    }
}
