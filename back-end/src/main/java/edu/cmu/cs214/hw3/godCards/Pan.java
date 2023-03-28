package edu.cmu.cs214.hw3.godCards;

import edu.cmu.cs214.hw3.Game;
import edu.cmu.cs214.hw3.GodCardsGame;
import edu.cmu.cs214.hw3.Player;
import edu.cmu.cs214.hw3.component.Worker;

public class Pan extends GodCardsGame{
    private int[] prevTowerLv;
    private int[] lvDiff;

    public Pan(Game baseGame) {
        super(baseGame);
        prevTowerLv = new int[4];
        lvDiff = new int[4];
    }

    public int getIdxOfList(Player currP, Worker currW) {
        int currPID = currP.getPlayerID().equals("A") ? 0 : 2;
        int currWID = currW.getWorkerID() == 0 ? 0 : 1;
        return currPID + currWID;
    }

    @Override
    public Game play(int x, int y) {
        if (baseGame.getWinner() != null) return baseGame;
        Player currPlayer = baseGame.getCurrPlayer();
        Worker pCurrW = currPlayer.getCurrWorker();
        if (!currPlayer.hasMoved()) {
            baseGame.play(x, y);
        }  else if (currPlayer.hasMoved()) {
            pCurrW = currPlayer.getCurrWorker();
            int idx = getIdxOfList(currPlayer, currPlayer.getCurrWorker());
            int currlv = pCurrW.getLevelOnTower();
            if (currlv > prevTowerLv[idx]) lvDiff[idx] = 0;
            else lvDiff[idx] += currlv - prevTowerLv[idx];
            if (lvDiff[idx] == -2) baseGame.gameSetWin(currPlayer);
            prevTowerLv[idx] = pCurrW.getLevelOnTower();
            baseGame.play(x, y);
        }
        return baseGame;
    }

}
