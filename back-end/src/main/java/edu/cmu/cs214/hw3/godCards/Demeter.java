package edu.cmu.cs214.hw3.godCards;

import edu.cmu.cs214.hw3.Game;
import edu.cmu.cs214.hw3.GodCardsGame;
import edu.cmu.cs214.hw3.Player;
import edu.cmu.cs214.hw3.component.Worker;

public class Demeter extends GodCardsGame {

    private int roundCount;

    public Demeter(Game baseGame) {
        super(baseGame);
        roundCount = 0;
    }

    @Override
    public Game play(int x, int y) {
        if (baseGame.getWinner() != null) return baseGame;
        Player currPlayer = baseGame.getCurrPlayer();
        Worker pCurrW = currPlayer.getCurrWorker();

        if (!currPlayer.hasMoved()) {
            baseGame.play(x, y);
        } else if (currPlayer.hasMoved() && roundCount == 0){
            String msg = this.currentBuild(currPlayer, x, y);
            if (!msg.equals("Succeeded!")) return this;
            roundCount++;
            return this.archiveAndReturnACopy(x, y);
        } else if (currPlayer.hasMoved() && roundCount == 1) {
            roundCount = 0;
            if (x == pCurrW.getXPosition() && y == pCurrW.getYPosition()) {
                currPlayer.setCurrWorker(null);
                currPlayer.setUnMoved();
                setNextCurrPlayer(currPlayer);
                return this.archiveAndReturnACopy(x, y);
            }
            String msg = this.currentBuild(currPlayer, x, y);
            if (!msg.equals("Succeeded!")) return this;
            currPlayer.setCurrWorker(null);
            currPlayer.setUnMoved();
            setNextCurrPlayer(currPlayer);
            return this.archiveAndReturnACopy(x, y);
        }

        return baseGame;
    }

}