package com.group11.topic9.test;

import com.group11.topic9.algorithm.Dijkstra;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Graph_example;

public class TestDJ {
    public static void main (String[] args){

        Graph vd = Graph_example.VD2();
        vd.showGraph();

        Dijkstra dj = new Dijkstra();
        dj.executeAlgorithm(vd);
        dj.run();



//        System.out.println(dj.);
//        System.out.println(dj.getListPseudoStep().getListofStep());
//        dj.getListPseudoStep().run();
    }
}
