package Graph;

public abstract class MST {

    protected Graph graph;
    protected Graph mst;
    protected double fullWeight;

    public MST(Graph graph){
        this.graph = graph;
        this.mst = new Graph();

        for(int count=0; count < graph.getOrder(); count++){
            mst.insertVertex();
        }

        calcMST();
    }

    abstract void calcMST();

    public double getFullWeight() {
        return fullWeight;
    }

    public Graph getMST() {
        return mst;
    }

    protected boolean checkForCycles(Vertex vert, Vertex ctrlVert){
        while(!vert.getEdges().isEmpty()){
            for(Edge nextEdge : vert.getEdges()){
                if(nextEdge.getEnd() == ctrlVert) return true;
                return checkForCycles(nextEdge.getEnd(), ctrlVert);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        double sum = 0;
        for(Edge edge : mst.getEdges()){
            str.append(edge.getStart().getLabel()).append(" <----")
                    .append(edge.getWeight()).append("-----> ")
                    .append(edge.getEnd().getLabel()).append("\n");
            sum = sum + edge.getWeight();
        }
        str.append(sum);
        return str.toString();
    }
}
