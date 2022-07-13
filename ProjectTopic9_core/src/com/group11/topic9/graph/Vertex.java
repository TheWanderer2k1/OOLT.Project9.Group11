package com.group11.topic9.graph;

import javafx.scene.shape.Circle;


public class Vertex {
    private int id;

    private Circle verCircle;

    private int pre;    //previous Vertex's ID

    private float dis;    //distance from Start Node to this vertex


    public void setVerCircle(Circle verCircle) {
        this.verCircle = verCircle;
    }

    public Circle getVerCircle() {
        return verCircle;
    }


    public int getPre() {
        return pre;
    }

    public void setPre(int pre) {
        this.pre = pre;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public void setDis( float dis){
        this.dis = dis;
    }

    public float getDis(){
        return dis;
    }


    public Vertex (){
        super();
    }
    public Vertex(int id) {
        this.id = id;
    }


    public Vertex(int id, Circle verCircle) {
        this.id = id;
        this.verCircle = verCircle;
    }

    //todo: compare vertex

    public boolean equals(Vertex v){
        return this.id == v.getId();
    }

    public void showVerInfor(){
//        System.out.println("---");
        System.out.print("ID : "+this.getId());
        System.out.println("("+this.getDis()+", "+this.getPre()+")");
    }

}
