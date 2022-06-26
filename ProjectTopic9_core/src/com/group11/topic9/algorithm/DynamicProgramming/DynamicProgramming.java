package com.group11.topic9.algorithm.DynamicProgramming;

import com.group11.topic9.algorithm.Algorithm;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;

import java.util.ArrayList;
import java.util.Scanner;

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

        pseudoStepOrder.add(0);
        //create prepare 2-dimensional array minDis[n][n] \\n is number of vertexes
        //set all minDis[i][j] to its i-j edge weight, other to infinite
    }

    public void dynamicProgrammingSP(Graph g){
        int numberVer = g.getListVertex().size();

        for (int k = 0; k < numberVer; k++){
            pseudoStepOrder.add(1);               //we consider vertex k which stands on the shortest path of two other vertexes
            listDetailedStep.add("we consider vertex " + k + " which stands on the shortest path of two other vertexes");
            for (int i = 0; i < numberVer; i++){
                pseudoStepOrder.add(2);           //set vertex i to the start vertex
                listDetailedStep.add("set vertex " + i + " to the start vertex");
                    for (int j = 0; j < numberVer; j++) {
                        pseudoStepOrder.add(3);   //set vertex j to the end vertex
                        pseudoStepOrder.add(4);
                        listDetailedStep.add("set vertex " + j + " to the end vertex");
                        if (i != j) {                           //i difference from j so we can process
                            listDetailedStep.add(i + " difference from " + j + " so we can process");
                            float tmp = minDis.get(i).get(k) + minDis.get(k).get(j);
                            pseudoStepOrder.add(5);   //calculate tmp =  minDis[i][k] + minDis[k][j]
                            listDetailedStep.add("calculate tmp = minDis[" + i + "][" + k + "] + minDis[" + k + "][" + j + "]");

                            pseudoStepOrder.add(6);
                            if (minDis.get(i).get(j) > tmp) {       //because tmp > current shortest distance between i and j => update minDis[i][j] = tmp
                                listDetailedStep.add("because tmp < current shortest distance between " + i + " and " + j + " => update minDis[" + i + "][" + j + "] = tmp");
                                minDis.get(i).set(j, tmp);
                                pseudoStepOrder.add(7);
                                listDetailedStep.add("update minDis[" + i + "][" + j + "] = tmp inside the 2-dimension array");
                            } else
                                listDetailedStep.add("because tmp > minDis[" + i + "][" + j + "] so we keep the current shortest distance between " + i + " and " + j); // else because tmp < minDis[i][j] so we keep the current shortest distance between i and j
                        }else
                            listDetailedStep.add("i = j (" + i + " = " + j + ") so we don't need to do anything");//else   i = j (print vertex id) so we need to change j
                }
            }
        }
    }

    @Override
    public void executeAlgorithm(Graph graph) {
        listPseudoStep = new ArrayList<>();
        pseudoStepOrder = new ArrayList<>();

        listPseudoStep.add("init DPAlgorithm");
        listPseudoStep.add("for each vertex k of graph");
        listPseudoStep.add(" for each vertex i of graph");
        listPseudoStep.add("  for each vertex j of graph");
        listPseudoStep.add("    if i != j then");
        listPseudoStep.add("      tmp = minDis[i][k] + minDis[k][j]");
        listPseudoStep.add("        if minDis[i][k] > tmp then");
        listPseudoStep.add("           minDis[i][k] = tmp");
        //need detail step

        listDetailedStep = new ArrayList<>();
        listDetailedStep.add("create prepare 2-dimensional array minDis[n][n] (n is number of vertexes)\n" +
                "set all minDis[i][j] to its i-j edge weight, other to infinite");

        initDP(graph);
        dynamicProgrammingSP(graph);
    }

    @Override
    public void run() {
        stepPointer = 0;
        Scanner sc = new Scanner(System.in);
        int a;

        while (true){
            System.out.println("----------------------------");
            System.out.println("Detail: ");
            System.out.println(listDetailedStep.get(stepPointer));

            System.out.println("----------------------------");
            System.out.println("Pseudo code:");
            for (int j=0; j< listPseudoStep.size(); j++){
                if (pseudoStepOrder.get(stepPointer) == j){
                    System.out.println(listPseudoStep.get(j) + "  <==");
                }else {
                    System.out.println(listPseudoStep.get(j));
                }
            }


            a = sc.nextInt();
            if (a == 1){
                System.out.println("next step");
                stepPointer++;
            }else if (a ==2 && stepPointer > 0){
                System.out.println("back one step");
                stepPointer--;
            }else if (a == 0){
                System.out.println("Break");
                break;
            }
        }

        System.out.println("End run");
    }

    public float getMinDistance(int start, int end){
        return minDis.get(start).get(end);
    }
}
