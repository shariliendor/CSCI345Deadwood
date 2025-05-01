public class Role {
    private final int reqRank;
    private int practiceChips;
    // and whatever other attributes we get from the xml file

    public Role(int reqRank) { // add attributes from xml
        this.reqRank = reqRank;
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

    public int getReqRank() {
        return reqRank;
    }

    // add getters for other xml attributes
}
