package search_agents;

import core_search.BestFirstSearch;
import core_search.FILOQueue;
import search_problems.SlidingTilePuzzle;

import java.util.List;

public class DFS_STP extends BestFirstSearch<List<Integer>, String> {
    public DFS_STP(){
        super(new SlidingTilePuzzle(), new FILOQueue<>());
    }

    public static void main(String[] args) {
        DFS_STP agent = new DFS_STP();
        agent.search();
    }

}
