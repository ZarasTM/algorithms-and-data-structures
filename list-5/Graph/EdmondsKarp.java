package Graph;

import java.util.ArrayDeque;
import java.util.Queue;

import static java.lang.Math.min;

public class EdmondsKarp {
    // To avoid overflow, set infinity to a value less than Long.MAX_VALUE;
    private static final long INF = Integer.MAX_VALUE / 2;

    // Inputs: nodeNum = number of nodes, source = source, t = sink
    private final int nodeNum;
    private final int source;
    private final int t;

    private int augPaths = 0;

    private long maxFlow;

    private Graph graph;
    private int visitedToken = 1;
    private int[] visited;
    private boolean solved;


    public EdmondsKarp(int source, int t, Graph graph) {
        this.graph = graph;
        this.nodeNum = graph.getNodeNum();
        this.source = source;
        this.t = t;
        visited = new int[nodeNum];
    }

    private void visit(int i) {
        visited[i] = visitedToken;
    }

    private boolean visited(int i) {
        return visited[i] == visitedToken;
    }

    private void markAllNodesAsUnvisited() {
        visitedToken++;
    }

    public long getMaxFlow() {
        execute();
        return maxFlow;
    }

    // Wrapper method that ensures we only call solve() once
    private void execute() {
        if (solved) return;
        solved = true;
        solve();
    }

    private void solve(){
        long flow;
        do {
            markAllNodesAsUnvisited();
            flow = bfs();
            maxFlow += flow;
        } while (flow != 0);
    }
    
    private long bfs() {
        Edge[] prev = new Edge[nodeNum];

        // The queue can be optimized to use a faster queue
        Queue<Integer> q = new ArrayDeque<>(nodeNum);
        visit(source);
        q.offer(source);

        // Perform BFS from source to sink
        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == t) break;
            for (Edge edge : graph.getVertex(node).getGotoEdges()) {
                long cap = edge.remainingCapacity();
                if (cap > 0 && !visited(edge.getEnd().getIntLabel())) {
                    visit(edge.getEnd().getIntLabel());
                    prev[edge.getEnd().getIntLabel()] = edge;
                    q.offer(edge.getEnd().getIntLabel());
                }
            }
        }

        // Sink not reachable
        if (prev[t] == null) return 0;

        long bottleNeck = INF;
        augPaths++;

        // Find augmented path and bottle neck
        for (Edge edge = prev[t]; edge != null; edge = prev[edge.getStart().getIntLabel()])
            bottleNeck = min(bottleNeck, edge.remainingCapacity());

        // Retrace augmented path and update flow values.
        for (Edge edge = prev[t]; edge != null; edge = prev[edge.getStart().getIntLabel()])
            edge.augment(bottleNeck);

        // Return bottleneck flow
        return bottleNeck;
    }

    public int getAugPaths() {
        return augPaths;
    }
}
