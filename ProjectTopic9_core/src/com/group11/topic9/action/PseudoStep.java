package com.group11.topic9.action;

import java.util.ArrayList;
import java.util.Scanner;

public class PseudoStep extends Step{

    public PseudoStep(String content){
        super(content);
    }

    public ArrayList<String> listofStep = new ArrayList<String>();

    public ArrayList<Integer> stepIndex = new ArrayList<Integer>();


    public ArrayList<String> getListofStep() {
        return listofStep;
    }

    public ArrayList<Integer> getStepIndex() {
        return stepIndex;
    }

//    @Override
//    public void run() {
//        int i=0;
//        Scanner sc = new Scanner(System.in);
//        int a;
//        while(true){
//
//                System.out.println("--------\n");
//
//                for (int j=0; j< listofStep.size(); j++){
//                    if (stepIndex.get(i) == j){
//                        System.out.println("==> "+listofStep.get(j));
//                    }else {
//                        System.out.println("     "+listofStep.get(j));
//                    }
//                }
//            a = sc.nextInt();
//            if (a == 1){
//                System.out.println("Turn up");
//                i++;
//            }else if (a ==2 && (i > 0)){
//                System.out.println("Turn back");
//                i--;
//            }else if (a == 0){
//                System.out.println("Break");
//                break;
//            }
//        }
//
//    }


}
