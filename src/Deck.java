import java.util.Random;

public class Deck extends Player{
    // constructor
    public Deck(String playerName) {
        super(playerName);
    }
    // random
    Random rand = new Random();
    //import the cards into deck
    public void importCard(){
        // for each suit
        for(Suit suit: Suit.values()){
            // for each rank
            for(Rank rank: Rank.values()){
                // create a new card with rank and suit parameters
                Card card = new Card(rank, suit);
                // add the card into the deck
                this.add(card);
            }
        }
    }
    // shuffle cards function
    public void shuffleCards(){
        for(int i = cards.size()-1; i>0; i--){
            // swap a random card between the beginning
            // and last card of the loop
            int pick = rand.nextInt(i);
            Card randCard = cards.get(pick);
            Card lastCard = cards.get(i);
            cards.set(i, randCard);
            cards.set(pick, lastCard);
        }
    }
    // cards per hand
    public void deal(Player p, int perHand){
        for(int i = 0; i < perHand; i++){
            this.give(cards.get(0), p);
        }
    }

    public void deal(Player[] p, int perHand){
        for(int i = 0; i < perHand; i++){
            for(int j = 0; j < 4; j++){
                this.give(cards.get(0), p[j]);
            }
        }
    }
    // center
    public void dealCenter(Player hand, int perHand){
        for(int i = 0; i < perHand; i++){
            this.give(cards.get(0), hand);
        }
    }
    // flip card
    public void flip(Card c){
        c.flip();
    }

    public boolean exhausted(){
        return cards.size() == 0;
    }
}

