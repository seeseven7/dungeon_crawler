import java.util.Random;
import java.util.LinkedList;

/**
 * @author Frankie
 * @version 2.0
 * 
 * Formatted so it's a lot more compact because it looked ugly beforehand!!
 */
public class Board {

    int size;
    Piece[][] board;
    Move myLoc;
    Move[] enemies;
    Player player;

    public Board(int size) {
        //Constructor - takes in dimensions of the board b/n 1 and 9, init. a fully null square board
        this.size = size;
        this.board = new Piece[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.board[i][j] = null;
            }
        }
    }

    boolean isValid(Move m) {
        //returns true if the move is within the bounds of the room
        return 
            m.row >= 0 && 
            m.row < size &&
            m.col >= 0 &&
            m.col < size;
    }

    Move getRandomLoc() {
        //helper method that returns a position that is not yet filled
        Random x = new Random();
        int row = x.nextInt(size);
        int col = x.nextInt(size);
        while(board[row][col] != null) {
            row = x.nextInt(size);
            col = x.nextInt(size);
        }
        return new Move(row, col);
    }

    void setPlayer(Move loc) {
        //sets the player piece to a specified location, and also sets the MyLoc instance variable
        this.player = new Player();
        this.board[loc.row][loc.col] = this.player;
        this.myLoc = loc;
    }

    void setEnemies(int num) {
        //takes in the nr. of enemies, initializes Move[] to have the length equal to the nr. of enemies
        //also picks a random unfilled location, sets the enemies there, and saves the location to Move[] enemies
        this.enemies = new Move[num];
        for(int i = 0; i < num; i++) {
            Move loc = getRandomLoc();
            this.enemies[i] = loc;
            this.board[loc.row][loc.col] = new Enemy(); 
        }
    }

    void setTreasure(int num) {
        //places treasure in a random unfilled space
        for(int i = 0; i < num; i++) {
            Move loc = getRandomLoc();
            this.board[loc.row][loc.col] = new Treasure();
        }
    }

    void setExit() {
        //places exit in a random unfilled space
        Move loc = getRandomLoc();
        this.board[loc.row][loc.col] = new Exit();
    }

    // PART 2
    // string was added by to fix an issue though I dont believe its supposed to be there
    String movePlayer(int deltaRow, int deltaCol) {
        // Calculates the new position
        int newRow = myLoc.row + deltaRow;
        int newCol = myLoc.col + deltaCol;
        Move newLoc = new Move(newRow, newCol);

        // Checks if the move is valid
        if (isValid(newLoc)) {

            // Checks for collision
            if (board[newLoc.row][newLoc.col] != null) {
                String result = player.collide(board[newLoc.row][newLoc.col]);
                System.out.println(result); // Print the result of the collision
                if (result.equals("Oh man, it's game over!") || result.equals("You've reached the exit. Onto the next adventure!")) {
                    return result;
                }
                // Remove the collided piece from the board
                board[newLoc.row][newLoc.col] = null;
            }

            // Remove the player from the current location
            board[myLoc.row][myLoc.col] = null;

            // Update the player's position
            myLoc = newLoc;
            board[myLoc.row][myLoc.col] = player;
        }
        return "Result of the move";
    }

    public Move getValidEnemyMove(Move curLoc) {
        // we first create a LinkedList of all possible locations. We then check to see
        // if each one is valid. If not, we remove it from the list. We return a random
        // move from the list, or null if no valid moves exist.
        LinkedList<Move> validMoves = new LinkedList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        for (int[] direction : directions) {
            Move newLoc = new Move(curLoc.row + direction[0], curLoc.col + direction[1]);
            if (isValid(newLoc) && !(board[newLoc.row][newLoc.col] instanceof Exit) && !(board[newLoc.row][newLoc.col] instanceof Enemy)) {
                validMoves.add(newLoc);
            }
        }

        if (validMoves.isEmpty()) {
            return null; // Signifies that the enemy should not move
        } else {
            Random rand = new Random();
            return validMoves.get(rand.nextInt(validMoves.size()));
        }
    }

    void moveEnemies() {
        // we iterate over all enemies. For each enemy, we get a valid move using the
        // getValidEnemyMove(Move curLoc) method and move the enemy to the new location.
        // If the new location contains a Player, we handle this event according to the
        // game’s rules. The enemy can eat the treasure or the player, but it should not
        // be able to eat the exit or other enemies. This is ensured by the checks in
        // the getValidEnemyMove(Move curLoc) method.
        for (int i = 0; i < enemies.length; i++) {
            Move enemyLoc = enemies[i];
            Move newLoc = getValidEnemyMove(enemyLoc);
            if (newLoc != null) {
                // Check for collision with the player
                if (board[newLoc.row][newLoc.col] instanceof Player) {
                    System.out.println("Oh man, it's game over!");
                    System.exit(0);
                }
                // Remove the enemy from the current location
                board[enemyLoc.row][enemyLoc.col] = null;
                // Update the enemy's position
                enemies[i] = newLoc;
                board[newLoc.row][newLoc.col] = new Enemy();
            }
        }
    }

    public String toString() {
        //sets up the board!!
        String b = " ";
        for(int i = 0; i < size; i++) {
            b += (i + 1) + " ";
        }
        b += "\n";
        for(int i = 0; i < size; i++) {
            b += (char) ('A' + i) + " ";
            for(int j = 0; j < size; j++) {
                if(board[i][j] == null) { // This nesting gives me a heart attack
                    b += "_ ";
                }
                else {
                    b += board[i][j] + " ";
                }
            }
            b += "| " + "\n";
        }
        for(int i = 0; i < size; i++) {
            b += "--";
        }
        // And finally...
        return b;
    }
}