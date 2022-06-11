package com.group11.topic9;

import com.group11.topic9.action.Draw;
import com.group11.topic9.graph.Graph;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Draw dr = new Draw();
        Graph testGraph = dr.drawGraph();
        testGraph.showGraph();
        //ae test cac truong hop ben duoi
    }
}
