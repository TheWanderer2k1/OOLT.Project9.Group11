package com.group11.topic9.algorithm;

import com.group11.topic9.algorithm.State;
import com.group11.topic9.graph.Vertex;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class DPState extends State {
    int i = 999;
    int j = 999;
    float changedValue = 999f;

    public DPState(ArrayList<Vertex> currentVertexes, ArrayList<Paint> vertexPaints, int i, int j, float value){
        super(currentVertexes, vertexPaints);
        this.i = i;
        this.j = j;
        this.changedValue = value;
    }

    public DPState(int i, int j, float changedValue){
        this.i = i;
        this.j = j;
        this.changedValue = changedValue;
    }

    public DPState(){
        super();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public float getChangedValue() {
        return changedValue;
    }
}
