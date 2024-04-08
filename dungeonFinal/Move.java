
/**
 * @author Frankie
 * @version 2.0
 */
public class Move {

    int row;
    int col;
    public int score;

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getScore() {
        // Return the score of the player
        return this.score;
    }

    public String toString() {
        return "(" + row + ", " + col + ")";
    }
}
