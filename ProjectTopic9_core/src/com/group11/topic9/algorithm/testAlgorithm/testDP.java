package com.group11.topic9.algorithm.testAlgorithm;

import com.group11.topic9.algorithm.DynamicProgramming.DynamicProgramming;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Graph_example;

public class testDP {
    public static void main(String[] args) {
        Graph vd = Graph_example.VD2();
        DynamicProgramming dp = new DynamicProgramming();

        vd.setDirected(false);

        dp.executeAlgorithm(vd);
        for (int i = 0; i < vd.getListVertex().size(); i++){
            System.out.println(dp.getMinDis().get(i));
        }

        System.out.println(dp.getListPseudoStep().getStepOrder());
        System.out.println(dp.getListPseudoStep().getListStep());
    }
}
