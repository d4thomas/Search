package search_agents;

import core_search.BestFirstSearch;
import core_search.FILOQueue;
import search_problems.Travel;

public class DFS_Travel extends BestFirstSearch<String, String> {
    public DFS_Travel(String mapFile){
        super(new Travel(mapFile), new FILOQueue<>());
    }

    public static void main(String[] args){
        DFS_Travel t = new DFS_Travel("RomaniaMap.txt");
        t.search();
    }

}
