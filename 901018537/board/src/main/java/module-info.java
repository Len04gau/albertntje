module com.example.board {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.swing;


    opens com.example.board to javafx.fxml;
    exports com.example.board;
}