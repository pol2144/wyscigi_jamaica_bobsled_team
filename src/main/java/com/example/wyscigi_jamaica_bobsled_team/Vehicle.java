package com.example.wyscigi_jamaica_bobsled_team;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Vehicle {

    private ImageView body;
    private int positionX;
    private int positionY;

    private double speed;
    private double acceleration;
    private int max_speed;
    private int handling;
    private int damage;

    private ArrayList<Coordinate> coordinates = new ArrayList<>();

    private double rotation = 0;
    private double rotationSpeed = 1;

    private int nitroPower;
    private boolean nitro;

    private static final Image[] vechiclesImgs = {
            new Image("file:src/graphics/cars/carDarkBlue.png"),
            new Image("file:src/graphics/cars/carGray.png"),
            new Image("file:src/graphics/cars/carGreen.png"),
            new Image("file:src/graphics/cars/carLightBlue.png"),
            new Image("file:src/graphics/cars/carPink.png"),
            new Image("file:src/graphics/cars/carRed.png"),
            new Image("file:src/graphics/cars/carRedStriped.png"),
            new Image("file:src/graphics/cars/carYellow.png")
    };

    public Vehicle(int positionX, int positionY,
                   double speed, double acceleration,
                   int max_speed, int handling,
                   int nitroPower, int vehicleIndex) {
        this.body = new ImageView(vechiclesImgs[vehicleIndex]);
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.acceleration = acceleration;
        this.max_speed = max_speed;
        this.handling = handling;
        this.damage = 0;
        this.nitroPower = nitroPower;
        this.nitro = false;
    }

    public void updateMovement(boolean forward, boolean backward, boolean left, boolean right) {

        if (forward) {
            if (speed < max_speed) {
                speed += acceleration;
            }
        }
        if (backward) {
            if (speed > -max_speed) {
                speed -= acceleration;
            }
        }

        if (!forward && speed > 0) {
            speed -= acceleration * 5;
            if (speed < 0) speed = 0;
        }
        if (!backward && speed < 0) {
            speed += acceleration * 5;
            if (speed > 0) speed = 0;
        }


        if (left) {
            rotation -= rotationSpeed;
        }
        if (right) {
            rotation += rotationSpeed;
        }


        double radians = Math.toRadians(rotation);

        double deltaX = Math.sin(radians) * speed;
        double deltaY = -Math.cos(radians) * speed;

        body.setX(body.getX() + deltaX);
        body.setY(body.getY() + deltaY);


        body.setRotate(rotation);
    }

    public void getCarsOutline(){
        int index = 0;
        for (int i = 0; i < vechiclesImgs.length; i++) {
            if(vechiclesImgs[i] == body.getImage()){
                for (int j = 0; j < vechiclesImgs[i].getWidth(); j++) {
                    for (int k = 0; k < vechiclesImgs[i].getHeight(); k++) {
                        if (vechiclesImgs[i].getPixelReader().getColor(j, k).equals(Color.BLACK)) {
                            coordinates.add(index,new Coordinate(j,k));
                            index++;
                        }
                    }
                }
            }
        }
    }

    public void setVehicleImage(Image imageView) {
        body.setImage(imageView);
    }

    public void move(int delta_time) {
        // waiting for track logic
    }

    public void useNitro() {
        nitro = true;
    }

    public void applyDamage(int amount) {
        damage += amount;
    }

    public void repair() {
        if (damage == 0) {
            return;
        }
        damage = damage - (damage / 10);
    }

    public void render(AnchorPane root){
        body.setX(positionX);
        body.setY(positionY);
        body.setRotate(rotation);

        root.getChildren().add(body);
    }

    public int getPositionX() {
        return positionX;
    }
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    public int getPositionY() {
        return positionY;
    }
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getAcceleration() {
        return acceleration;
    }
    public int getMax_speed() {
        return max_speed;
    }
    public int getDamage() {
        return damage;
    }
    public int getNitroPower() {
        return nitroPower;
    }
    public boolean isNitro() {
        return nitro;
    }
    public ImageView getBody() {
        return body;
    }
}
