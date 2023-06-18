
public enum Rank {
    // create rank for cards
    ACE(11,"A", 0),
    DEUCER(2,"2", 0),
    THREE(3,"3", 0),
    FOUR(4,"4", 0),
    FIVE(5,"5", 0),
    SIX(6,"6", 0),
    SEVEN(7,"7", 0),
    EIGHT(8,"8", 0),
    NINE(9,"9", 0),
    TEN(10,"10", 4),
    JACK(10,"J", 3),
    KING(10,"K", 2),
    QUEEN(10,"Q", 1);
    // integer variable for rank
    private final int rankValue;
    // string variable for rank
    private final String rankString;
    private final int priority;
    // constructor(rankValue, rankString)
    private Rank(int rankValue, String rankString, int priority) {
        this.rankValue = rankValue;
        this.rankString = rankString;
        this.priority = priority;
    }
    // get rank value
    public int getRankValue(){
        return rankValue;
    }
    // get rank string
    public String getRankString(){
        return rankString;
    }

    public int getRankPriority(){
        return priority;
    }

    public static Rank fromString(String input) {
        for (Rank rank : Rank.values()) {
            if (rank.getRankString().equalsIgnoreCase(input)) {
                return rank;
            }
        }
        return null; // Return null if no matching enum value is found
    }
}
