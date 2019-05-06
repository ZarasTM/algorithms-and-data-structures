package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private int label;
    private Edge prev = null;
    private double distance = Double.MAX_VALUE;
    private List<Edge> edges = new ArrayList<Edge>();

    // Constructor
    public Vertex(int label) {
        this.label = label;
    }

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    // GETTERS
    public int getLabel() {
        return label;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public double getDistance() {
        return distance;
    }

    public Edge getPrev() {
        return prev;
    }

    // SETTERS
    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setPrev(Edge prev){ this.prev = prev; }
}
