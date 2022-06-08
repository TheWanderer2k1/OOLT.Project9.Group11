module com.group11.interactivegraph {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.group11.interactivegraph to javafx.fxml;
    exports com.group11.interactivegraph;
}