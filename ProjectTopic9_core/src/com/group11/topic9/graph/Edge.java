package com.group11.topic9.graph;

import java.util.ArrayList;
import java.util.Scanner;

public class Edge {
    private Vertex from;
    private Vertex to;

    private ArrayList<Vertex> myVertex;

    private boolean isWeighed;
    private boolean isDirected;
    private float weight;

    public Edge(Vertex from, Vertex to, ArrayList<Vertex> myVertex, boolean isWeighed, boolean isDirected, float weight) {
        this.from = from;
        this.to = to;
        this.myVertex = myVertex;
        this.isWeighed = isWeighed;
        this.isDirected = isDirected;
        this.weight = weight;
    }

    public Vertex getFrom() {
        return from;
    }

    public Vertex getTo() {
        return to;
    }

    public ArrayList<Vertex> getMyVertex() {
        return myVertex;
    }

    public boolean isWeighed() {
        return isWeighed;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public float getWeight() {
        return weight;
    }

    public void setFrom(Vertex from) {
        this.from = from;
    }

    public void setTo(Vertex to) {
        this.to = to;
    }

    public void setMyVertex(ArrayList<Vertex> myVertex) {
        this.myVertex = myVertex;
    }

    public void setWeighed(boolean weighed) {
        isWeighed = weighed;
    }

    public void setDirected() {
        this.isDirected = true;
        this.from = this.myVertex.get(0);
        this.to = this.myVertex.get(1);
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setUnDirected(){
        this.isDirected = false;
        this.from = null;
        this.to = null;
    }

    public boolean equals(Edge e){
        if (this.isDirected)
            return (this.from == e.getFrom() && this.to == e.getTo());
        return this.myVertex.equals(e.getMyVertex());
    }
}
