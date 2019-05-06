package Graph;

import PriorityQueue.*;

public class DijkstraAlg {

    private Graph graph;
    private PriorityQueue pq = new PriorityQueue();

    public DijkstraAlg(Graph graph, int startLabel){
        this.graph = graph;

        calcPath(startLabel);
    }

    private void calcPath(int startLabel){
        Vertex start = graph.getVertex(startLabel);
        start.setDistance(0);
        makeQueue();

        double distance;

        while(!pq.empty()){
            Vertex current = graph.getVertex(pq.pop().getVal());

            for(Edge edge : current.getEdges()){
                distance = edge.getWeight();

                if(edge.getEnd().getDistance() > current.getDistance() + distance && edge.getEnd() != current){
                    edge.getEnd().setDistance(current.getDistance() + distance);
                    edge.getEnd().setPrev(edge);
                    pq.priority(edge.getEnd().getLabel(), edge.getEnd().getDistance());
                }
            }
        }
    }

    private void makeQueue(){
        for(Vertex vert : graph.getVerticies()){
            pq.insert(vert.getLabel(), vert.getDistance());
        }
    }

    public void printPaths(){
        StringBuilder str = new StringBuilder();
        for(Vertex vert : graph.getVerticies()){
            System.out.println("Vert: "+vert.getLabel()+"\tDist: "+vert.getDistance());

            Vertex tmpVert = vert;
            Edge connector = tmpVert.getPrev();
            while(connector != null) {
                str.append(tmpVert.getLabel()).append(" <---").append(connector.getWeight()).append("--- ");
                tmpVert = connector.getStart();
                connector = tmpVert.getPrev();
            }
            str.append(tmpVert.getLabel());

            // Print and clear str builder
            System.err.println(str.toString());
            str.setLength(0);
        }
    }

}
