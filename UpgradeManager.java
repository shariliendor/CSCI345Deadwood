public class UpgradeManager {
    private static final int MAX_RANK = 6;
    private static final int[] dollarCosts = {0, 4, 10, 18, 28, 40, 55}; // index = rank
    private static final int[] creditCosts = {0, 5, 10, 15, 20, 25, 30};

    public static int getDollarCost(int rank) {
        return dollarCosts[rank];
    }

    public static int getCreditCost(int rank) {
        return creditCosts[rank];
    }

    public static int getMaxRank() {
        return MAX_RANK;
    }
}
