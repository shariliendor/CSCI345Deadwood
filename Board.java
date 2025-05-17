public class Board {
    private final Room[] rooms;
    private final Set[] sets;
    private final Room trailer;
    private final CastingOffice castingOffice;

    public Board(Room trailer, Set[] sets, CastingOffice castingOffice) {
        this.sets = sets;
        this.castingOffice = castingOffice;
        this.trailer = trailer;

        this.rooms = new Room[sets.length + 2];
        System.arraycopy(sets, 0, this.rooms, 0, sets.length);
        this.rooms[rooms.length - 2] = trailer;
        this.rooms[rooms.length - 1] = castingOffice;

    }

    public int getSetsToShoot() {
        int activeSets = 0;
        for (Set set: sets) {
            if (set.isActive()) {
                activeSets++;
            }
        }

        return activeSets;
    }

    public void resetShotMarkers() {
        for (Set set: sets) {
            set.resetShotCounters();
        }
    }

    public void activateSets() {
        for (Set set: sets) {
            set.setActive(true);
        }
    }
    
    public Set[] getSets() {
        return sets;
    }

    public Room getTrailer() {
        return this.trailer;
    }

    public CastingOffice getCastingOffice() {
        return this.castingOffice;
    }


}
