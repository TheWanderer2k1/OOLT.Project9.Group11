package com.group11.topic9.action;

import java.util.ArrayList;

public abstract class  Step {

    String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Step(String content){
        this.content = content;
    }


    //phần cũ
//    public abstract void run();
//
//    public abstract void pause();
//
//    public abstract void nextOneStep();
//
//    public abstract void backOneStep();
}

