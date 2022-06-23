package com.group11.topic9.algorithm.testAlgorithm;

import com.group11.topic9.algorithm.DynamicProgramming.DynamicProgramming;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Graph_example;

import java.util.Scanner;

public class testDP {
    public static void main(String[] args) {
        Graph vd = Graph_example.VD2();
        DynamicProgramming dp = new DynamicProgramming();

        vd.setDirected(false);

        dp.executeAlgorithm(vd);

        int a;
        Scanner sc = new Scanner(System.in);
        while (true){

            dp.getListDetailedStep().run();
            dp.getListPseudoStep().run();

            a = sc.nextInt();
            if (a == 1){
                System.out.println("next step");
                dp.getListPseudoStep().nextOneStep();
                dp.getListDetailedStep().nextOneStep();
            }else if (a ==2 && (dp.getListPseudoStep().getStepPointer() > 0) && (dp.getListDetailedStep().getStepPointer() > 0)){
                System.out.println("back one step");
                dp.getListPseudoStep().backOneStep();
                dp.getListDetailedStep().backOneStep();
            }else if (a == 0){
                System.out.println("Break");
                break;
            }
        }

//        for (int i = 0; i < vd.getListVertex().size(); i++){
//            System.out.println(dp.getMinDis().get(i));
//        }

//        System.out.println(dp.getListPseudoStep().getStepOrder());
//        System.out.println(dp.getListPseudoStep().getListStep());
    }
}
