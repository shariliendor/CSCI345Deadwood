public class Upgrade {
    private final int level;
    private final String currency;
    private final int amount;
    private final Area area;

    public Upgrade(int level, String currency, int amount, Area area) {
        this.level = level;
        this.currency = currency;
        this.amount = amount;
        this.area = area;
    }

    public Area getArea() {
        return area;
    }

    public int getRank() {
        return level;
    }

    public int getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
