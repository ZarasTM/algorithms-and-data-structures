package PriorityQueue;

public class PQNode {
    private int val;
    private double priority;

    PQNode(int val, double priority){
        if(priority < 0){
            throw new RuntimeException("Error: invalid priority value = "+priority);
        }else{
            this.val = val;
            this.priority = priority;
        }
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }
}

