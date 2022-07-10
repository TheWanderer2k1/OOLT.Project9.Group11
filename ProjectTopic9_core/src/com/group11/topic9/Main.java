package com.group11.topic9;

import com.group11.topic9.action.Draw;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.algorithm.*;
import com.group11.topic9.graph.Vertex;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static final float INFINITE = 1000;

    public static void main(String[] args) {
        // write your code here
        ArrayList<Vertex> checkVertex = new ArrayList<Vertex>();

        Draw dr = new Draw();
        Graph g = dr.drawGraph();
        g.showGraph();

        //ae test cac truong hop ben duoi
    }
}







