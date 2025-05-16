import java.util.HashMap;
import java.util.Map;

public class UpgradeManager {
    private static int maxRank = 0;
    private static final Map<String, Integer> dollarCosts = new HashMap<>();
    private static final Map<String, Integer> creditCosts = new HashMap<>();

    // Call this once from game setup or CastingOffice constructor
    public static void initialize(Upgrade[] upgrades) {
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
