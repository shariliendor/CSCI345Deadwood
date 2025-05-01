import java.util.HashMap;

public class Player {
    String name;
    int rank;
    Room location;
    Role role;
    HashMap<String, Integer> assets;

    public Player(String name, int startRank, int startCredits) {

    }

    public void takeTurn() {

    }

    public boolean upgrade() {
        return false;
    }

    private int chooseRank() {
        return 0;
    }

    private boolean canUpgrade() {
        return false;
    }

    private void updateRank(int newRank) {

    }

    public void earn(int earned, String currency) {
        //this.assets.get(currency) += earned;
    }

    public void spend(int spent, String currency) {
        //this.assets.get(currency) -= earned;
    }

    public void act() {

    }

    private int calcActingScore(int practiceChips) {
        return (int) (Math.random() * 6) + practiceChips;
    }

    public void rehearse() {
        this.role.addPracticeChip();
    }

    public void move(Room room) {
        this.location = room;
    }

    public void takeRole(Role role) {
        this.role = role;
    }

    private boolean canTakeRole(Role role) {
        return false;
    }
}
