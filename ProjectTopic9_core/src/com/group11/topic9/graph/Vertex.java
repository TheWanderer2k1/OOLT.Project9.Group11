package com.group11.topic9.graph;

public class Vertex {
    private int id;

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public Vertex(int id) {
        this.id = id;
    }

    public boolean equals(Vertex v){
        return this.id == v.getId();
    }
}
