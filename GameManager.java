import java.util.*;

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
        this.currentPlayer = (int) (Math.random() * players.length);
        this.controller = controller;
    }

    public void playGame() {
        controller.displayStandings(getStandings());

        boolean gameEnded = false;
        deck.shuffle();

        while (daysLeft > 0 && !gameEnded) {
            gameEnded = playDay();
        }

        controller.displayEndGame(getStandings());
    }

    public boolean playDay() {
        dealToSets();
        board.resetShotMarkers();
        board.activateSets();

        // Set all players to trailer at day start and show room image
        for (Player player : players) {
            player.setLocation(board.getTrailer());
            controller.displayRoomImage(player.getLocation());
        }

        while (board.getSetsToShoot() > 1) {
            controller.displayPlayerLocations(players);
            boolean endGame = nextTurn();

            for (Set set : board.getSets()) {
                if (set.isActive() && set.getRemainingShots() == 0) {
                    wrap(set);
                }
            }

            if (endGame)
                return true;
        }
        daysLeft--;

        return false;
    }

    private void dealToSets() {
        for (Set set : board.getSets()) {
            Card card = deck.draw();
            set.setCard(card);
        }
    }

    public void wrap(Set set) {
        ArrayList<Player> playersOnCard = getPlayersOnCard(set);
        ArrayList<Player> playersOffCard = getPlayersOffCard(set);
        HashMap<Player, Integer> playerEarnings = new HashMap<>();

        distributeOnCardBonuses(set, playersOnCard, playerEarnings);

        // Off card players get dollar bonuses equal to the level of their role
        for (Player player : playersOffCard) {
            int bonus = player.getRole().getLevel();
            player.earn(bonus, "dollar");
            playerEarnings.put(player, bonus);
        }

        // Display wrap outcome
        controller.displayWrapOutcome(set, playerEarnings, board.getSetsToShoot());

        // Clear roles and remove players from roles
        set.clearRoles();
        set.setActive(false);
        clearPlayerRoles(playersOffCard);
        clearPlayerRoles(playersOnCard);
    }

    private void distributeOnCardBonuses(Set set, ArrayList<Player> playersOnCard,
            HashMap<Player, Integer> playerEarnings) {
        if (playersOnCard.isEmpty()) {
            return;
        }

        int budget = set.getCard().getBudget();
        int[] diceRolls = new int[budget];
        for (int i = 0; i < budget; i++) {
            diceRolls[i] = (int) (Math.random() * 6) + 1;
        }
        Arrays.sort(diceRolls);
        // Reverse for descending order
        for (int i = 0; i < diceRolls.length / 2; i++) {
            int temp = diceRolls[i];
            diceRolls[i] = diceRolls[diceRolls.length - i - 1];
            diceRolls[diceRolls.length - i - 1] = temp;
        }

        int numRolesOnCard = set.getCard().getRoles().size();
        int[] onCardEarnings = new int[numRolesOnCard];
        for (int i = 0; i < diceRolls.length; i++) {
            onCardEarnings[i % onCardEarnings.length] += diceRolls[i];
        }

        for (int i = 0; i < numRolesOnCard; i++) {
            Role role = set.getCard().getRoles().get(i);
            if (role.isTaken()) {
                role.getPlayer().earn(onCardEarnings[i], "dollar");
                playerEarnings.put(role.getPlayer(), onCardEarnings[i]);
            }
        }
    }

    // Returns whether the game was ended
    public boolean nextTurn() {
        controller.setLastActingPlayer(players[currentPlayer]); // <== Add this line
        boolean gameEnded = players[currentPlayer].takeTurn(controller);

        // After player's turn, update displayed room image
        controller.displayRoomImage(players[currentPlayer].getLocation());

        currentPlayer++;
        currentPlayer %= players.length;
        return gameEnded;
    }

    public Player calcWinner() {
        return getStandings()[0];
    }

    public Player[] getStandings() {
        ArrayList<Player> toBeSorted = new ArrayList<>(List.of(players));
        Player[] standings = new Player[players.length];

        for (int i = 0; i < players.length; i++) {
            Player highScorePlayer = toBeSorted.get(0);
            int highScore = highScorePlayer.getPoints() + highScorePlayer.getRank() * 5;

            for (Player player : toBeSorted) {
                int playerScore = player.getPoints() + player.getRank() * 5;
                if (playerScore > highScore) {
                    highScorePlayer = player;
                    highScore = playerScore;
                }
            }

            standings[i] = highScorePlayer;
            toBeSorted.remove(highScorePlayer);
        }

        return standings;
    }

    private ArrayList<Player> getPlayersOnRoles(Role[] roles) {
        ArrayList<Player> players = new ArrayList<>();
        for (Role role : roles) {
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
        for (Player player : players) {
            player.clearRole();
        }
    }
}
