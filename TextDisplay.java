import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextDisplay implements Display {
    public TextDisplay() {

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayWelcome() {
        displayMessage("Welcome to Deadwood!");
    }

    public void displayInvalidInput(String message) {
        displayMessage("Invalid input. " + message);
    }

    public void displayPlayerTurn(Player player) {
        System.out.println("It is " + player.getName() + "'s turn.");
    }

    public void displayCurrentRank(Player player) {
        System.out.println(player.getName() + " is currently rank " + player.getRank() + ".");
    }

    public void displayPlayerInfo(Player player) {
        System.out.println("Name: " + player.getName());
        System.out.println("Rank: " + player.getRank());
        System.out.println("Points: " + player.getPoints());
        System.out.println("Location: " + player.getLocation().getName());

        if (player.hasRole()) {
            System.out.println("Role: " + player.getRole().getName());
        } else {
            System.out.println("Role: None");
        }

        HashMap<String, Integer> assets = player.getAssets();
        for (String currency: assets.keySet()) {
            System.out.println(currency + "s: " + assets.get(currency));
        }

        System.out.println();
    }

    public void displayPlayerLocations(Player[] players) {//Chester
        System.out.println();
        System.out.println("Player Locations");
        for (Player player: players) {
            System.out.println(player.getName() + ": " + player.getLocation().getName());
        }
    }

    public void displayUpgradeCosts() {
        System.out.println("Upgrade Costs:");
        for (int r = 2; r <= UpgradeManager.getMaxRank(); r++) {
            int dollarCost = UpgradeManager.getDollarCost(r);
            int creditCost = UpgradeManager.getCreditCost(r);
            System.out.println("Rank " + r + ": " + dollarCost + " dollars or " + creditCost + " credits");
        }
    }

    public void displayRoomInfo(Room room) {
        System.out.println("Name: " + room.getName());
        System.out.println("Neighbors: " + join(room.getNeighbors(), ", "));
    }

    public void displayRoleInfo(Role role) {
        String takenStatus = role.isTaken() ? "Taken" : "Available";
        System.out.println("Role: " + role.getName() +
                           ", Level: " + role.getLevel() +
                           ", " + takenStatus);
    }

    public void displayStandings(Player[] players) {
        for (int i = 0; i < players.length; i++) {
            System.out.println((i + 1) + ": " + players[i].getName() + " (" + players[i].getPoints() + " points)");
        }
    }

    public void displayUpdatedRank(int newRank) {
        System.out.println("Congratulations! You are now rank " + newRank + "!");
    }

    public void displayActOutcome(boolean success, HashMap<String, Integer> earnings, int shotsLeft) {
        if (success) System.out.println("Success!");
        else System.out.println("Failure...");

        if(earnings.isEmpty()) {
            System.out.println("You didn't earn anything.");
        } else {
            for (String currency: earnings.keySet()) {
                System.out.println("You earned " + earnings.get(currency) + " " + currency + "(s).");
            }
        }

        System.out.println("There are " + shotsLeft + " scenes left to shoot on this set.");
    }

    public void displayRehearseOutcome(Role role) {
        System.out.println("You have rehearsed. There are now " + role.getPracticeChips() + " practice chips on " + role.getName());
    }

    public void displayMoveOutcome(Room room) {
        System.out.println("You are now in the " + room.getName() + ".");
    }

    // playerEarnings is a list of HashMaps with playerIndex(from gameManager), dollars, credits
    public void displayWrapOutcome(Set set, HashMap<Player, Integer> playerEarnings, int scenesLeft) {
        System.out.println();
        System.out.println(set.getName() + " has been wrapped!");

        for (Player player: playerEarnings.keySet()) {
            System.out.println(player.getName() + " earned " + playerEarnings.get(player) + " dollars.");
        }

        System.out.println();
        System.out.println("There are " + scenesLeft + " sets left to shoot.");
    }

    public void announceWinner(Player player) {
        System.out.println(player.getName() + " wins with " + (player.getPoints() + player.getRank()*5) + " points!");
    }

    public void displayGameEnd(Player[] standings) {
        System.out.println("\n===== Game Over! Final Standings =====");

        for (int i = 0; i < standings.length; i++) {
            Player p = standings[i];
            int basePoints = p.getPoints();
            int rankBonus = p.getRank() * 5;
            int finalScore = basePoints + rankBonus;

            System.out.println((i + 1) + ". " + p.getName()
                + " - Base Points: " + basePoints
                + ", Rank Bonus: " + rankBonus
               + ", Final Score: " + finalScore);
        }

        System.out.println("======================================");
        announceWinner(standings[0]);
    }

    public String join(String[] arr, String connector) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < arr.length - 1; i++) {
            str.append(arr[i]).append(connector);
        }
        str.append(arr[arr.length - 1]);

        return str.toString();
    }

    public void displayTakeRoleOutcome(Role role) {
        // Try to get the player's current room and check if it's a Set
        Room location = role.getPlayer().getLocation();

        String takenType = "Unknown role type";
        if (location instanceof Set set) {
            takenType = set.isOnCardRole(role) ? "On-card role" : "Off-card role";
        }

        System.out.println("You have taken the " + takenType + ": \"" + role.getName() + "\" (Level " + role.getLevel() + ")");
        System.out.println("Line: \"" + role.getLine() + "\"");
    }
    public void showRoomImage(Room room){
        
    }
}
