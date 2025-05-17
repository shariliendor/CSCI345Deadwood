public class Set extends Room{
    private final int shotsToComplete;
    private final Area[] shotCounterAreas;
    private int shotCounters;
    private Card card;
    private final Role[] extraRoles;

    public Set(String name, String[] neighbors, Area area, Area[] shotCounterAreas, Role[] extraRoles) {
        super(name, neighbors, area);
        this.shotsToComplete = shotCounterAreas.length;
        this.shotCounterAreas = shotCounterAreas;
        this.extraRoles = extraRoles;
        this.shotCounters = 0;
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

    public Area[] getShotCounterAreas() {
        return this.shotCounterAreas;
    }

    public Role[] getExtraRoles() {
        return extraRoles;
    }

    public int getRemainingShots() {
        return shotsToComplete - shotCounters;
    }

    public int getShotCounters() {
        return shotCounters;
    }

    public boolean isOnCardRole(Role role) {
        for (Role r : card.getRoles()) {
            if (r == role) return true;
        }
        return false;
    }
}
