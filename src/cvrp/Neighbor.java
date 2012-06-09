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
    public static final char ROUTE_POSITION_TABU = 1;
    public static final char ROUTE_TABU = 2;

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

    public Tabu getTabu(char tabuType) throws NoSuchTabuTypeException {
        switch(tabuType){
            case ROUTE_POSITION_TABU:
                return new RoutePositionTabu();
            case ROUTE_TABU:
                return new RouteTabu();
            default:
                throw new NoSuchTabuTypeException();
        }
    }
}
