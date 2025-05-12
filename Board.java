public class Board {
    private final Room[] rooms;
    private final Set[] sets;
    private final CastingOffice castingOffice;

    public Board(Room[] rooms, Set[] sets, CastingOffice castingOffice) {
        this.rooms = rooms;
        this.sets = sets;
        this.castingOffice = castingOffice;
    }

    public int getScenesToShoot() {
        return 0;
        // loop through rooms
            // if room is a set, and its active, increment count
        // return count
    }

    public void resetShotMarkers() {
        // loop rooms
            // if set, call resetShotMarkers in it
    }
}
