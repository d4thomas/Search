package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.SlidingTilePuzzle;

import java.util.Comparator;
import java.util.List;

public class UCS_STP extends BestFirstSearch<List<Integer>, String> {

    public UCS_STP() {
        super(new SlidingTilePuzzle(), new SortedQueue<List<Integer>, String>(new ComparePathCost()));

    }

    public static void main(String[] args) {
        UCS_STP agent = new UCS_STP();
        agent.search();
    }

    public static class ComparePathCost implements Comparator<Node<List<Integer>, String>> {
        @Override
        public int compare(Node<List<Integer>, String> o1, Node<List<Integer>, String> o2) {
            if (o1.getPathCost() < o2.getPathCost()) {
                return -1;
            } else if (o1.getPathCost() == o2.getPathCost()) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}