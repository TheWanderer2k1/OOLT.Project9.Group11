package com.group11.topic9.graph;

import java.util.ArrayList;

public class Graph {
    private ArrayList<Edge> listEdge;
    private ArrayList<Vertex> listVertex;

    private boolean isDirected = true;

    public Graph(ArrayList<Edge> listEdge, ArrayList<Vertex> listVertex) {
        this.listEdge = listEdge;
        this.listVertex = listVertex;
    }

    public ArrayList<Edge> getListEdge() {
        return listEdge;
    }

    public ArrayList<Vertex> getListVertex() {
        return listVertex;
    }

    public void setListEdge(ArrayList<Edge> listEdge) {
        this.listEdge = listEdge;
    }

    public void setListVertex(ArrayList<Vertex> listVertex) {
        this.listVertex = listVertex;
    }

    public void setDirected(boolean directed) {
        isDirected = directed;
        if (!isDirected)
            for (int i = 0; i < listEdge.size(); i++){
                listEdge.get(i).setUnDirected();
            }
        else
            for (int i = 0; i < listEdge.size(); i++){
                listEdge.get(i).setDirected();
            }
    }

    public void showGraph(){
        if (this.isDirected)
            for (int i = 0; i < listEdge.size(); i++){
                System.out.println(listEdge.get(i).getFrom().getId() + "->" + listEdge.get(i).getTo().getId() + " = " + listEdge.get(i).getWeight());
            }
        else
            for (int i = 0; i < listEdge.size(); i++){
                System.out.println(listEdge.get(i).getMyVertex().get(0).getId() + "-" + listEdge.get(i).getMyVertex().get(1).getId() + " = " + listEdge.get(i).getWeight());
            }
    }
}
