package edu.cmu.cs214.hw3.gameBoard;

import edu.cmu.cs214.hw3.component.Tower;
import edu.cmu.cs214.hw3.component.Worker;

public class Grid {
    private int[] position;
    private Tower siteTower;
    private Worker siteWorker;
    private String text;

    public Grid(int x, int y) {
        position = new int[2];
        position[0] = x;
        position[1] = y;
        siteTower = null;
        siteWorker = null;
        text = null;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /** 
     * @return Tower
     */
    public Tower getGridTower() {
        return siteTower;
    }

    
    /** 
     * @return Worker
     */
    public Worker getGridWorker() {
        return siteWorker;
    }

    /** 
     * @return boolean win or not
     */
    public boolean winStatus() {
        if (siteWorker != null && siteTower != null && siteTower.getLevel() == 3) {
            return true;
        } else return false;
    }

    
    /** 
     * @return int[] first: column position. second: row position
     */
    public int[] getPosition() {
        return position;
    }

    
    /** 
     * @return boolean can be built or not
     * @throws Exception error info
     */
    public String canBeBuiltFrom(int x, int y) {
        if (siteWorker != null) return "has a worker here!";
        if ((Math.abs(position[0] - x) <= 1 && Math.abs(position[1] - y) <= 1))
            return "Succeeded!";
        return "too far to get!";
    }

    public void setWorker(Worker worker) {
        siteWorker = worker;
    }

    /** 
     * @return int can be built or not
     * @throws Exception error info
     */
    public String gridBuildTowerFrom(int x, int y) {
        String msg = "";
        if (this.canBeBuiltFrom(x, y).equals("Succeeded!")) {
            if (siteTower == null) {
                siteTower = new Tower(position[0], position[1]);
            } else {
                siteTower.buildTower();
            }
            msg = "Succeeded!";
        } else msg = this.canBeBuiltFrom(x, y);
        return msg;
    }
}