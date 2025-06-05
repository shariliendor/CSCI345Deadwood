import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpgradeManager {
    private static Upgrade[] upgrades;
    private static int maxRank = 0;
    private static final Map<String, Integer> dollarCosts = new HashMap<>();
    private static final Map<String, Integer> creditCosts = new HashMap<>();

    // Call this once from game setup or CastingOffice constructor
    public static void initialize(Upgrade[] upgrades) {
        UpgradeManager.upgrades = upgrades;

        for (Upgrade upgrade : upgrades) {
            int rank = upgrade.getRank();
            String currency = upgrade.getCurrency().toLowerCase();
            int amount = upgrade.getAmount();

            if (rank > maxRank) {
                maxRank = rank;
            }

            if (currency.equals("dollar")) {
                dollarCosts.put(String.valueOf(rank), amount);
            } else if (currency.equals("credit")) {
                creditCosts.put(String.valueOf(rank), amount);
            }
        }
    }

    public static Upgrade[] getPossibleUpgrades(Player player) {
        ArrayList<Upgrade> possibleUpgrades = new ArrayList<>();

        for (int rank = player.getRank() + 1; rank <= maxRank; rank++) {
            if (player.getCurrency("dollar") >= getDollarCost(rank)) {
                possibleUpgrades.add(getUpgrade(rank, "dollar"));
            }
            if (player.getCurrency("credit") >= getCreditCost(rank)) {
                possibleUpgrades.add(getUpgrade(rank, "credit"));
            }
        }

        return possibleUpgrades.toArray(new Upgrade[0]);
    }

    private static Upgrade getUpgrade(int rank, String currency) {
        for (Upgrade upgrade: upgrades) {
            if (upgrade.getRank() == rank && upgrade.getCurrency().equals(currency)) {
                return upgrade;
            }
        }

        return null;
    }

    public static Upgrade[] getUpgrades() {
        return upgrades;
    }

    public static int getDollarCost(int rank) {
        return dollarCosts.getOrDefault(String.valueOf(rank), -1);
    }

    public static int getCreditCost(int rank) {
        return creditCosts.getOrDefault(String.valueOf(rank), -1);
    }

    public static int getMaxRank() {
        return maxRank;
    }
}
