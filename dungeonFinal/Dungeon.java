import java.util.Scanner;

/**
 * @author Frankie
 * @version 2.0
 */
public class Dungeon {

    Board room;
    int totalScore = 0;
    int highScore = 0;

    void init(int level, int size) {
        room = new Board(size);
        Move playerLoc = new Move(size - 1, size - 1);
        room.setPlayer(playerLoc);
        room.setTreasure(5);
        room.setEnemies(level);
        room.setExit();
    }

    //PART 2
    String playerTurn() {
        // This method asks for input from the user, validates the input, calculates the direction of the move,
        // and calls the movePlayer method in Board.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your move (a, s, d, or w):");
        String input = scanner.nextLine();

        while (!input.equals("a") && !input.equals("s") && !input.equals("d") && !input.equals("w")) {
            System.out.println("Invalid move! Please enter a valid move (a, s, d, or w):");
            input = scanner.nextLine();
        }

        int deltaRow = 0;
        int deltaCol = 0;

        switch (input) {
            case "a":
                deltaCol = -1;
                break;
            case "d":
                deltaCol = 1;
                break;
            case "w":
                deltaRow = -1;
                break;
            case "s":
                deltaRow = 1;
                break;
        }
        return room.movePlayer(deltaRow, deltaCol);
    }

    void play() {
        // OH MY GOLLY GOONESS THERES SO MUCH STUFF GOING ON AT ONCE IN THIS METHOD
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int level = 1;

        //board initialization and
        while (!input.equals("quit")) {
            System.out.println("Enter your board size (b/n 3 and 9):");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid number!");
                scanner.next();
            }
            int size = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (size * size < level + 6) {
                System.out.println("Game over: not enough space on the board!");
                break;
            }

            init(level, size);
            System.out.println(room); // Print the initial game board

            boolean gameOver = false;
            while (!gameOver) {
                String result = playerTurn();
                room.moveEnemies();
                System.out.println(room);

                if (result.equals("Oh man, it's game over!") || result.equals("You've reached the exit. Onto the next adventure!")) {
                    gameOver = true;
                    totalScore += room.myLoc.getScore();

                    //isolated test
                    System.out.println("Your score for this level: " + room.player.score);
                    totalScore += room.player.score;
                    //IT WORKS OH MY GOODNESS

                    System.out.println("Your total score: " + totalScore);
                    if (totalScore > highScore) {
                        highScore = totalScore;
                        System.out.println("Congratulations! You've set a new high score!");
                    }
                    System.out.println("Level: " + level);
                    System.out.println("High score: " + highScore);
                    System.out.println("Type 'quit' to exit or any other key to play again.");
                    input = scanner.nextLine();
                }
            }
            level++;
        }
    }

}
