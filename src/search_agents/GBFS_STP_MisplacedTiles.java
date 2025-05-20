package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class GBFS_STP_MisplacedTiles extends BestFirstSearch<List<Integer>, String> {

    public GBFS_STP_MisplacedTiles() {
        super(new SlidingTilePuzzle(),
                new SortedQueue<List<Integer>, String>(new CompareMismatchedCount(new SlidingTilePuzzle())));

    }

    public static void main(String[] args) {
        GBFS_STP_MisplacedTiles agent = new GBFS_STP_MisplacedTiles();
        agent.search();
    }

    /**
     * Comparator class used for Greedy Best-First Search on the Sliding Tile
     * Puzzle.
     * This comparator ranks nodes solely based on the Misplaced Tiles heuristic,
     * which counts
     * how many tiles are not in their correct positions in the goal state.
     */
    public static class CompareMismatchedCount implements Comparator<Node<List<Integer>, String>> {
        private final SlidingTilePuzzle p;

        public CompareMismatchedCount(SlidingTilePuzzle p) {
            this.p = p;
        }

        /**
         * @param o1 the first node to be compared.
         * @param o2 the second node to be compared.
         * @return -1 if o1 has a lower estimated forward cost,
         *         0 if their estimated forward costs are equal,
         *         1 otherwise.
         */
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if (p.mismatchedTiles(o1.getState()) < p.mismatchedTiles(o2.getState())) {
                return -1;
            } else if (p.mismatchedTiles(o1.getState()) == p.mismatchedTiles(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}