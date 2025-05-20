package core_search;

import java.util.*;

/**
 *  WARNING:
 *     1. This class will NOT work if S (i.e., the data type of states) is an array type
 *     (e.g., int[], String[], etc.) because S is used as the key for the reached map.
 *     2. The data type of states must provide the equals method.
 *
 *  An implementation of the general best-first search algorithm
 *
 *  To implement a specific search algorithm (such as BFS, DFS, etc.),
 *  extend this class and provide an implementation of the PriorityQueue
 *
 *  Type parameters:
 *      S: the data type of states
 *      A: the data type of actions
 * */

public class BestFirstSearch<S,A> {
    private final Problem<S,A> p;
    private final MyPriorityQueue<S,A> frontier;
    private final Map<S, Node<S,A>> reached = new HashMap<>();

    public BestFirstSearch(Problem<S, A> p, MyPriorityQueue<S, A> frontier) {
        this.p = p;
        this.frontier = frontier;
    }

    public int search(){
        Node<S,A> start = new Node<>(p.initialState(), null, 0, null);
        frontier.add(start);
        reached.put(p.initialState(), start);

        while(!frontier.isEmpty()){
            Node<S,A> node = frontier.pop();
            if(node.getState().equals(p.goalState())){
                return printPath(node);
            }
            for(Tuple<S,A> t : p.execution(node.getState())){
                if(!reached.containsKey(t.getState()) ||
                        node.getPathCost()+t.getCost()<reached.get(t.getState()).getPathCost()
                ){
                    //if the state is not seen before, or, if the state was seen previously but
                    //the current path is better than the previous one.
                    Node<S,A> child = new Node<>(
                            t.getState(),
                            t.getAction(),
                            node.getPathCost()+t.getCost(),
                            node);
                    reached.put(child.getState(), child);
                    frontier.add(child);
                }
            }
        }
        return -1;
    }

    public int printPath(Node<S,A> node) {
        int pathCost = node.getPathCost();
        Stack<S> path = new Stack<>();
        do {
            path.add(node.getState());
            node = node.getParent();
        } while (node != null);

        System.out.println("Path (from initial state to goal state): ");
        while (!path.isEmpty()) {
            p.printState(path.pop());
            if (!path.isEmpty()) {
                System.out.println('\u2193');
            }
        }
        System.out.println("\nPath cost: " + pathCost);
        return pathCost;
    }

}
