// Read "PLEASEREAD.txt"
// Collision Tests that I can't get to run!!!!

/*
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {
    @Test
    public void testPlayerCollisions() {
        Player player = new Player();

        // Test collision with Treasure
        Treasure treasure = new Treasure(100);
        assertEquals("You found a treasure worth 100 points!", player.collide(treasure));
        assertEquals(100, player.getScore());

        // Test collision with Enemy
        Enemy enemy = new Enemy();
        assertEquals("Oh man, it's game over!", player.collide(enemy));

        // Test collision with Exit
        Exit exit = new Exit();
        assertEquals("You've reached the exit. Onto the next adventure!", player.collide(exit));
    }

    @Test
    public void testEnemyCollisions() {
        Enemy enemy = new Enemy();

        // Test collision with Treasure
        Treasure treasure = new Treasure(100);
        assertEquals("The enemy ate the treasure!", enemy.collide(treasure));

        // Test collision with Enemy
        Enemy anotherEnemy = new Enemy();
        assertEquals("Invalid move: An enemy cannot collide with another enemy.", enemy.collide(anotherEnemy));

        // Test collision with Exit
        Exit exit = new Exit();
        assertEquals("Invalid move: An enemy cannot collide with an exit.", enemy.collide(exit));

        // Test collision with Player
        Player player = new Player();
        assertEquals("Oh man, it's game over!", enemy.collide(player));
    }
}

 */