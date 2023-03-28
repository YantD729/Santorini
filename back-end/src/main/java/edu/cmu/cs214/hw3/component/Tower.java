package edu.cmu.cs214.hw3.component;

/**
 * Tower class. Tower class is designed as a component of grid.
 * 
 * @author Yanting Deng
 * @andrewID yantingd
 */

public class Tower {
    private int[] currentPosition;
    private int currentLevel;
    private boolean dome;

    public Tower(int x, int y) {
        currentPosition = new int[2];
        currentPosition[0] = x;
        currentPosition[1] = y;
        currentLevel = 1;
        dome = false;
    }

    /** 
     * get the level of the tower.
     * @return int current level(s) of the tower.
     */
    public int getLevel() {
        return currentLevel;
    }

    /** 
     * get the position of the tower.
     * @return int[] the first element is x, the second is y.
     */
    public int[] getPosition() {
        return currentPosition;
    }

    /** 
     * get the level of the tower after being buildt.
     * @return int current level(s) of the tower.
     */
    public int buildTower() {
        if (currentLevel < 3) currentLevel += 1;
        else if (currentLevel == 3 && dome == false) dome = true;
        return currentLevel;
    }

    /** 
     * @return boolean if has a dome.
     */
    public boolean hasDome() {
        return dome;
    }
}
