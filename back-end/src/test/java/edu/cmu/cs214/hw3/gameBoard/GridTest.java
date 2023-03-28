package edu.cmu.cs214.hw3.gameBoard;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import edu.cmu.cs214.hw3.component.Worker;

public class GridTest {
    private Grid grid1;
    private Worker worker1;

    @Before
    public void setup() {
        grid1 = new Grid(0, 0);
        worker1 = new Worker("A", 1, 0, 1);
    }

    @Test
    public void testGridBuildTowerFrom() {
        assertTrue(grid1.gridBuildTowerFrom(worker1.getXPosition(), worker1.getYPosition()).equals("Succeeded!"));
        assertTrue(grid1.getGridTower().getLevel() == 1);
    }
}
