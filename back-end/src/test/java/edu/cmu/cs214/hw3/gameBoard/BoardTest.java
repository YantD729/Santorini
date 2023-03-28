package edu.cmu.cs214.hw3.gameBoard;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    private Board playGround;


    @Before
    public void setup() {
        playGround = new Board();

    }

    @Test
    public void testValidBuildTower() {
        assertTrue(playGround.validBuildTower(0, 0, 5, 3).equals("grid position out of range!"));
    }

    @Test
    public void testCurrentBuild() {
        assertTrue(playGround.currentBuild(1, 0, 0, 0).equals("Succeeded!"));
        assertTrue(playGround.getGrid(0, 0).getGridTower().getLevel() == 1);
    }
}
