package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;

public class GBFS_Travel extends BestFirstSearch<String, String> {
    public GBFS_Travel(String map, String estimates) {
        super(new Travel(map, estimates),
                new SortedQueue<>(new CompareEstimates(new Travel(map, estimates))));
    }

    public static class CompareEstimates implements Comparator<Node<String, String>> {
        public final Travel problem;

        public CompareEstimates(Travel problem) {
            this.problem = problem;
        }

        public int compare(Node<String, String> o1, Node<String, String> o2) {
            if (problem.getEstimatedDistance(o1.getState()) < problem.getEstimatedDistance(o2.getState())) {
                return -1;
            } else if (problem.getEstimatedDistance(o1.getState()) == problem.getEstimatedDistance(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }

        public static void main(String[] args) {
            GBFS_Travel agent = new GBFS_Travel("RomaniaMap.txt",
                    "RomaniaMapEstimates.txt");
            agent.search();
        }
    }

}