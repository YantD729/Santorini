package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.cmu.cs214.hw3.component.Worker;

public class GameTest {
    private Player A;
    private Player B;
    private OriginalGame game;

    @Before
    public void setup() {
        Player A = new Player("A");
        Worker wA = new Worker("A", 0, 1, 0);
        A.setCurrWorker(wA);

        Player B = new Player("B");
        Worker wB = new Worker("B", 0, 0, 1);
        B.setCurrWorker(wB);
        game = new OriginalGame(A, B);
    }


    @Test
    public void testCurrentBuild() {
        // assertTrue(game.currentBuild(B, 0, 0).equals("not the right player"));
        // System.out.println(game.currentBuild(game.getCurrPlayer(), 0, 0));
        // //assertTrue(game.currentBuild(A, 0, 0).equals("Tower is Level 1"));
    }

    @Test
    public void testCurrentMove() {
        // assertTrue(game.currentMove(B, 0, 0).equals("not the right player"));
        // assertTrue(game.currentMove(A, 1, 0).equals("Succeeded!"));

        // game.currentBuild(B, 0, 0);
        // game.currentMove(A, 0, 0);
        // assertTrue(game.checkWin());
    }
}
