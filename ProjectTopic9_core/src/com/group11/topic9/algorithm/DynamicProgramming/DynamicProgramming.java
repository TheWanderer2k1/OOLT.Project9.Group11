package com.group11.topic9.algorithm.DynamicProgramming;

import com.group11.topic9.action.DetailedStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.algorithm.Algorithm;
import com.group11.topic9.graph.*;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Scanner;

public class DynamicProgramming extends Algorithm {

    ArrayList<ArrayList<Float>> minDis = new ArrayList<>();
    ArrayList<ArrayList<Float>> initMatrix = new ArrayList<>();

    public ArrayList<ArrayList<Float>> getInitMatrix() {
        return initMatrix;
    }

    public ArrayList<ArrayList<Float>> getMinDis() {
        return minDis;
    }

    public void initDP(Graph g){
        int n = g.getListVertex().size();
        for (int i  = 0; i < n; i++){
            minDis.add(new ArrayList());
            initMatrix.add(new ArrayList());
        }


        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                minDis.get(i).add(999f);
                initMatrix.get(i).add(999f);
            }


        for (int i = 0; i < g.getListEdge().size(); i++) {
            Edge e = g.getListEdge().get(i);
            int from = 0;
            int to = 0;
            if (!g.isDirected()){
                from = e.getMyVertex().get(0).getId();
                to = e.getMyVertex().get(1).getId();
                minDis.get(from).set(to, e.getWeight());
                minDis.get(to).set(from, e.getWeight());

                initMatrix.get(from).set(to, e.getWeight());
                initMatrix.get(to).set(from, e.getWeight());
            }else {
                from = e.getFrom().getId();
                to = e.getTo().getId();
                minDis.get(from).set(to, e.getWeight());

                initMatrix.get(from).set(to, e.getWeight());
            }
            minDis.get(from).set(to, e.getWeight());
            initMatrix.get(from).set(to, e.getWeight());
        }

        //initMatrix.addAll(minDis);

        pseudoStepOrder.add(0);
        //create prepare 2-dimensional array minDis[n][n] \\n is number of vertexes
        //set all minDis[i][j] to its i-j edge weight, other to infinite
    }

    public void dynamicProgrammingSP(Graph g){      //co van de voi state list
        ArrayList<Vertex> currentVertex;
        ArrayList<Paint> vertexPaint;
        int numberVer = g.getListVertex().size();

        int i = 0, j = 0, k = 0;

        for (k = 0; k < numberVer; k++){
            pseudoStepOrder.add(1);               //we consider vertex k which stands on the shortest path of two other vertexes
            listDetailedStep.add(new DetailedStep("we consider vertex " + k + " which stands on the shortest path of two other vertexes"));

            // tao state, them dinh 0, them mau
            currentVertex = new ArrayList<>();
            vertexPaint = new ArrayList<>();
            currentVertex.add(g.getListVertex().get(i));
            currentVertex.add(g.getListVertex().get(j));
            currentVertex.add(g.getListVertex().get(k));
            vertexPaint.add(Color.ORANGE);
            vertexPaint.add(Color.ORANGE);
            vertexPaint.add(Color.RED);
            listState.add(new DPState(currentVertex, vertexPaint, 999, 999, 999f));

            //i = 0;
            for (i = 0; i < numberVer; i++){
                pseudoStepOrder.add(2);           //set vertex i to the start vertex
                listDetailedStep.add(new DetailedStep("set vertex " + i + " to the start vertex"));

                // tao state, them 0, them mau
                currentVertex = new ArrayList<>();
                vertexPaint = new ArrayList<>();
                currentVertex.add(g.getListVertex().get(k));
                currentVertex.add(g.getListVertex().get(j));
                currentVertex.add(g.getListVertex().get(i));
                vertexPaint.add(Color.RED);
                vertexPaint.add(Color.ORANGE);
                vertexPaint.add(Color.YELLOW);
                listState.add(new DPState(currentVertex, vertexPaint,999, 999, 999f));

                    //j = 0;
                    for (j = 0; j < numberVer; j++) {
                        pseudoStepOrder.add(3);   //set vertex j to the end vertex
                        pseudoStepOrder.add(4);
                        listDetailedStep.add(new DetailedStep("set vertex " + j + " to the end vertex"));

                        // tao state
                        currentVertex = new ArrayList<>();
                        vertexPaint = new ArrayList<>();
                        currentVertex.add(g.getListVertex().get(k));
                        currentVertex.add(g.getListVertex().get(i));
                        currentVertex.add(g.getListVertex().get(j));
                        vertexPaint.add(Color.RED);
                        vertexPaint.add(Color.YELLOW);
                        vertexPaint.add(Color.BLUE);
                        listState.add(new DPState(currentVertex, vertexPaint,999, 999, 999f));


                        if (i != j) {                           //i difference from j so we can process
                            listDetailedStep.add(new DetailedStep(i + " difference from " + j + " so we can process"));
                            float tmp = minDis.get(i).get(k) + minDis.get(k).get(j);
                            pseudoStepOrder.add(5);   //calculate tmp =  minDis[i][k] + minDis[k][j]
                            listDetailedStep.add(new DetailedStep("calculate tmp = minDis[" + i + "][" + k + "] + minDis[" + k + "][" + j + "]"));
                            pseudoStepOrder.add(6);

                            // tuong duong len nop
                            listState.add(new DPState());
                            listState.add(new DPState());

                            //tao state,
                            if (minDis.get(i).get(j) > tmp) {       //because tmp > current shortest distance between i and j => update minDis[i][j] = tmp
                                listDetailedStep.add(new DetailedStep("because tmp < current shortest distance between " + i + " and " + j + " => update minDis[" + i + "][" + j + "] = tmp"));
                                minDis.get(i).set(j, tmp);
                                pseudoStepOrder.add(7);
                                listDetailedStep.add(new DetailedStep("update minDis[" + i + "][" + j + "] = tmp inside the 2-dimension array"));

                                //tao state: thay doi minDis[i][j]
                                listState.add(new DPState());
                                listState.add(new DPState(i, j, tmp));

                            } else {
                                listDetailedStep.add(new DetailedStep("because tmp > minDis[" + i + "][" + j + "] so we keep the current shortest distance between " + i + " and " + j)); // else because tmp < minDis[i][j] so we keep the current shortest distance between i and j
                                listState.add(new DPState());
                            }
                        }else {
                            listDetailedStep.add(new DetailedStep("i = j (" + i + " = " + j + ") so we don't need to do anything"));//else   i = j (print vertex id) so we need to change j
                            listState.add(new DPState());
                        }
                }
                  j--;
            }
            i--;
        }
    }

    @Override
    public void executeAlgorithm(Graph graph) {
        listState = new ArrayList<>();
        listPseudoStep = new ArrayList<>();
        pseudoStepOrder = new ArrayList<>();

        listPseudoStep.add(new PseudoStep("init DPAlgorithm"));
        listPseudoStep.add(new PseudoStep("for each vertex k of graph"));
        listPseudoStep.add(new PseudoStep(" for each vertex i of graph"));
        listPseudoStep.add(new PseudoStep("  for each vertex j of graph"));
        listPseudoStep.add(new PseudoStep("    if i != j  then"));
        listPseudoStep.add(new PseudoStep("    tmp = minDis[i][k] + minDis[k][j]"));
        listPseudoStep.add(new PseudoStep("        if minDis[i][k] > tmp then"));
        listPseudoStep.add(new PseudoStep("           minDis[i][k] = tmp"));




        listDetailedStep = new ArrayList<>();
        listDetailedStep.add(new DetailedStep("create prepare 2-dimensional array minDis[n][n] (n is number of vertexes)\n" +
                "set all minDis[i][j] to its i-j edge weight, other to infinite"));
        listState.add(new DPState());

        initDP(graph);
        dynamicProgrammingSP(graph);
    }

    @Override
    public void run() {
//        stepPointer = 0;
//        Scanner sc = new Scanner(System.in);
//        int a;
//
//        while (true){
//            System.out.println("----------------------------");
//            System.out.println("Detail: ");
//            System.out.println(listDetailedStep.get(stepPointer).getContent());
//
//            if (listState.get(stepPointer).getCurrentVertexes() != null ) {
//                System.out.println(listState.get(stepPointer).getCurrentVertexes().get(0).getId());
//            }
//            else if (((DPState) listState.get(stepPointer)).getI() != 999)
//                    System.out.println( "change: " + ((DPState) listState.get(stepPointer)).getChangedValue());
//
//
//            System.out.println("----------------------------");
//            System.out.println("Pseudo code:");
//            for (int j=0; j< listPseudoStep.size(); j++){
//                if (pseudoStepOrder.get(stepPointer) == j){
//                    System.out.println(listPseudoStep.get(j).getContent() + "  <==");
//                }else {
//                    System.out.println(listPseudoStep.get(j).getContent());
//                }
//            }
//
//
//            a = sc.nextInt();
//            if (a == 1){
//                System.out.println("next step");
//                stepPointer++;
//            }else if (a ==2 && stepPointer > 0){
//                System.out.println("back one step");
//                stepPointer--;
//            }else if (a == 0){
//                System.out.println("Break");
//                break;
//            }
//        }
//
//        System.out.println("End run");
//        for (int i = 0; i < listState.size(); i++){
//            listState.get(stepPointer).getCurrentVertexes().get(i).getVerCircle().setFill(listState.get(stepPointer).getVertexPaints().get(i));
//        }
    }

    public float getMinDistance(int start, int end){
        return minDis.get(start).get(end);
    }

    public ArrayList<State> getListState(){
        return listState;
    }



    public String getPseudoAndDetailStep(int stepPointer) {
        String str = "";
        str = str + "---------------------------\nDetail: \n";
        str = str + listDetailedStep.get(stepPointer).getContent() + "\n";
        str = str + "---------------------------\nPseudo code:\n";

        for (int j=0; j< listPseudoStep.size(); j++){
                if (pseudoStepOrder.get(stepPointer) == j){
                    str = str + listPseudoStep.get(j).getContent() + "  <==\n";
                }else {
                    str = str + listPseudoStep.get(j).getContent() + "\n";
                }
            }
        return str;
    }
}
