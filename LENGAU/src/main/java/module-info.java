module com.example.lengau {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.lengau to javafx.fxml;
    exports com.example.lengau;
}