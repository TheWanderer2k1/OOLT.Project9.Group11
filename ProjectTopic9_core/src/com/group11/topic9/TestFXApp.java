package com.group11.topic9;


import com.group11.topic9.algorithm.DynamicProgramming.DynamicProgramming;
import com.group11.topic9.state.DPState;
import com.group11.topic9.algorithm.Dijkstra.Dijkstra;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;
import java.util.ArrayList;

public class TestFXApp extends Application {
    final double pi = 3.14159265359;
    int state = 1;
    boolean stateFinish = false;
    Line line;
    int id = 0;

    ArrayList<Vertex> graphVertex;
    ArrayList<Edge> graphEdge;

    ArrayList<ArrayList<Paint>> abc ;

    ArrayList<ArrayList<Paint>> def;

    Graph g;
    Vertex ver1, ver2;
    Edge e;

    Node n;
    ArrayList<Node> defaultState = new ArrayList<>();


    boolean check;

    int stepPointer;

    Double x1, y1, x2, y2, length;

    int a;

    int h; //dung de test

    double thetaRadian;

    double thetaDegree;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Pane boxCenter = new Pane();
        boxCenter.setPrefSize(400, 400);
        boxCenter.setStyle("-fx-background-color: white;");
        root.setCenter(boxCenter);

        Pane boxLeft = new Pane();
        boxLeft.setPrefSize(100, 400);
        root.setLeft(boxLeft);

        Pane boxRight = new Pane();
        boxRight.setPrefSize(600, 400);
        root.setRight(boxRight);

        Pane boxBottom = new Pane();
        boxBottom.setPrefSize(600, 100);
        root.setBottom(boxBottom);

        Pane boxTop = new Pane();
        boxTop.setPrefSize(600, 100);
        root.setTop(boxTop);

        Scene newScene = new Scene(root, 1200, 600);

        Text textStatus = new Text();
        textStatus.setLayoutX(20);
        textStatus.setLayoutY(50);
        textStatus.setText("Status");
        textStatus.setWrappingWidth(90);

        boxTop.getChildren().add(textStatus);

        EventHandler<MouseEvent> mouseEventCreateVertex = event -> {
            check = true;
            for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                n = boxCenter.getChildren().get(i);
                if (n.getLayoutBounds().contains(event.getX(), event.getY()) && n.toString().contains("Circle")) {
                    textStatus.setText("chọn đỉnh " + n.getId());
                    check = false;
                    break;
                }
            }

            if (check) {
                //System.out.println("create");

                Circle circle = new Circle();
                circle.setCenterY(event.getY());
                circle.setCenterX(event.getX());
                circle.setRadius(13);
                circle.setStrokeWidth(3);
                circle.setStroke(Color.BLACK);
                circle.setFill(Color.WHITE);
                //circle.setStroke(Color.RED);
                System.out.println(event.getX() + "-" + event.getY());

                circle.setOnMouseClicked(eventOnVertex -> {
                    if (state == 1) {
                        line = new Line();
                        line.setStartX(circle.getCenterX());
                        line.setStartY(circle.getCenterY());
                        for (Vertex vertex : graphVertex) {
                            if (circle.equals(vertex.getVerCircle())) {
                                System.out.println("set start");
                                ver1 = vertex;
                            }
                        }

                        state++;
                    } else if (state == 2) {
                        state = 1;
                        line.setEndX(circle.getCenterX());
                        line.setEndY(circle.getCenterY());
                        line.setStrokeWidth(3);
                        line.setStroke(Color.BLACK);

                        for (Vertex vertex : graphVertex) {
                            if (circle.equals(vertex.getVerCircle())) {
                                System.out.println("set end");
                                ver2 = vertex;
                            }
                        }
                        if (ver1.getId() != ver2.getId()) {
                            ArrayList<Vertex> groupVer = new ArrayList<>();
                            groupVer.add(ver1);
                            groupVer.add(ver2);
                            check = true;

                            x1 = ver1.getVerCircle().getCenterX();
                            y1 = ver1.getVerCircle().getCenterY();
                            x2 = ver2.getVerCircle().getCenterX();
                            y2 = ver2.getVerCircle().getCenterY();
                            length = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));


                            e = new Edge(ver1, ver2, groupVer, line, true, true, Math.round(length.floatValue()) / 10.0f);
                            for (Edge edge : graphEdge) {
                                if (e.equals(edge)) {
                                    textStatus.setText("trùng cạnh");
                                    check = false;
                                    break;
                                }
                            }
                            if (check) {
                                graphEdge.add(e);
                                textStatus.setText(textStatus.getText() + "\ntạo cạnh");
                            }


                            boxCenter.getChildren().add(0, line);
                        } else
                            textStatus.setText("trùng đỉnh");
                        //System.out.println("trung dinh");
                    }
                });
                System.out.println("tạo đỉnh ok");
                // tao dinh cho nay
                circle.setId(String.valueOf(id));
                graphVertex.add(new Vertex(id, circle));
                textStatus.setText("tạo đỉnh " + id);
                Text text = new Text(event.getX(), event.getY() - 20, String.valueOf(id));
                id++;
                boxCenter.getChildren().addAll(circle, text);

            }
        };


//        design button
        Button button1 = new Button("Create");
        button1.setLayoutX(20);
        button1.setOnMouseClicked(eventButton1 -> {
            textStatus.setText("Creating graph");
            stateFinish = false;

            boxCenter.getChildren().clear();
            boxCenter.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);

            boxRight.getChildren().clear();

            graphVertex = new ArrayList<>();
            graphEdge = new ArrayList<>();
            id = 0;
        });
        boxLeft.getChildren().add(button1);

        Button button2 = new Button("Finish");
        button2.setLayoutX(20);
        button2.setLayoutY(50);
        button2.setOnMouseClicked(eventButton2 -> {
            if (graphVertex != null && graphVertex.size() > 0 && !stateFinish) {

                textStatus.setText("Finish create");
                stateFinish = true;
                state = 1;

                boxCenter.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);

                Node n;
                for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                    n = boxCenter.getChildren().get(i);
                    n.setOnMouseClicked(replaceEvent -> {
                        System.out.println("remove done");
                    });

                    if (n.toString().contains("Text")) {
                        n.setLayoutY(n.getLayoutY() + 25);
                        n.setLayoutX(n.getLayoutX() - 3);
                        ((Text) n).setFill(Color.BLACK);
                    }
                }

                defaultState.clear();
                defaultState.addAll(boxCenter.getChildren());

                g = new Graph(graphEdge, graphVertex);
                g.showGraph();
            } else
                textStatus.setText("chua co graph");
        });
        boxLeft.getChildren().add(button2);



        Button button3 = new Button("Run DP");
        button3.setLayoutX(20);
        button3.setLayoutY(100);
        //dat su kien o day
        boxLeft.getChildren().add(button3);



        Button button4 = new Button("Run DJ");
        button4.setLayoutX(20);
        button4.setLayoutY(150);
        //event button4

        boxLeft.getChildren().add(button4);
        
        Button button5 = new Button("Run BF");
        button5.setLayoutY(200);
        button5.setLayoutX(20);
        //event button5
        boxLeft.getChildren().add(button5);



        Button button6 = new Button("Generate Example");
        button6.setLayoutX(100);
        button6.setLayoutY(20);
        button6.setOnMouseClicked(eventButton6 -> {
            boxCenter.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);
            boxRight.getChildren().clear();
            boxCenter.getChildren().clear();
            graphVertex = new ArrayList<>();
            graphEdge = new ArrayList<>();

            Circle circle0 = new Circle();
            circle0.setCenterY(124);
            circle0.setCenterX(84);
            circle0.setRadius(13);
            circle0.setStrokeWidth(3);
            circle0.setStroke(Color.BLACK);
            circle0.setFill(Color.WHITE);
            circle0.setId(String.valueOf(0));
            graphVertex.add(new Vertex(0, circle0));

            Text t0 = new Text(81, 127, String.valueOf(0));

            Circle circle1 = new Circle();
            circle1.setCenterY(124);
            circle1.setCenterX(208);
            circle1.setRadius(13);
            circle1.setStrokeWidth(3);
            circle1.setStroke(Color.BLACK);
            circle1.setFill(Color.WHITE);
            circle1.setId(String.valueOf(1));
            graphVertex.add(new Vertex(1, circle1));

            Text t1 = new Text(205, 127, String.valueOf(1));

            Circle circle2 = new Circle();
            circle2.setCenterY(124);
            circle2.setCenterX(345);
            circle2.setRadius(13);
            circle2.setStrokeWidth(3);
            circle2.setStroke(Color.BLACK);
            circle2.setFill(Color.WHITE);
            circle2.setId(String.valueOf(2));
            graphVertex.add(new Vertex(2, circle2));

            Text t2 = new Text(342, 127, String.valueOf(2));

            Circle circle3 = new Circle();
            circle3.setCenterY(231);
            circle3.setCenterX(84);
            circle3.setRadius(13);
            circle3.setStrokeWidth(3);
            circle3.setStroke(Color.BLACK);
            circle3.setFill(Color.WHITE);
            circle3.setId(String.valueOf(3));
            graphVertex.add(new Vertex(3, circle3));

            Text t3 = new Text(81, 234, String.valueOf(3));

            Circle circle4 = new Circle();
            circle4.setCenterY(231);
            circle4.setCenterX(208);
            circle4.setRadius(13);
            circle4.setStrokeWidth(3);
            circle4.setStroke(Color.BLACK);
            circle4.setFill(Color.WHITE);
            circle4.setId(String.valueOf(4));
            graphVertex.add(new Vertex(4, circle4));

            Text t4 = new Text(205, 234, String.valueOf(4));

            Line l0 = new Line();
            l0.setStartX(84);
            l0.setStartY(124);
            l0.setEndX(208);
            l0.setEndY(124);
            l0.setStrokeWidth(3);

            Line l1 = new Line();
            l1.setStartX(208);
            l1.setStartY(124);
            l1.setEndX(345);
            l1.setEndY(124);
            l1.setStrokeWidth(3);

            Line l2 = new Line();
            l2.setStartX(84);
            l2.setStartY(124);
            l2.setEndX(84);
            l2.setEndY(231);
            l2.setStrokeWidth(3);

            Line l3 = new Line();
            l3.setStartX(84);
            l3.setStartY(124);
            l3.setEndX(208);
            l3.setEndY(231);
            l3.setStrokeWidth(3);

            Line l4 = new Line();
            l4.setStartX(84);
            l4.setStartY(231);
            l4.setEndX(208);
            l4.setEndY(231);
            l4.setStrokeWidth(3);

            Line l5 = new Line();
            l5.setStartX(208);
            l5.setStartY(231);
            l5.setEndX(208);
            l5.setEndY(124);
            l5.setStrokeWidth(3);

            ArrayList<Vertex> a0 = new ArrayList<>();
            a0.add(graphVertex.get(0));
            a0.add(graphVertex.get(1));
            graphEdge.add(new Edge(graphVertex.get(0), graphVertex.get(1), a0,l0, true, true, 10));

            ArrayList<Vertex> a1 = new ArrayList<>();
            a1.add(graphVertex.get(1));
            a1.add(graphVertex.get(2));
            graphEdge.add(new Edge(graphVertex.get(1), graphVertex.get(2), a1,l1, true, true, 15));

            ArrayList<Vertex> a2 = new ArrayList<>();
            a2.add(graphVertex.get(0));
            a2.add(graphVertex.get(3));
            graphEdge.add(new Edge(graphVertex.get(0), graphVertex.get(3), a2,l2, true, true, 13));

            ArrayList<Vertex> a3 = new ArrayList<>();
            a3.add(graphVertex.get(0));
            a3.add(graphVertex.get(4));
            graphEdge.add(new Edge(graphVertex.get(0), graphVertex.get(4), a3,l3, true, true, 12));

            ArrayList<Vertex> a4 = new ArrayList<>();
            a4.add(graphVertex.get(3));
            a4.add(graphVertex.get(4));
            graphEdge.add(new Edge(graphVertex.get(3), graphVertex.get(4), a4,l4, true, true, 11));

            ArrayList<Vertex> a5 = new ArrayList<>();
            a5.add(graphVertex.get(4));
            a5.add(graphVertex.get(1));
            graphEdge.add(new Edge(graphVertex.get(4), graphVertex.get(1), a5,l5, true, true, 17));

            g = new Graph(graphEdge, graphVertex);
            g.showGraph();

            boxCenter.getChildren().addAll(l0,l1,l2,l3,l4,l5,circle0,circle1,circle2,circle3,circle4, t0,t1,t2,t3,t4);
            defaultState.clear();
            defaultState.addAll(boxCenter.getChildren());

            stateFinish = true;
            state = 1;

        });
        boxBottom.getChildren().add(button6);

        stage.setScene(newScene);
        stage.show();
    }
}
