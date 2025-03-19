package com.example.wyscigi_jamaica_bobsled_team;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;

public class Game extends Application {
    public static Track track;
    public static Player player;
    public static int vechicleIndex = 0;
    public static int trackID = 0;
    //    public static ArrayList<Opponent> opponents;
    //    public static ScoreBoard scoreBoard;
    public static final int WIDTH = 1920;
    public static final int HEIGHT = 1080;
    public static javax.swing.Timer timer;
    public static int timeElapsed = 0; // czas miniony od początku wyścigu-Xmx256M -Xms256M
    public static AnchorPane menuPane, customizePane, trackSelectPane, racePane, scorePane;
    public static Stage stage;
    public static Scene menuScene, raceScene, scoreScene, customizeScene, trackSelectScene;
    public static gameState gameState;

    public static void main(String[] args) {
        launch(args);
    }

    public static void exitGame() {
        timer.stop();
        stage.close();
    }

    public static void showCustomization() {
        gameState = gameState.VechicleCustomization;
        if(customizeScene == null) {
            customizeScene = UIManager.renderVechicleCustomization(customizePane, WIDTH, HEIGHT);//renderowanie sceny jeżeli ta jeszcze nie istnieje
        }
        stage.setScene(customizeScene);
    }

    public static void showTrackSelection() {
        gameState = gameState.trackSelection;
        if(trackSelectScene == null) {
            trackSelectScene = UIManager.renderTrackSelection(trackSelectPane, WIDTH, HEIGHT);//renderowanie sceny jeżeli ta jeszcze nie istnieje
        }
        stage.setScene(trackSelectScene);
    }

    public static void setPlayerVechicle(int Index) {
        vechicleIndex = Index;
    }

    public static void setTrack(int trackIndex) {
        trackID = trackIndex;
    }

    public static void startGame() {
        track = new Track(trackID);

        player = new Player(1, vechicleIndex, "Player");


        raceScene = UIManager.renderRace(track, player, racePane, WIDTH, HEIGHT);
        gameState = gameState.racing;
        stage.setScene(raceScene);

        player.initilizeMovement(raceScene);


        timer.start();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.initStyle(StageStyle.UNDECORATED);


        timer = new Timer(10, e-> update());

        showMenu();
        stage.show();
    }

    public static void showMenu() {
        gameState = gameState.menu;
        if(menuScene == null) {
            menuScene = UIManager.renderMenu(menuPane, WIDTH, HEIGHT);
        }
        stage.setScene(menuScene);
    }

    public static void showScoreboard() {
        gameState = gameState.finished;
        if(scoreScene == null) {
            scoreScene = UIManager.renderMenu(scorePane, WIDTH, HEIGHT);
        }
        stage.setScene(scoreScene);
    }

    public static void restartGame(){
        timer.stop();
        timeElapsed = 0;
        //TODO restart gry
    }

    private void update() {
        timeElapsed++;
        UIManager.updateUI(timeElapsed);

        if (player != null) {
            player.update();
        }
    }
}

