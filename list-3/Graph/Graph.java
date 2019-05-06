package Graph;

import java.util.*;

public class Graph {
    private Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();    // Map of vertices and their labels
    private List<Edge> edges = new ArrayList<Edge>();   // list of edges
    private int order = 0;   // Order of graph = #vertices
    private int size = 0;   // Size of graph = #edges

    // Inserts vertex to a graph
    public void insertVertex(){
        order++;
        vertices.put(order, new Vertex(order));
    }

    // Creates edge from vertex lStart to lEnd where lStart, lEnd are labels
    public void createEdge(int lStart, int lEnd, double weight){
        Vertex start = getVertex(lStart);
        Vertex end = getVertex(lEnd);

        if(start == null) throw new RuntimeException("Error: Invalid vertex label "+lStart);
        if(end == null) throw new RuntimeException("Error: Invalid vertex label "+lEnd);

        Edge edge = new Edge(start, end, weight);
        edges.add(edge);
        start.addEdge(edge);

        size++;
    }

    // Creates edge from vertex lStart to lEnd where lStart, lEnd are labels
    public void createEdge(int lStart, int lEnd){
        Vertex start = getVertex(lStart);
        Vertex end = getVertex(lEnd);

        if(start == null) throw new RuntimeException("Error: Invalid vertex label "+lStart);
        if(end == null) throw new RuntimeException("Error: Invalid vertex label "+lEnd);

        Edge edge = new Edge(start, end);
        edges.add(edge);
        start.addEdge(edge);

        size++;
    }

    public Graph getTransposedGraph() {
        Graph transGraph = new Graph();

        // Insert vertices
        for (int i = 1; i <= order; i++) {
            transGraph.insertVertex();
        }

        // Insert transposed edges
        for (Edge edge : edges) {
            transGraph.createEdge(edge.getEnd().getLabel(), edge.getStart().getLabel(), edge.getWeight());
        }
        return transGraph;
    }


    private void depthFirstSearch(Vertex vertex, boolean[] visited) {
        visited[vertex.getLabel()] = true;
        System.out.print(vertex.getLabel());

        for(Edge adjacent : vertex.getEdges()) {
            if(!visited[adjacent.getEnd().getLabel()]) {
                System.out.print(" --- ");
                depthFirstSearch(adjacent.getEnd(), visited);
            }
        }
    }

    private void fillOrder(Vertex vertex, boolean[] visited, Stack<Vertex> stack){
        visited[vertex.getLabel()] = true;
        for(Edge adjacent : vertex.getEdges()){
            if(!visited[adjacent.getEnd().getLabel()]){
                fillOrder(adjacent.getEnd(), visited, stack);
            }
        }
        stack.push(vertex);
    }

    // Calculates strongly connected components
    public void calcSCC()
    {
        Stack<Vertex> stack = new Stack<Vertex>();

        // Set vertices as not visited
        boolean[] visited = new boolean[order+1];
        for(int i = 1; i <= order; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing times
        for (int i = 1; i <= order; i++){
            if(!visited[i]){
                fillOrder(getVertex(i), visited, stack);
            }
        }

        // Create a reversed graph
        Graph transposedGraph = getTransposedGraph();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 1; i <= order; i++) {
            visited[i] = false;
        }

        // Now process all vertices in order defined by Stack
        while (!stack.empty()) {
            Vertex vertex = stack.pop();

            // Print Strongly connected component of the popped vertex
            if (!visited[vertex.getLabel()]) {
                transposedGraph.depthFirstSearch(vertex, visited);
                System.out.println();
            }
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    // Returns vertex with given label or null if it doesn't exists
    public Vertex getVertex(int label){
        return vertices.get(label);
    }

    public Collection<Vertex> getVerticies(){
        return vertices.values();
    }

    public int getOrder() {
        return order;
    }

    public int getSize() {
        return size;
    }
}
