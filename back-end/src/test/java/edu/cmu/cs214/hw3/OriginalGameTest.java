package edu.cmu.cs214.hw3;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.component.Worker;

public class OriginalGameTest {
    private Player A;
    private Player B;
    private OriginalGame game;

    @Before
    public void setup() {
        Player A = new Player("A");
        Player B = new Player("B");

        game = new OriginalGame(A, B);

        game.play(0, 0);

    }

    // @Test
    // public void testCopyStatus() {

    //     //assertTrue(tower1.buildTower() == 3);
    // }
    
}
