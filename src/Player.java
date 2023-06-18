import java.util.ArrayList;

public class Player {
    // Strinimport java.util.ArrayList;

public class Player {
    // String name
    public Rank rank;
    public String playerName;
    // create array to import the cards
    public int initialValue, trickPoints;
    // create initial value
    public boolean trickWin;
    public ArrayList<Card> cards;
    // constructer(String name)
    public Player(String playerName){
        this.playerName = playerName;
        cards = new ArrayList<Card>();
    }

    public void loadCards(String data) {

    }
    public String showName(){
        return playerName;
    }
    public void add(Card card){
        // add cards into the list
        cards.add(card);
    }
    public String showCard(){
        String cardString = "";
        boolean faceUp = true;
        int x = 0;
        for(Card c: cards){
            x ++;
            if (x == cards.size()){
                cardString += c.cardString();
            }
            else {
                cardString += c.cardString()+",";
            }
            if(!c.faceUp){
                faceUp = false;
            }
        }
        // if the card face up then show total points
//        if(faceUp){
//            cardString += "] total points = " + getTotal() + "\n";
//        }
        return cardString;
    }
    public int getCardCount(){
        return cards.size();
    }

    public void flipCards(){
        for(Card c: cards){
            c.flip();
        }
    }
    // giveCard
    public boolean give(Card card, Player otherHand){
        if(!cards.contains(card)){
            return false;
        } 
        else{
            cards.remove(card);
            otherHand.add(card);
            return true;
        }
    }
    public boolean giveCardToCenter(String cardString, Player center) {
        for(Card card: cards) {
            if(card.cardString().equals(cardString)) {
                cards.remove(card);
                center.add(card);
                // System.out.println("same card " + cardString);
                return true;
            } 
//            else{
//                // System.out.println("not same card " + cardString);
//                return true;
//            }
        }
        return false; // Card not found in the player's hand
    }
    // get total points function
    public int getTotal(){
        int totalPts = 0;
        boolean hasAce = false;
        // getting total points ace default = 1
        for (Card card : cards) {
            totalPts += card.getRankValue();
            // check ace
            if (card.getRankString().equals("Ace")) {
                hasAce = true;
            }
            // ace = 11 if total points <= 11
            if (hasAce && totalPts <= 11) {
                totalPts += 10;
            }
        }
        return totalPts;
    }

//    public int trickPoints(){
//        int totalPts = 0;
//        boolean hasAce = false;
//        // getting total points ace default = 1
//        for (Card card : cards) {
//            totalPts += card.getRankValue();
//            // check ace
//            if (card.getRankString().equals("Ace")) {
//                hasAce = true;
//            }
//            // ace = 11 if total points <= 11
//            if (hasAce && totalPts <= 11) {
//                totalPts += 10;
//            }
//        }
//        return totalPts - ;
//    }
    public int getRankValue(){
        int rankVal = 0;
        for(Card c: cards){
            rankVal += c.getRank();
        }
        return rankVal;
    }
    public String getRankString(){
        String rankStr = "";
        for(Card c: cards){
            rankStr += c.getString();
        }
        return rankStr;
    }

    public int getRankPriority() {
        int rankPrio = 0;
        for(Card c: cards){
            rankPrio += c.getRankPriority();
        }
        return rankPrio;
    }

    public void dealLoad(String hand){

        String[] hands = hand.split(",");

        // Iterate through each card element
        for (String oneCard : hands) {
            // Extract the suit and rank of each card
            String suitString = oneCard.substring(oneCard.length() - 1);
            String rankString = oneCard.substring(0, oneCard.length() - 1);

            Rank rank = Rank.fromString(rankString);
            Suit suit = Suit.fromString(suitString);

            Card card = new Card(rank, suit);

            cards.add(card);
        }
    }

    public int getTrickPoints(){
        return trickPoints;
    }
}

    }

    public void loadCards(String data) {

    }
    public String showName(){
        return playerName;
    }
    public void add(Card card){
        // add cards into the list
        cards.add(card);
    }
    public String showCard(){
        String cardString = "";
        boolean faceUp = true;
        int x = 0;
        for(Card c: cards){
            x ++;
            if (x == cards.size()){
                cardString += c.cardString();
            }
            else {
                cardString += c.cardString()+",";
            }
            if(!c.faceUp){
                faceUp = false;
            }
        }
        // if the card face up then show total points
//        if(faceUp){
//            cardString += "] total points = " + getTotal() + "\n";
//        }
        return cardString;
    }
    public int getCardCount(){
        return cards.size();
    }

    public void flipCards(){
        for(Card c: cards){
            c.flip();
        }
    }
    // giveCard
    public boolean give(Card card, Player otherHand){
        if(!cards.contains(card)){
            return false;
        } 
        else{
            cards.remove(card);
            otherHand.add(card);
            return true;
        }
    }
    public boolean giveCardToCenter(String cardString, Player center) {
        for(Card card: cards) {
            if(card.cardString().equals(cardString)) {
                cards.remove(card);
                center.add(card);
                // System.out.println("same card " + cardString);
                return true;
            } 
//            else{
//                // System.out.println("not same card " + cardString);
//                return true;
//            }
        }
        return false; // Card not found in the player's hand
    }
    // get total points function
    public int getTotal(){
        int totalPts = 0;
        boolean hasAce = false;
        // getting total points ace default = 1
        for (Card card : cards) {
            totalPts += card.getRankValue();
            // check ace
            if (card.getRankString().equals("Ace")) {
                hasAce = true;
            }
            // ace = 11 if total points <= 11
            if (hasAce && totalPts <= 11) {
                totalPts += 10;
            }
        }
        return totalPts;
    }

//    public int trickPoints(){
//        int totalPts = 0;
//        boolean hasAce = false;
//        // getting total points ace default = 1
//        for (Card card : cards) {
//            totalPts += card.getRankValue();
//            // check ace
//            if (card.getRankString().equals("Ace")) {
//                hasAce = true;
//            }
//            // ace = 11 if total points <= 11
//            if (hasAce && totalPts <= 11) {
//                totalPts += 10;
//            }
//        }
//        return totalPts - ;
//    }
    public int getRankValue(){
        int rankVal = 0;
        for(Card c: cards){
            rankVal += c.getRank();
        }
        return rankVal;
    }
    public String getRankString(){
        String rankStr = "";
        for(Card c: cards){
            rankStr += c.getString();
        }
        return rankStr;
    }

    public void dealLoad(String hand){

        String[] hands = hand.split(",");

        // Iterate through each card element
        for (String oneCard : hands) {
            // Extract the suit and rank of each card
            String suitString = oneCard.substring(oneCard.length() - 1);
            String rankString = oneCard.substring(0, oneCard.length() - 1);

            Rank rank = Rank.fromString(rankString);
            Suit suit = Suit.fromString(suitString);

            Card card = new Card(rank, suit);

            cards.add(card);
        }
    }
}
