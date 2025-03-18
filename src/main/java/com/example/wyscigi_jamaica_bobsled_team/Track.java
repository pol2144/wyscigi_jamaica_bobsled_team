package com.example.wyscigi_jamaica_bobsled_team;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Track {

    private int id;
    private Image[] images = {new Image("file:src/graphics/testTrack.jpg")};
    private TrackColor[][] trackcolor = {{new TrackColor(Color.color(0,0,0),Reaction.normal),new TrackColor(Color.color(1,0.9568627450980392,0),Reaction.normal),new TrackColor(Color.color(0.93333333333,0.9803921568,0.9803921567),Reaction.wall),new TrackColor(Color.color(0.9137254902960,0.1019607843137,0.1254901960784),Reaction.wall)}};
    private ImageView display;

    public Track( int id){
        display = new ImageView(images[id]);
        this.id = id;
    }

    public void bounds(Vehicle vehicle){

    }

    public void getTrackSurface(){
        for (int i = 0; i < images[id].getWidth(); i++) {
            for (int j = 0; j < images[id].getHeight(); j++) {
                Color trackColor = images[id].getPixelReader().getColor(i,j);
            }
        }
    }

    public void render(AnchorPane root){
        display.setX(0);
        display.setY(0);
        display.setFitHeight(1080);
        display.setFitWidth(1920);
//        root.getChildren().add(display);
        BackgroundFill backgroundFill = new BackgroundFill(new ImagePattern(images[id]), CornerRadii.EMPTY, Insets.EMPTY);
        root.setBackground(new Background(backgroundFill));
    }
}
