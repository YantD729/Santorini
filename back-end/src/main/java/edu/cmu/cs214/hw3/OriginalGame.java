package edu.cmu.cs214.hw3;

import java.util.ArrayList;
import java.util.List;

import edu.cmu.cs214.hw3.component.Worker;
import edu.cmu.cs214.hw3.gameBoard.Board;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public class OriginalGame implements Game {
    private boolean gameWin;
    private Player[] playerList;
    private Board playGround;
    private Player currPlayer;
    private Player winner;
    private List<OriginalGame> history;
    private String msg;

    public OriginalGame(Player A, Player B) {
        gameWin = false;
        playGround = new Board();
        playerList = new Player[2];
        playerList[0] = A;
        playerList[1] = B;
        currPlayer = A;
        winner = null;
        history = new ArrayList<OriginalGame>();
        msg = "";
    }

    public String getMessage() {
        return msg;
    }

    public Grid getGameGrid(int x, int y) {
        return playGround.getGrid(x, y);
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrPlayer() {
        return currPlayer;
    }

    public List<OriginalGame> getHistory() {
        return history;
    }

    public Board getPlayGround() {
        return playGround;
    }

    public void gameSetWin(Player A) {
        A.setWin();
        winner = A;
        gameWin = true;
    }

    public void setNextCurrPlayer(Player A) {
        currPlayer = A == playerList[0] ? playerList[1] : playerList[0];
    }

    public OriginalGame play(int x, int y) {
        if (this.getWinner() != null) return this;
        Grid currGrid = this.getGameGrid(x, y);
        Worker gridW = currGrid.getGridWorker();
        Worker pCurrW = currPlayer.getCurrWorker();
        Worker[] wList = currPlayer.getWorkerList();

        //handling move
        if (!currPlayer.hasMoved()) {
            if (gridW == null && pCurrW == null) {
                for (Worker i: wList) {
                    if (i.getXPosition() == -1) {
                        msg = i.move(currGrid);
                        currPlayer.setCurrWorker(i);
                        currPlayer.setMoved();
                        return this.archiveAndReturnACopy(x, y);
                    } 
                } 
            } else if (gridW == null && pCurrW != null) {
                msg = this.currentMove(currPlayer, x, y);
                if (!msg.equals("Succeeded!")) {
                    for (Worker i: wList) {
                        if (i.getXPosition() == -1) {
                            msg = i.move(currGrid);
                            currPlayer.setCurrWorker(i);
                            currPlayer.setMoved();
                            return this.archiveAndReturnACopy(x, y);
                        }
                    }
                    return this;
                }
                return this.archiveAndReturnACopy(x, y);
            } else if (gridW != null) {
                if (pCurrW == null) {
                    if (gridW.getPlayer().equals(currPlayer.getPlayerID())){
                        currPlayer.setCurrWorker(gridW);
                        return this.archiveAndReturnACopy(x, y);
                    }
                    return this;
                }
            } 
        } else if (currPlayer.hasMoved()) {
            msg = this.currentBuild(currPlayer, x, y);
            if (!msg.equals("Succeeded!")) return this;
            currPlayer.setCurrWorker(null);
            currPlayer.setUnMoved();
            currPlayer = currPlayer == playerList[0] ? playerList[1] : playerList[0];
            return this.archiveAndReturnACopy(x, y);
        }

        return this;
    }

    public void copyGame(Board playGrd, List<OriginalGame> oldHistory, String oldMsg, Player oldPlayer) {
        this.playGround = playGrd;
        this.history = oldHistory;
        this.msg = oldMsg;
        this.currPlayer = oldPlayer;
    }

    public OriginalGame archiveAndReturnACopy(int x, int y) {
        List<Game> newHistory = new ArrayList<>(this.history);
        newHistory.add(this);
        OriginalGame newGame = new OriginalGame(playerList[0], playerList[1]);
        newGame.copyGame(playGround.updateGrid(x, y, this.getGameGrid(x, y).getGridWorker(), this.getGameGrid(x, y).getGridTower()), history, msg, currPlayer);
        return newGame;
    }

    /** 
     * @param A current player
     * @param workerID the ID of the worker you want to move
     * @param x x parameter of target position
     * @param y y parameter of target position
     * @return boolean successful or not
     * @throws Exception error move info
     */
    public String currentMove(Player A, int x, int y) {
        if (!playerRound(A)) return "not the right player";
        Grid target = playGround.getGrid(x, y);
        Grid origin = playGround.getGrid(A.getCurrWorker().getXPosition(), A.getCurrWorker().getYPosition());
        String res = A.moveWorker(target);
        System.out.println("game" + res);

        if (res.equals("Succeeded!")) {
            origin.setWorker(null);
            if (A.win()) {
                winner = A;
                gameWin = true;
            }
        } 
        return res;
    }

    
    /** 
     * @param A current player
     * @param x x parameter of target position
     * @param y y parameter of target position
     * @return int tower level after built
     * @throws Exception error move info
     */
    public String currentBuild(Player A, int x, int y) {
        if (!this.playerRound(A)) return "not the right player";
        Grid target = playGround.getGrid(x, y);
        Worker currW = currPlayer.getCurrWorker();

        String res = target.gridBuildTowerFrom(currW.getXPosition(), currW.getYPosition());
        
        return res;
    }

    
    /** 
     * @param A player want to test
     * @return boolean whether is the round of A
     */
    public boolean playerRound(Player A) {
        return currPlayer == A;
    }

    
    /** 
     * @return boolean
     */
    public boolean checkWin() {
        return gameWin;
    }

    public String getMsg() {
        return msg;
    }

    public OriginalGame undo() {
        int s = this.history.size();
        if (s == 0)
            return this;
        OriginalGame oldGame = this.history.get(s - 1);
        this.history.remove(s - 1);
        OriginalGame newGame = new OriginalGame(playerList[0], playerList[1]);
        newGame.copyGame(oldGame.getPlayGround(), oldGame.getHistory(), oldGame.getMsg(), oldGame.getCurrPlayer());
        return newGame;
    }

}
