package edu.cmu.cs214.hw3;

import java.util.Arrays;

import edu.cmu.cs214.hw3.component.Tower;
import edu.cmu.cs214.hw3.component.Worker;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public class GameState {

    private final Cell[] cells;
    private final String currPlayer;
    private final String winner;
    private final String message;
    private final int hasSetGodCards;

    private GameState(Cell[] Cells, String currP, String winnerP, String msg, int setOrNot) {
        this.cells = Cells;
        this.currPlayer = currP;
        this.winner = winnerP;
        this.message = msg;
        this.hasSetGodCards = setOrNot;
    }

    public static GameState forGame(Game game, int setOrNot) {
        Cell[] newCells = getCells(game);
        String currP = game.getCurrPlayer().getPlayerID();
        String winner = game.getWinner() == null ? null : game.getWinner().getPlayerID(); 
        String newMsg = getMessage(game);
        return new GameState(newCells, currP, winner, newMsg, setOrNot);
    }

    private static String getMessage(Game game) {
        return game.getMessage();
    }

    public Cell[] getCells() {
        return this.cells;
    }

    /**
     * toString() of GameState will return the string representing
     * the GameState in JSON format.
     */
    @Override
    public String toString() {
        return """
                { "cells": %s,
                  "currPlayer": "%s",
                  "winner": "%s",
                  "message" : "%s",
                  "hasSetGodCards": "%d"
                }
                """.formatted(Arrays.toString(this.cells), this.currPlayer, this.winner, this.message, this.hasSetGodCards);
    }

    private static Cell[] getCells(Game game) {
        Cell Cells[] = new Cell[25];
        for (int x = 0; x <= 4; x++) {
            for (int y = 0; y <= 4; y++) {
                StringBuilder sb = new StringBuilder();

                Grid currG = game.getGameGrid(x, y);
                Worker currW = currG.getGridWorker();
                Tower currT = currG.getGridTower();

                boolean playable = true;
                
                if ( currT != null) {
                    int lv = currT.getLevel();
                    for (int i = 0; i < lv; i++) {
                        sb.append("[");
                    }
                }

                if (currW != null) {
                    sb.append(currW.getPlayer());
                    sb.append(currW.getWorkerID());
                }

                if ( currT != null) {
                    if (currT.hasDome()) {
                        playable = false;
                        sb.append("O");
                    }
                    int lv = currT.getLevel();
                    for (int i = 0; i < lv; i++) {
                        sb.append("]");
                    }
                }
                Cells[5*x + y] =  new Cell(x, y, sb.toString(), playable);;
            }
        }
        return Cells;
    }
}

class Cell {
    private final int x;
    private final int y;
    private final String text;
    private final boolean playable;

    Cell(int x, int y, String text, boolean playable) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    @Override
    public String toString() {
        return """
                {
                    "text": "%s",
                    "playable": %b,
                    "x": %d,
                    "y": %d 
                }
                """.formatted(this.text, this.playable, this.x, this.y);
    }
}

