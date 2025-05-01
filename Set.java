public class Set extends Room{
    private final int shotsToComplete;
    private int shotCounters;
    private Card card;
    private Role[] extraRoles;

    // add other xml attributes
    public Set(String name, Room[] neighbors, int shotsToComplete, Role[] extraRoles) {
        super(name, neighbors);
        this.shotsToComplete = shotsToComplete;
        this.extraRoles = extraRoles;
    }

    public boolean isActive() {
        return !(shotCounters == shotsToComplete);
    }

    public void removeShotCounter() {
        this.shotCounters++;
    }

    public void resetShotCounters() {
        shotCounters = 0;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }
}
