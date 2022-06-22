package com.group11.topic9.graph;

import com.group11.topic9.action.Draw;

import java.util.ArrayList;

public class Graph_example {
    public static Graph  VD1(){
        Draw dr = new Draw();
        ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
        ArrayList<Edge> listEdge = new ArrayList<Edge>();
        for (int id = 0; id < 10; id++)
            listVertex.add(new Vertex(id));

        listEdge.add(dr.createEdge(listVertex.get(1),listVertex.get(2), 0.0f));
        listEdge.add(dr.createEdge(listVertex.get(6),listVertex.get(3), 0.0f));
        listEdge.add(dr.createEdge(listVertex.get(2),listVertex.get(4), 0.0f));
        listEdge.add(dr.createEdge(listVertex.get(1),listVertex.get(5), 0.0f));
        listEdge.add(dr.createEdge(listVertex.get(6),listVertex.get(2), 0.0f));
        listEdge.add(dr.createEdge(listVertex.get(3),listVertex.get(7), 0.0f));
        return new Graph(listEdge, listVertex);
    }

    public static Graph  VD2(){
        Draw dr = new Draw();
        ArrayList<Vertex> listVertex = new ArrayList<Vertex>();
        ArrayList<Edge> listEdge = new ArrayList<Edge>();
        for (int id = 0; id < 5; id++)
            listVertex.add(new Vertex(id));

        listEdge.add(dr.createEdge(listVertex.get(0),listVertex.get(2), 2.0f));
        listEdge.add(dr.createEdge(listVertex.get(2),listVertex.get(4), 5.0f));
        listEdge.add(dr.createEdge(listVertex.get(4),listVertex.get(1), 1.0f));
        listEdge.add(dr.createEdge(listVertex.get(1),listVertex.get(3), 6.0f));
        listEdge.add(dr.createEdge(listVertex.get(3),listVertex.get(0), 1.0f));
        return new Graph(listEdge, listVertex);
    }
}
