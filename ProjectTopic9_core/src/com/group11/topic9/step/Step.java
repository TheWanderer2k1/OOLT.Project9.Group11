package com.group11.topic9.step;

public abstract class  Step {
    private String content;

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
