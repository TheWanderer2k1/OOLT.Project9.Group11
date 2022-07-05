package com.group11.topic9.graph;

import javafx.scene.shape.Circle;

public class Vertex {
    private int id;

    Circle verCircle;

    public void setVerCircle(Circle verCircle) {
        this.verCircle = verCircle;
    }

    public Circle getVerCircle() {
        return verCircle;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public Vertex(int id) {
        this.id = id;
    }

    public Vertex(int id, Circle verCircle) {
        this.id = id;
        this.verCircle = verCircle;
    }


    public boolean equals(Vertex v){
        return this.id == v.getId();
    }
}
