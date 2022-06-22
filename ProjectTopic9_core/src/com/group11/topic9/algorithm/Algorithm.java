package com.group11.topic9.algorithm;

import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.graph.Graph;

public abstract class Algorithm {

    public PseudoStep listPseudoStep;

    public PseudoStep getListPseudoStep() {
        return listPseudoStep;
    }

    public abstract void executeAlgorithm(Graph graph);
}
