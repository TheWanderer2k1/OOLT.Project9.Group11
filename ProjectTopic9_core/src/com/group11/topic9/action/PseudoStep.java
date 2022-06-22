package com.group11.topic9.action;

import java.util.ArrayList;

public class PseudoStep extends Step{

    protected ArrayList<String> listStep = new ArrayList<>();
    protected ArrayList<Integer> stepOrder = new ArrayList<>();

    public ArrayList<Integer> getStepOrder() {
        return stepOrder;
    }

    public ArrayList<String> getListStep() {
        return listStep;
    }

    @Override
    public void run() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void nextOneStep() {

    }

    @Override
    public void backOneStep() {

    }
}
