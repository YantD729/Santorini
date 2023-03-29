package edu.cmu.cs214.hw3;

import java.util.List;

import edu.cmu.cs214.hw3.gameBoard.Board;
import edu.cmu.cs214.hw3.gameBoard.Grid;

public interface Game {
    String getMessage();

    Grid getGameGrid(int x, int y);

    Player getWinner();

    void setNextCurrPlayer(Player A);

    Player getCurrPlayer();

    List<OriginalGame> getHistory();

    Board getPlayGround();

    void gameSetWin(Player A);

    Game play(int x, int y);

    String currentMove(Player A, int x, int y);

    String currentBuild(Player A, int x, int y);

    boolean playerRound(Player A);

    boolean checkWin();

    void copyGame(Board playGrd, List<OriginalGame> oldHistory, String oldMsg, Player oldPlayer);

    OriginalGame archiveAndReturnACopy(int x, int y);

    Game undo();
}
