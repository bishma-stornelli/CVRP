/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cvrp;

/**
 *
 * @author tamerdark
 */
public class Neighbor {
    private Solution neighbor;
    private Move move;
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public Solution getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Solution neighbor) {
        this.neighbor = neighbor;
    }
    
    

    public Solution applyMove() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Tabu getTabu() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
