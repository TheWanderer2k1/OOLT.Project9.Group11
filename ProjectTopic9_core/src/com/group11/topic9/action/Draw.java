package com.group11.topic9.action;

import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;

import java.util.ArrayList;
import java.util.Scanner;

public class Draw {
    private int id = 0;

    public Graph drawGraph(){
        ArrayList<Vertex> graphVertex = new ArrayList<Vertex>();
        ArrayList<Edge> graphEdge = new ArrayList<Edge>();

        Vertex ver1 = new Vertex(0);
        Vertex ver2 = new Vertex(0);

        Edge e;

        boolean check = false;

        System.out.println("Test create graph:");

        Scanner sc = new Scanner(System.in);
        int choose = 1;
        while(true){
            System.out.println("1. Add vertex\n" + "0. exit");
            choose = Integer.parseInt(sc.nextLine());
            if (choose != 1) break;
            System.out.println("Vertex " + id + " added");
            graphVertex.add(addVertex());
        }       //add Vertex

        while (true){
            System.out.println("1. Add edge\n" + "0. exit");
            choose = Integer.parseInt(sc.nextLine());
            if (choose != 1) break;

            do {
                System.out.print("nhap dinh 1: ");
                choose = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < graphVertex.size(); i++) {
                    if (choose == graphVertex.get(i).getId()) {
                        ver1 = graphVertex.get(i);
                        check = true;
                        break;
                    }
                }
            } while (!check);

            check = false;

            do {
                System.out.print("nhap dinh 2: ");
                choose = Integer.parseInt(sc.nextLine());

                for (int i = 0; i < graphVertex.size(); i++) {
                    if (choose == graphVertex.get(i).getId()) {
                        ver2 = graphVertex.get(i);
                        check = true;
                        break;
                    }
                }
            } while (!check);

            check = false;

            e = createEdge(ver1, ver2, 0.0f);

            for (int i = 0; i < graphEdge.size(); i++){
                if (e == null || graphEdge.get(i).equals(e)){
                    check = true;
                    break;
                }
            }

            if (!check && e != null) {
                graphEdge.add(e);
                System.out.println("edge was added");
            }

        }      //add Edge

        return new Graph(graphEdge, graphVertex);
    }

    public Vertex addVertex(){
        return new Vertex(id++);
    }

    public Edge createEdge(Vertex ver1, Vertex ver2, float weight){
        if (!ver1.equals(ver2)) {
            ArrayList<Vertex> edgeVer = new ArrayList<Vertex>();
            edgeVer.add(ver1);
            edgeVer.add(ver2);
            return new Edge(ver1, ver2, edgeVer, true, true, weight);
        }
        System.out.println("trung dinh");
        return null;
    }
}
