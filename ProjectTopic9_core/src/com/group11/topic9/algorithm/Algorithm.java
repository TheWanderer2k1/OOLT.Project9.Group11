package com.group11.topic9.algorithm;

import com.group11.topic9.action.DetailedStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.action.Step;
import com.group11.topic9.graph.Graph;

public abstract class Algorithm {
    protected PseudoStep listPseudoStep;
    public PseudoStep getListPseudoStep() {
        return listPseudoStep;
    }
    //need detail step

    protected DetailedStep listDetailedStep;
    public DetailedStep getListDetailedStep() {
        return listDetailedStep;
    }

    public abstract void executeAlgorithm(Graph graph);

}
