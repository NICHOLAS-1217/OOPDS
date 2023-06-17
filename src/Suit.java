public enum Suit {
    // create suit for cards
    HEARTS("h"),
    SPADES("s"),
    DIAMONDS("d"),
    CLUBS("c");
    // string variable for suit
    private final String suitString;
    // constructor(suitString)
    private Suit(String suitString){
        this.suitString = suitString;
    }
    // get suit string
    public String getSuitString(){
        return suitString;
    }

    public static Suit fromString(String input) {
        for (Suit suit : Suit.values()) {
            if (suit.getSuitString().equalsIgnoreCase(input)) {
                return suit;
            }
        }
        return null; // Return null if no matching enum value is found
    }
}

