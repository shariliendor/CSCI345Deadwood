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

    public void upgrade(Controller controller) {//James
        // display current rank
        // show upgrade costs
        // ask which rank to buy (out of possible ones)
        // update points accordingly
    }

    public boolean canPurchaseRank(int rank, String currency) {//James
        return false;
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

    public void act(Controller controller) {//James
        //calculate acting score
        //compare to budget
        //give rewards if succesfull based off of on or off card
    }

    public boolean canAct() {//James
        return role != null;
    }

    public void rehearse(Controller controller) {
        this.role.addPracticeChip();
        controller.displayRehearseOutcome(role);
    }

    public boolean canRehearse() {//James
        return false;
        // checks if have role and acting isnt guarunted success
    }

    public void move(Controller controller) {//Chester
        String[] adjRoomNames = location.getNeighbors();
        location = controller.selectRoom(adjRoomNames);
        controller.displayMoveOutcome(location);
    }

    public boolean canMove() {
        return !hasRole();
    }

    public void takeRole(Controller controller) {//james
        // get possible roles
        // prompt player for role
        // move to that room
    }

    public boolean canTakeRole(Role role) {
        return rank >= role.getLevel();
    }

    public boolean canTakeRole() {return false;//james
    //if on an active set loops through roles with canTakeRole()
    }

    public boolean hasRole() {
        return role != null;
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
