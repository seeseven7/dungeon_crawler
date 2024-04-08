
/**
 * @author Frankie
 * @version 2.0
 */
public class Enemy extends Piece {

    public Enemy() {
        this.symbol = '&';
    }

    public String collide(Piece piece) {
        //gets called when the player goes to a space already occupied by another piece and returns a string
        if (piece instanceof Treasure) {
            return "The enemy ate the treasure!";
        }
        else if (piece instanceof Enemy) {
            return "Invalid move: An enemy cannot collide with another enemy.";
        }
        else if (piece instanceof Exit) {
            return "Invalid move: An enemy cannot collide with an exit.";
        }
        else if (piece instanceof Player) {
            return "Oh man, it's game over!";
        }
        else{
            return "There was an error";
        }
    }
}
