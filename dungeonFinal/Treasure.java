import java.util.Random;

/**
 * @author Frankie
 * @version 2.0
 */
public class Treasure extends Piece {

    int value;

    public Treasure() {
        this.symbol = '$';
        Random x = new Random();
        this.value = (x.nextInt(10) + 1) * 100;
    }

    public int getValue() {
        return this.value;
    }
}
