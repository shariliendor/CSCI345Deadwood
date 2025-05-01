import java.util.ArrayList;
import java.util.Stack;

public class Deck {
    // might consider using a stack for easier drawing
    private Card[] cards;

    public Deck(String fileName) {

    }

    public Deck(Card[] cards) {
        this.cards = cards;
    }

    public void shuffle() {

    }

    public Card draw() {
        return cards[0];
    }
}
