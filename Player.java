import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String name;
    private int rank;
    private Room location;
    private Role role;
    private HashMap<String, Integer> assets;

    // make into a hashmap
    private static String[] actions = {"upgrade", "move", "take role", "act", "rehearse"};

    // make a static list of hashmaps<String, Integer> with rank, credits, and dollars keys

    private int points;

    public Player(String name, int startRank, int startCredits) {
        this.name = name;
        this.rank = startRank;
        this.role = null;
        this.points = 0;
        // init hashmap with dollars and credits
    }

    public void takeTurn() {
        // get possible actions
        // while there are actions the player can do
            // ask user which action to do
            // do that action
            // remove actions that the player can't do anymore
    }

    private ArrayList<String> getPossibleActions() {
        return null;
        // make hashmap action name to canDoAction(), loop through that
    }

    public void upgrade() {
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

    public void act() {
        //calculate acting score
        //compare to budget
        //give rewards if succesfull based off of on or off card
    }

    public boolean canAct() {
        return false;
        // check if have role

    }

    public void rehearse() {
        this.role.addPracticeChip();
    }

    public boolean canRehearse() {
        return false;
        // checks if have role and acting isnt guarunted success
    }

    public void move(Room room) {
        this.location = room;
    }

    public boolean canMove() {
        return !hasRole();
    }

    public void takeRole(Role role) {
        this.role = role;
    }

    public boolean canTakeRole(Role role) {
        return false;
        //checks if have required rank for role
    }

    public boolean canTakeRole() {return false;
    //if on an active set loops through roles with canTakeRole()
    }

    public boolean hasRole() {
        return role != null;
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
