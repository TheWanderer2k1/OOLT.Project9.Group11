package com.group11.topic9.algorithm.Dijkstra;

import com.group11.topic9.step.DetailedStep;
import com.group11.topic9.step.PseudoStep;
import com.group11.topic9.algorithm.Algorithm;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;
import com.group11.topic9.state.State;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class Dijkstra extends Algorithm {
    public static final float INFINITE = 1000;

    public void dijkstraProgram(Graph g, int startID){
        int count;
        //co canh di toi : ORANGE
        //dang xet      : LIGHTGREEN
        //da xet       : LIGHTBLUE
        ArrayList<Vertex> currentVertex;
        ArrayList<Edge> currentEdge ;
        ArrayList<Paint> vertexPaint;
        ArrayList<Paint> edgePaint ;

        ArrayList<Vertex> checkVertex = new ArrayList<Vertex>();

        Vertex temp ;

        float d1 = 0;
        float d0 =0;
        float d1bd = 0;
        float sumD0D1=0;
        float weid0d1=0;

        //int startID= 0;
        Vertex Start ;
        Start = g.getVerFromID(startID);

        for (int i=0; i<g.getListVertex().size(); i++){
            g.getListVertex().get(i).setDis(INFINITE);
            if (g.getListVertex().get(i) == Start){
                g.getListVertex().get(i).setPre(0);
                g.getListVertex().get(i).setDis(0.0f);
            }
        }
        checkVertex.addAll(g.getListVertex());

//        //TODO:init all of Vertex
//        Start.setDis(0.0f);
//        Start.setPre(0);

        currentVertex = new ArrayList<>();        //TODO: STATE SOURCE VERTEX
        vertexPaint = new ArrayList<>();
        currentEdge = new ArrayList<>();
        edgePaint = new ArrayList<>();
        currentVertex.add(g.getListVertex().get(0));
        vertexPaint.add(Color.ORANGE);
        listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
        pseudoStepOrder.add(4);
        listDetailedStep.add(new DetailedStep(" "));


        pseudoStepOrder.add(0);                 //TODO: initSSSP & pre-populate PQ
        if (checkVertex.size() == 1){           //khi do thi chi co 1 Vertex
            listDetailedStep.add(new DetailedStep("Vertex "+startID+" is the source vertex\n"
            +"Set p[v]=0; d[v]=INF, but d["+startID+"]"
            +"PQ= { "+checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+")"));
        }else {
            listDetailedStep.add(new DetailedStep("Vertex " + startID + " is the source vertex\n"
                    + "Set p[v]=0; d[v] = INF, but d[" + startID + "]=0 " +
                    "\nPQ={  ("+
                        checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+") , ("+
                        checkVertex.get(1).getId()+", "+checkVertex.get(1).getPre()+"-"+checkVertex.get(1).getDis()+")    }"));
            //todo: can them truong hop do thi co 1 dinh
        }



        if (g.numOutdegree(Start) == 0){
            System.out.println("Khong co dinh ke cua "+startID);
            temp = this.extractMin(checkVertex);
            currentVertex = new ArrayList<>();
            vertexPaint = new ArrayList<>();
            currentEdge = new ArrayList<>();
            edgePaint = new ArrayList<>();

            currentVertex.add(g.getListVertex().get(startID));
            currentEdge.add(new Edge());
            vertexPaint.add(Color.LIGHTGREEN);
            edgePaint.add(Color.BLACK);
            pseudoStepOrder.add(0);                 //TODO: initSSSP & pre-populate PQ
            listDetailedStep.add(new DetailedStep("Vertex "+startID+" is the source vertex\n"
                    +"Set p[v]=0; d[v]=INF, but d["+startID+"]"
                    +"PQ= { "+checkVertex.get(0).getId()+", "+checkVertex.get(0).getPre()+"-"+checkVertex.get(0).getDis()+")"));


            currentVertex.add(g.getListVertex().get(startID));
            currentEdge.add(new Edge());
            vertexPaint.add(Color.LIGHTGREEN);
            edgePaint.add(Color.BLACK);
            pseudoStepOrder.add(1);                                         //TODO: while !PQ.empty() //PQ is a Priority Queue
            listDetailedStep.add(new DetailedStep("The current PQ={ ("
//                                Math.ceil(temp.getDis()*100.0)/100.0
                    +checkVertex.get(0).getId()+","+checkVertex.get(0).getPre()+"-"+Math.ceil(checkVertex.get(0).getDis()*100.0)/100.0+") \n"
                    + "Exploring neighbor of u = "+temp.getId()+", d["+temp.getId()+"]="+ Math.ceil(temp.getDis()*100.0)/100.0));


            currentVertex.add(g.getListVertex().get(startID));
            currentEdge.add(new Edge());
            vertexPaint.add(Color.ORANGE);
            edgePaint.add(Color.BLACK);
            pseudoStepOrder.add(2);     //TODO:for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
            listDetailedStep.add(new DetailedStep("d["+checkVertex.get(startID).getId()+"]= " +checkVertex.get(startID).getDis()+" is visted and removed"));

            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));


        }else {
            while (checkVertex.size() != 0) {
                boolean check = true;
                count = 0;
                for (int t=0;t<checkVertex.size(); t++){            //khoi tao vo cung
                    if (checkVertex.get(t).getDis() == INFINITE){
                        count++;
                    }
                }

                for (int i = 0; i < g.getListVertex().size(); i++) {                  //todo : find extractMin
                    temp = this.extractMin(checkVertex);
                    currentVertex = new ArrayList<>();
                    vertexPaint = new ArrayList<>();
                    currentEdge = new ArrayList<>();
                    edgePaint = new ArrayList<>();

                    if (temp.getId() == startID){
                        currentVertex.add(temp);
                        currentEdge.add(new Edge());
                        vertexPaint.add(Color.LIGHTGREEN);
                        edgePaint.add(Color.BLACK);
                    }
                    for (int l=0; l < g.getListVertex().size(); l++){
                        if (g.getListVertex().get(l).getId() == temp.getId()){        // todo: to mau LIGHTGREEN cho extractMin
                            currentVertex.add(g.getListVertex().get(l));
                            currentEdge.add(new Edge());
                            vertexPaint.add(Color.LIGHTGREEN);
                            edgePaint.add(Color.BLACK);
                        }
                    }
                    pseudoStepOrder.add(1);                                         //TODO: while !PQ.empty() //PQ is a Priority Queue
                    if (checkVertex.size()>=2 ){
                        listDetailedStep.add(new DetailedStep("The current PQ={ ("
//                                Math.ceil(temp.getDis()*100.0)/100.0
                                +checkVertex.get(0).getId()+","+checkVertex.get(0).getPre()+"-"+Math.ceil(checkVertex.get(0).getDis()*100.0)/100.0+") , ("
                                +checkVertex.get(1).getId()+","+checkVertex.get(1).getPre()+"-"+Math.ceil(checkVertex.get(1).getDis()*100.0)/100.0+")...} \n"
                                + "Exploring neighbor of u = "+temp.getId()+", d["+temp.getId()+"]="+ Math.ceil(temp.getDis()*100.0)/100.0));
                    }else {
                        listDetailedStep.add(new DetailedStep("The current PQ={ ("
//                                Math.ceil(temp.getDis()*100.0)/100.0
                                +checkVertex.get(0).getId()+","+checkVertex.get(0).getPre()+"-"+Math.ceil(checkVertex.get(0).getDis()*100.0)/100.0+") \n"
                                + "Exploring neighbor of u = "+temp.getId()+", d["+temp.getId()+"]="+ Math.ceil(temp.getDis()*100.0)/100.0));
                    }



                    int edgeProcess = 0;
                    for (int j = 0; j < g.getListVertex().size(); j++) {
                        //TODO: check neighborhood
                        if (g.hasEdgeFrom(temp, g.getListVertex().get(j)) &&  g.getListVertex().get(j) != temp) {
//                            edgeProcess++;

                            //vd : extractMin la A (id:0) ---> B (id:1)  => j=B(id)=1
                            if (existInArr(g.getListVertex().get(j), checkVertex)){             //neu B tontai trong checkVertex (B chua bi loai)


                                d0 = temp.getDis();                                                 //d0 hien tai
                                weid0d1 = g.getWeight(temp, g.getListVertex().get(j));
                                d1bd = g.getListVertex().get(j).getDis();                           //d1 hien tai
                                sumD0D1 = d0+g.getWeight(temp, g.getListVertex().get(j));           //d[0] +w(0, 1)

                                if (sumD0D1 > d1bd){
                                    currentVertex.add(g.getListVertex().get(j));
                                    currentEdge.add(g.getEdgeByVer(temp, g.getListVertex().get(j)));
                                    vertexPaint.add(Color.LIGHTBLUE);
                                    edgePaint.add(Color.LIGHTGREY);
                                }else {

                                    currentVertex.add(g.getListVertex().get(j));
                                    currentEdge.add(g.getEdgeByVer(temp, g.getListVertex().get(j)));
                                    vertexPaint.add(Color.LIGHTBLUE);
                                    edgePaint.add(Color.ORANGE);
                                }
                                Relaxing(g, temp, g.getListVertex().get(j));
                                d1=g.getListVertex().get(j).getDis();



                                pseudoStepOrder.add(2);              //for each neighbor v of u = PQ.front(), PQ.pop() , relax and remove
                                if (checkVertex.size()>=2){
                                    if (sumD0D1 < d1bd){         //d0+ w(0, 1) < 1000
                                        //neighbor co nam trong Checkvertex va da reset

                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+g.getListVertex().get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+g.getListVertex().get(j).getId()+"] = "+"d["+temp.getId()+"]+w("+temp.getId()+","+g.getListVertex().get(j).getId()+")"+"="
//                                                +d0+"+"+weid0d1+"="+d1
                                                +Math.ceil(d0*100.0)/100.0+"+"+Math.ceil(weid0d1*100.0)/100.0+"="+Math.ceil(d1*100.0)/100.0
                                                +"\nPQ={("+g.getListVertex().get(0).getId()+", "+g.getListVertex().get(0).getPre()+"-"+g.getListVertex().get(0).getDis()+"), ("+
                                                +g.getListVertex().get(j).getId()+", "+g.getListVertex().get(j).getPre()+"-"+Math.ceil(g.getListVertex().get(j).getDis()*100.0)/100.0+")...}"));
//                                        Math.ceil(g.getListVertex().get(j).getDis()*100.0)/100.0
                                    }else {
                                        //neighbor nam trong CheckVertex nhung KHONG co Relax
                                        //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+g.getListVertex().get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+temp.getId()+"]+w("+temp.getId()+","+g.getListVertex().get(j).getId()+")"+">"+"d["+g.getListVertex().get(j).getId()+"] i.e. "
                                                +d0+"+"+weid0d1+">"+d1bd +"\n, so there is no change 1"
                                                +"\nPQ={("+checkVertex.get(0).getId()+", "+g.getListVertex().get(0).getPre()+"-"+g.getListVertex().get(0).getDis()+"), ("+
                                                +g.getListVertex().get(j).getId()+", "+g.getListVertex().get(j).getPre()+"-"+g.getListVertex().get(j).getDis()+")...}"));
                                    }
                                }else if (g.getListVertex().size() < 2) {
                                    if (sumD0D1 < d1bd) {
                                        //co thay doi < 2 + PQ
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + g.getListVertex().get(j).getId() + ") #edge process = " + edgeProcess
                                                + "\nd[" + g.getListVertex().get(j).getId() + "] = " + "d[" + temp.getId() + "]+w(" + temp.getId() + "," + g.getListVertex().get(j).getId() + ")" + "="
                                                + d0 + "+" + weid0d1 + "=" + d1
                                                + "\nPQ={ (" + g.getListVertex().get(j).getId() + ", " + g.getListVertex().get(j).getPre() + "-" + g.getListVertex().get(j).getDis() + ")...}"));
                                    } else{
                                        //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + g.getListVertex().get(j).getId() + ") #edge process = " + edgeProcess
                                                + "\nd[" + temp.getId() + "]+w(" + temp.getId() + "," + g.getListVertex().get(j).getId() + ")" + ">" + "d[" + g.getListVertex().get(j).getId() + "] i.e. "
                                                + d0 + "+" + weid0d1 + ">" + d1bd + ", so there is no change 2"
                                                + "\nPQ={ (" + g.getListVertex().get(j).getId() + ", " + g.getListVertex().get(j).getPre() + "-" + g.getListVertex().get(j).getDis() + ") }"));

                                    }
                                }
                            }else{
                                currentVertex.add(g.getListVertex().get(j));
                                currentEdge.add(g.getEdgeByVer(temp, g.getListVertex().get(j)));
                                vertexPaint.add(Color.ORANGE);
                                edgePaint.add(Color.LIGHTGREY);
                                //todo: noi den dinh ma da duoc xoa khoi checkVertex
                                d0 = temp.getDis();
                                weid0d1 = g.getWeight(temp, g.getListVertex().get(j));
                                d1bd = g.getListVertex().get(j).getDis();
                                sumD0D1 = d0+g.getWeight(temp, g.getListVertex().get(j));

//                                Relaxing(g, temp, g.getListVertex().get(j));
                                //sumD0D1 la d0 + w(0,1) luon > d1
                                d1=g.getListVertex().get(j).getDis();

                                if (checkVertex.size()>=2){
                                    if (sumD0D1 < d1bd){         //d0+ w(0, 1) < 1000

                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+g.getListVertex().get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+g.getListVertex().get(j).getId()+"] = "+"d["+temp.getId()+"]+w("+temp.getId()+","+g.getListVertex().get(j).getId()+")"+"="
                                                +d0+"+"+weid0d1+"="+sumD0D1
                                                +"\nPQ={("+g.getListVertex().get(0).getId()+", "+g.getListVertex().get(0).getPre()+"-"+g.getListVertex().get(0).getDis()+"), ("
                                                +g.getListVertex().get(j).getId()+", "+g.getListVertex().get(j).getPre()+"-"+Math.ceil(g.getListVertex().get(j).getDis()*100.0)/100.0+")...}"));
//                                        Math.ceil(g.getListVertex().get(j).getDis()*100.0)/100.0
                                    }else {
                                        //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax ("+temp.getId()+", "+g.getListVertex().get(j).getId()+") #edge process = "+edgeProcess
                                                +"\nd["+temp.getId()+"]+w("+temp.getId()+","+g.getListVertex().get(j).getId()+")"+">"+"d["+g.getListVertex().get(j).getId()+"] i.e. "
                                                +d0+"+"+weid0d1+">"+d1bd +"\n, so there is no change 3"
                                                +"\nPQ={("+checkVertex.get(0).getId()+", "+g.getListVertex().get(0).getPre()+"-"+g.getListVertex().get(0).getDis()+")...}"));
                                    }
                                }else if (g.getListVertex().size() < 2) {
                                    if (sumD0D1 < d1bd) {
                                        //co thay doi < 2 + PQ
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + g.getListVertex().get(j).getId() + ") #edge process = " + edgeProcess
                                                + "\nd[" + g.getListVertex().get(j).getId() + "] = " + "d[" + temp.getId() + "]+w(" + temp.getId() + "," + g.getListVertex().get(j).getId() + ")" + "="
                                                + d0 + "+" + weid0d1 + "=" + sumD0D1
                                                + "\nPQ={ (" + g.getListVertex().get(j).getId() + ", " + g.getListVertex().get(j).getPre() + "-" + g.getListVertex().get(j).getDis() + ")...}"));
                                    } else{
                                        //d[3]+w(3,2) > d[2], i.e. 1+2 > 3, so there is no change.
                                        listDetailedStep.add(new DetailedStep("Relax (" + temp.getId() + ", " + g.getListVertex().get(j).getId() + ") #edge process = " + edgeProcess
                                                + "\nd[" + temp.getId() + "]+w(" + temp.getId() + "," + g.getListVertex().get(j).getId() + ")" + ">" + "d[" + g.getListVertex().get(j).getId() + "] i.e. "
                                                + d0 + "+" + weid0d1 + ">" + d1bd + ", so there is no change 4"
                                                + "\nPQ={ (" + g.getListVertex().get(j).getId() + ", " + g.getListVertex().get(j).getPre() + "-" + g.getListVertex().get(j).getDis() + ") }"));

                                    }
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

                                currentVertex.add(checkVertex.get(j));
                                currentEdge.add(new Edge());
                                vertexPaint.add(Color.ORANGE);
                                edgePaint.add(Color.BLACK);

                                checkVertex.remove(j);
                            }
                        }
                    }

                    listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));

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
                    System.out.println("Khong co duong di tu " + startID + " den "
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


    public boolean existInArr( Vertex ver1, ArrayList<Vertex> checkVertex){
        for (int i=0; i< checkVertex.size(); i++){
            if (checkVertex.get(i) == ver1){
                return true;
            }
        }
        return false;
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
            return checkVertex.get(0);
        }else{
            return temp;
        }
    }


    public String printPath(Graph g, Vertex tmp, Vertex start){
        if (tmp != start){
            printPath(g, g.getVerFromID(tmp.getPre()), start);
            return "->" + tmp.getPre();
        }
        return "->" + tmp.getPre();
    }
    @Override
    public void executeAlgorithm (Graph g, int startID){
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

        dijkstraProgram(g, startID);
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

