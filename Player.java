import java.lang.reflect.Method;
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

    public void takeTurn(Controller controller) {
        controller.displayPlayerTurn(this);
        ArrayList<String> actionsTaken = new ArrayList<String>();

        ArrayList<String> possibleActions = ActionManager.getPossibleActions(this, actionsTaken);
        while (!possibleActions.isEmpty()) {
            String actionToTake = controller.selectAction(possibleActions.toArray(new String[0]));
            ActionManager.executeAction(this, actionToTake, controller);
            actionsTaken.add(actionToTake);
            possibleActions = ActionManager.getPossibleActions(this, actionsTaken);
        }
    }

    public void upgrade(Controller controller) {
        // show upgrade costs
        // ask which rank to buy (out of possible ones)
    }

    public boolean canUpgrade() {
        return false;
        // if in casting office and can buy at least one rank
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
        //calculate acting score
        //compare to budget
        //give rewards if succesfull based off of on or off card
    }

    public boolean canAct() {
        return role != null;
    }

    public void rehearse(Controller controller) {
        this.role.addPracticeChip();
    }

    public boolean canRehearse() {
        return false;
        // checks if have role and acting isnt guarunted success
    }

    public void move(Controller controller) {
        // prompt player for room
        // move to that room
    }

    public boolean canMove() {
        return !hasRole();
    }

    public void takeRole(Controller controller) {
        // get possible roles
        // prompt player for role
        // move to that room
    }

    public boolean canTakeRole(Role role) {
        return rank >= role.getLevel();
    }

    public boolean canTakeRole() {return false;
    //if on an active set loops through roles with canTakeRole()
    }

    public boolean hasRole() {
        return role != null;
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

    public HashMap<String, Integer> getAssets() {
        return assets;
    }
}
