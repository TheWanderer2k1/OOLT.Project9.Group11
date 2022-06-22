package com.group11.topic9.algorithm.DynamicProgramming;

import com.group11.topic9.action.AlgorithmStep.DP_PseudoStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.algorithm.Algorithm;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;

import java.util.ArrayList;

public class DynamicProgramming extends Algorithm {
    ArrayList<ArrayList<Float>> minDis = new ArrayList<>();

    public ArrayList<ArrayList<Float>> getMinDis() {
        return minDis;
    }

    public void initDP(Graph g){
        int n = g.getListVertex().size();
        for (int i  = 0; i < n; i++){
            minDis.add(new ArrayList());
        }


        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                minDis.get(i).add(999f);


        for (int i = 0; i < g.getListEdge().size(); i++) {
            Edge e = g.getListEdge().get(i);
            int from = 0;
            int to = 0;
            if (!g.isDirected()){
                from = e.getMyVertex().get(0).getId();
                to = e.getMyVertex().get(1).getId();
                minDis.get(from).set(to, e.getWeight());
                minDis.get(to).set(from, e.getWeight());
            }else {
                from = e.getFrom().getId();
                to = e.getTo().getId();
                minDis.get(from).set(to, e.getWeight());
            }
            minDis.get(from).set(to, e.getWeight());
        }
    }

    public void dynamicProgrammingSP(Graph g){
        int numberVer = g.getListVertex().size();

        for (int k = 0; k < numberVer; k++){
            listPseudoStep.getStepOrder().add(0);
            for (int i = 0; i < numberVer; i++){
                listPseudoStep.getStepOrder().add(1);
                    for (int j = 0; j < numberVer; j++) {
                        listPseudoStep.getStepOrder().add(2);
                        listPseudoStep.getStepOrder().add(3);
                        if (i != j) {
                            float tmp = minDis.get(i).get(k) + minDis.get(k).get(j);
                            listPseudoStep.getStepOrder().add(4);
                            listPseudoStep.getStepOrder().add(5);
                            if (minDis.get(i).get(j) > tmp) {
                                minDis.get(i).set(j, tmp);
                                listPseudoStep.getStepOrder().add(6);
                            }
                        }
                }
            }
        }
    }

    @Override
    public void executeAlgorithm(Graph graph) {
        listPseudoStep = new DP_PseudoStep();
        ((DP_PseudoStep) listPseudoStep).initStep();
        //need detail step

        initDP(graph);
        dynamicProgrammingSP(graph);
    }

    public float getMinDistance(int start, int end){
        return minDis.get(start).get(end);
    }
}
