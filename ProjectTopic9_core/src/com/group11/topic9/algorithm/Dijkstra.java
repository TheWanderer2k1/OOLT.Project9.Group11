package com.group11.topic9.algorithm;

import com.group11.topic9.action.DetailedStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;

import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra extends  Algorithm{
    public static final float INFINITE = 100;


//    PseudoStep pStep = new PseudoStep();

    public void dijkstraProgram(Graph testGraph){
        ArrayList<Vertex> checkVertex = new ArrayList<Vertex>();

        System.out.print("Nhap id dinh Start: ");
        Scanner sc1 = new Scanner(System.in);
        int startID = sc1.nextInt();

        Vertex Start = new Vertex();
        Start = testGraph.getVertex(startID);
        for (int i=0; i<testGraph.getListVertex().size(); i++){
            if (testGraph.getListVertex().get(i) == Start){
                testGraph.getListVertex().get(i).setPre(0);
                testGraph.getListVertex().get(i).setDis(0.0f);
            }
        }
        pseudoStepOrder.add(0);                 //TODO: initSSSP & pre-populate PQ

        //init all of Vertex
        for (int i = 0; i < testGraph.getListVertex().size(); i++) {
            testGraph.getListVertex().get(i).setDis(INFINITE);
        }
        Start.setDis(0.0f);
        Start.setPre(0);
        checkVertex.addAll(testGraph.getListVertex());

        listDetailedStep.add(new DetailedStep("Vertex "+startID + " is the source vertex\n"
                +"Set p[v]=0; d[v] = INF, but d["+startID+"]=0 " +
                "PQ={("+
                checkVertex.get(1).getId()+", "+checkVertex.get(1).getPre()+"-"+checkVertex.get(1).getDis()+"), ("+
                checkVertex.get(2).getId()+", "+checkVertex.get(2).getPre()+"-"+checkVertex.get(2).getDis()+"), ("+
                checkVertex.get(3).getId()+", "+checkVertex.get(3).getPre()+"-"+checkVertex.get(3).getDis()+")...}"));


        if (testGraph.numOutdegree(Start) == 0){
            System.out.println("Khong co dinh ke cua "+Start.getId());
        }else {
            while (checkVertex.size() != 0) {
                pseudoStepOrder.add(1);         //TODO: while !PQ.empty() //PQ is a Priority Queue
                boolean check = true;

                for (int i = 0; i < testGraph.getListVertex().size(); i++) {
                    pseudoStepOrder.add(2);     //TODO:for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
                    Vertex temp = new Vertex();
                    temp = this.extractMin(checkVertex);
                    if (checkVertex.size()>=2 ){
                        listDetailedStep.add(new DetailedStep("The current PQ={("
                                +checkVertex.get(0).getId()+","+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+"), ("+
                                +checkVertex.get(1).getId()+","+checkVertex.get(1).getPre()+"-"+checkVertex.get(1).getDis()+")...} \n"
                                + "Exploring neighbor of u = "+temp.getId()+", d["+temp.getId()+"]="+ temp.getDis()));
                    }
//
                    //duyet tat cac ke cua dinh do
                    int edgeProcess = 0;
                    for (int j = 0; j < checkVertex.size(); j++) {
                        //neu la dinh ke va chua duoc tham
                        if (testGraph.hasEdgeFrom(temp, checkVertex.get(j)) && checkVertex.get(j) != temp) {

                            edgeProcess++;
                            this.Relaxing1(testGraph, temp, checkVertex.get(j));
                            if (checkVertex.size()>=3)
                            listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+checkVertex.get(j).getId()+") #edge process = "+edgeProcess
                                    +"\nPQ={("+checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+"), ("+
                                    +checkVertex.get(j).getId()+", "+checkVertex.get(j).getPre()+"-"+checkVertex.get(j).getDis()+")...}"));
                        }
                        edgeProcess=0;
                    }

                    //remove extractMin
                    if (temp != null) {
                        for (int j = 0; j < checkVertex.size(); j++) {
                            if (checkVertex.get(j).getId() == temp.getId() && checkVertex.get(j) != null) {
                                pseudoStepOrder.add(2);     //TODO:for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
                                listDetailedStep.add(new DetailedStep("d["+checkVertex.get(j).getId()+"]= "
                                        +checkVertex.get(j).getDis()+" is visted and removed"));
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
            if (checkVertex.size() <= 0){
                listDetailedStep.add(new DetailedStep("This is SSSP spaning from vertex "+startID));
                pseudoStepOrder.add(3);
            }
            System.out.println("------------------------------------------------");
            System.out.println("Danh sach cac dinh sau Dijkstra");
            for (int i = 0; i < testGraph.getListVertex().size(); i++) {
                System.out.print("(" + testGraph.getListVertex().get(i).getId() + ","
                        + testGraph.getListVertex().get(i).getDis() + "): ");
                if (testGraph.getListVertex().get(i).getDis() == INFINITE) {
                    System.out.println("Khong co duong di tu " + Start.getId() + " den "
                            + testGraph.getListVertex().get(i).getId());
                } else {
                    this.printPath(testGraph, testGraph.getListVertex().get(i), Start);
                    System.out.println("->" + testGraph.getListVertex().get(i).getId());
                }
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

    public void printPath(Graph g, Vertex v, Vertex start){
        if (v != start){
            printPath(g, g.getVertex(v.getPre()), start);
            System.out.print("->"+ v.getPre());
        }
    }

    @Override
    public void executeAlgorithm (Graph testGraph){
        listPseudoStep = new ArrayList<>();
        pseudoStepOrder = new ArrayList<>();

        listPseudoStep.add (new PseudoStep("initSSSP & pre-populate PQ"));                      //0
        listPseudoStep.add (new PseudoStep("while !PQ.empty() //PQ is a Priority Queue "));     //1
        listPseudoStep.add (new PseudoStep("    for each neighbor v of u = PQ.front(), PQ.pop()\n"
                                                    +"           relax(u, v, w(u, v)) + update PQ")); //2
        listPseudoStep.add (new PseudoStep("End program"));     //3

        listDetailedStep = new ArrayList<>();
        dijkstraProgram(testGraph);
    }

    @Override
    public void run() {
        stepPointer = 0;
        Scanner sc = new Scanner(System.in);
        int a;

        while (true){
            System.out.println("<===================================================>");
//            System.out.println("-----------------------------");
//            System.out.println("Detail                       ");
            System.out.println(listDetailedStep.get(stepPointer).getContent());

            System.out.println(" ");
//            System.out.println("Pseudo code                  ");
            for (int j=0; j< listPseudoStep.size(); j++){
                if (pseudoStepOrder.get(stepPointer) == j){
                    System.out.println("==>"+listPseudoStep.get(j).getContent() );
                }else {
                    System.out.println("   "+listPseudoStep.get(j).getContent());
                }
            }
            System.out.println("<===================================================>");
            a = sc.nextInt();

            if (a == 1 && stepPointer < pseudoStepOrder.size() - 1){
//                System.out.println("next step");
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

}

