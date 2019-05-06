package PriorityQueue;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    List<PQNode> pQueue = new ArrayList<PQNode>();

    public boolean empty() {
        return pQueue.size() == 0;
    }

    public void insert(int value, double priority) {
        PQNode tmpNode = new PQNode(value, priority);

        // Add node to priority queue
        pQueue.add(tmpNode);

        int current = pQueue.size()-1;

        // Repair
        while (pQueue.get(current).getPriority() < pQueue.get(getParent(current)).getPriority()) {
            swap(current, getParent(current));
            current = getParent(current);
        }
    }

    public PQNode pop() {
        PQNode node = pQueue.get(0);

        // Change root to last
        pQueue.set(0, pQueue.get(pQueue.size()-1));

        // Remove
        pQueue.remove(pQueue.size()-1);

        // Repair
        heapifyDown(0);
        return node;
    }

    public PQNode top(){
        return pQueue.get(0);
    }

    public void priority(int value, double priority) {
        for(int idx=0; idx<pQueue.size(); idx++){
            if(pQueue.get(idx).getVal() == value && pQueue.get(idx).getPriority() > priority){
                pQueue.get(idx).setPriority(priority);
                heapifyUp(idx);
            }
        }
    }

    public void print(){
        System.out.println(this);
    }

    private int getParent(int idx) {
        return idx / 2;
    }

    private int getLeftChild(int idx) {
        return (2 * idx);
    }

    private int getRightChild(int idx) {
        return (2 * idx) + 1;
    }

    private boolean isLeaf(int idx) {
        if (idx >= (pQueue.size() / 2) && idx <= pQueue.size()) {
            return true;
        }
        return false;
    }

    private void swap(int fpos, int spos) {
        PQNode tmp;
        tmp = pQueue.get(fpos);
        pQueue.set(fpos, pQueue.get(spos));
        pQueue.set(spos, tmp);
    }

    private void heapifyDown(int idx) {
        if (!isLeaf(idx)) {
            if (pQueue.get(idx).getPriority() > pQueue.get(getLeftChild(idx)).getPriority()
                    || pQueue.get(idx).getPriority() > pQueue.get(getRightChild(idx)).getPriority()) {

                if (pQueue.get(getLeftChild(idx)).getPriority() < pQueue.get(getRightChild(idx)).getPriority()) {
                    swap(idx, getLeftChild(idx));
                    heapifyDown(getLeftChild(idx));
                } else {
                    swap(idx, getRightChild(idx));
                    heapifyDown(getRightChild(idx));
                }
            }
        }
    }

    private void heapifyUp(int idx) {
        if (idx != 0) {
            if (pQueue.get(idx).getPriority() < pQueue.get(getParent(idx)).getPriority()) {
                swap(idx, getParent(idx));
                heapifyDown(getParent(idx));
                heapifyUp(getParent(idx));
            }
        }
    }

    public void buildHeap() {
        for (int idx = (pQueue.size() / 2); idx >= 1; idx--) {
            heapifyDown(idx);
        }
    }

    @Override
    public String toString(){
        StringBuilder strBuild = new StringBuilder();
        int pow = 0;
        for(int idx = 0; idx < pQueue.size(); idx++){
            if(idx+1 == Math.pow(2, pow)){
                strBuild.append("(").append(pQueue.get(idx).getVal()).append(" , ").append(pQueue.get(idx).getPriority()).append(") \n");
            }else {
                strBuild.append("(").append(pQueue.get(idx).getVal()).append(" , ").append(pQueue.get(idx).getPriority()).append(")");
            }
        }

        return strBuild.toString();
    }
}
