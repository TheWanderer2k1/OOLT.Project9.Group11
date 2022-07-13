package com.group11.topic9;

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
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

//    ArrayList<Node> currentState = new ArrayList<>();

    boolean check;

    int stepPointer;

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
        //StackPane boxCenter = new StackPane();
        boxCenter.setPrefSize(400, 400);
        boxCenter.setStyle("-fx-background-color: white;");
        root.setCenter(boxCenter);

        Pane boxLeft = new Pane();
        boxLeft.setPrefSize(100, 200);
        root.setLeft(boxLeft);

        Pane boxRight = new Pane();
        boxRight.setPrefSize(600, 200);
        root.setRight(boxRight);

        Pane boxBottom = new Pane();
        boxBottom.setPrefSize(600, 100);
        root.setBottom(boxBottom);

        Pane boxTop = new Pane();
        boxTop.setPrefSize(600, 100);
        root.setTop(boxTop);

        Scene newScene = new Scene(root, 1200, 600);
        newScene.setFill(Color.ORANGE);


        Text textStatus = new Text();
        textStatus.setLayoutX(20);
        textStatus.setLayoutY(50);
        textStatus.setText("Status");
        textStatus.setWrappingWidth(90);

        boxTop.getChildren().add(textStatus);

        EventHandler<MouseEvent> mouseEventCreateVertex = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                check = true;
                for (int i = 0; i < boxCenter.getChildren().size(); i++){
                    n = boxCenter.getChildren().get(i);
                    if (n.getLayoutBounds().contains(event.getX(), event.getY()) && n.toString().contains("Circle")){
                        textStatus.setText("chọn đỉnh " + n.getId());
                        check = false;
                        break;
                    }
                }

                if (check) {
                    Circle circle = new Circle();
                    circle.setCenterY(event.getY());
                    circle.setCenterX(event.getX());
                    circle.setRadius(15);
                    circle.setFill(Color.WHITE);
                    circle.setStroke(Color.BLACK);
                    circle.setStrokeWidth(2);

                    circle.setOnMouseClicked(eventOnVertex -> {
                        if (state == 1) {
                            line = new Line();
                            line.setStartX(circle.getCenterX());
                            line.setStartY(circle.getCenterY());

                            for (int i = 0; i < graphVertex.size(); i++){
                                if (circle.equals(graphVertex.get(i).getVerCircle())){
                                    System.out.println("set start");
                                    ver1 = graphVertex.get(i);
                                }
                            }
                            state++;
                        } else if (state == 2) {
                            state = 1;
                            line.setEndX(circle.getCenterX());
                            line.setEndY(circle.getCenterY());
                            line.setStrokeWidth(3);
                            line.setStroke(Color.BLACK);


                            for (int i = 0; i < graphVertex.size(); i++){
                                if (circle.equals(graphVertex.get(i).getVerCircle())){
                                    System.out.println("set end");
                                    ver2 = graphVertex.get(i);
                                }
                            }
                            if (ver1.getId() != ver2.getId()) {
                                ArrayList<Vertex> groupVer = new ArrayList<>();
                                groupVer.add(ver1);
                                groupVer.add(ver2);

                                check = true;

                                //test do dai Canh
                                double X1 = ver1.getVerCircle().getCenterX();
                                double Y1 = ver1.getVerCircle().getCenterY();
                                double X2 = ver2.getVerCircle().getCenterX();
                                double Y2 = ver2.getVerCircle().getCenterY();
                                double X = Math.abs(X1-X2);
                                double Y = Math.abs(Y1-Y2);

                                double length=0;
                                length = Math.sqrt(Math.pow(X, 2) + Math.pow(Y, 2));


//                                Arrow arr = new Arrow(X1, Y1, X2, Y2);

                                double angle = Math.atan2((Y2 - Y1), (X2 - X1)) - Math.PI / 2.0;
                                double sin = Math.sin(angle);
                                double cos = Math.cos(angle);
                                //point1
                                double x1 = (- 1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 10.0 + X2;
                                double y1 = (- 1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 10.0 + Y2;
                                //point2
                                double x2 = (1.0 / 2.0 * cos + Math.sqrt(3) / 2 * sin) * 10.0 + X2;
                                double y2 = (1.0 / 2.0 * sin - Math.sqrt(3) / 2 * cos) * 10.0 + Y2;

//                                boxCenter.getChildren().add(arr);

//arrow
                                System.out.println("Y = "+Y+"X = "+X);
                                thetaRadian = Math.atan2(Y, X);
                                thetaRadian = Math.toDegrees(Math.atan2(Y, X));
                                System.out.println("Goc lech Ra = "+thetaRadian);
                                System.out.println("Goc lech Ge ="+thetaDegree);
                                e = new Edge(ver1, ver2, groupVer, line, true, true, (float) length);
                                for (int i = 0; i < graphEdge.size(); i++){
                                    if (e.equals(graphEdge.get(i))) {
                                        //System.out.println("trùng cạnh");
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
                        }
                    });

//                    circle.setOnDragDetected(eventOnVertex ->{});
                    System.out.println("tạo đỉnh ok");

                    // tao dinh cho nay
                    circle.setId(String.valueOf(id));
                    graphVertex.add(new Vertex(id, circle));
                    textStatus.setText("tạo đỉnh " + id);
                    Text text = new Text(event.getX(), event.getY() - 20, String.valueOf(id));
                    id++;
                    boxCenter.getChildren().addAll(circle, text);

                }

            }
        };  //tao do thi

        //design button
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
            if (graphVertex != null && graphVertex.size() > 0) {
                textStatus.setText("Finish create");
                stateFinish = true;
                System.out.println(button2.getLayoutX() + " - " + button2.getLayoutY());

                boxCenter.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);

//                graphVertex.get(0).getVerCircle().setFill(Color.BLACK);

                Node n;
                for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                    n = boxCenter.getChildren().get(i);
                    n.setOnMouseClicked(replaceEvent -> {
                        System.out.println("remove done");
                    });
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
        //content of Dynamic Programming
        boxLeft.getChildren().add(button3);



        Button button4 = new Button("Run DJ");
        button4.setLayoutX(20);
        button4.setLayoutY(150);
        button4.setOnMouseClicked(eventButton3 -> {
            if (stateFinish) {

                textStatus.setText("Running DJ");
                boxCenter.getChildren().clear();
                boxCenter.getChildren().addAll(defaultState);

                boxRight.getChildren().clear();

                abc = new ArrayList<>();
                def = new ArrayList<>();

                BorderPane newWindowRoot = new BorderPane();

                Pane messageStep = new Pane();
                messageStep.setPrefSize(400, 250);
                newWindowRoot.setCenter(messageStep);

                Text message = new Text("Pseudo and Detail step here");
                message.setWrappingWidth(380);
                message.setLayoutY(15);
                message.setLayoutX(20);
                messageStep.getChildren().add(message);

                Pane controlBox = new Pane();
                controlBox.setStyle("-fx-background-color:azure;");
                controlBox.setPrefSize(600, 50);

                Button next = new Button("Next");
                next.setLayoutX(50);
                next.setLayoutY(15);

                Button back = new Button("Back");
                back.setLayoutX(100);
                back.setLayoutY(15);

                Button run = new Button("run");
                run.setLayoutX(150);
                run.setLayoutY(15);

                Button pause = new Button("pause");
                pause.setLayoutX(200);
                pause.setLayoutY(15);

                Slider speed = new Slider();
                speed.setLayoutX(300);
                speed.setLayoutY(15);
                speed.setMin(0.1);
                speed.setMax(2.0);
                speed.setValue(0.5);
                speed.setBlockIncrement(0.5);
                speed.setShowTickLabels(true);
                speed.setShowTickMarks(true);

                controlBox.getChildren().addAll(next, back, run, pause, speed);
                newWindowRoot.setBottom(controlBox);

                Dijkstra dj = new Dijkstra();
                dj.executeAlgorithm(g);

                stepPointer = 1;
                a = -1;
//                for (int i=0; i<dj.getListState().size(); i++){
//                    System.out.println("size "+i+" : "+dj.getListState().get(i).getCurrentVertexes().size());
//                }

                h = 0;

                next.setOnMouseClicked(eventNext -> {

                    //khi o cuoi cua list
                    if ( h == abc.size()) {
                        abc.add(new ArrayList<>());
                        def.add(new ArrayList<>());

                        //TODO: luu mau do thi hien tai
                        for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                            if (boxCenter.getChildren().get(i).toString().contains("Circle"))
                                abc.get(h).add(((Circle) boxCenter.getChildren().get(i)).getFill());
                            if (boxCenter.getChildren().get(i).toString().contains("Line"))
                                def.get(h).add(((Line) boxCenter.getChildren().get(i)).getStroke());
                        }
                    }


                    if (stepPointer < dj.getListState().size() - 1
                            && a == dj.getListState().get(stepPointer).getCurrentVertexes().size()-1) {
                        stepPointer++;
                        System.out.println("New state step : "+stepPointer);
                        a = -1;
                    }
                    if (a < dj.getListState().get(stepPointer).getCurrentVertexes().size()-1){
                        a++;
                        h++;
                    }

                    if (dj.getListState().get(stepPointer).getCurrentVertexes() != null
                            &&dj.getListState().get(stepPointer).getCurrentEdges() != null ) {
                        for (int j=a; j>=0; j--){
                            if (dj.getListState().get(stepPointer).getCurrentVertexes().get(j).getVerCircle().getFill() != Color.ORANGE){
                                dj.getListState().get(stepPointer).getCurrentVertexes().get(j).getVerCircle().setFill(dj.getListState().get(stepPointer).getVertexPaints().get(j));
                            }
                            if (dj.getListState().get(stepPointer).getCurrentEdges().get(j).getWeight() != 0){
                                dj.getListState().get(stepPointer).getCurrentEdges().get(j).getEdgeLine().setStroke(dj.getListState().get(stepPointer).getEdgePaints().get(j));
                            }

                            if (a == dj.getListState().get(stepPointer).getCurrentVertexes().size()-1
                                    && j==0){
                                break;
                            }
                        }

                        System.out.println("\nh = "+h);
                        System.out.println("a = "+a);
                        System.out.println("st= "+stepPointer);
                    }
                    System.out.println();
                    message.setText(dj.getPseudoAndDetailStep(h));
//                    dj.getPseudoAndDetailStep(stepPointer);
                });


                back.setOnMouseClicked(eventBack -> {
                    if (h > 0 ) {
                        h--;

                        int tmpV = 0;
                        int tmpE = 0;
                        for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                            if (boxCenter.getChildren().get(i).toString().contains("Circle")) {
                                ((Circle) boxCenter.getChildren().get(i)).setFill(abc.get(h).get(tmpV));
                                tmpV++;
                            }

                            if (boxCenter.getChildren().get(i).toString().contains("Line")) {
                                ((Line) boxCenter.getChildren().get(i)).setStroke(def.get(h).get(tmpE));
                                tmpE++;
                            }
                        }

                        if (a == 0 && stepPointer > 0) {
                            stepPointer--;
                            System.out.println("new Step " + stepPointer);
                            a = dj.getListState().get(stepPointer).getCurrentVertexes().size() - 1;
                        } else if (a > 0) {
                            a--;
                            System.out.println("Step " + stepPointer);
                        }
                        System.out.println("\nh = " + h);
                        System.out.println("a = " + a);
                        System.out.println("st= " + stepPointer);
                        System.out.println("soSt=" + dj.getListState().size());
                    }
                    message.setText(dj.getPseudoAndDetailStep(h));
//                    dj.getPseudoAndDetailStep(h);
                });

                PauseTransition showAlgorithm = new PauseTransition(Duration.seconds(0.1));

                showAlgorithm.setOnFinished(actionEvent -> {

                    if (h == abc.size()) {
                        abc.add(new ArrayList<>());
                        def.add(new ArrayList<>());

                        //TODO: luu mau do thi hien tai
                        for (int i = 0; i < boxCenter.getChildren().size(); i++) {
                            if (boxCenter.getChildren().get(i).toString().contains("Circle"))
                                abc.get(h).add(((Circle) boxCenter.getChildren().get(i)).getFill());
                            if (boxCenter.getChildren().get(i).toString().contains("Line"))
                                def.get(h).add(((Line) boxCenter.getChildren().get(i)).getStroke());
                        }
                    }

                    if (stepPointer < dj.getListState().size() - 1
                            && a == dj.getListState().get(stepPointer).getCurrentVertexes().size()-1) {
                        stepPointer++;
                        System.out.println("New state step : "+stepPointer);
                        a = -1;
                    }

                    if (a < dj.getListState().get(stepPointer).getCurrentVertexes().size()-1){
                        a++;
                        h++;
                    }

                    if (dj.getListState().get(stepPointer).getCurrentVertexes() != null
                            &&dj.getListState().get(stepPointer).getCurrentEdges() != null ) {



                        for (int j=a; j>=0; j--){
                            if (dj.getListState().get(stepPointer).getCurrentVertexes().get(j).getVerCircle().getFill() != Color.ORANGE){
                                dj.getListState().get(stepPointer).getCurrentVertexes().get(j).getVerCircle().setFill(dj.getListState().get(stepPointer).getVertexPaints().get(j));
                            }
//                               dj.getListState().get(stepPointer).getCurrentVertexes().get(j).getVerCircle().setFill(dj.getListState().get(stepPointer).getVertexPaints().get(j));
                            if (dj.getListState().get(stepPointer).getCurrentEdges().get(j).getWeight() != 0){
                                dj.getListState().get(stepPointer).getCurrentEdges().get(j).getEdgeLine().setStroke(dj.getListState().get(stepPointer).getEdgePaints().get(j));
                            }
                            if (a == dj.getListState().get(stepPointer).getCurrentVertexes().size()-1
                                    && j==0){
                                break;
                            }
                        }

                        System.out.println("\nh = "+h);
                        System.out.println("a = "+a);
                        System.out.println("st= "+stepPointer);
                    }
                    message.setText(dj.getPseudoAndDetailStep(h));
                    showAlgorithm.playFromStart();

                });


                run.setOnMouseClicked(mouseEvent -> {
                    showAlgorithm.play();
                });

                pause.setOnMouseClicked(mouseEvent -> {
                    showAlgorithm.stop();
                });

                speed.valueProperty().addListener((observableValue, oldVal, newVal) -> {
                    showAlgorithm.setDuration(Duration.seconds((Double) newVal));
                });
                boxRight.getChildren().add(newWindowRoot);

            }else
                textStatus.setText("Chua tao xong graph");

        });
        boxLeft.getChildren().add(button4);



        Button button5 = new Button("Run BF");
        button5.setLayoutY(200);
        button5.setLayoutX(20);
        //event button5
        boxLeft.getChildren().add(button5);


        stage.setScene(newScene);
        stage.show();
    }
}
