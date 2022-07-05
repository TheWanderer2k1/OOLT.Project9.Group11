import com.group11.topic9.graph.Graph;
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class TestFXApp extends Application {
    int state = 1;
    Line line;

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
//                        boxCenter.getChildren().get(i).setStyle("-fx-background-color: red;");
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
                    circle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (state == 1) {
                                System.out.println("set start");
                                line = new Line();
                                line.setStartX(circle.getCenterX());
                                line.setStartY(circle.getCenterY());
                                state++;
                            } else if (state == 2){
                                System.out.println("set end");
                                state = 1;
                                line.setEndX(circle.getCenterX());
                                line.setEndY(circle.getCenterY());
                                boxCenter.getChildren().add(line);
                            }

                        }
                    });
                    System.out.println("tạo đỉnh ok");
                    boxCenter.getChildren().add(circle);
                }


            }
        };


        boxCenter.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseEventCreateVertex);


        stage.setScene(newScene);
        stage.show();
    }
}
