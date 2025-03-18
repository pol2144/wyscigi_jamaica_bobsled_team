package com.example.wyscigi_jamaica_bobsled_team;
import javafx.scene.Scene;

public class Player {

    private String name;
    private int id;
    private double time;

    private boolean forwardKeyIsPressed;
    private boolean backwardKeyIsPressed;
    private boolean leftKeyIsPressed;
    private boolean rightKeyIsPressed;

    private Vehicle vehicle;

    private Vehicle DarkBlue = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 0);
    private Vehicle Gray     = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 1);
    private Vehicle Green    = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 2);
    private Vehicle LightBlue= new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 3);
    private Vehicle Pink     = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 4);
    private Vehicle Red      = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 5);
    private Vehicle RedStriped= new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0),2, 0.01, 5, 1, 0, 6);
    private Vehicle Yellow   = new Vehicle((int)(Game.WIDTH/2.0), (int)(Game.HEIGHT/2.0), 2, 0.01, 5, 1, 0, 7);

    private Vehicle[] cars = {
            DarkBlue, Gray, Green, LightBlue,
            Pink, Red, RedStriped, Yellow
    };

    public Player(int id, int vehicleIndex, String name) {
        this.id = id;
        this.name = name;

        this.vehicle = cars[vehicleIndex];
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getTime() {
        return time;
    }

    public void initilizeMovement(Scene scene) {

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    forwardKeyIsPressed = true;
                    break;
                case S:
                    backwardKeyIsPressed = true;
                    break;
                case A:
                    leftKeyIsPressed = true;
                    break;
                case D:
                    rightKeyIsPressed = true;
                    break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case W:
                    forwardKeyIsPressed = false;
                    break;
                case S:
                    backwardKeyIsPressed = false;
                    break;
                case A:
                    leftKeyIsPressed = false;
                    break;
                case D:
                    rightKeyIsPressed = false;
                    break;
                default:
                    break;
            }
        });
    }


    public void update() {
        vehicle.updateMovement(
                forwardKeyIsPressed,
                backwardKeyIsPressed,
                leftKeyIsPressed,
                rightKeyIsPressed
        );
    }

    public void place_obstacle() {
        //waiting for track and obstacle
    }

    public void collect_bonus() {
        //waiting for bonus
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
