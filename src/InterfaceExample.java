import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

interface OperateCar {
    String brandName = "BMW"; // implicitly public, static and final

    enum Direction {
        RIGHT, LEFT
    }

    int signalTurn(Direction direction, boolean signalOn);

    int turn(Direction direction, double radius, double startSpeed, double endSpeed);

    // int getRadarRear(double distanceToCard, double speedOfCard);
    // int changeLane(Direction direction, double startSpeed, double endSpeed);
}

class OperateBWM760i implements OperateCar {
    public int signalTurn(Direction direction, boolean signalOn) {
        // code to turn BMW's LEFT turn indicator lights on
        // code to turn BMW's LEFT turn indicator lights off
        // code to turn BMW's RIGHT turn indicator lights on
        // code to turn BMW's RIGHT turn indicator lights off
        return -1;
    }

    public int turn(Direction direction, double radius, double startSpeed, double endSpeed) {
        return -1;
    }

    public String getBrandName() {
        return brandName;
    } // or this.brandName
}

interface Relatable {
    /**
     * the reason for using Relatable as a type here is because
     * we don't know what all classes are actually going to use this
     * interface, hence we cannot hardcode any class over here. instead,
     * one thing we know for sure is that the class must have implemented
     * the Relatable interface, hence we define it itself in the method
     * signature over here, and the developer who implements this method
     * must explicitly typecast the Relatable type to the Class explicitly
     */
    int isLargerThan(Relatable other);
}

class Point {
    public int x = 0;
    public int y = 0;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class RectanglePlus implements Relatable {
    public Point origin;
    public int width = 0;
    public int height = 0;

    RectanglePlus() {
        this.origin = new Point(0, 0);
    }

    RectanglePlus(Point p) {
        this.origin = p;
    }

    RectanglePlus(int w, int h) {
        this.width = w;
        this.height = h;
        this.origin = new Point(0, 0);
    }

    RectanglePlus(int w, int h, Point p) {
        this.width = w;
        this.height = h;
        this.origin = p;
    }

    public void move(int x, int y) {
        this.origin.x = x;
        this.origin.y = y;
    }

    public int getArea() {
        return this.height * this.width;
    }

    public int isLargerThan(Relatable other) {
        RectanglePlus otherRectangle = (RectanglePlus) other;

        int thisArea = this.getArea();
        int otherArea = otherRectangle.getArea();

        if (thisArea > otherArea)
            return 1;

        if (thisArea == otherArea)
            return 0;

        return -1;
    }
}

interface TimeClient {
    void setTime(int hour, int minute, int second);

    void setDate(int day, int month, int year);

    void setDateAndTime(int day, int month, int year, int hour, int minute, int second);

    LocalDateTime getLocalDateTime();

    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }

    static ZoneId getZoneId(String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString + "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }
}

// any class implementing this interface will have to
// explicitly implement the method getZonedDateTime
interface AnotherTimeClient extends TimeClient {
    public ZonedDateTime getZonedDateTime(String zoneString);
}

// this interface overrides the original getZonedDateTime
// method and any class implementing this interface will use the
// implementation of getZonedDateTime method specified by this interface
interface HandleInvalidTimeZoneClient extends TimeClient {
    default public ZonedDateTime getZonedDateTime(String zoneString) {
        try {
            return ZonedDateTime.of(getLocalDateTime(), ZoneId.of(zoneString));
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString + "; using default time zone instead.");
            return ZonedDateTime.of(getLocalDateTime(), ZoneId.systemDefault());
        }
    }
}

class SimpleTimeClient implements TimeClient {
    private LocalDateTime dateAndTime;

    SimpleTimeClient() {
        this.dateAndTime = LocalDateTime.now();
    }

    public void setTime(int hour, int minute, int second) {
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        this.dateAndTime = this.dateAndTime.with(timeToSet);
    }

    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(year, month, day);
        this.dateAndTime = this.dateAndTime.with(dateToSet);
    }

    public void setDateAndTime(int day, int month, int year, int hour, int minute, int second) {
        this.dateAndTime = LocalDateTime.of(year, month, day, hour, minute, second);
    }

    public LocalDateTime getLocalDateTime() {
        return this.dateAndTime;
    }

    public String toString() {
        return this.dateAndTime.toString();
    }
}

interface CardInterface extends Comparable<CardInterface> {
    enum Suit {
        DIAMONDS(1, "Diamonds"),
        CLUBS(2, "Clubs"),
        HEARTS(3, "Hearts"),
        SPADES(4, "Spades");

        private final int value;
        private final String string;

        Suit(int value, String string) {
            this.value = value;
            this.string = string;
        }

        public int getValue() {
            return this.value;
        }

        public String toString() {
            return this.string;
        }
    }

    enum Rank {
        DEUCE(2, "Two"),
        THREE(3, "Three"),
        FOUR(4, "Four"),
        FIVE(5, "Five"),
        SIX(6, "Six"),
        SEVEN(7, "Seven"),
        EIGHT(8, "Eight"),
        NINE(9, "Nine"),
        TEN(10, "Ten"),
        JACK(11, "Jack"),
        QUEEN(12, "Queen"),
        KING(13, "King"),
        ACE(14, "Ace");

        private final int value;
        private final String string;

        Rank(int value, String string) {
            this.value = value;
            this.string = string;
        }

        public int getValue() {
            return this.value;
        }

        public String toString() {
            return this.string;
        }
    }

    public String toString();

    public CardInterface.Suit getSuit();

    public CardInterface.Rank getRank();
}

interface DeckInterface {
    List<CardInterface> getCards();

    DeckInterface getDeckFactory();

    int getSize();

    void addCard(CardInterface card);

    void addCards(List<CardInterface> cards);

    void addDeck(DeckInterface deck);

    void shuffle();

    void sort();

    void sort(Comparator<CardInterface> cardComparator);

    String deckToString();

    Map<Integer, DeckInterface> deal(int players, int numberOfCards) throws IllegalArgumentException;
}

class PlayingCard implements CardInterface {
    private final CardInterface.Suit suit;
    private final CardInterface.Rank rank;

    public PlayingCard(CardInterface.Suit suit, CardInterface.Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public CardInterface.Suit getSuit() {
        return this.suit;
    }

    public CardInterface.Rank getRank() {
        return this.rank;
    }

    public boolean isEqual(Object object) {
        if (object instanceof PlayingCard) {
            // object has been type-casted here
            return ((PlayingCard) object).getRank() == this.getRank() &&
                    ((PlayingCard) object).getSuit() == this.getSuit();
        }

        return false;
    }

    public int getHashCode() {
        return ((this.suit.getValue() - 1) * 13) + this.rank.getValue();
    }

    public int compareTo(CardInterface card) {
        return this.getHashCode() - card.hashCode();
    }

    public String toString() {
        return this.rank.toString() + " of " + this.suit.toString();
    }

    public static void main() {
        new PlayingCard(Suit.CLUBS, Rank.ACE);
        new PlayingCard(Suit.HEARTS, Rank.KING);
    }
}

class StandardDeck implements DeckInterface {
    private List<CardInterface> entireDeck;

    StandardDeck(List<CardInterface> cards) {
        this.entireDeck = cards;
    }

    StandardDeck() {
        this.entireDeck = new ArrayList<CardInterface>();

        for (CardInterface.Suit suit : CardInterface.Suit.values()) {
            for (CardInterface.Rank rank : CardInterface.Rank.values()) {
                this.entireDeck.add(new PlayingCard(suit, rank));
            }
        }
    }

    public List<CardInterface> getCards() {
        return this.entireDeck;
    }

    public int getSize() {
        return this.entireDeck.size();
    }

    public DeckInterface getDeckFactory() {
        return new StandardDeck();
    }

    public void addCard(CardInterface card) {
        this.entireDeck.add(card);
    }

    public void addCards(List<CardInterface> cards) {
        this.entireDeck.addAll(cards);
    }

    public void addDeck(DeckInterface deck) {
        this.entireDeck.addAll(deck.getCards());
    }

    public void sort() {
        Collections.sort(this.entireDeck);
    }

    public void shuffle() {
        Collections.shuffle(this.entireDeck);
    }

    public void sort(Comparator<CardInterface> cardComparator) {
        Collections.sort(this.entireDeck, cardComparator);
    }

    public String deckToString() {
        return this.entireDeck
                .stream()
                .map(CardInterface::toString)
                .collect(Collectors.joining("\n"));
    }

    public Map<Integer, DeckInterface> deal(int players, int numberOfCards) throws IllegalArgumentException {
        int cardsDealt = players * numberOfCards;
        int deckSize = this.entireDeck.size();

        if (cardsDealt > deckSize) {
            throw new IllegalArgumentException("The number of cards dealt cannot be more than the size of deck");
        }

        Map<Integer, List<CardInterface>> dealtDeck = this.entireDeck
                .stream()
                .collect(Collectors.groupingBy(card -> {
                    int cardIndex = this.entireDeck.indexOf(card);
                    return cardIndex >= cardsDealt ? players + 1 : (cardIndex % players) + 1;
                }));

        // Convert Map<Integer, List<CardInterface>> to Map<Integer, Deck>
        Map<Integer, DeckInterface> mapToReturn = new HashMap<>();

        for (int i = 1; i <= (players + 1); i++) {
            DeckInterface currentDeck = getDeckFactory();
            currentDeck.addCards(dealtDeck.get(i));
            mapToReturn.put(i, currentDeck);
        }

        return mapToReturn;
    }
}

public class InterfaceExample {
    public static void main() {
        RectanglePlus r1 = new RectanglePlus(10, 20);
        RectanglePlus r2 = new RectanglePlus(20, 20);
        r1.isLargerThan(r2);

        // an interface can be also used as type for a variable, such that the object
        // assigned to it must be an instance of a class that implements the interface
        Relatable r3 = new RectanglePlus();
        Relatable r4 = (Relatable) new RectanglePlus();

        SimpleTimeClient simpleTimeClient = new SimpleTimeClient();
        simpleTimeClient.setTime(2, 2, 2);
        simpleTimeClient.setDate(1, 1, 2022);
        System.out.println(simpleTimeClient.getLocalDateTime());
        System.out.println(simpleTimeClient.toString());
        System.out.println(simpleTimeClient.getZonedDateTime("America/Anchorage"));

        StandardDeck deck = new StandardDeck();

        // very verbose
        deck.sort(new Comparator<CardInterface>() {
            public int compare(CardInterface firstCard, CardInterface secondCard) {
                int rankComparison = firstCard.getRank().getValue() - secondCard.getRank().getValue();

                if (rankComparison != 0) {
                    return rankComparison;
                }

                return firstCard.getSuit().getValue() - secondCard.getSuit().getValue();
            }
        });

        // as comparator is a functional interface we can use a lambda interface as an argument
        // with below method in case we are comparing the cards just by rank, regardless of suit
        deck.sort((CardInterface firstCard, CardInterface secondCard) -> firstCard.getRank().getValue() - secondCard.getRank().getValue());

        // simplify it further by invoking a static method on the Comparator interface and passing
        // an object which can extract a numerical value based on which the comparison could be done.
        // here, such object is being passed by invoking the getRank() method which returns the Rank enum
        // which has the getValue() method which would return the required numerical value for comparison
        deck.sort(Comparator.comparing((CardInterface card) -> card.getRank()));

        // TODO: research how card.getRank() works as it can have any method to get numerical value apart from getValue()

        // as the above is invoking an existing method, we can use a method reference instead
        deck.sort(Comparator.comparing(CardInterface::getRank));

        // equivalent lambda expression of the anonymous class used
        // above which compares the cards first by rank and then by suit
        deck.sort((CardInterface firstCard, CardInterface secondCard) -> {
            int rankComparison = firstCard.getRank().getValue() - secondCard.getRank().getValue();

            if (rankComparison != 0) {
                return rankComparison;
            }

            return firstCard.getSuit().getValue() - secondCard.getSuit().getValue();
        });

        // to simplify it further we could use create the Comparator
        // instance from a series of Comparator instance as below
        deck.sort(
                Comparator
                        .comparing((CardInterface card) -> card.getRank())
                        // .thenComparing((CardInterface card) -> card.getSuit()) is this valid?
                        .thenComparing(Comparator.comparing(((CardInterface card) -> card.getSuit())))
        );

        // or just use method references instead as both the lambda
        // expressions are invoking existing methods respectively
        deck.sort(
                Comparator
                        .comparing(CardInterface::getRank)
                        // .thenComparing(CardInterface::getSuit) is this valid?
                        .thenComparing(Comparator.comparing(CardInterface::getSuit))
        );

        // if we need to compare card first by ranks in reverse and then by suit as it is
        deck.sort(Comparator
                .comparing(CardInterface::getRank)
                .reversed() // a default method of Comparator interface has been invoked
                .thenComparing(Comparator.comparing(CardInterface::getSuit))
        );
    }
}
