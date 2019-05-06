package Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KruskalMST extends MST{

    public KruskalMST(Graph graph) {
        super(graph);
    }

    @Override
    void calcMST() {
        UnionFind disjointSet = new UnionFind(graph.getOrder());
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(graph.getSize(), Comparator.comparingDouble(o -> o.getWeight()));
        for(Edge edge : graph.getEdges()){
            pq.add(edge);
        }

        while (!pq.isEmpty() && !(disjointSet.getNumberOfComponents() == 1)) {
            Edge edge = pq.poll();
            int startLabel = edge.getStart().getLabel();
            int endLabel = edge.getEnd().getLabel();
            if (!disjointSet.connected(startLabel, endLabel)) {
                disjointSet.unify(startLabel, endLabel);
                mst.createEdge(startLabel, endLabel, edge.getWeight());
            }
        }
    }
}
