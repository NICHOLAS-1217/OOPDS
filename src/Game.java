import java.nio.file.Path;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Game {

    private static final Scanner scanner = new Scanner(System.in);
    public static Player[] p = new Player[4];
    public static Player center;
    public static Player[] centerStr;
    public static Player[] centerHand;
    public static Deck deck;
    public static boolean gameOver = false;
    public static int trick;
    public static int turn;
    public static String firstCardToGiveString = "";


    private static final String fileName = "saved/anotherGame.txt";


    // menu parts------------------------
    static String[] menuChoices = {
        "s - Start a new game",
        "l - Load game",
        "x - Exit the game", 
        "d - Draw cards from deck until a playable card is obtained. If the deck is empty, skip to the next player. ",
        "card - a card played by the current player",
        "q - quit the game to the menu"
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

    public static void writeFiles(int order) {
        try {
            FileWriter writer = new FileWriter(fileName);

            for(Player player : p) {
                writer.write(player.playerName+"\n"); // players' name
            }

            for(Player player : p) {
                writer.write(player.showCard()+"\n"); // players' hand
            }

            for(Player player : p) {
                writer.write(player.trickPoints+"\n"); // players' trick points
            }

            for(Player player : p) {
                writer.write(player.initialValue+"\n"); // players' initial value
            }

            writer.write(centerHand[0].showCard()+"\n"); // center deck
            writer.write(deck.showCard()+"\n"); // deck

            writer.write(Integer.toString(trick)+"\n");
            writer.write(Integer.toString(turn)+"\n");
            writer.write(firstCardToGiveString+"\n");
            writer.write(Integer.toString(order)+"\n");

            writer.write("end of line");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int loadFiles(){
        try {
            String line;
            int lineNum = 0;
            int nextIteration = 4;
            Path path = Paths.get((fileName));
            p = new Player[4]; // clear array

            // read player name
            for (; lineNum < nextIteration; lineNum++) {
                line = Files.readAllLines(path).get(lineNum);
                Player player = new Player(line);
                p[lineNum] = player;
            }

            nextIteration += 4;
            // players' hand
            for (; lineNum < nextIteration; lineNum++) {
                int x = lineNum % 4;
                line = Files.readAllLines(path).get(lineNum);
                p[x].dealLoad(line);
            }

            nextIteration += 4;
            // players' trick points
            for (; lineNum < nextIteration; lineNum++) {
                int x = lineNum % 4;
                line = Files.readAllLines(path).get(lineNum);
                p[x].trickPoints = Integer.parseInt(line);
            }

            nextIteration += 4;
            // players' initial value
            for (; lineNum < nextIteration; lineNum++) {
                int x = lineNum % 4;
                line = Files.readAllLines(path).get(lineNum);
                p[x].initialValue = Integer.parseInt(line);
            }

            line = Files.readAllLines(path).get(lineNum);
            center = new Player("center");
            centerHand = new Player[]{center};
            centerStr = new Player[]{center};
            centerHand[0].dealLoad(line);

            lineNum++;
            line = Files.readAllLines(path).get(lineNum);
            deck = new Deck(p[0].playerName);
            deck.dealLoad(line);

            lineNum++;
            line = Files.readAllLines(path).get(lineNum);
            trick = Integer.parseInt(line);

            lineNum++;
            line = Files.readAllLines(path).get(lineNum);
            turn = Integer.parseInt(line);

            lineNum++;
            line = Files.readAllLines(path).get(lineNum);
            firstCardToGiveString = line;

            lineNum++;
            line = Files.readAllLines(path).get(lineNum);
            System.out.println(line);

            return Integer.parseInt(line);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void start(){
        createGameFiles();

            // enter player name ------------------------------------------------
            for (int k = 0; k < 4; k++) {
                System.out.println("Enter player " + (k + 1) + " name: ");
                String playerName = scanner.nextLine();
                p[k] = new Player(playerName);
            }

            // say hi to player ---------------------------------------------------
            System.out.print("Hi, ");
            for (int j = 0; j < 4; j++) {
                System.out.print(p[j].playerName + ", ");
            }
            System.out.print(" enjoy the game!!!");

            deck = new Deck(p[0].playerName);
            deck.importCard();
            //System.out.println("CARD DECK:\n" + "[" + deck.showCard());

            deck.shuffleCards();
            //System.out.println("SHUFFLLED CARD DECK:\n" + "[" + deck.showCard());

            String centerString = "center";
            center = new Player(centerString);
            centerStr = new Player[]{center};
            centerHand = new Player[]{center};

            deck.deal(p, 7);
            for (Player player : p) {
                player.initialValue = player.getTotal();
            }

            deck.dealCenter(center, 1);
            // 1 turn
            clearScreen();
            // first lead
            String firstLeadCard = centerHand[0].getRankString();
            trick = 1;
            turn = 0;
            int order = 0;
            // not game over
            // gameplay(trick, turn);
            gameplay(firstLeadCard, order);
    }

    public static void gameplay(String firstLeadCard, int order) {

        while (!gameOver) {
            int highestTrickPoint = 0;
            int highestPrio = 0;
            int leadPlayer = -1;

            for (int j = 0; j < p.length; j++) {
                Player player = p[j];
                if (player.trickPoints > highestTrickPoint) {
                    highestTrickPoint = player.trickPoints;
                    highestPrio = player.getRankPriority();
                    leadPlayer = j;
                } else if (player.trickPoints == highestTrickPoint) {
                    if (player.getRankPriority() < highestPrio) {
                        leadPlayer = j;
                    } 
                }
            }

            if (trick > 1)
                System.out.println("Player " + p[leadPlayer].playerName + " wins the trick");

            for (; order < 4; order++) {

                if (order == 0 && trick == 1) {
                    switch (firstLeadCard) {
                        case "A", "5", "9", "K" -> turn = 0;
                        case "2", "6", "10" -> turn = 1;
                        case "3", "7", "J" -> turn = 2;
                        case "4", "8", "Q" -> turn = 3;
                    }
                } else if (order == 0 && !deck.exhausted()) {
                    turn = leadPlayer;
                }
                // first lead player

                //player 1 lead
                System.out.println("trick #" + trick);
                System.out.println("Turn #" + (order+1));

                // show player
                int player_turn = turn;
                for (int k = 0; k < 4; k++) {
                    player_turn %= 4;
                    System.out.print(p[player_turn].playerName + ": ");
                    System.out.print("[");
                    System.out.print(p[player_turn].showCard() + "] total points = " + p[player_turn].getTotal() + "\n");
                    player_turn++;
                }
                // show center
                for (int j = 0; j < centerHand.length; j++) {
                    System.out.print(centerStr[j].showName() + ": ");
                    System.out.print("[" + centerHand[j].showCard() + "] total points = " + centerHand[j].getTotal() + "\n");
                    //System.out.println(centerHand[j].getRankValue());
                }
                // show deck
                System.out.println("Deck: [" + deck.showCard() + "] total points = " + deck.getTotal() + "\n");
                // show player turn
                System.out.print(p[turn].playerName + "> ");
                // player card input
                String cardToGiveString;
                boolean success = false;

                if (order == 0) {
                    firstCardToGiveString = scanner.nextLine();
                    success = p[turn].giveCardToCenter(firstCardToGiveString, center);

                    if (firstCardToGiveString.equals("d")) {
                        if (deck.exhausted()) {
                            System.out.println("this player can't win the trick!");
                            turn++;
                            turn %= 4;
                            continue;
                        }
                        deck.deal(p[turn], 1);
                        order--;
                        continue;
                    }
                    if (firstCardToGiveString.equals("q")) {
                        writeFiles(order);
                        startGame();
                    }
                } else {
                    cardToGiveString = scanner.nextLine();

                    if (cardToGiveString.equals("d")) {
                        if (deck.exhausted()) {
                            System.out.println("this player can't win the trick!");
                            turn++;
                            turn %= 4;
                            continue;
                        }
                        deck.deal(p[turn], 1);
                        order--;
                        continue;
                    }

                    String[] suitAndRank = cardToGiveString.split("");
                    for (String val : suitAndRank) {
                        if (firstCardToGiveString.contains(val)) {
                            success = p[turn].giveCardToCenter(cardToGiveString, center);
                            break;
                        }
                    }

                    if (cardToGiveString.equals("q")) {
                        writeFiles(order);
                        startGame();
                    }
                }

                p[turn].trickPoints = p[turn].initialValue - p[turn].getTotal();
                p[turn].initialValue -= p[turn].trickPoints;

                if (!success) {
                    System.out.println("Invalid card or card not found in your hand.");
                    order--;
                    continue;
                }
                if (p[order].cards.isEmpty()) {
                        Player lowestPointPlayer = p[0]; // Initialize with the first player
                            for (Player player : p) {
                                if (player.getTotal() < lowestPointPlayer.getTotal()) {
                                    lowestPointPlayer = player;
                                }
                            }

                            System.out.println(lowestPointPlayer.playerName + " wins the game");
                            System.out.println(">>>>>LEADERBOARD<<<<<<");

                            // Sort players by total points from lowest to highest
                            Arrays.sort(p, Comparator.comparingInt(Player::getTotal));

                            for (Player player : p) {
                                System.out.println(player.playerName + ": " + player.getTotal() + " points");
                            }
                        
                    
                            gameOver = true;
                            break;
                }

                turn++;
                turn %= 4;

                if(order==3){
                    order=0;
                    break;
                }

            }
            trick++;

        }
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
        while(!quit){
        
            System.out.println("commands> ");
            String input = scanner.nextLine();

            switch (input) {
                case "s" -> {
                    start();
                }
                case "l" -> {
                    int order = loadFiles();
                    gameplay("", order);
                }
                case "x" -> {
                    // Quit the game
                    System.out.println("Thanks for playing!");
                    quit = true;
                }
                case "r" -> {
                    System.out.println("game restarted");
                    deleteGameFiles();
                    startGame();
                }
                default ->
                    // No such option
                        System.out.println("no such command!");
            }
        }
    }
}
