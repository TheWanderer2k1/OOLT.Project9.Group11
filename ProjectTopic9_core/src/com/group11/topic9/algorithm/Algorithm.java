package com.group11.topic9.algorithm;

import com.group11.topic9.step.DetailedStep;
import com.group11.topic9.step.PseudoStep;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.state.State;

import java.util.ArrayList;

public abstract class Algorithm {

    protected ArrayList<State> listState;
    protected ArrayList<PseudoStep>listPseudoStep;
    protected ArrayList<Integer>pseudoStepOrder;
    protected ArrayList<DetailedStep>listDetailedStep;

    //protected int stepPointer;

    public abstract void executeAlgorithm(Graph graph);

    //public abstract void run();
}
