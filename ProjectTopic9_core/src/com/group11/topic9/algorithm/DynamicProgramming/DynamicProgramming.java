package com.group11.topic9.algorithm.DynamicProgramming;

import com.group11.topic9.action.AlgorithmStep.containDPStep.DP_DetailedStep;
import com.group11.topic9.action.AlgorithmStep.containDPStep.DP_PseudoStep;
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

        listPseudoStep.getStepOrder().add(0);
        //create prepare 2-dimensional array minDis[n][n] \\n is number of vertexes
        //set all minDis[i][j] to its i-j edge weight, other to infinite
    }

    public void dynamicProgrammingSP(Graph g){
        int numberVer = g.getListVertex().size();

        for (int k = 0; k < numberVer; k++){
            listPseudoStep.getStepOrder().add(1);               //we consider vertex k which stands on the shortest path of two other vertexes
            listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd1(k));
            for (int i = 0; i < numberVer; i++){
                listPseudoStep.getStepOrder().add(2);           //set vertex i to the start vertex
                listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd2(i));
                    for (int j = 0; j < numberVer; j++) {
                        listPseudoStep.getStepOrder().add(3);   //set vertex j to the end vertex
                        listPseudoStep.getStepOrder().add(4);
                        listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd3(j));
                        if (i != j) {                           //i difference from j so we can process
                            listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd4(i,j));
                            float tmp = minDis.get(i).get(k) + minDis.get(k).get(j);
                            listPseudoStep.getStepOrder().add(5);   //calculate tmp =  minDis[i][k] + minDis[k][j]
                            listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd5(i,k,j));

                            listPseudoStep.getStepOrder().add(6);
                            if (minDis.get(i).get(j) > tmp) {       //because tmp > current shortest distance between i and j => update minDis[i][j] = tmp
                                listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd6(i,j));
                                minDis.get(i).set(j, tmp);
                                listPseudoStep.getStepOrder().add(7);
                            } else
                                listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd7(i,j)); // else because tmp < minDis[i][j] so we keep the current shortest distance between i and j
                        }else
                            listDetailedStep.getDetailedCmd().add(DP_DetailedStep.setCmd8(i,j));//else   i = j (print vertex id) so we need to change j
                }
            }
        }
    }

    @Override
    public void executeAlgorithm(Graph graph) {
        listPseudoStep = new DP_PseudoStep();
        ((DP_PseudoStep) listPseudoStep).initStep();
        //need detail step

        listDetailedStep = new DP_DetailedStep();
        ((DP_DetailedStep) listDetailedStep).initStep();
        initDP(graph);
        dynamicProgrammingSP(graph);
    }

    public float getMinDistance(int start, int end){
        return minDis.get(start).get(end);
    }
}
