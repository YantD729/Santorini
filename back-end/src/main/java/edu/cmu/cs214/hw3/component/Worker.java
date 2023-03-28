package edu.cmu.cs214.hw3.component;

import edu.cmu.cs214.hw3.gameBoard.Grid;

/**
 * Worker is belong to player.
 * 
 * @author Yanting Deng
 * @andrewID yantingd
 */

public class Worker {
    private int[] currentPosition;
    private int currentLevelOnTower;
    private int workerID;
    private String playerID;

    public Worker(String pID, int ID, int x, int y) {
        playerID = pID;
        currentPosition = new int[2];
        workerID = ID;
        currentPosition[0] = x;
        currentPosition[1] = y;
        currentLevelOnTower = 0;
    }

    
    /** 
     * @param targetGrid the grid you want to move worker to.
     * @return boolean whether this is a valid move
     * @throws Exception the grid is adjacent/occupied
     *         by a worker/has a high tower/has a dome.
     */
    public String isValidMove(Grid targetGrid) {
        int currx = currentPosition[0];
        int curry = currentPosition[1];
        int x = targetGrid.getPosition()[0];
        int y = targetGrid.getPosition()[1];

        if ((currx != -1) && ((Math.abs(currx - x) > 1 || Math.abs(curry - y) > 1))) {
            return "target position is too far!";
        }

        if (currx == x && curry == y) {
            return "cannot be the same Grid";
        }

        if (targetGrid.getGridWorker() != null) {
            return "already has a worker!";
        }

        if (targetGrid.getGridTower() != null) {
            if (Math.abs(targetGrid.getGridTower().getLevel() - currentLevelOnTower) > 1) {
                return "Tower is too high!";
            } else if (targetGrid.getGridTower().hasDome()) {
                return "there is a dome";
            }
        }
        return "Succeeded!";
    }

     /** 
     * @param targetGrid the grid you want to move worker to.
     * @return boolean whether move successful or not.
     * @throws Exception the grid is adjacent/occupied
     *         by a worker/has a high tower/has a dome.
     */
    public String move(Grid targetGrid) {
        String msg = isValidMove(targetGrid);
        if (msg.equals("Succeeded!")) {
            targetGrid.setWorker(this);
            if (targetGrid.getGridTower() != null) {
                setLevelOnTower(targetGrid.getGridTower().getLevel());
            } else setLevelOnTower(0);
            currentPosition[0] = targetGrid.getPosition()[0];
            currentPosition[1] = targetGrid.getPosition()[1];
        }
        return msg;
    }
    
    /** 
     * @return int worker ID
     */
    public int getWorkerID() {
        return workerID;
    }
    
    /** 
     * @return int[] the first is column position, the second is row position.
     */
    public int[] getPosition() {
        return currentPosition;
    }

    public int getXPosition() {
        return currentPosition[0];
    }

    public int getYPosition() {
        return currentPosition[1];
    }

    public void setXPosition(int x) {
        currentPosition[0] = x;
    }

    public void setYPosition(int y) {
        currentPosition[1] = y;
    }

    public String getPlayer() {
        return playerID;
    }

    
    /** 
     * @return int which level is the worker stands on.
     */
    public int getLevelOnTower() {
        return currentLevelOnTower;
    }

    public void setLevelOnTower(int x) {
        currentLevelOnTower = x;
    }

}