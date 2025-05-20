package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class AStar_STP_MisplacedTiles extends BestFirstSearch<List<Integer>, String> {
    public AStar_STP_MisplacedTiles() {
        super(new SlidingTilePuzzle(),
                new SortedQueue<>(new ComparePathCostMisplacedTiles(new SlidingTilePuzzle())));
    }

    public static void main(String[] args) {
        AStar_STP_MisplacedTiles agent = new AStar_STP_MisplacedTiles();
        agent.search();
    }

    /**
     * Comparator class used by Greedy Best-First Search on the Sliding Tile Puzzle.
     * It compares two nodes based on their evaluation scores f(n) = g(n) + h(n),
     * where:
     * - g(n) is the path cost from the start node to the current node
     * - h(n) is the estimated forward cost to the goal given by the misplaced tiles
     * heuristic
     */
    public static class ComparePathCostMisplacedTiles implements Comparator<Node<List<Integer>, String>> {
        private final SlidingTilePuzzle p;

        public ComparePathCostMisplacedTiles(SlidingTilePuzzle p) {
            this.p = p;
        }

        /**
         *
         * @param o1 the first object to be compared.
         * @param o2 the second object to be compared.
         * @return -1 if o1 has a lower f(n),
         *         0 if their f(n) values are equal,
         *         1 otherwise.
         */
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if (o1.getPathCost() + p.mismatchedTiles(o1.getState()) < o2.getPathCost()
                    + p.mismatchedTiles(o2.getState())) {
                return -1;
            } else if (o1.getPathCost() + p.mismatchedTiles(o1.getState()) == o2.getPathCost()
                    + p.mismatchedTiles(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}