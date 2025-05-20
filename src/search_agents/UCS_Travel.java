package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;

public class UCS_Travel extends BestFirstSearch<String, String> {
    public UCS_Travel(String mapFile) {
        super(new Travel(mapFile),
                new SortedQueue<>(new ComparePathCost()));
    }

    public static void main(String[] args) {
        UCS_Travel t = new UCS_Travel("RomaniaMap.txt");
        t.search();
    }

    public static class ComparePathCost implements
            Comparator<Node<String, String>> {
        public int compare(Node<String, String> o1, Node<String, String> o2) {
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