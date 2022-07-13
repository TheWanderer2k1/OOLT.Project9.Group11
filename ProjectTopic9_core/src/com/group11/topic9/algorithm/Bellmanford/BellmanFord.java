package com.group11.topic9.algorithm.Bellmanford;

import com.group11.topic9.algorithm.Algorithm;
import com.group11.topic9.state.State;
import com.group11.topic9.step.DetailedStep;
import com.group11.topic9.step.PseudoStep;
import com.group11.topic9.graph.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class BellmanFord extends Algorithm {
    public static final int INFINITE = 1000;
    public void bellmanfordProgram(Graph g) {

        //co canh di toi : ORANGE
        //dang xet      : LIGHTGREEN
        //da xet       : LIGHTBLUE
        ArrayList<Vertex> currentVertex;
        ArrayList<Edge> currentEdge ;
        ArrayList<Paint> vertexPaint;
        ArrayList<Paint> edgePaint ;

        Vertex temp = new Vertex();
        int startID = 0;
        Vertex Start = new Vertex();
        Start = g.getVerFromID(startID);

        // Step 1: Initialize distances from src to all other
        // vertices as INFINITE
        for(int i = 0; i< g.getListVertex().size();i++ ) {
            g.getListVertex().get(i).setDis(INFINITE);
        }
        Start.setDis(0.0f);
        Start.setPre(0);
        listState.add(new State());
        currentVertex = new ArrayList<>();        //TODO: STATE SOURCE VERTEX
        vertexPaint = new ArrayList<>();
        currentEdge = new ArrayList<>();
        edgePaint = new ArrayList<>();
        currentVertex.add(g.getListVertex().get(Start.getId()));
        vertexPaint.add(Color.ORANGE);
        listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
        pseudoStepOrder.add(0);
        listDetailedStep.add(new DetailedStep( Start.getId() + " is the source vertex.\n" + "Set parent[v] = -1, but d[" + Start.getId() + "] = 0 and push this vertex to queue."));

			/*
	        // Step 2: Relax all edges |V| - 1 times. A simple
	        // shortest path from src to any other vertex can
	        // have at-most |V| - 1 edges
	         for (int i = 1; i < V; ++i) {
	            for (int j = 0; j < E; ++j) {
	                int u = graph.edge[j].src;
	                int v = graph.edge[j].dest;
	                int weight = graph.edge[j].weight;
	                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
	                    dist[v] = dist[u] + weight;
	            }
	        }
	         */

        int count = 1,k=0,j=0;
        int edge_processed = 1;
        for(int i=1; i < g.getListVertex().size() ;i++) {

            if(count == 1) {
                pseudoStepOrder.add(1);
                listDetailedStep.add(new DetailedStep("This is the first pass.\n" +
                        "The highlighted edges are the current SSSP spanning tree so far."));
            }     //for i = 1 to |V|-1"
            currentVertex = new ArrayList<>();          //TODO: STATE VISTITED VERTEX
            vertexPaint = new ArrayList<>();
            currentEdge = new ArrayList<>();
            edgePaint = new ArrayList<>();

            currentVertex.add(g.getListVertex().get(Start.getId()));
            vertexPaint.add(Color.LIGHTGREEN);
            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));

            pseudoStepOrder.add(1);
            listDetailedStep.add(new DetailedStep("Prepare all edges for this #pass: " + count));


            for(j = 0; j < g.getListEdge().size();j++) {
                Vertex u = g.getListEdge().get(j).getFrom();
                Vertex v = g.getListEdge().get(j).getTo();
                float weight =  g.getWeight(u, v);
                currentVertex = new ArrayList<>();          //TODO: STATE VISTITED VERTEX
                vertexPaint = new ArrayList<>();
                currentEdge = new ArrayList<>();
                edgePaint = new ArrayList<>();

                currentEdge.add(g.getEdgeByVer(u,v));
                currentVertex.add(g.getListVertex().get(v.getId()));
                edgePaint.add(Color.ORANGE);
                vertexPaint.add(Color.LIGHTGREEN);
                listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
                pseudoStepOrder.add(2);
                listDetailedStep.add(new DetailedStep("#Pass:" + count + ", " +
                        "relax(" + u.getId() + "," + v.getId() + "," +
                        weight + "),#edge_processed = " + edge_processed));

                if(u.getDis() != INFINITE && u.getDis() + weight < v.getDis()) {
                    v.setDis(u.getDis()+weight);
                    v.setPre(u.getId());
                }
                edge_processed++;
            }
            count++;
        }

		/*
        // Step 3: check for negative-weight cycles. The above
        // step guarantees shortest distances if graph doesn't
        // contain negative weight cycle. If we get a shorter
        // path, then there is a cycle.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }
        printArr(dist, V);
         */
        for(j = 0; j < g.getListEdge().size(); j++  ) {

            Vertex u = g.getListEdge().get(j).getFrom();
            Vertex v = g.getListEdge().get(j).getTo();
            float weight =  g.getWeight(u, v);
            if(u.getDis() != INFINITE && u.getDis() + weight < v.getDis()) {
                pseudoStepOrder.add(4);
//                System.out.println("Danh sach cac dinh sau Bellman Ford");
                for (int i = 0; i < g.getListVertex().size(); i++) {
                    System.out.print("(" + g.getListVertex().get(i).getId() + ","
                            + g.getListVertex().get(i).getDis() + ");");
                }
                listDetailedStep.add(new DetailedStep("Graph  contains negative weight cycle"));
                return;
            }
        }


        for (int i = 0; i < g.getListVertex().size(); i++) {
//            Vertex v = g.getVerFromID(g.getListVertex().get(i).getId());
//            Edge Edge1 = g.getEdgeByVer (g.getVerFromID(g.getListVertex().get(i).getId()), g.getVerFromID(g.getListVertex().get(i).getPre()));
//            System.out.println("("+Edge1.getFrom()+","+Edge1.getTo()+")");

            currentVertex = new ArrayList<>();          //TODO: STATE VISTITED VERTEX
            vertexPaint = new ArrayList<>();
            currentEdge = new ArrayList<>();
            edgePaint = new ArrayList<>();

//            currentEdge.add(Edge1);
//            edgePaint.add(Color.AQUA);
//            currentVertex.add(g.getListVertex().get(i));
//            vertexPaint.add(Color.LIGHTGREEN);
            listState.add(new State(currentVertex, currentEdge, vertexPaint, edgePaint));
            pseudoStepOrder.add(3);
            listDetailedStep.add(new DetailedStep("#edge_processed = " + edge_processed + ", V*E = " +
                    g.getListVertex().size() + "*" + g.getListEdge().size() + " = " + g.getListVertex().size() + g.getListEdge().size() + ".\n" +
                    "This is the SSSP spanning tree from source vertex "+Start.getId()+"\n"));
            this.printPath(g, g.getListVertex().get(i), Start);
            System.out.println("->" + g.getListVertex().get(i).getId());
        }

    }
    public void printPath(Graph g, Vertex v, Vertex start){
        if (v != start){
            printPath(g, g.getVerFromID(v.getPre()), start);
            System.out.print("->"+ v.getPre());
        }
    }

    @Override
    public void executeAlgorithm(Graph g) {
        listState = new ArrayList<>();
        listPseudoStep = new ArrayList<>();
        pseudoStepOrder = new ArrayList<>();

        listPseudoStep.add (new PseudoStep("initSSSP"));                      //0
        listPseudoStep.add (new PseudoStep("for i = 1 to |V|-1"));     //1
        listPseudoStep.add (new PseudoStep("    for each edge(u, v) in E // in Edge List order\n"
                +"           relax(u, v, w(u, v))")); //2
        listPseudoStep.add (new PseudoStep("    for each edge(u, v) in E\n"
                +"           if can still relax that edge, -∞ cycle found")); //3

        listDetailedStep = new ArrayList<>();
        bellmanfordProgram(g);

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
