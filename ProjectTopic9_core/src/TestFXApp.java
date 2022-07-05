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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;

public class TestFXApp extends Application {
    int state = 1;
    Line line;
    int id = 0;

    ArrayList<Vertex> graphVertex;
    ArrayList<Edge> graphEdge;

    Graph g;

    Vertex ver1, ver2;

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
        boxRight.setPrefSize(100, 200);
        root.setRight(boxRight);

        Pane boxBottom = new Pane();
        boxBottom.setPrefSize(600, 100);

        Text text = new Text("\nThao tác trong ô trên,tạo đỉnh click chuột, tạo cạnh thì click vào đỉnh đầu và cuối");
        text.setFont(Font.font("Roboto Blacak", FontWeight.BOLD, 20));
        text.setFill(Color.AZURE);
        text.setStroke(Color.GREEN);
        text.setTextAlignment(TextAlignment.JUSTIFY);
        text.setWrappingWidth(500);
        boxBottom.getChildren().add(text);

        root.setBottom(boxBottom);

        Pane boxTop = new Pane();
        boxTop.setPrefSize(600, 100);
        root.setTop(boxTop);

        Scene newScene = new Scene(root, 600, 600);
        newScene.setFill(Color.ORANGE);


        EventHandler<MouseEvent> mouseEventCreateVertex = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                boolean check = true;
                for (int i = 0; i < boxCenter.getChildren().size(); i++){
                    if (boxCenter.getChildren().get(i).getLayoutBounds().contains(event.getX(), event.getY())){
                        System.out.println("chọn đỉnh");
                        check = false;
                        break;
                    }
                }

                if (check) {
                    Circle circle = new Circle();
                    circle.setCenterY(event.getY());
                    circle.setCenterX(event.getX());
                    circle.setRadius(20);
                    circle.setFill(Color.AQUAMARINE);
                    circle.setStroke(Color.RED);
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

                            Polygon triangle = new Polygon();
                            triangle.getPoints().addAll(new Double[]{
                                    circle.getCenterX(), circle.getCenterY(),
                                    circle.getCenterX() + 5, circle.getCenterY() + 5,
                                    circle.getCenterX() - 5, circle.getCenterY() - 5
                            });

                            for (int i = 0; i < graphVertex.size(); i++){
                                if (circle.equals(graphVertex.get(i).getVerCircle())){
                                    System.out.println("set end");
                                    ver2 = graphVertex.get(i);
                                }
                            }

                            ArrayList<Vertex> groupVer  = new ArrayList<>();
                            groupVer.add(ver1);
                            groupVer.add(ver2);
                            graphEdge.add(new Edge(ver1, ver2, groupVer, line, true, true, 10f));

                            boxCenter.getChildren().addAll(line, triangle);
                        }

                    });
                    System.out.println("tạo đỉnh ok");

                    // tao dinh cho nay
                    graphVertex.add(new Vertex(id, circle));
                    Text text = new Text(event.getX(), event.getY() - 20, String.valueOf(id));
                    id++;
                    boxCenter.getChildren().addAll(circle, text);
                }


            }
        };

        //design button
        Button button1 = new Button("Create");
        button1.setLayoutX(20);
        button1.setOnMouseClicked(eventButton1 -> {
            System.out.println(button1.getLayoutX() + " - " + button1.getLayoutY());

            boxCenter.getChildren().clear();
            boxCenter.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);

            graphVertex = new ArrayList<>();
            graphEdge = new ArrayList<>();
            id = 0;
        });
        boxRight.getChildren().add(button1);

        Button button2 = new Button("Finish");
        button2.setLayoutX(20);
        button2.setLayoutY(50);
        button2.setOnMouseClicked(eventButton2 -> {
            System.out.println(button2.getLayoutX() + " - " + button2.getLayoutY());

            boxCenter.removeEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);
            Node n;
            for (int i = 0; i < boxCenter.getChildren().size(); i++){
                n = boxCenter.getChildren().get(i);
                n.setOnMouseClicked(replaceEvent->{
                    System.out.println("remove done");
                });
            }

            g = new Graph(graphEdge, graphVertex);
            g.showGraph();
        });
        boxRight.getChildren().add(button2);



        stage.setScene(newScene);
        stage.show();
    }
}
