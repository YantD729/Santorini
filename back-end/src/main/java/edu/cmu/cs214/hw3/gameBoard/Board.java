package edu.cmu.cs214.hw3.gameBoard;

import java.util.Arrays;

import edu.cmu.cs214.hw3.component.Tower;
import edu.cmu.cs214.hw3.component.Worker;

public class Board {
    private Grid[][] gridStatus;

    public Board() {
        gridStatus = new Grid[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gridStatus[i][j] = new Grid(i, j);
            }
        }
    }

    public Board(Grid[][] newCells) {
        gridStatus = newCells;
    }

    
    /** 
     * @param x column position
     * @param y row position
     * @return Grid target grid
     */
    public Grid getGrid(int x, int y) {
        return gridStatus[x][y];
    }

    public Board updateGrid(int x, int y, Worker W, Tower T) {
        Grid[][] newCells = Arrays.copyOf(this.gridStatus, this.gridStatus.length);
        gridStatus[x][y].setGridTower(T);
        gridStatus[x][y].setGridWorker(W);
        return new Board(newCells);
    }

    
    /** 
     * @param act add level or build dome
     * @param x column position
     * @param y row position
     * @return boolean whether the place is valid to build
     * @throws Exception building error infos
     */
    public String validBuildTower(int fromX, int fromY, int toX, int toY) {
        if (fromX > 4 || fromY > 4 || fromX < 0 || fromY < 0 ||
            toX > 4 || toY > 4 || toX < 0 || toY < 0) return "grid position out of range!";

        Grid currGrid = gridStatus[toX][toY];

        if (currGrid.getGridWorker() != null) return "has a worker here!";
        return currGrid.canBeBuiltFrom(fromX, fromY);
    }

    
    /** 
     * @param x column position
     * @param y row position
     * @return int levels after built
     * @throws Exception building errors infos
     */
    public String currentBuild(int fromX, int fromY, int toX, int toY) {
        Grid trgtGrid = gridStatus[toX][toY];
        if (validBuildTower(fromX, fromY, toX, toY).equals("Succeeded!")) {
            return trgtGrid.gridBuildTowerFrom(fromX, fromY);
        }
        return validBuildTower(fromX, fromY, toX, toY);
    }
}
