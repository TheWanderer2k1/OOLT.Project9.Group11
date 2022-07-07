import com.group11.topic9.algorithm.DynamicProgramming.DynamicProgramming;
import com.group11.topic9.graph.DPState;
import com.group11.topic9.graph.Edge;
import com.group11.topic9.graph.Graph;
import com.group11.topic9.graph.Vertex;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TestFXApp extends Application {
    int state = 1;
    boolean stateFinish = false;
    Line line;
    int id = 0;

    ArrayList<Vertex> graphVertex;
    ArrayList<Edge> graphEdge;

    Graph g;
    Vertex ver1, ver2;
    Edge e;

    Node n;
    ArrayList<Node> defaultState = new ArrayList<>();

    boolean check;

    int stepPointer;

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
//        textStatus.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldStr, String newStr) {
//                System.out.println(oldStr + " and " + newStr);
//                textStatus.setFill(Color.RED);
//            }
//        });       vi du ve doi gia tri
        boxTop.getChildren().add(textStatus);

        EventHandler<MouseEvent> mouseEventCreateVertex = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                check = true;
                for (int i = 0; i < boxCenter.getChildren().size(); i++){
                    n = boxCenter.getChildren().get(i);
                    if (n.getLayoutBounds().contains(event.getX(), event.getY()) && n.toString().contains("Circle")){
                        //System.out.println("chọn đỉnh");
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
                    circle.setFill(Color.ORANGE);
                    //circle.setStroke(Color.RED);

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
                        } else if (state == 2){
                            state = 1;
                            line.setEndX(circle.getCenterX());
                            line.setEndY(circle.getCenterY());
                            line.setStrokeWidth(3);
                            line.setStroke(Color.AQUAMARINE);

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
                                e = new Edge(ver1, ver2, groupVer, line, true, true, 10f);
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

            }
        };  //tao do thi

        //design button
        Button button1 = new Button("Create");
        button1.setLayoutX(20);
        button1.setOnMouseClicked(eventButton1 -> {
            textStatus.setText("Creating graph");
            stateFinish = false;
            //System.out.println(button1.getLayoutX() + " - " + button1.getLayoutY());

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
        button3.setOnMouseClicked(eventButton3 -> {
            if (stateFinish) {
                textStatus.setText("Running DP");
                boxCenter.getChildren().clear();
                boxCenter.getChildren().addAll(defaultState);

                boxRight.getChildren().clear();

                //Stage newWindow = new Stage();
                BorderPane newWindowRoot = new BorderPane();

                GridPane matrix = new GridPane();
                //matrix.setPrefSize(200, 200);
                //matrix.setStyle("-fx-background-color:red;");
                newWindowRoot.setCenter(matrix);

                Pane messageStep = new Pane();
                //messageStep.setStyle("-fx-background-color:orange;");
                messageStep.setPrefSize(400, 250);
                newWindowRoot.setRight(messageStep);

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
                back.setLayoutX(150);
                back.setLayoutY(15);
                controlBox.getChildren().addAll(next, back);
                newWindowRoot.setBottom(controlBox);

                //Scene windowScene = new Scene(newWindowRoot, 600, 300);

                matrix.setHgap(3);
                matrix.setVgap(3);
                matrix.setGridLinesVisible(true);

                for (int i = 1; i < g.getListVertex().size() + 1; i++){
                    Text text1 = new Text(String.valueOf(i - 1));
                    text1.setFill(Color.GREEN);

                    Text text2 = new Text(String.valueOf(i - 1));
                    text2.setFill(Color.GREEN);

                    matrix.add(text1, 0, i);
                    matrix.add(text2, i, 0);
                }

                Text verSign = new Text("Vertex");
                matrix.add(verSign, 0, 0);
                Text verSign1 = new Text("Vertex");
                matrix.add(verSign1, g.getListVertex().size() + 2, 0);
                Text verSign2 = new Text("Vertex");
                matrix.add(verSign2, 0,g.getListVertex().size() + 2);

                DynamicProgramming dp = new DynamicProgramming();
                dp.executeAlgorithm(g);         //chay thuat toan


                for (int i = 1; i < g.getListVertex().size() + 1; i++){
                    for (int j = 1; j < g.getListVertex().size() + 1; j++){
                        Text text = new Text(String.valueOf(dp.getInitMatrix().get(i - 1).get(j - 1)));
                        text.textProperty().addListener((observableValue, oldStr, newStr) -> text.setFill(Color.RED));
                        matrix.add(text, i, j);
                    }
                }

                stepPointer = 0;

                next.setOnMouseClicked(eventNext -> {
                    if (stepPointer < dp.getListState().size() - 1)
                        stepPointer++;

                    if (dp.getListState().get(stepPointer).getCurrentVertexes() != null ) {

                            for (int i = 0; i < boxCenter.getChildren().size(); i++){
                                if (boxCenter.getChildren().get(i).toString().contains("Circle"))
                                    ((Circle) boxCenter.getChildren().get(i)).setFill(Color.ORANGE);
                            }

                            for (int i = 0; i < dp.getListState().get(stepPointer).getCurrentVertexes().size(); i++)
                                dp.getListState().get(stepPointer).getCurrentVertexes().get(i).getVerCircle().setFill(dp.getListState().get(stepPointer).getVertexPaints().get(i));

                    }
                    else if (((DPState) dp.getListState().get(stepPointer)).getI() != 999) {
                        //System.out.println("change: " + ((DPState) dp.getListState().get(stepPointer)).getChangedValue());
                        for (Node node : matrix.getChildren()){
                            if (GridPane.getColumnIndex(node) != null && GridPane.getRowIndex(node) != null)
                                if (GridPane.getColumnIndex(node) == ((DPState) dp.getListState().get(stepPointer)).getI() + 1 &&
                                GridPane.getRowIndex(node) == ((DPState) dp.getListState().get(stepPointer)).getJ() + 1){
                                    ((Text) node).setText(String.valueOf(((DPState) dp.getListState().get(stepPointer)).getChangedValue()));
                                    System.out.println(((DPState) dp.getListState().get(stepPointer)).getI() + "-" + ((DPState) dp.getListState().get(stepPointer)).getJ());
                                    break;
                                }
                        }
                    }

                    message.setText(dp.getPseudoAndDetailStep(stepPointer));
                    //dp.getPseudoAndDetailStep(stepPointer);

                });

                back.setOnMouseClicked(eventBack -> {
                    if (stepPointer > 0)
                        stepPointer--;

                    if (dp.getListState().get(stepPointer).getCurrentVertexes() != null ) {
                        for (int i = 0; i < boxCenter.getChildren().size(); i++){
                            if (boxCenter.getChildren().get(i).toString().contains("Circle"))
                                ((Circle) boxCenter.getChildren().get(i)).setFill(Color.ORANGE);
                        }

                        for (int i = 0; i < dp.getListState().get(stepPointer).getCurrentVertexes().size(); i++)
                            dp.getListState().get(stepPointer).getCurrentVertexes().get(i).getVerCircle().setFill(dp.getListState().get(stepPointer).getVertexPaints().get(i));

                    }
                    else if (((DPState) dp.getListState().get(stepPointer)).getI() != 999){
                        //mada dekimasen
                    }

                    message.setText(dp.getPseudoAndDetailStep(stepPointer));
                });

                boxRight.getChildren().add(newWindowRoot);
                //newWindow.setScene(windowScene);
                //newWindow.show();
            }else
                textStatus.setText("Chua tao xong graph");

        });
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





        stage.setScene(newScene);
        stage.show();
    }
}
