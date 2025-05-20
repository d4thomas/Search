package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class GBFS_STP_ManhattanDistance extends BestFirstSearch<List<Integer>, String> {

    public GBFS_STP_ManhattanDistance(){
        super(new SlidingTilePuzzle(),
                new SortedQueue<>(new CompareDistanceSum(new SlidingTilePuzzle()))
        );

    }

    public static void main(String[] args){
        GBFS_STP_ManhattanDistance agent = new GBFS_STP_ManhattanDistance();
        agent.search();
    }

    /**
     * Comparator class used by Greedy Best-First Search on the Sliding Tile Puzzle.
     * It compares two nodes based only on the estimated forward cost to reach the goal.
     *
     * Students: Implement the compare method so that nodes are ranked solely by h(n),
     * where h(n) is the Manhattan distance heuristic for the puzzle state.
     */
    public static class CompareDistanceSum implements Comparator<Node<List<Integer>, String>>{
        private final SlidingTilePuzzle p;
        public CompareDistanceSum(SlidingTilePuzzle p){
            this.p = p;
        }
        /**
         * @param o1 the first node to be compared.
         * @param o2 the second node to be compared.
         * @return -1 if o1 has a lower estimated forward cost,
         *          0 if their estimated forward costs are equal,
         *          1 otherwise.
         */
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            int h1 = p.manhattanDistance(o1.getState());
            int h2 = p.manhattanDistance(o2.getState());

            return Integer.compare(h1, h2);
        }
    }
}
