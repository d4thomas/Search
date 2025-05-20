package core_search;

import java.util.LinkedList;
import java.util.Queue;

public class FIFOQueue<S,A> implements MyPriorityQueue<S,A> {
    private final Queue<Node<S,A>> myQueue = new LinkedList<>();
    public Node<S,A> pop(){
        return myQueue.remove();
    }
    public void add(Node<S,A> e){
        myQueue.add(e);
    }
    public boolean isEmpty(){
        return myQueue.isEmpty();
    }
    public int size() {
        return myQueue.size();
    }
}
