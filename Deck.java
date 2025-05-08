import java.util.Collections;
import java.util.Stack;

public class Deck {
    private Stack<Card> cards;

    public Deck() {
        this.cards = new Stack<>();
    }

    public void addCard(Card card) {
        cards.push(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        return cards.isEmpty() ? null : cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}

