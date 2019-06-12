package Graph;

import java.util.ArrayList;
import java.util.List;

public abstract class Graph {
    protected List<Edge> edges = new ArrayList<Edge>();
    protected int nodeNum;

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNodeNum() {
        return nodeNum;
    }

    public abstract Vertex[] getVertices();

    public abstract Vertex getVertex(int num);
}
