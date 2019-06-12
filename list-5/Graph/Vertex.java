package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final String strLabel;
    private final int intLabel;
    private final int hammingWeight;
    private List<Edge> gotoEdges = new ArrayList<Edge>();

    Vertex(String strLabel, int intLabel){
        this.strLabel = strLabel;
        this.intLabel = intLabel;
        hammingWeight = calcHammingWeight();
    }

    Vertex(int label){
        this.strLabel = "";
        this.intLabel = label;
        hammingWeight = calcHammingWeight();
    }

    public void addEdge(Edge e){
        gotoEdges.add(e);
    }

    private int calcHammingWeight(){
        int weight=0;
        for(int idx=0; idx<strLabel.length(); idx++){
            if(strLabel.charAt(idx) == '1'){
                weight++;
            }
        }
        return weight;
    }

    // Getters & Setters
    public int getHammingWeight() {
        return hammingWeight;
    }

    public String getStrLabel() {
        return strLabel;
    }

    public int getIntLabel() {
        return intLabel;
    }

    public List<Edge> getGotoEdges() {
        return gotoEdges;
    }
}
