package com.example.board;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
//import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.scene.media.MediaPlayer;

//import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

//import javax.swing.text.Style;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    private GraphicsContext gc;
    private Pane drawingArea; // to hold canvas + media
    @Override
    public void start(Stage primaryStage) {
        // Canvas
        Canvas canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // Drawing Events
        canvas.setOnMousePressed(e -> {
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });
        canvas.setOnMouseDragged(e -> {

            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        // Wrap canvas in Pane (to allow stacking images/videos on top)
        drawingArea = new Pane();
        drawingArea.getChildren().add(canvas);

        // Tools
        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(e -> gc.setStroke(colorPicker.getValue()));



        Button clearBtn = new Button("Clear");
        clearBtn.setOnAction(e -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));

        Button addImageBtn = new Button("Add Image");
        addImageBtn.setOnAction(e -> addImage(primaryStage));

        Button addVideoBtn = new Button("Add Video");
        addVideoBtn.setOnAction(e -> addVideo(primaryStage));

        Button saveBtn = new Button("Save as PNG");
        saveBtn.setOnAction(e -> saveCanvasAsImage(primaryStage, drawingArea));
        // adding inline css to the buttons
        clearBtn.setStyle("-fx-background-color: #ff5555;" +
                "-fx-text-fill:black;" +
                "-fx-font-weight:bond;"

        );

        addImageBtn.setStyle(
                "-fx-background-color: #ffaa55;" +
                        "-fx-text-fill:black;" +
                        "-fx-font-weight:bond;"

        );

        addVideoBtn.setStyle(
                "-fx-background-color: #5555ff;" +
                        "-fx-text-fill:black;" +
                        "-fx-font-weight:bond;"

        );
        saveBtn.setStyle(
                "-fx-background-color: #ffaa00;" +
                        "-fx-text-fill:black;" +
                        "-fx-font-weight:bond;"

        );


        // Toolbar
        HBox toolbar = new HBox(10, colorPicker, clearBtn, addImageBtn, addVideoBtn, saveBtn);
        toolbar.setStyle("-fx-padding: 10; -fx-background-color: #ddd;");

        // Layout
        BorderPane root = new BorderPane();
        root.setTop(toolbar);
        root.setCenter(drawingArea);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add("styles.css");

        primaryStage.setTitle("Interactive Whiteboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addImage(Stage primaryStage) {
        FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image files", "*.png", ".jpg","jpeg" )
        );
        File file= fileChooser.showOpenDialog(primaryStage);
        if (file!=null){
            Image image = new Image( file.toURI().toString() );
            ImageView imageView= new ImageView(image);
            imageView.setFitWidth(200);
            imageView.setPreserveRatio(true);
            imageView.setLayoutY(50);
            imageView.setLayoutX(50);


            drawingArea.getChildren().add(imageView);
        }

    }

    private void saveCanvasAsImage(Stage primaryStage, Pane drawingArea) {
    }

    private void addVideo(Stage primaryStage) {
        FileChooser fileChooser2 = new FileChooser();
        fileChooser2.setTitle("select a video of your choice");
        FileChooser.ExtensionFilter videoFilter = new FileChooser.ExtensionFilter("video files","*.mp4","*.flv","*m4v");
        fileChooser2.getExtensionFilters().add(videoFilter);

        File file2= fileChooser2.showOpenDialog(primaryStage);
        if (file2!= null){
            System.out.println("Choose a video of your choice" + file2.getAbsolutePath() );
            Media media= new Media(file2.toURI().toString());
            MediaPlayer mediaPlayer= new MediaPlayer(media);
            MediaView mediaView= new MediaView(mediaPlayer);
            mediaView.setFitWidth(100);
            mediaView.setPreserveRatio(true);
            mediaPlayer.setAutoPlay(true);
            drawingArea.getChildren().add(mediaView);

        }



    }


    public static void main(String[] args) {
        launch();
    }
}