public enum Rank {
    // create rank for cards
    ACE(11,"A"),
    DEUCER(2,"2"),
    THREE(3,"3"),
    FOUR(4,"4"),
    FIVE(5,"5"),
    SIX(6,"6"),
    SEVEN(7,"7"),
    EIGHT(8,"8"),
    NINE(9,"9"),
    TEN(10,"10"),
    JACK(10,"J"),
    KING(10,"K"),
    QUEEN(10,"Q");
    // integer variable for rank
    private final int rankValue;
    // string variable for rank
    private final String rankString;
    // constructor(rankValue, rankString)
    private Rank(int rankValue, String rankString){
        this.rankValue = rankValue;
        this.rankString = rankString;
    }
    // get rank value
    public int getRankValue(){
        return rankValue;
    }
    // get rank string
    public String getRankString(){
        return rankString;
    }
}
