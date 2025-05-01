public class Role {
    private final String name;
    private final int level;
    private final String line;
    private final Area area;
    private int practiceChips;
    // and whatever other attributes we get from the xml file

    public Role(String name, int level, String line, Area area) {
        this.name = name;
        this.level = level;
        this.line = line;
        this.area = area;
        this.practiceChips = 0;
    }

    public void addPracticeChip() {
        this.practiceChips ++;
    }

    public void resetPracticeChips() {
        this.practiceChips = 0;
    }

    public int getPracticeChips() {
        return this.practiceChips;
    }

    public int getLevel() {
        return level;
    }

    public String getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public Area getArea() {
        return area;
    }
}
