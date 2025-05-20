package search_agents;

import core_search.BestFirstSearch;
import core_search.FIFOQueue;
import search_problems.Travel;

public class BFS_Travel extends BestFirstSearch<String, String> {
    public BFS_Travel(String mapFile){
        super(new Travel(mapFile), new FIFOQueue<>());
    }

    public static void main(String[] args){
        BFS_Travel t = new BFS_Travel("RomaniaMap.txt");
        t.search();
    }
}
