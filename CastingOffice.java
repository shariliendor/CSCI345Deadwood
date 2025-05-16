public class CastingOffice extends Room {
    private final Upgrade[] upgrades;

    public CastingOffice(String[] neighbors, Area area, Upgrade[] upgrades) {
        super("office", neighbors, area);
        this.upgrades = upgrades;

        // Initialize UpgradeManager with upgrades (no more hardcoding)
        UpgradeManager.initialize(upgrades);
    }

    public Upgrade[] getUpgrades() {
        return upgrades;
    }

    public Upgrade getUpgrade(int rank, String currency) {
        for (Upgrade u : upgrades) {
            if (u.getRank() == rank && u.getCurrency().equalsIgnoreCase(currency)) {
                return u;
            }
        }
        return null;
    }
}
