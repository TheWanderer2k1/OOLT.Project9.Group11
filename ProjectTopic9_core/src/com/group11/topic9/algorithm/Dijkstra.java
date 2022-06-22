package com.group11.topic9.algorithm;

import com.group11.topic9.action.algorithmStep.DJ_PseudoStep;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;

import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra extends  Algorithm{
    public static final float INFINITE = 1000;


//    PseudoStep pStep = new PseudoStep();

    public void dijkstraProgram(Graph testGraph){
        ArrayList<Vertex> checkVertex = new ArrayList<Vertex>();
                                                            //TODO: init Graph
        listPseudoStep.getStepIndex().add(0);
        System.out.print("Nhap id dinh Start: ");
        Scanner sc1 = new Scanner(System.in);
        int startID = sc1.nextInt();

        Vertex Start = new Vertex();
        Start = testGraph.getVertex(startID);

        for (int i = 0; i < testGraph.getListVertex().size(); i++) {  //todo: khoi tao vo cung
            testGraph.getListVertex().get(i).setDis(INFINITE);
        }

        for (int i=0; i<testGraph.getListVertex().size(); i++){
            if (testGraph.getListVertex().get(i) == Start){
                testGraph.getListVertex().get(i).setPre(0);
                testGraph.getListVertex().get(i).setDis(0.0f);
            }
        }

        checkVertex.addAll(testGraph.getListVertex());

        Start.setDis(0.0f);
        Start.setPre(0);
        if (testGraph.numOutdegree(Start) == 0){
            System.out.println("Khong co dinh ke cua "+Start.getId());
        }else {
            while (checkVertex.size() != 0) {
                listPseudoStep.getStepIndex().add(1);
                boolean check = true;

                for (int i = 0; i < testGraph.getListVertex().size(); i++) {
                    listPseudoStep.getStepIndex().add(2);
                    Vertex temp = new Vertex();
                    temp = this.extractMin(checkVertex);

                    //duyet tat cac ke cua dinh do
                    for (int j = 0; j < checkVertex.size(); j++) {
                        //neu la dinh ke va chua duoc tham
                        if (testGraph.hasEdgeFrom(temp, checkVertex.get(j)) && checkVertex.get(j) != temp) {     //
                            listPseudoStep.getStepIndex().add(3);
                            this.Relaxing1(testGraph, temp, checkVertex.get(j));
                        }
                    }

//                    temp = this.extractMin(checkVertex);

                    //remove extractMin
                    if (temp != null) {
                        for (int j = 0; j < checkVertex.size(); j++) {
                            if (checkVertex.get(j).getId() == temp.getId() && checkVertex.get(j) != null) {
                                checkVertex.remove(j);
                            }
                        }
                    }

                }

                for (int i = 0; i < checkVertex.size(); i++) {
                    if (checkVertex.get(i).getDis() != INFINITE) {
                        check = false;
                        break;
                    }
                }
                if (check) {
                    break;
                }
            }
            System.out.println("------------------------------------------------\n");
            System.out.println("Danh sach cac dinh sau Dijkstra");
            for (int i = 0; i < testGraph.getListVertex().size(); i++) {
                System.out.print("(" + testGraph.getListVertex().get(i).getId() + ","
                        + testGraph.getListVertex().get(i).getDis() + "): ");
                if (testGraph.getListVertex().get(i).getDis() == INFINITE) {
                    System.out.println("Khong co duong di tu " + Start.getId() + " den "
                            + testGraph.getListVertex().get(i).getId());
                } else {
                    this.in_duong(testGraph, testGraph.getListVertex().get(i), Start);
                    System.out.println("->" + testGraph.getListVertex().get(i).getId());
                }
                System.out.println("----------");
            }
        }
    }

    public void Relaxing1 (Graph g, Vertex ver1, Vertex ver2){   //a:ver1----> b:ver2
        if ((ver1.getDis() +g.getWeight(ver1, ver2)) <  ver2.getDis()){    //d[a]+ab < d[b]
            float newDis = ver1.getDis()+ g.getWeight(ver1, ver2);
            ver2.setDis(newDis);      //d[b] ::= d[a]+ab
            ver2.setPre(ver1.getId());
        }
    }

    public Vertex extractMin (ArrayList<Vertex> checkVertex){
        Vertex temp = new Vertex();
        float minDis =  INFINITE;
        for (int i=0; i < checkVertex.size(); i++){
            if (checkVertex.get(i).getDis() < minDis){
//                System.out.println(" Minimum is : "+checkVertex.get(i).getId());
                minDis = checkVertex.get(i).getDis();
                temp = checkVertex.get(i);
            }
        }
        if (minDis == INFINITE){
            return null;
        }else{
            return temp;
        }
    }

    public void in_duong(Graph g, Vertex v, Vertex start){
        if (v != start){
            in_duong(g, g.getVertex(v.getPre()), start);
            System.out.print("->"+ v.getPre());
        }
    }

    @Override
    public void executeAlgorithm (Graph testGraph){
        listPseudoStep = new DJ_PseudoStep();
        ((DJ_PseudoStep) listPseudoStep).initStep();
        dijkstraProgram(testGraph);
    }

}
