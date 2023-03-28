package edu.cmu.cs214.hw3;

import java.util.List;
import edu.cmu.cs214.hw3.gameBoard.Board;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public abstract class GodCardsGame implements Game {
    protected Game baseGame;

    public GodCardsGame(Game game) {
        baseGame = game;
    }

    public Game play(int x, int y) {
        return baseGame.play(x, y);
    }

    public String getMessage() {
        return baseGame.getMessage();
    }

    public Grid getGameGrid(int x, int y) {
        return baseGame.getGameGrid(x, y);
    }

    public Player getWinner() {
        return baseGame.getWinner();
    }

    public Player getCurrPlayer() {
        return baseGame.getCurrPlayer();
    }

    public List<OriginalGame> getHistory() {
        return baseGame.getHistory();
    }

    public Board getPlayGround() {
        return baseGame.getPlayGround();
    }

    public void setNextCurrPlayer(Player A) {
        baseGame.setNextCurrPlayer(A);
    }

    public void gameSetWin(Player A) {
        baseGame.gameSetWin(A);
    }

    public void copyStatus(boolean gameStatus, Board pg, Player curr, Player win, List<OriginalGame> hist) {
        baseGame.copyStatus(gameStatus, pg, curr, win, hist);
    }

    public void archiveGame() {
        baseGame.archiveGame();
    }

    public String currentMove(Player A, int x, int y) {
        return baseGame.currentMove(A, x, y);
    }

    public String currentBuild(Player A, int x, int y) {
        return baseGame.currentBuild(A, x, y);
    }

    public boolean playerRound(Player A) {
        return baseGame.playerRound(A);
    }

    public Game undo() {
        return baseGame.undo();
    }

    public boolean checkWin() {
        return baseGame.checkWin();
    }
}