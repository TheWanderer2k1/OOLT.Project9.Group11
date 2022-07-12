package com.group11.topic9.algorithm;

import com.group11.topic9.graph.Graph;

import java.util.ArrayList;

public abstract class Algorithm {
//    protected PseudoStep listPseudoStep;
//    public PseudoStep getListPseudoStep() {
//        return listPseudoStep;
//    }
//    //need detail step
//
//    protected DetailedStep listDetailedStep;
//    public DetailedStep getListDetailedStep() {
//        return listDetailedStep;
//    }

    protected ArrayList<State> listState;
    protected ArrayList<PseudoStep>listPseudoStep;
    protected ArrayList<Integer>pseudoStepOrder;

    protected ArrayList<DetailedStep>listDetailedStep;

    protected int stepPointer;

    public abstract void executeAlgorithm(Graph graph);

    public abstract void run();
}
