package com.group11.topic9.action.AlgorithmStep;

import com.group11.topic9.action.PseudoStep;

public class DP_PseudoStep extends PseudoStep {
    public void initStep(){
            listStep.add("for each vertex k of graph");
            listStep.add("for each vertex i of graph");
            listStep.add("for each vertex j of graph");
            listStep.add("if i != j then");
            listStep.add("tmp = minDis[i][k] + minDis[k][j]");
            listStep.add("if minDis[i][k] > tmp then");
            listStep.add("minDis[i][k] = tmp");
    }
}
