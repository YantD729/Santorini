package edu.cmu.cs214.hw3;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import edu.cmu.cs214.hw3.gameBoard.Grid;

public class PlayerTest {
    private Player testee;
    private Grid target1;
    private Grid target2;


    @Before
    public void setup() {
        testee = new Player("testee");
        target1 = new Grid(2, 3);
        target2 = new Grid(2, 2);
    }

    // @Test
    // public void testCurrentMove() throws Exception {
    //     assertTrue(testee.currentMove(1, target1));
    //     assertTrue(testee.currentMove(1, target2));
    // }

    // @Test
    // public void testGetWorker() throws Exception {
    //     thrown.expect(Exception.class);
    //     thrown.expectMessage("ID should be 0 or 1");
    //     testee.getWorker(3);
    // }

    // @Test
    // public void testWin() throws Exception {
    //     target1.gridBuildTower();
    //     testee.currentMove(0, target1);
    //     assertTrue(testee.win());
    // }
}
