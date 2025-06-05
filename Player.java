import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private final String name;
    private int rank;
    private Room location;
    private Role role;
    private HashMap<String, Integer> assets;
    private int points;

    public Player(String name, int startRank, int startCredits) {
        this.name = name;
        this.rank = startRank;
        this.location = Room.getRoom("trailer");
        this.role = null;
        this.points = 0;
        this.assets = initAssets(0, startCredits);
    }

    // returns whether the game was ended
    public boolean takeTurn(Controller controller) {
        controller.displayPlayerTurn(this);
        controller.displayPlayerInfo(this);
        ArrayList<String> actionsTaken = new ArrayList<>();

        ArrayList<String> possibleActions = ActionManager.getPossibleActions(this, actionsTaken);
        while (!possibleActions.isEmpty()) {
            String actionToTake = controller.selectAction(possibleActions.toArray(new String[0]));

            if (actionToTake.equals("end game")) {
                return true;
            }

            ActionManager.executeAction(this, actionToTake, controller);
            actionsTaken.add(actionToTake);
            possibleActions = ActionManager.getPossibleActions(this, actionsTaken);
        }

        return false;
    }

    public void upgrade(Controller controller) {
        if (!location.getName().equalsIgnoreCase("office")) {
            controller.displayInvalidInput("You must be in the Casting Office to upgrade your rank.");
            return;
        }

        controller.displayUpgradeCosts();


        Upgrade[] possibleUpgrades = UpgradeManager.getPossibleUpgrades(this);

        if (possibleUpgrades.length == 0) {
            controller.displayInvalidInput("You cannot afford any rank upgrades at this time.");
            return;
        }

        Upgrade chosenUpgrade = controller.chooseUpgrade(possibleUpgrades);
        rank = chosenUpgrade.getRank();
        spend(chosenUpgrade.getAmount(), chosenUpgrade.getCurrency());
        controller.displayUpdatedRank(rank);
    }


    public boolean canPurchaseRank(int desiredRank, String currency) {
        if (currency.equalsIgnoreCase("dollar")) {
            int cost = UpgradeManager.getDollarCost(desiredRank);
            return assets.getOrDefault("dollar", 0) >= cost;
        } else if (currency.equalsIgnoreCase("credit")) {
            int cost = UpgradeManager.getCreditCost(desiredRank);
            return assets.getOrDefault("credit", 0) >= cost;
        }
        return false; // Invalid currency input
    }

    public boolean canUpgrade() {
        // Must be in the casting office
        if (!location.getName().equalsIgnoreCase("office")) {
            return false;
        }

        // Check if player can afford any rank upgrade above current rank
        for (int newRank = rank + 1; newRank <= UpgradeManager.getMaxRank(); newRank++) {
            int dollarCost = UpgradeManager.getDollarCost(newRank);
            int creditCost = UpgradeManager.getCreditCost(newRank);
            if (assets.get("dollar") >= dollarCost || assets.get("credit") >= creditCost) {
                return true;
            }
        }

        return false;
    }

    public void earn(int earned, String currency) {
        int currAmount = assets.get(currency);
        assets.put(currency, currAmount + earned);
        points += earned;
    }

    public void spend(int spent, String currency) {
        int currAmount = assets.get(currency);
        assets.put(currency, currAmount - spent);
        points -= spent;
    }

    public void act(Controller controller) {
        if (!(location instanceof Set)) {
            controller.displayInvalidInput("You are not on a film set.");
            return;
        } 

        Set set = (Set) location;
        Card card = set.getCard();

        if (role == null || card == null || !set.isActive()) {
            controller.displayInvalidInput("You cannot act right now.");
            return;
        }

        int budget = card.getBudget();
        int practiceBonus = role.getPracticeChips();
        int roll = 1 + (int)(Math.random() * 6);
        int total = roll + practiceBonus;
        boolean success = total >= budget;

        HashMap<String, Integer> earnings = new HashMap<>();

        if (success) {
            set.removeShotCounter();

            if (set.isOnCardRole(role)) {
                // On-card: 2 credits
                earn(2, "credit");
                earnings.put("credit", 2);
            } else {
                // Off-card: 1 dollar, 1 credit
                earn(1, "dollar");
                earn(1, "credit");
                earnings.put("dollar", 1);
                earnings.put("credit", 1);
            }
        } else {
            if (!set.isOnCardRole(role)) {
                // Fail but off-card: 1 dollar
                earn(1, "dollar");
                earnings.put("dollar", 1);
            }
        }

        int shotsLeft = set.getRemainingShots();
        controller.displayActOutcome(success, earnings, shotsLeft);
    }


    public boolean canAct() {
        // Must have a role assigned
        if (role == null) {
            return false;
        }

        // Must be on a Set with an active scene
        if (!(location instanceof Set)) {
            return false;
        }

        Set set = (Set) location;
        if (!set.isActive() || set.getCard() == null) {
            return false;
        }

        return true;
    }

    public void rehearse(Controller controller) {
        this.role.addPracticeChip();
        controller.displayRehearseOutcome(role);
    }

    public boolean canRehearse() {
        // Must have a role assigned
        if (role == null) {
            return false;
        }

        // Must be on a Set with an active scene
        if (!(location instanceof Set)) {
            return false;
        }

        Set set = (Set) location;
        Card card = set.getCard();
        if (!set.isActive() || card == null) {
            return false;
        }

        // Can't rehearse if already guaranteed success
        int total = role.getPracticeChips() + 1; // rehearsing would add one
        return total < card.getBudget();
    }


    public void move(Controller controller) {//Chester
        String[] adjRoomNames = location.getNeighbors();
        location = controller.selectRoom(adjRoomNames);
        controller.displayMoveOutcome(location);
    }

    public boolean canMove() {
        return !hasRole();
    }

    public void takeRole(Controller controller) {
        if (!(location instanceof Set)) {
            controller.displayInvalidInput("You are not on a set.");
            return;
        }

        Set set = (Set) location;
        if (!set.isActive() || set.getCard() == null) {
            controller.displayInvalidInput("There is no active scene to take a role in.");
            return;
        }

        ArrayList<Role> availableRoles = new ArrayList<>();

        // Add available on-card roles
        for (Role r : set.getCard().getRoles()) {
            if (!r.isTaken() && canTakeRole(r)) {
                availableRoles.add(r);
            }
        }

        // Add available off-card roles
        for (Role r : set.getExtraRoles()) {
            if (!r.isTaken() && canTakeRole(r)) {
                availableRoles.add(r);
            }
        }

        if (availableRoles.isEmpty()) {
            controller.displayInvalidInput("No available roles you can take.");
            return;
        }

        Role chosenRole = controller.selectRole(availableRoles.toArray(new Role[0]));

        if (chosenRole != null) {
            chosenRole.setPlayer(this);
            this.role = chosenRole;
            controller.displayTakeRoleOutcome(chosenRole);
        } else {
            controller.displayInvalidInput("Invalid role selection.");
        }
    }

    public boolean canTakeRole(Role role) {
        return rank >= role.getLevel();
    }

    public boolean canTakeRole() {
    // Player must not already have a role
    if (hasRole()) {
        return false;
    }

    // The location must be a Set with an active scene
    if (location instanceof Set) {
        Set set = (Set) location;

        if (!set.isActive() || set.getCard() == null) {
            return false;
        }

        // Check on-card roles from the Card
        for (Role role : set.getCard().getRoles()) {
            if (!role.isTaken() && canTakeRole(role)) {
                return true;
            }
        }

        // Check off-card roles from the Set
        for (Role role : set.getExtraRoles()) {
            if (!role.isTaken() && canTakeRole(role)) {
                return true;
            }
        }
    }

    return false;
}

    public boolean hasRole() {
        return role != null;
    }

    public void clearRole() {
        role = null;
    }

    public boolean canEndTurn() {
        return true;
    }

    public void endTurn(Controller controller) {
        // don't delete this method, it gets used in ActionManager
    }

    public boolean canEndGame() {
        return true;
    }

    private static HashMap<String, Integer> initAssets(int startDollars, int startCredits) {
        HashMap<String, Integer> assets = new HashMap<>();
        assets.put("dollar", startDollars);
        assets.put("credit", startCredits);

        return assets;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    public int getPoints() {
        return points;
    }

    public Role getRole() {
        return role;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(String roomName) {
        location = Room.getRoom(roomName);
    }

    public void setLocation(Room room) {
        location = room;
    }

    public HashMap<String, Integer> getAssets() {
        return assets;
    }

    public int getCurrency(String currency) {
        return assets.get(currency);
    }
}
