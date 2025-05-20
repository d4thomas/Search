package search_problems;

import core_search.Problem;
import core_search.Tuple;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.System.exit;

/**
 * Assumption is that the board is a square, e.g., 3x3, 4x4, etc.
 */
public class SlidingTilePuzzle implements Problem<List<Integer>, String> {

    //Size of the board, e.g., SIZE=3 means it's a 3x3 board
    private final int SIZE;
    // test case
    private final List<Integer> INITIAL_STATE = new ArrayList<>(
            List.of(7,2,4, 5,0,6, 8,3,1));
    private final List<Integer> GOAL_STATE = new ArrayList<>(
            List.of(0,1,2, 3,4,5, 6,7,8));


    private final Map<Integer, Integer> tile_rowColumn = new HashMap<>();
    public SlidingTilePuzzle(){
        SIZE = (int) Math.sqrt(INITIAL_STATE.size());
        if (SIZE * SIZE != INITIAL_STATE.size() || INITIAL_STATE.size() != GOAL_STATE.size()){
            System.out.println("Wrong input!");
            exit(1);
        }
        buildGoalCoordinates();
    }

    public List<Tuple<List<Integer>,String>> execution (List<Integer> s){
        List<Tuple<List<Integer>,String>> l = new ArrayList<>();
        int empty = -1;
        for(int i=0; i<s.size(); i++){
                if (s.get(i) == 0){
                    empty = i;
                    break;
                }
            }
        //the r and c represents the row# and column# of the empty cell
        int r = empty / SIZE;
        int c = empty % SIZE;
        //move up
        if (r-1 >= 0){
            List<Integer> a = new ArrayList<>(s);
            int tile2Swap = empty - SIZE;
            a.set(empty, s.get(tile2Swap));
            a.set(tile2Swap, 0);
            l.add(new Tuple<>(a, "up", 1));
        }
        //move down
        if (r+1 < SIZE){
            List<Integer> a = new ArrayList<>(s);
            int tile2Swap = empty + SIZE;
            a.set(empty,s.get(tile2Swap));
            a.set(tile2Swap, 0);
            l.add(new Tuple<>(a, "down", 1));
        }
        //move left
        if (c-1 >= 0){
            List<Integer> a = new ArrayList<>(s);
            int tile2Swap = empty -1;
            a.set(empty,s.get(tile2Swap));
            a.set(tile2Swap, 0);
            l.add(new Tuple<>(a, "left", 1));
        }
        //move right
        if (c+1 < SIZE){
            List<Integer> a = new ArrayList<>(s);
            int tile2Swap = empty +1;
            a.set(empty, s.get(tile2Swap));
            a.set(tile2Swap, 0);
            l.add(new Tuple<>(a, "right", 1));
        }
        return l;
    }

    public List<Integer> initialState() {
        return INITIAL_STATE;
    }

    public List<Integer> goalState() {
        return GOAL_STATE;
    }

    public void printState(List<Integer> state){
        for(int i=0; i<state.size(); i++) {
            if (state.get(i) != 0) {
                System.out.printf("%3s", state.get(i) + " ");
            } else {
                System.out.print("   ");
            }
            if (i % SIZE == SIZE - 1) {
                System.out.println();
            }
        }
    }

    private void buildGoalCoordinates(){
        for(int i=0; i<GOAL_STATE.size(); i++){
            tile_rowColumn.put(GOAL_STATE.get(i), i/SIZE);
            tile_rowColumn.put(-GOAL_STATE.get(i), i%SIZE);
        }
    }

    public int mismatchedTiles(List<Integer> state){
        int count = 0;
        for (int i=0; i<state.size(); i++){
            if(state.get(i) == 0){
                continue;
            }
            if (!state.get(i).equals(GOAL_STATE.get(i))){
                count ++;
            }
        }
        return count;
    }

    /**
     * This method is used by search algorithms like A* or Greedy Best-First Search
     * to estimate how far the current board configuration is from the goal.
     *
     * Students: Complete this method by computing the Manhattan distance
     * for all tiles in the current state compared to their positions in the goal state.
     *
     * @return the total Manhattan distance for this state
     */
    public int manhattanDistance(List<Integer> state){
        int distance = 0;

        for (int i = 0; i < state.size(); i++) {
            int tile = state.get(i);

            if (tile == 0)
                continue;

            int currentRow = i / SIZE;
            int currentCol = i % SIZE;
            int goalRow = tile_rowColumn.get(tile);
            int goalCol = tile_rowColumn.get(-tile);

            distance += Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
        }

        return distance;
    }
}
