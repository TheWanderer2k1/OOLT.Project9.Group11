package com.group11.topic9.state;

import com.group11.topic9.state.State;
import com.group11.topic9.graph.Vertex;
import javafx.scene.paint.Paint;

import java.util.ArrayList;

public class DPState extends State {
    private int verFrom = 999;
    private int verTo = 999;
    private float changedValue = 999f;

    public DPState(ArrayList<Vertex> currentVertexes, ArrayList<Paint> vertexPaints, int verFrom, int verTo, float value){
        super(currentVertexes, vertexPaints);
        this.verFrom = verFrom;
        this.verTo = verTo;
        this.changedValue = value;
    }

    public DPState(int verFrom, int verTo, float changedValue){
        this.verFrom = verFrom;
        this.verTo = verTo;
        this.changedValue = changedValue;
    }

    public DPState(){
        super();
    }

    public int getVerFrom() {
        return verFrom;
    }

    public int getVerTo() {
        return verTo;
    }

    public float getChangedValue() {
        return changedValue;
    }
}
