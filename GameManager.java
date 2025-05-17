import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
        controller.displayWelcome();
        boolean gameEnded = false;

        while (daysLeft > 0 && !gameEnded) {
            gameEnded = playDay();
        }

        controller.displayEndGame(getStandings());
    }

    public boolean playDay() {
        dealToSets();
        board.resetShotMarkers();
        board.activateSets();

        for (Player player: players) {
            player.setLocation(board.getTrailer());
        }

        while(board.getSetsToShoot() > 1) {
            boolean endGame = nextTurn();

            for (Set set: board.getSets()) {
                if (set.isActive() && set.getRemainingShots() == 0) {
                    wrap(set);
                }
            }

            return endGame;
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
        ArrayList<Player> playersOnCard = getPlayersOnCard(set);
        ArrayList<Player> playersOffCard = getPlayersOffCard(set);
        HashMap<Player, Integer> playerEarnings= new HashMap<>();

        // on card players get dollar bonuses based on rolling dice
        int[] diceRolls = new int[set.getCard().getBudget()];
        for (int i = 0; i < set.getCard().getBudget(); i++) {
            diceRolls[i] = (int) (Math.random() * 6) + 1;
        }
        Arrays.sort(diceRolls); // sorted in ascending order

        // distribute the highest roll to first role, etc
        int numRolesOnCard = set.getCard().getRoles().size();
        int[] onCardEarnings = new int[numRolesOnCard];
        for (int i = diceRolls.length - 1; i >= 0; i--) {
            onCardEarnings[(i - diceRolls.length + 1) % onCardEarnings.length] += diceRolls[i];
        }

        // give out rewards
        for (int i = 0; i < onCardEarnings.length; i++) {
            playersOnCard.get(i).earn(onCardEarnings[i], "dollar");
            playerEarnings.put(playersOnCard.get(i), onCardEarnings[i]);
        }


        for (int i = 0; i < numRolesOnCard; i++) {
            playersOnCard.get(i).earn(onCardEarnings[i], "dollars");
            playerEarnings.put(playersOnCard.get(i), onCardEarnings[i]);
        }

        // off card players get dollar bonuses equal to the level of their role
        for (Player player : playersOffCard) {
            int bonus = player.getRole().getLevel();
            player.earn(bonus, "dollar");
            playerEarnings.put(player, bonus);
        }

        // display outcome
        controller.displayWrapOutcome(set, playerEarnings, board.getSetsToShoot());

        // clear roles and take players off roles
        set.clearRoles();
        clearPlayerRoles(playersOffCard);
        clearPlayerRoles(playersOnCard);

        set.setActive(false);
    }

    // returns whether the game was ended
    public boolean nextTurn() {
        boolean gameEnded = players[currentPlayer].takeTurn(controller);
        currentPlayer ++;
        currentPlayer %= players.length;

        return gameEnded;
    }

    private Player calcWinner() {
        return getStandings()[0];
    }

    private Player[] getStandings() {
        ArrayList<Player> toBeSorted = new ArrayList<>(List.of(players));
        Player[] standings = new Player[players.length];

        for (int i = 0; i < players.length; i++) {
            Player highScorePlayer = toBeSorted.get(0);

            for (Player player : toBeSorted) {
                if (player.getPoints() > highScorePlayer.getPoints()) {
                    highScorePlayer = player;
                }
            }

            standings[i] = highScorePlayer;
            toBeSorted.remove(highScorePlayer);
        }

        return standings;
    }

    private ArrayList<Player> getPlayersOnRoles(Role[] roles) {
        ArrayList<Player> players = new ArrayList<>();
        for (Role role: roles) {
            if (role.isTaken()) {
                players.add(role.getPlayer());
            }
        }

        return players;
    }

    private ArrayList<Player> getPlayersOnCard(Set set) {
        return getPlayersOnRoles(set.getCard().getRoles().toArray(new Role[0]));
    }

    private ArrayList<Player> getPlayersOffCard(Set set) {
        return getPlayersOnRoles(set.getExtraRoles());
    }

    private void clearPlayerRoles(ArrayList<Player> players) {
        for (Player player: players) {
            player.clearRole();
        }
    }
}
