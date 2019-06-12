package Graph;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

public class BipartiteGraph extends Graph {
    private SecureRandom secRand = new SecureRandom();
    private Vertex[] vertArr1;
    private Vertex[] vertArr2;
    private Vertex source, target;

    public BipartiteGraph(int k, int i){
        int arrPow = (int)Math.pow(2, k);
        nodeNum = (arrPow*2)+2;
        vertArr1 = new Vertex[arrPow];
        vertArr2 = new Vertex[arrPow];
        int calc=0;

        source = new Vertex(calc++);

        for(int idx=0; idx<vertArr1.length; idx++){
            vertArr1[idx] = new Vertex(calc++);
            vertArr2[idx] = new Vertex(calc++);
        }

        target = new Vertex(calc);

        initEdges(i);
    }

    public Vertex getVertex(int num){
        if(num == 0) return source;
        if(num == nodeNum-1) return target;
        return num%2 == 0 ? vertArr2[(num/2)-1] : vertArr1[num/2];
    }

    private void initEdges(int i){
        Vertex goTo;
        Set<Integer> used = new HashSet<>();;
        int idx;
        for(Vertex vert: vertArr1){
            used.clear();
            while(vert.getGotoEdges().size() < i){
                do{
                    idx = secRand.nextInt(vertArr1.length);
                } while(used.contains(idx));

                used.add(idx);

                goTo = vertArr2[idx];
                Edge newEdge = new Edge(vert, goTo, 1);
                Edge resEdge = new Edge(goTo, vert, 0);
                edges.add(newEdge);
                newEdge.setResidual(resEdge);
                resEdge.setResidual(newEdge);
                vert.addEdge(newEdge);
                goTo.addEdge(resEdge);
            }
        }

        // Add source edges
        while(source.getGotoEdges().size() < vertArr1.length){
            goTo = vertArr1[source.getGotoEdges().size()];
            Edge newEdge = new Edge(source, goTo, 1);
            Edge resEdge = new Edge(goTo, source, 0);
            edges.add(newEdge);
            newEdge.setResidual(resEdge);
            resEdge.setResidual(newEdge);
            source.addEdge(newEdge);
            goTo.addEdge(resEdge);
        }

        // Add target edges
        while(target.getGotoEdges().size() < vertArr2.length){
            goTo = vertArr2[target.getGotoEdges().size()];
            Edge newEdge = new Edge(goTo, target, 1);
            Edge resEdge = new Edge(target, goTo, 0);
            edges.add(newEdge);
            newEdge.setResidual(resEdge);
            resEdge.setResidual(newEdge);
            goTo.addEdge(newEdge);
            target.addEdge(resEdge);
        }
    }

    @Override
    public Vertex[] getVertices() {
        Vertex[] result = new Vertex[vertArr1.length+vertArr2.length];
        System.arraycopy(vertArr1, 0, result, 0, vertArr1.length);
        System.arraycopy(vertArr2, 0, result, vertArr1.length, vertArr2.length);

        return result;
    }

    public Vertex[] getVertArr1() {
        return vertArr1;
    }

    public Vertex[] getVertArr2() {
        return vertArr2;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }
}
