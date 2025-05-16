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
        boolean gameEnded = false;

        while (daysLeft > 0 && !gameEnded) {
            gameEnded = playDay();
        }

        controller.displayEndGame(players);
    }

    public boolean playDay() {
        dealToSets();

        for (Player player: players) {
            player.setLocation(board.getTrailer());
        }

        while(board.getScenesToShoot() > 1) {
            if (nextTurn()) {
                return true;
            }
        }
        daysLeft --;

        return false;
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

    // returns whether the game was ended
    public boolean nextTurn() {
        boolean gameEnded = players[currentPlayer].takeTurn(controller);
        currentPlayer ++;
        currentPlayer %= players.length;

        return gameEnded;
    }

    public Player calcWinner() {//chester
        // find player with most points
        return players[0];
    }
}
