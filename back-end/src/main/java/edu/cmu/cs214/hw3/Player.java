package edu.cmu.cs214.hw3;

import edu.cmu.cs214.hw3.component.Worker;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public class Player {
    private Worker[] workers;
    private Worker currWorker;
    private String playerID;
    private boolean winFlag;
    private boolean hasMovedFlag;
    
    public Player(String ID) {
        workers = new Worker[2];
        workers[0] = new Worker(ID, 0, -1, -1);
        workers[1] = new Worker(ID, 1, -1, -1);
        currWorker = null;
        playerID = ID;
        winFlag = false;
        hasMovedFlag = false; 
    }

    public Worker getCurrWorker() {
        return currWorker;
    }

    public void setCurrWorker(Worker worker) {
        currWorker = worker;
    }

    public void clearCurrWorker() {
        currWorker = null;
    }

    public boolean win() {
        return winFlag;
    }

    public Worker[] getWorkerList() {
        return workers;
    }

    public void setWin() {
        winFlag = true;
    }

    public boolean hasMoved() {
        return hasMovedFlag; 
    }

    public void setMoved() {
        hasMovedFlag = true;
    }

    public void setUnMoved() {
        hasMovedFlag = false;
    }

    public Worker getWorker(int ID) {
        if (ID != 0 && ID != 1) {
            return null;
        }
        return workers[ID];
    }

    public String getPlayerID() {
        return playerID;
    }

    public String moveWorker(Grid targetGrid) {
        String canMove = currWorker.move(targetGrid);
        if (targetGrid.winStatus()) winFlag = true;
        return canMove;
    }
}
