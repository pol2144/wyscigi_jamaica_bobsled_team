package com.example.wyscigi_jamaica_bobsled_team;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;

    private ImageView obstacle = new ImageView(new Image("file:src/graphics/backButtonSmall.png")) ;

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public void render(AnchorPane root) {
        obstacle.setX(getX());
        obstacle.setY(getY());
        obstacle.setFitHeight(getHeight());
        obstacle.setFitWidth(getWidth());
        root.getChildren().add(obstacle);
    }
}
