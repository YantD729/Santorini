package edu.cmu.cs214.hw3.component;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public class WorkerTest {
    private Grid grid1;
    private Grid gridWithTower;
    private Grid gridWithHighTower;
    private Grid gridWithDome;
    private Worker workerToTest;
    @Before
    public void setup() {
        grid1 = new Grid(1, 4);

        gridWithTower = new Grid(0, 1);
        gridWithTower.gridBuildTowerFrom(0, 0);

        gridWithHighTower = new Grid(1, 0);
        gridWithHighTower.gridBuildTowerFrom(0, 0);
        gridWithHighTower.gridBuildTowerFrom(0, 0);

        gridWithDome = new Grid(0, 1);
        gridWithDome.gridBuildTowerFrom(0, 0);
        gridWithDome.gridBuildTowerFrom(0, 0);
        gridWithDome.gridBuildTowerFrom(0, 0);
        
        workerToTest = new Worker("A", 1, 0, 0);

    }

    @Test
    public void testIsValidMove() {

        assertTrue(workerToTest.isValidMove(grid1).equals("target position is too far!"));
        assertTrue(workerToTest.isValidMove(gridWithTower).equals("Succeeded!"));
        assertTrue(workerToTest.isValidMove(gridWithHighTower).equals("Tower is too high!"));

    }

}
