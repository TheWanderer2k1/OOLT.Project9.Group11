package com.group11.topic9.state;

import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Vertex;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class State {

//    Graph observedGraph;

    private ArrayList<Vertex> currentVertexes; //nhung dinh dc xet
    private ArrayList<Edge> currentEdges;      //nhung canh dc xet

    private ArrayList<Paint> vertexPaints;
    private ArrayList<Paint> edgePaints;



    public ArrayList<Vertex> getCurrentVertexes() {
        return currentVertexes;
    }

    public void setCurrentVertexes(ArrayList<Vertex> currentVertexes) {
        this.currentVertexes = currentVertexes;
    }

    public ArrayList<Edge> getCurrentEdges() {
        return currentEdges;
    }

    public void setCurrentEdges(ArrayList<Edge> currentEdges) {
        this.currentEdges = currentEdges;
    }

    public ArrayList<Paint> getVertexPaints() {
        return vertexPaints;
    }

    public void setVertexPaints(ArrayList<Paint> vertexPaints) {
        this.vertexPaints = vertexPaints;
    }

    public ArrayList<Paint> getEdgePaints() {
        return edgePaints;
    }

    public void setEdgePaints(ArrayList<Paint> edgePaints) {
        this.edgePaints = edgePaints;
    }

    public State(){
        super();
    }

    public State(ArrayList<Vertex> currentVertexes, ArrayList<Paint> vertexPaints) {
        this.currentVertexes = currentVertexes;
        this.vertexPaints = vertexPaints;
    }


    public State(ArrayList<Vertex> currentVertexes, ArrayList<Edge> currentEdges, ArrayList<Paint> vertexPaints, ArrayList<Paint> edgePaints) {
        this.currentVertexes = currentVertexes;
        this.currentEdges = currentEdges;
        this.vertexPaints = vertexPaints;
        this.edgePaints = edgePaints;
    }
}

