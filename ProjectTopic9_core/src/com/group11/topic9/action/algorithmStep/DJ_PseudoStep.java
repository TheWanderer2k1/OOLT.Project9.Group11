package com.group11.topic9.action.algorithmStep;

import com.group11.topic9.action.PseudoStep;

import java.util.ArrayList;

public class DJ_PseudoStep extends PseudoStep {

    public void initStep (){
        listofStep.add("initSSSP & pre-populate PQ");                       //0
        listofStep.add("while !PQ.empty() //PQ is a Priority Queue ");     //1
        listofStep.add("for each neighbor v of u = PQ.front(), PQ.pop()");  //2
        listofStep.add("relax(u, v, w(u, v)) + update PQ");                 //3
    }

}
