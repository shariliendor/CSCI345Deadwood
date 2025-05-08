public class Board {
    private final Room[] rooms;
    private final Deck deck;

    public Board(Room[] rooms, Deck deck) {
        this.rooms = rooms;
        this.deck = deck;
    }

    public int getScenesToShoot() {
        return 0;
        // loop through rooms
            // if room is a set, and its active, increment count
        // return count
    }

    private void dealToSets() {
        // loop rooms
            // if set, draw a card and set set's card to the card drawn
    }

    public void resetShotMarkers() {
        // loop rooms
            // if set, call resetShotMarkers in it
    }
}
