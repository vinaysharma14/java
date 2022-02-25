enum Suit {
    CLUB, SPADES, HEART, DIAMOND
}

enum Rank {
    DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
}

class Card {
    private final Suit suit;
    private final Rank rank;

    Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    public Rank getRank() {
        return this.rank;
    }

    public String toString() {
        return this.rank + " of " + this.suit;
    }
}

class Deck {
    private final Card[] cards = new Card[52];

    Deck() {
        int i = 0;

        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards[i++] = new Card(suit, rank);
            }
        }
    }
}

public class EnumExercise {
    public static void main() {
        Card c1 = new Card(Suit.CLUB, Rank.ACE);

        System.out.println(c1.getRank());
        System.out.println(c1.getSuit());
        System.out.println(c1.toString());

        Deck d1 = new Deck();
    }
}
