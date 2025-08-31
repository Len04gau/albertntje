package com.example.lengau;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import  javafx.scene.layout.Pane;
import  javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;


import java.awt.*;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        Image [] images={
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/c1.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/c2.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/c3.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/c4.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/se.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/lo.jpg"),
                new Image("file:C:/Users/dell/Documents/DMSE/DMSE Y3S1/Interactive Multimedia/LENGAU/smooth.jpg"),


        };
        ImageView imageView= new ImageView(images[0]);
        imageView.setFitHeight(400);
        imageView.setFitWidth(400);
        imageView.setLayoutX(500);
        imageView.setLayoutY(100);
        imageView.setCursor(Cursor.HAND);
        imageView.setPreserveRatio(true);

        Label caption= new Label("The movie of your preference");
        caption.setStyle(
                "-fx-font-size:14px; -fx-font-weight:bold;-fx-text-fill:blue"
        );
        caption.setLayoutX(520);
        caption.setLayoutY(520);


        /// Image enlargement when clicked
        final boolean[] iszoomed={false};
        imageView.setOnMouseClicked(e->{
            if (!iszoomed[0]){
                imageView.setFitWidth(800);
                imageView.setFitHeight(800);
            } else {
                imageView.setFitWidth(400);
                imageView.setFitHeight(400);
            }
            iszoomed[0]=!iszoomed[0];
        });


        int[] currentindex={0};

        Button nextButton= new Button("NEXT Movie");
        Button previousButton= new Button("PREVIOUS Movie");

        nextButton.setOnAction(e->{
            currentindex[0]=(currentindex[0]+1) % images.length;
            imageView.setImage(images[currentindex[0]]);
        });

        previousButton.setOnAction(e->{
            currentindex[0]=(currentindex[0]-1 % images.length);
            imageView.setImage(images[currentindex[0]]);

        });
        HBox buttons= new HBox(10,previousButton,nextButton);
        buttons.setLayoutX(120);
        buttons.setLayoutY(590);
        buttons.setCursor(Cursor.HAND);

        //button css ans image css
        nextButton.setStyle(
                "-fx-background-color: #CAF50;" +
                        "-fx-text-fill:blue;"+
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-cursor:hand;"

        );
        previousButton.setStyle(
                "-fx-background-color: #CAF50;" +
                        "-fx-text-fill:blue;"+
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-cursor:hand;"
        );
        imageView.setStyle(
                "-fx-effect:dropshadow( gaussian,gray, 10,0.5,0,0 );" +
                        "-fx-border-radius:45;"+
                        "-fx-background-radius:45;"+
                        "-fx-clip:rect( 0px,100px,100px,0px ;)"
        );
        Label heading= new Label("Maseru Popcorn House");
        heading.setStyle("-fx-font-size:54px; -fx-font-weight:bold;-fx-text-fill:blue");
        heading.setLayoutX(320);
        heading.setLayoutY(20);








        Pane pane= new Pane();
        pane.getChildren().addAll(imageView,buttons,caption,heading);
        pane.setStyle(
                "-fx-background-color:black"
        );

       Scene scene= new Scene(pane,300,400);
        stage.setTitle("Cartoon world");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}