package com.group11.topic9.algorithm;

import com.group11.topic9.action.DetailedStep;
import com.group11.topic9.action.PseudoStep;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;
import com.group11.topic9.state.State;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra extends  Algorithm{
    public static final float INFINITE = 1000;

    public void dijkstraProgram(Graph g){
        int count = 0;
        //co canh di toi : ORANGE
        //dang xet      : LIGHTGREEN
        //da xet       : LIGHTBLUE
        ArrayList<Vertex> currentVertex;
        ArrayList<Edge> currentEdge ;
        ArrayList<Paint> vertexPaint;
        ArrayList<Paint> edgePaint ;

        ArrayList<Vertex> checkVertex = new ArrayList<Vertex>();

        Vertex temp = new Vertex();

        float d1 = 0;
        float d0 =0;
        float d1bd = 0;
        float sumD0D1=0;
        float weid0d1=0;

        int startID= 0;
        Vertex Start = new Vertex();
        Start = g.getVerFromID(startID);

        for (int i=0; i<g.getListVertex().size(); i++){
            g.getListVertex().get(i).setDis(INFINITE);
            if (g.getListVertex().get(i) == Start){
                g.getListVertex().get(i).setPre(0);
                g.getListVertex().get(i).setDis(0.0f);
            }
        }
        checkVertex.addAll(g.getListVertex());

        //init all of Vertex
        Start.setDis(0.0f);
        Start.setPre(0);
        listState.add(new State());
        currentVertex = new ArrayList<>();        //TODO: STATE SOURCE VERTEX
        vertexPaint = new ArrayList<>();
        currentEdge = new ArrayList<>();
        edgePaint = new ArrayList<>();

        currentVertex.add(g.getListVertex().get(0));
        vertexPaint.add(Color.ORANGE);
        listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
//
        pseudoStepOrder.add(4);
        listDetailedStep.add(new DetailedStep(" "));

        pseudoStepOrder.add(0);                 //TODO: initSSSP & pre-populate PQ
        listDetailedStep.add(new DetailedStep("Vertex "+startID + " is the source vertex\n"
                +"Set p[v]=0; d[v] = INF, but d["+startID+"]=0 " +
                "\nPQ={  ("+checkVertex.get(1).getId()+", "+checkVertex.get(1).getPre()+"-"+checkVertex.get(1).getDis()+") , ("+
                        checkVertex.get(2).getId()+", "+checkVertex.get(2).getPre()+"-"+checkVertex.get(2).getDis()+") , ("+
                        checkVertex.get(3).getId()+", "+checkVertex.get(3).getPre()+"-"+checkVertex.get(3).getDis()+")...}"));



        System.out.println("Check vertex");
        for (int i=0; i<checkVertex.size(); i++){
            checkVertex.get(i).showVerInfor();
        }
        System.out.println("-----------");



        if (g.numOutdegree(Start) == 0){
            System.out.println("Khong co dinh ke cua "+Start.getId());
        }else {
            while (checkVertex.size() != 0) {
                count=0;
                for (int t=0;t<checkVertex.size(); t++){
                    if (checkVertex.get(t).getDis() == INFINITE){
                        count++;
                    }
                }
                if (count == checkVertex.size()){
                    for (int j = 0; j < checkVertex.size(); j++) {
                        if (checkVertex.get(j) != null) {
                            pseudoStepOrder.add(2);     //TODO:for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
                            listDetailedStep.add(new DetailedStep("d[" + checkVertex.get(j).getId() + "]= "
                                    + checkVertex.get(j).getDis() + " is visted and removed"));

                            currentVertex = new ArrayList<>();          //TODO: STATE VISTITED VERTEX
                            vertexPaint = new ArrayList<>();
                            currentEdge = new ArrayList<>();
                            edgePaint = new ArrayList<>();

                            currentVertex.add(checkVertex.get(j));
                            vertexPaint.add(Color.SKYBLUE);
                            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));

                            checkVertex.remove(j);
                        }
                    }
                }
//                count++;
                System.out.println("count = "+count);

                boolean check = true;


                for (int i = 0; i < g.getListVertex().size(); i++) {
//
//                    listState.add(new State());
                    temp = this.extractMin(checkVertex);

                    for (int l=0; l < g.getListVertex().size(); l++){
                        if (g.getListVertex().get(l).getId() == temp.getId()){
                            currentVertex = new ArrayList<>();                      //TODO: STATE EXTRACTMIN
                            vertexPaint = new ArrayList<>();
                            currentEdge = new ArrayList<>();
                            edgePaint = new ArrayList<>();

                            currentVertex.add(g.getListVertex().get(l));
                            vertexPaint.add(Color.LIGHTGREEN);
                            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
                        }
                    }

                    pseudoStepOrder.add(1);         //TODO: while !PQ.empty() //PQ is a Priority Queue
                    if (checkVertex.size()>=2 ){
                        listDetailedStep.add(new DetailedStep("The current PQ={ ("
                                +checkVertex.get(0).getId()+","+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+") , ("+
                                +checkVertex.get(1).getId()+","+checkVertex.get(1).getPre()+"-"+checkVertex.get(1).getDis()+")...} \n"
                                + "Exploring neighbor of u = "+temp.getId()+", d["+temp.getId()+"]="+ temp.getDis()));
                    }


                    int edgeProcess = 0;
                    for (int j = 0; j < checkVertex.size(); j++) {
                        if (g.hasEdgeFrom(temp, checkVertex.get(j)) &&  checkVertex.get(j) != temp) {
                            edgeProcess++;

                            currentVertex = new ArrayList<>();          //TODO:STATE NEGIBOR OF CHECKING VERTEX
                            vertexPaint   = new ArrayList<>();
                            currentEdge   = new ArrayList<>();
                            edgePaint     = new ArrayList<>();

                            currentVertex.add(checkVertex.get(j));
                            currentEdge.add(g.getEdgeByVer(temp, checkVertex.get(j)));
                            vertexPaint.add(Color.ORANGE);
                            edgePaint.add(Color.ORANGE);
                            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));

                            d0 = temp.getDis();
                            weid0d1 = g.getWeight(temp, checkVertex.get(j));
                            d1bd = checkVertex.get(j).getDis();
                            sumD0D1 = d0+g.getWeight(temp, checkVertex.get(j));

                            Relaxing(g, temp, checkVertex.get(j));

                            d1=checkVertex.get(j).getDis();
//                            if (sumD0D1 == d1){         //neu d[0]+w(0,1) == d[1]after --> co relax
//                                currentVertex = new ArrayList<>();          //TODO:STATE NEGIBOR OF CHECKING VERTEX
//                                vertexPaint = new ArrayList<>();
//                                currentEdge = new ArrayList<>();
//                                edgePaint = new ArrayList<>();
//
//                                currentVertex.add(checkVertex.get(j));
//                                currentEdge.add(g.getEdgeByVer(temp, checkVertex.get(j)));
//                                vertexPaint.add(Color.ORANGE);
//                                edgePaint.add(Color.ORANGE);
//                                listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
//                            }else{
//                                currentVertex = new ArrayList<>();          //TODO:STATE NEGIBOR OF CHECKING VERTEX
//                                vertexPaint = new ArrayList<>();
//                                currentEdge = new ArrayList<>();
//                                edgePaint = new ArrayList<>();
//
//                                currentVertex.add(checkVertex.get(j));
////                                currentEdge.add(g.getEdgeByVer(temp, checkVertex.get(j)));
//                                vertexPaint.add(Color.ORANGE);
////                                edgePaint.add(Color.GRAY);
//                                listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
//                            }

                            pseudoStepOrder.add(2);              //for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
                            if (checkVertex.size()>=2){
                                    if (sumD0D1 < d1bd){         //d0+ w(0, 1) < 1000

                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+checkVertex.get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+checkVertex.get(j).getId()+"] = "+"d["+temp.getId()+"]+w("+temp.getId()+","+checkVertex.get(j).getId()+")"+"="
                                                +d0+"+"+weid0d1+"="+d1
                                                +"\nPQ={("+checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+"), ("+
                                                +checkVertex.get(j).getId()+", "+checkVertex.get(j).getPre()+"-"+checkVertex.get(j).getDis()+")...}"));
                                    }else {
                                            //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+checkVertex.get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+temp.getId()+"]+w("+temp.getId()+","+checkVertex.get(j).getId()+")"+">"+"d["+checkVertex.get(j).getId()+"] i.e. "
                                                +d0+"+"+weid0d1+">"+d1bd +"\n, so there is no change"
                                                +"\nPQ={("+checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+"), ("+
                                                +checkVertex.get(j).getId()+", "+checkVertex.get(j).getPre()+"-"+checkVertex.get(j).getDis()+")...}"));
                                    }
                            }else if (checkVertex.size() < 2) {
                                    if (sumD0D1 < d1bd) {
                                        //co thay doi < 2 + PQ
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + checkVertex.get(j).getId() + ") #edge process = " + edgeProcess
                                            + "\nd[" + checkVertex.get(j).getId() + "] = " + "d[" + temp.getId() + "]+w(" + temp.getId() + "," + checkVertex.get(j).getId() + ")" + "="
                                            + d0 + "+" + weid0d1 + "=" + d1
                                            + "\nPQ={ (" + checkVertex.get(j).getId() + ", " + checkVertex.get(j).getPre() + "-" + checkVertex.get(j).getDis() + ")...}"));
                                    } else{
                                           //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + checkVertex.get(j).getId() + ") #edge process = " + edgeProcess
                                                + "\nd[" + temp.getId() + "]+w(" + temp.getId() + "," + checkVertex.get(j).getId() + ")" + ">" + "d[" + checkVertex.get(j).getId() + "] i.e. "
                                                + d0 + "+" + weid0d1 + ">" + d1bd + ", so there is no change"
                                                + "\nPQ={ (" + checkVertex.get(j).getId() + ", " + checkVertex.get(j).getPre() + "-" + checkVertex.get(j).getDis() + ")...}"));

                                    }
                            }
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

                                currentVertex = new ArrayList<>();          //TODO: STATE VISTITED VERTEX
                                vertexPaint   = new ArrayList<>();
                                currentEdge   = new ArrayList<>();
                                edgePaint     = new ArrayList<>();

                                currentVertex.add(checkVertex.get(j));
                                vertexPaint.add(Color.SKYBLUE);
                                listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));

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
            g.showGraph();
            System.out.println("------------------------------------------------");
            System.out.println("Danh sach cac dinh sau Dijkstra");
            for (int i = 0; i < g.getListVertex().size(); i++) {
                System.out.print("(" + g.getListVertex().get(i).getId() + ","
                        + g.getListVertex().get(i).getDis() + "): ");
                if (g.getListVertex().get(i).getDis() == INFINITE) {
                    System.out.println("Khong co duong di tu " + Start.getId() + " den "
                            + g.getListVertex().get(i).getId());
                } else {
                    this.printPath(g, g.getListVertex().get(i), Start);
                    System.out.println("->" + g.getListVertex().get(i).getId());
                }
            }



        }
    }

    public void Relaxing (Graph g, Vertex ver1, Vertex ver2){               //a:ver1----> b:ver2

        if ((ver1.getDis() +g.getWeight(ver1, ver2)) < ver2.getDis()){    //d[a]+ab < d[b]

            float newDis = ver1.getDis()+ g.getWeight(ver1, ver2);
            ver2.setDis(newDis);                                       //d[b] ::= d[a]+ab
            ver2.setPre(ver1.getId());

        }
    }


    public Vertex extractMin (ArrayList<Vertex> checkVertex){

        Vertex temp = new Vertex();
        float minDis =  INFINITE;

        for (int i=0; i < checkVertex.size(); i++) {

            if (checkVertex.get(i).getDis() < minDis) {
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
            printPath(g, g.getVerFromID(v.getPre()), start);
            System.out.print("->"+ v.getPre());
        }
    }


    @Override
    public void executeAlgorithm (Graph g){
        listState = new ArrayList<>();
        listPseudoStep = new ArrayList<>();
        pseudoStepOrder = new ArrayList<>();

        listPseudoStep.add (new PseudoStep("initSSSP & pre-populate PQ"));                      //0
        listPseudoStep.add (new PseudoStep("while !PQ.empty() //PQ is a Priority Queue "));     //1
        listPseudoStep.add (new PseudoStep("for each neighbor v of u = PQ.front(), PQ.pop()\n"
                                                    +"relax(u, v, w(u, v)) + update PQ")); //2
        listPseudoStep.add (new PseudoStep("End program"));     //3
        listPseudoStep.add(new PseudoStep(" "));                //4 step Nope
        listDetailedStep = new ArrayList<>();

//        listState.add(new State());
        dijkstraProgram(g);
    }


    @Override
    public void run() {
//        stepPointer = 0;
//        Scanner sc = new Scanner(System.in);
//        int a;
//
//        while (true){
//            System.out.println("<===================================================>");
////            System.out.println("-----------------------------");
////            System.out.println("Detail                       ");
//            System.out.println(listDetailedStep.get(stepPointer).getContent());
//
//            System.out.println(" ");
////            System.out.println("Pseudo code                  ");
//            for (int j=0; j< listPseudoStep.size(); j++){
//                if (pseudoStepOrder.get(stepPointer) == j){
//                    System.out.println("==>"+listPseudoStep.get(j).getContent() );
//                }else {
//                    System.out.println("   "+listPseudoStep.get(j).getContent());
//                }
//            }
//            System.out.println("<===================================================>");
//            a = sc.nextInt();
//
//            if (a == 1 && stepPointer < pseudoStepOrder.size() - 1){
////                System.out.println("next step");
//                stepPointer++;
//            }else if (a ==2 && stepPointer > 0){
//                System.out.println("back one step");
//                stepPointer--;
//            }else if (a == 0){
//                System.out.println("Break");
//                break;
//            }
//        }
//        System.out.println("End run");
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
            if (pseudoStepOrder.get(stepPointer) == j && listPseudoStep.get(j).getContent()!=" "){
                str = str + "\n==> "+listPseudoStep.get(j).getContent() ;
            }else {
                str = str + "\n    "+listPseudoStep.get(j).getContent() ;
            }
        }
        return str;
    }
}

