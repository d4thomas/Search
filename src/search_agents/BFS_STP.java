package search_agents;

import core_search.BestFirstSearch;
import core_search.FIFOQueue;
import search_problems.SlidingTilePuzzle;

import java.util.List;

public class BFS_STP extends BestFirstSearch<List<Integer>, String> {
    public BFS_STP(){
        super(new SlidingTilePuzzle(), new FIFOQueue<>());
    }

    public static void main(String[] args) {
        BFS_STP agent = new BFS_STP();
        agent.search();
    }

}


