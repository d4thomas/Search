package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class AStar_STP_ManhattanDistance extends BestFirstSearch<List<Integer>, String> {

    public AStar_STP_ManhattanDistance(){
        super(new SlidingTilePuzzle(),
                new SortedQueue<>(new ComparePathCostDistanceSum(new SlidingTilePuzzle()))
        );

    }

    //Please note that A* may take several minutes to solve the 4x4 puzzle.
    public static void main(String[] args){
        AStar_STP_ManhattanDistance agent = new AStar_STP_ManhattanDistance();
        agent.search();
    }


    /**
     *  Comparator class used by Greedy Best-First Search on the Sliding Tile Puzzle.
     *  It compares two nodes based on their evaluation scores f(n) = g(n) + h(n), where:
     * - g(n) is the path cost from the start node to the current node
     * - h(n) is the estimated forward cost to the goal given by the Manhattan distance heuristic
     *
     *  Students: Implement the compare method so that nodes are ranked by their evaluation scores
     *  f(n) as described above.
     */
    public static class ComparePathCostDistanceSum implements Comparator<Node<List<Integer>,String>>{
        private final SlidingTilePuzzle p;
        public ComparePathCostDistanceSum(SlidingTilePuzzle p){
            this.p = p;
        }
        /**
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return -1 if o1 has a lower f(n),
         *          0 if their f(n) values are equal,
         *          1 otherwise.
         */
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            int f1 = o1.getPathCost() + p.manhattanDistance(o1.getState());
            int f2 = o2.getPathCost() + p.manhattanDistance(o2.getState());

            return Integer.compare(f1, f2);
        }
    }
}
