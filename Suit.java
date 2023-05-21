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
}