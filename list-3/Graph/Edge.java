package Graph;

public class Edge {

    private Vertex start;
    private Vertex end;

    private double weight;

    // Constructor
    public Edge(Vertex s, Vertex e, double w){
        this.start = s;
        this.end = e;
        this.weight = w;
    }

    public Edge(Vertex s, Vertex e){
        this.start = s;
        this.end = e;
        this.weight = 0;
    }

    // GETTERS
    public Vertex getStart() {
        return start;
    }

    public Vertex getEnd() {
        return end;
    }

    public double getWeight() {
        return weight;
    }

    // SETTERS
    public void setStart(Vertex start) {
        this.start = start;
    }

    public void setEnd(Vertex end) {
        this.end = end;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
