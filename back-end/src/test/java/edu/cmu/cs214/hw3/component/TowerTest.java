package edu.cmu.cs214.hw3.component;

import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class TowerTest {
    private Tower tower1;

    @Before
    public void setup() {
        tower1 = new Tower(0, 0);
    }

    @Test
    public void testBuildTower() {
        tower1.buildTower();
        assertTrue(tower1.buildTower() == 3);
        assertTrue(tower1.buildTower() == 3);
        assertTrue(tower1.hasDome());
    }

}
