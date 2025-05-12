public class CastingOffice extends Room{
    private final Upgrade[] upgrades;

    // maybe have something to keep track of upgrade costs
    // so they're easier to look up

    public CastingOffice(String[] neighbors, Area area, Upgrade[] upgrades) {
        super("office", neighbors, area);
        this.upgrades = upgrades;
    }

    public Upgrade[] getUpgrades() {
        return upgrades;
    }
}
