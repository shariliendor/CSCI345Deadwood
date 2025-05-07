import java.util.HashMap;

public class Player {
    String name;
    int rank;
    Room location;
    Role role;
    HashMap<String, Integer> assets;

    private int points;

    public Player(String name, int startRank, int startCredits) {

    }

    public void takeTurn() {

    }

    public void upgrade() {}

    public boolean canUpgrade() {
        return false;
    }

    public void earn(int earned, String currency) {
        //this.assets.get(currency) += earned;
    }

    public void spend(int spent, String currency) {
        //this.assets.get(currency) -= spent;
    }

    public void act() {

    }

    public boolean canAct() {
        return false;
    }

    public void rehearse() {
        this.role.addPracticeChip();
    }

    public boolean canRehearse() {
        return false;
    }

    public void move(Room room) {
        this.location = room;
    }

    public boolean canMove() {
        return role == null;
    }

    public void takeRole(Role role) {
        this.role = role;
    }

    public boolean canTakeRole(Role role) {
        return false;
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
