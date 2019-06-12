package Graph;

import java.util.Objects;

public class Edge {
    private Vertex start;
    private Vertex end;
    private Edge residual;
    private long capacity;
    private long flow = 0;

    public Edge(Vertex s, Vertex e, int cap){
        this.start = s;
        this.end = e;
        this.capacity = cap;
    }

    // Calculates remainig capacity
    public long remainingCapacity() {
        return capacity - flow;
    }

    // Augments edge and residual edge
    public void augment(long bottleNeck) {
        flow += bottleNeck;
        residual.flow -= bottleNeck;
    }

    // Checks if edge is residual
    public boolean isResidual() {
        return capacity == 0;
    }

    // Getters & Setters
    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getFlow() {
        return flow;
    }

    public void setFlow(long flow) {
        this.flow = flow;
    }

    public Edge getResidual() {
        return residual;
    }

    public void setResidual(Edge res) {
        this.residual = res;
    }

    @Override
    public String toString(){
        return start.getStrLabel()+"("+start.getIntLabel()+") ---> "+end.getStrLabel()+"("+end.getIntLabel()+")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return start == edge.start &&
                end == edge.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
