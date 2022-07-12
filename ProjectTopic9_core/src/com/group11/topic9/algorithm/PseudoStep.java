package com.group11.topic9.algorithm;

public class PseudoStep extends Step{

//    protected ArrayList<String> listStep = new ArrayList<>();
//    protected ArrayList<Integer> stepOrder = new ArrayList<>();
//
//    public ArrayList<Integer> getStepOrder() {
//        return stepOrder;
//    }
//
//    public ArrayList<String> getListStep() {
//        return listStep;
//    }
//
//    private int stepPointer = 0;
//
//    public int getStepPointer() {
//        return stepPointer;
//    }
//
//    @Override
//    public void run() {
//
//        System.out.println("----------------------------");
//        System.out.println("Pseudo code:");
//            for (int j=0; j< listStep.size(); j++){
//                if (stepOrder.get(stepPointer) == j){
//                    System.out.println("==> "+listStep.get(j));
//                }else {
//                    System.out.println("     "+listStep.get(j));
//                }
//            }
//    }
//
//    @Override
//    public void pause() {
//
//    }
//
//    @Override
//    public void nextOneStep() {
//        stepPointer++;
//    }
//
//    @Override
//    public void backOneStep() {
//        stepPointer--;
//    }

    public PseudoStep(String content) {
        super(content);
    }
}
