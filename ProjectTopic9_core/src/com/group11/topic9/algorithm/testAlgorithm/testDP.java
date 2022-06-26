package com.group11.topic9.algorithm.testAlgorithm;

import com.group11.topic9.algorithm.DynamicProgramming.DynamicProgramming;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Graph_example;


public class testDP {
    public static void main(String[] args) {
        Graph vd = Graph_example.VD2();
        DynamicProgramming dp = new DynamicProgramming();

        //vd.setDirected(false);

        dp.executeAlgorithm(vd);

        dp.run();
    }
}
