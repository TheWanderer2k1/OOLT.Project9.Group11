package com.group11.topic9.algorithm;


import java.util.ArrayList;

public abstract class  Step {
//    public abstract void run();
//    public abstract void pause();
//    public abstract void nextOneStep();
//    public abstract void backOneStep();
    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Step(String content) {
        this.content = content;
    }
}
