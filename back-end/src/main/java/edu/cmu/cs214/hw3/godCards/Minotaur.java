package edu.cmu.cs214.hw3.godCards;

import edu.cmu.cs214.hw3.Game;
import edu.cmu.cs214.hw3.GodCardsGame;
import edu.cmu.cs214.hw3.Player;
import edu.cmu.cs214.hw3.component.Tower;
import edu.cmu.cs214.hw3.component.Worker;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public class Minotaur extends GodCardsGame{


    public Minotaur(Game game) {
        super(game);
    
    }

    @Override
    public Game play(int x, int y) {
        if (baseGame.getWinner() != null) return baseGame;
        Player currPlayer = baseGame.getCurrPlayer();
        Worker pCurrW = currPlayer.getCurrWorker();
        Grid currG = baseGame.getGameGrid(x, y);
        Worker currGWorker = currG.getGridWorker();

        if (!currPlayer.hasMoved()) {
            if (currGWorker != null && currGWorker.getPlayer().equals(currPlayer.getPlayerID())) {
                currPlayer.setCurrWorker(currGWorker);
            }
            if (currGWorker != null && !currGWorker.getPlayer().equals(currPlayer.getPlayerID())
                && pCurrW != null) {
                int workerPosX = pCurrW.getXPosition();
                int workerPosY = pCurrW.getYPosition();
                Tower gridTower = currG.getGridTower();
                if (gridTower == null || !gridTower.hasDome()) {
                    Grid forcedMovedGrid = null;
                    int forcedMovedGridX = x;
                    int forcedMovedGridY = y;
                    if (workerPosX == x ) {
                        forcedMovedGridY = workerPosY < y ? y + 1 : y - 1;
                    } else if (workerPosY == y) {
                        forcedMovedGridX = workerPosX < x ? x + 1 : x - 1;
                    } else if (Math.abs(y - workerPosY) == Math.abs(x - workerPosX)) {
                        forcedMovedGridY = workerPosY < y ? y + 1 : y - 1;
                        forcedMovedGridX = workerPosX < x ? x + 1 : x - 1;
                    }
                    if (forcedMovedGridX >= 0 && forcedMovedGridX < 5 &&
                        forcedMovedGridY >= 0 && forcedMovedGridY < 5) {
                        forcedMovedGrid = baseGame.getGameGrid(forcedMovedGridX, forcedMovedGridY);
                        if (forcedMovedGrid.getGridWorker() == null && 
                            (forcedMovedGrid.getGridTower() == null ||!forcedMovedGrid.getGridTower().hasDome())) {
                            forcedMovedGrid.setWorker(currGWorker);

                            Grid formerGrid = baseGame.getGameGrid(pCurrW.getXPosition(), pCurrW.getYPosition());
                            formerGrid.setWorker(null);

                            int lv_forced = forcedMovedGrid.getGridTower() == null ? 0 : forcedMovedGrid.getGridTower().getLevel();
                            currGWorker.setLevelOnTower(lv_forced);
                            currGWorker.setXPosition(forcedMovedGridX);
                            currGWorker.setYPosition(forcedMovedGridY);
                            currG.setWorker(pCurrW);

                            int lv_currW = currG.getGridTower() == null ? 0 : currG.getGridTower().getLevel();
                            pCurrW.setLevelOnTower(lv_currW);
                            pCurrW.setXPosition(x);
                            pCurrW.setYPosition(y);
                        }
                    }
                }
            } else baseGame.play(x, y);
        }  else if (currPlayer.hasMoved()) {
            baseGame.play(x, y);
        }

        return baseGame;
    }

}

