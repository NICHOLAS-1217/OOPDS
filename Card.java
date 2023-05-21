public class Card {
    // calling rank and suit class
    // String value
    private Rank rank;
    private Suit suit;
    // face up boolean
    public boolean faceUp;
    // constructor(rank, suit)
    public Card(Rank rank, Suit suit){
        this.rank = rank;
        this.suit = suit;
        faceUp = true;
    }
    // calling functions from rank class
    public int getRankValue(){
        return rank.getRankValue();
    }
    public String getRankString(){
        return rank.getRankString();
    }
    // calling functions form suit class
    public String getSuitString(){
        return suit.getSuitString();
    }
    // create a flip card function
    public void flip(){
        faceUp = true;
    }
    // create card 
    public String cardString(){
        String cardString = "";
        // if card = faceup then show card rank and suit
        if(faceUp){
            cardString += getRankString() + getSuitString();
        }
        // else show card only
        else{
            cardString = "Card";
        }
        return cardString;
    }
    public int getRank(){
        int cardRank = 0;
        cardRank += getRankValue();
        return cardRank;
    }
    public String getString(){
        String cardRankString = "";
        cardRankString += getRankString();
        return cardRankString;
    }
}
