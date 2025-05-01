public class Board {
    private final Room[] rooms;
    private final Deck deck;

    public Board(String filename) {

    }

    public Board(Room[] rooms, Deck deck) {
        this.rooms = rooms;
        this.deck = deck;
    }

    public int getScenesToShoot() {
        return 0;
    }

    private void dealToSets() {

    }

    public void resetShotMarkers() {

    }
}
