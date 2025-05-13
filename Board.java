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
        int activeSets = 0;
        for (Set set: sets) {
            if (set.isActive())
                activeSets++;
        }

        return activeSets;
    }

    public void resetShotMarkers() {
        for (Set set: sets) {
            set.resetShotCounters();
        }
    }
}
