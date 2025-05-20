package search_agents;

import core_search.BestFirstSearch;
import core_search.Node;
import core_search.SortedQueue;
import search_problems.Travel;

import java.util.Comparator;

public class AStar_Travel extends BestFirstSearch<String, String> {
    public AStar_Travel(String map, String estimates) {
        super(new Travel(map, estimates),
                new SortedQueue<>(new CompareDistances(new Travel(map, estimates))));
    }

    public static class CompareDistances implements Comparator<Node<String, String>> {
        public final Travel problem;

        public CompareDistances(Travel problem) {
            this.problem = problem;
        }

        public int compare(Node<String, String> o1, Node<String, String> o2) {
            if (o1.getPathCost() + problem.getEstimatedDistance(o1.getState()) < o2.getPathCost()
                    + problem.getEstimatedDistance(o2.getState())) {
                return -1;
            } else if (o1.getPathCost() + problem.getEstimatedDistance(o1.getState()) == o2.getPathCost()
                    + problem.getEstimatedDistance(o2.getState())) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) {
        AStar_Travel agent = new AStar_Travel("RomaniaMap.txt",
                "RomaniaMapEstimates.txt");
        agent.search();
    }
}