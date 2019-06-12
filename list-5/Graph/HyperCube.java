package Graph;

import java.security.SecureRandom;
import java.util.Arrays;

public class HyperCube extends Graph {
    private SecureRandom secRand = new SecureRandom();
    private Vertex[] vertices;
    private int dimension; // passed k

    public HyperCube(int k){
        this.dimension = k;

        // Init vertices
        nodeNum = (int)Math.pow(2, k);
        vertices = new Vertex[nodeNum];
        for(int label=0; label<vertices.length; label++){
            vertices[label] = new Vertex(decToBin(label), label);
        }

        createEdges();
    }

    public Vertex getVertex(String num){
        return vertices[binToDec(num)];
    }

    public Vertex getVertex(int num){
        return vertices[num];
    }

    private void createEdges(){
        for(Vertex v : vertices){
            for(Vertex neighbour : getNeighbours(v)){
                if(neighbour.getHammingWeight() > v.getHammingWeight()){
                    int maxCapacity = Arrays.stream(new int[]{neighbour.getHammingWeight(),
                            v.getHammingWeight(),
                            dimension-neighbour.getHammingWeight(),
                            dimension-v.getHammingWeight()}).max().getAsInt();
                    Edge newEdge = new Edge(v, neighbour, 1+secRand.nextInt((int)Math.pow(2,maxCapacity)-1));
                    Edge resEdge = new Edge(neighbour, v, 0);
                    newEdge.setResidual(resEdge);
                    resEdge.setResidual(newEdge);
                    edges.add(newEdge);
                    v.addEdge(newEdge);
                    neighbour.addEdge(resEdge);
                }
            }
        }
    }

    private Vertex[] getNeighbours(Vertex v){
        Vertex[] neighbours = new Vertex[dimension];
        String label = v.getStrLabel();
        for(int idx=0; idx<dimension; idx++){
            if(label.charAt(idx) == '0'){
                neighbours[idx] = vertices[binToDec(label.substring(0, idx)+"1"+label.substring(idx+1))];
            }else{
                neighbours[idx] = vertices[binToDec(label.substring(0, idx)+"0"+label.substring(idx+1))];
            }
        }
        return neighbours;
    }

    private int binToDec(String bin){
        int dec = 0;
        for(int mul=0; mul<bin.length(); mul++){
            if(bin.charAt(mul) == '1'){
                dec += (int)Math.pow(2, mul);
            }
        }
        return dec;
    }

    private String decToBin(int dec){
        String bin = Integer.toBinaryString(dec);

        // Fill remaining zeroes
        while(bin.length() != dimension) bin = "0"+bin;

        return bin;
    }

    public Vertex[] getVertices() {
        return vertices;
    }

    public int getDimension() {
        return dimension;
    }
}
