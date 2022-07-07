package com.group11.topic9.algorithm;

import com.group11.topic9.action.DetailedStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.graph.Graph;

import java.util.ArrayList;

public abstract class Algorithm {



    protected ArrayList<PseudoStep>listPseudoStep;
    protected ArrayList<Integer>pseudoStepOrder;
    protected ArrayList<DetailedStep> listDetailedStep;

    protected int stepPointer;

    public abstract void executeAlgorithm(Graph graph);

    public abstract void run();
}
