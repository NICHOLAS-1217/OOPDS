import java.util.*;
import java.io.*;
import frame.WelcomePage;

public class Game {

    private static final Scanner scanner = new Scanner(System.in);

    // menu parts------------------------
    static String[] menuChoices = {
        "s - Start a new game", 
        "x - Exit the game", 
        "d - Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player. ",
        "card - a card played by the current player"
    };
    static Instructions menu = new Instructions(menuChoices);

    // clear screen parts----------------------
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // welcome page -------------------------------
    public static void call_welcomePage(){
        new WelcomePage();
    }

    // save load delete -------------------------------------------------
    public static void createGameFiles(){
        try {
            File myGame = new File("saved/myGame.txt");
            if (myGame.createNewFile()) {
              System.out.println("File created: " + myGame.getName());
            } else {
              System.out.println("File already exists.");
            }
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    public static void deleteGameFiles(){
        File myGame = new File("saved/myGame.txt");
        if (myGame.exists()){
            if (myGame.delete()) { 
            System.out.println("Deleted the file: " + myGame.getName());
            } else {
            System.out.println("Failed to delete the file.");
            } 
        } else {
            System.out.println("This file doesn't exist");
        }
    }

    public static void writeFiles(Player[] p, Deck deck) {
        try {
            FileWriter writer = new FileWriter("saved/anotherGame.txt");
            for (int i = 0; i < 4; i++) {
                writer.write(p[i].playerName+"\n");
                writer.write(p[i].showCard());

            }
//            for (int i = 0; i < 4; i++) {
//                writer.write(p[i].showCard()+"\n");
//            }
            writer.write(deck.showCard());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFiles(Player[] p, Deck deck){

    }

    // start game function ----------------------------------------------------------------
    public static void startGame(){

        boolean quit = false;
        //call_welcomePage();

        // menu ----------------------------------------------------------------
        System.out.println(">>>>>>>>>>> GO BOOM <<<<<<<<<<<<");
        System.out.println("+                              +");
        System.out.println("|      !!!! Welcome !!!!       |");
        System.out.println("+                              +");
        System.out.println(">>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<\n");
        System.out.println("Here are the game instructions:");
        menu.display();
  
        // start the game ------------------------------------------------------
        while(quit == false){
        
            System.out.println("commands> ");
            String input = scanner.nextLine();
            
            if(input.equals("s")) {

                createGameFiles();
                try (FileWriter writer = new FileWriter("saved/myGame.txt")) {
                    writer.write("something");

                    System.out.println("You entered: " + input);
                    Player[] p = new Player[4];

                    // enter player name ------------------------------------------------
                    for (int i = 0; i < 4; i++) {
                        System.out.println("Enter player " + (i + 1) + " name: ");
                        String playerName = scanner.nextLine();
                        p[i] = new Player(playerName);
                    }

                    // say hi to player ---------------------------------------------------
                    System.out.print("Hi, ");
                    for (int i = 0; i < 4; i++) {
                        System.out.print(p[i].playerName + ", ");
                        writer.write(p[i].playerName+"\n");
                    }
                    System.out.print(" enjoy the game!!!");

                    Deck deck = new Deck(p[0].playerName);
                    deck.importCard();
                    //System.out.println("CARD DECK:\n" + "[" + deck.showCard());
                    writer.write("CARD DECK:\n" + "[" + deck.showCard());

                    deck.shuffleCards();
                    //System.out.println("SHUFFLLED CARD DECK:\n" + "[" + deck.showCard());
                    writer.write("SHUFFLLED CARD DECK:\n" + "[" + deck.showCard());

                    String centerString = "center";
                    Player center = new Player(centerString);
                    Player[] centerStr = {center};
                    Player[] centerHand = {center};

                    deck.deal(p, 7);
                    for(Player player : p){
                        player.initialValue = player.getTotal();
                    }

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


                        for (int trick = 1; trick < 10; trick++) {
                            if(gameOver){
                                break;
                            }

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
                                    } else if (i == 0 && !deck.exhausted()) {
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
                                        if(deck.exhausted()){
                                            System.out.println("this player can't win the trick!");
                                            turn++;
                                            turn %= 4;
                                            continue;
                                        }
                                        deck.deal(p[turn],1);
                                        i--;
                                        continue;
                                    }
                                    if(firstCardToGiveString.equals("q")){
                                        writeFiles(p, deck);
                                        writer.close();
                                        startGame();
                                    }
                                }
                                else {
                                    cardToGiveString = scanner.nextLine();

                                    if(cardToGiveString.equals("d")){
                                        if(deck.exhausted()){
                                            System.out.println("this player can't win the trick!");
                                            turn++;
                                            turn %= 4;
                                            continue;
                                        }
                                        deck.deal(p[turn],1);
                                        i--;
                                        continue;
                                    }

                                    if(cardToGiveString.equals("q")){
                                        writeFiles(p, deck);
                                        writer.close();
                                        startGame();
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
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } 
                // game over
                System.out.println("blablabla wins the game");
            } else if(input.equals("x")){
                // Quit the game
                System.out.println("Thanks for playing!");
                quit = true;
            } else if(input.equals("r")){
                System.out.println("game restarted");
                deleteGameFiles();
                startGame();
            } else{ 
                // No such option
                System.out.println("no such command!");
            }
        }
    }
}
