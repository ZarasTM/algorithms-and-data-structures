package Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimMST extends MST{

    Set<Vertex> visited;
    PriorityQueue<Edge> pq;
    public PrimMST(Graph graph) {
        super(graph);
    }

    @Override
    void calcMST() {
        visited = new HashSet<Vertex>(graph.getOrder());
        pq = new PriorityQueue<Edge>(graph.getSize(), Comparator.comparingDouble(o -> o.getWeight()));

        visit(graph.getVertex(1));

        while (!pq.isEmpty() && !(visited.size() == graph.getOrder())) {
            Edge edge = pq.poll();

            if(!visited.contains(edge.getEnd()) || !visited.contains(edge.getEnd())){
                mst.createEdge(edge.getStart().getLabel(), edge.getEnd().getLabel(), edge.getWeight());
                if (visited.contains(edge.getStart())) {
                    visit(edge.getEnd());
                } else{
                    visit(edge.getStart());
                }
            }
        }
    }

    private void visit(Vertex vert){
        visited.add(vert);
        for (Edge edge : vert.getEdges()) {
            pq.add(edge);
        }
    }
}
