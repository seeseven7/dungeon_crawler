
/**
 * @author Frankie
 * @version 2.0
 */
public class Player extends Piece {

    public int score;

    public Player() {
        this.symbol = '*';
        this.score = 0;
    }

    public String collide(Piece piece) {
        //gets called when the player goes to a space already occupied by another piece and returns a string
        if(piece instanceof Treasure) {
            Treasure treasure =  (Treasure) piece;
            this.score += treasure.getValue(); // Update the player's score
            return "You found a treasure worth " + treasure.getValue() + " points!";
        }
        else if (piece instanceof Enemy) {
            return "Oh man, it's game over!";
        }
        else if (piece instanceof Exit) {
            return "You've reached the exit. Onto the next adventure!";
        }
        else {
            return "There was an error.";
        }
    }
}
