import java.util.Scanner;

public class Game {
    // scanner
    private static final Scanner scanner = new Scanner(System.in);
    // create array for menu choices
    static String[] menuChoices = {
        "s - Start a new game", 
        "x - Exit the game", 
        "d - Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player. ",
        "card - a card played by the current player"
    };
    // generate menu
    static Instructions menu = new Instructions(menuChoices);
    // clear screen function
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    // press any key to continue function
    public static void pressAnyKeyToContinue() {
        System.out.println("Press any key to continue...");
        scanner.nextLine();
    }
    // create start game function
    public static void startGame(){
        // Display game intro
        System.out.println(">>>>>>>>>>> GO BOOM <<<<<<<<<<<<");
        System.out.println("+                              +");
        System.out.println("|      !!!! Welcome !!!!       |");
        System.out.println("+                              +");
        System.out.println(">>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<\n");
        pressAnyKeyToContinue();
        // Display game instructions
        System.out.println("Here are the game instructions:");
        menu.display();
        // Start the game loop
        while(true){
            // get player input
            System.out.println("commands> ");
            String input = scanner.nextLine();
            // if player input = yes
            if(input.equals("s")) {
                System.out.println("You entered: " + input);
                // Ask player for name
                Player[] p = new Player[4];

                for (int i = 0; i < 4; i++) {
                    System.out.println("Enter player " + (i + 1) + " name: ");
                    String playerName = scanner.nextLine();
                    p[i] = new Player(playerName);
                }
                // say hi to the player

                System.out.print("Hi, ");
                for (int i = 0; i < 4; i++) {
                    System.out.print(p[i].playerName + ", ");
                }
                System.out.print(" enjoy the game!!!");
//                System.out.println("Hi, " + player1Name + ", " + player2Name + ", " + player3Name + ", " + player4Name + " enjoy the game!!!");
                pressAnyKeyToContinue();
                // create deck
                Deck deck = new Deck(p[0].playerName);
                // import cards
                deck.importCard();
                // show deck
                System.out.println("CARD DECK:\n" + "[" + deck.showCard());
                // shuffle cards
                deck.shuffleCards();
                // show shuffle deck cards
                System.out.println("SHUFFLLED CARD DECK:\n" + "[" + deck.showCard());

                // create center
                String centerString = "center";
                Player center = new Player(centerString);

                // center
                Player[] centerStr = {center};
                Player[] centerHand = {center};
                // 7 cards per hand
                deck.deal(p, 7);
                for(Player player : p){
                    player.initialValue = player.getTotal();
                }
                // 1 card for center
                deck.dealCenter(center, 1);
                // 1 turn
                boolean gameOver = false;
                clearScreen();
                // first lead 
                String firstLeadCard = centerHand[0].getRankString();
                int turn = 0; //player's turn
                int player_turn = 0; //player's turn (after modulus)
                // not game over
                while (!gameOver) {
                    // trick count
                    for (int trick = 1; trick < 4; trick++) {

                        if(gameOver)
                            break;

                        int highestTrickPoint = 0;
                        int leadPlayer = -1;

                        for(int i=0; i<p.length; i++){
                            Player player = p[i];

                            if(player.trickPoints > highestTrickPoint){
                                highestTrickPoint = player.trickPoints;
                                leadPlayer = i;
                            }
                        }

                        if(trick > 1)
                            System.out.println("Player " + p[leadPlayer].playerName + " wins the trick");

                        String firstCardToGiveString = "";
                        for (int i = 0; i < 4; i++) {

                            System.out.println(firstLeadCard);
                            if (i == 0 && trick == 1) {
                                switch (firstLeadCard) {
                                    case "A", "5", "9", "K" -> turn = 0;
                                    case "2", "6", "10" -> turn = 1;
                                    case "3", "7", "J" -> turn = 2;
                                    case "4", "8", "Q" -> turn = 3;
                                }
                            } else if (i == 0){
                                turn = leadPlayer;
                            }
                            // first lead player

                            //player 1 lead
                            System.out.println("trick #" + trick);
                            // show player
                            player_turn = turn;
                            for (int k = 0; k < 4; k++) {
                                player_turn %= 4;
                                System.out.print(p[player_turn].playerName + ": ");
                                System.out.print("[");
                                System.out.print(p[player_turn].showCard());
                                player_turn++;
                            }
                            // show center
                            for (int j = 0; j < centerHand.length; j++) {
                                System.out.print(centerStr[j].showName() + ": ");
                                System.out.print("[" + centerHand[j].showCard());
                                //System.out.println(centerHand[j].getRankValue());
                            }
                            // show deck
                            System.out.println("Deck: [" + deck.showCard());
                            // show player turn
                            System.out.print(p[turn].playerName + "> ");
                            // player card input
                            String cardToGiveString = "";
                            boolean success = false;

                            if(i==0){
                                firstCardToGiveString = scanner.nextLine();
                                success = p[turn].giveCardToCenter(firstCardToGiveString, center);

                                if(firstCardToGiveString.equals("d")){
                                    deck.deal(p[turn],1);
                                    i--;
                                    continue;
                                }
                            } else {
                                cardToGiveString = scanner.nextLine();

                                if(cardToGiveString.equals("d")){
                                    deck.deal(p[turn],1);
                                    i--;
                                    continue;
                                }

                                String[] suitAndRank = cardToGiveString.split("");
                                for(String val : suitAndRank) {
                                    if(firstCardToGiveString.contains(val)) {
                                        success = p[turn].giveCardToCenter(cardToGiveString, center);
                                        break;
                                    }
                                }
                            }

                            p[turn].trickPoints = p[turn].initialValue - p[turn].getTotal();
                            p[turn].initialValue -= p[turn].trickPoints;

                            if (!success) {
                                System.out.println("Invalid card or card not found in your hand.");
                                i--;
                                continue;
                            }

                            if (p[i].cards.isEmpty()){
                                gameOver = true;
                                break;
                            }

                            turn++;
                            turn %= 4;
                        }
                    }
                    // game over
                    System.out.println("blablabla wins the game");
                }
                // if player input = x
            } else if(input.equals("x")){
                // Quit the game
                System.out.println("Thanks for playing!");
                break;
            }
            // if player input = else
            else{
                // No such option
                System.out.println("no such command!");
            }
        }
    }
}
