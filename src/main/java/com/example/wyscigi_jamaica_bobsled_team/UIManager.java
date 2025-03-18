package com.example.wyscigi_jamaica_bobsled_team;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UIManager {
    //dodanie obrazow
    private static final Image[] trackImgs = {new Image("file:src/graphics/testTrack.jpg")};
    private static final Image[] vechiclesImgs = {new Image("file:src/graphics/cars/carDarkBlue.png"),new Image("file:src/graphics/cars/carGray.png"),new Image("file:src/graphics/cars/carGreen.png"),new Image("file:src/graphics/cars/carLightBlue.png"),new Image("file:src/graphics/cars/carPink.png"),new Image("file:src/graphics/cars/carRed.png"),new Image("file:src/graphics/cars/carRedStriped.png"),new Image("file:src/graphics/cars/carYellow.png")};
    private static final Image backButtonImg = new Image("file:src/graphics/backButtonSmall.png");
    private static final Image exitButtonImg = new Image("file:src/graphics/exitButton.png");
    private static final Image customizeButtonImg =  new Image("file:src/graphics/dostosujButton.png");
    private static final Image playButtonImg = new Image("file:src/graphics/startButton.png");
    private static final Image playButtonSmallImg = new Image("file:src/graphics/playButtonSmall.png");
    private static final Image menuImg = new Image("file:src/graphics/menu.png");
    private static final Image miataImg = new Image("file:src/graphics/miata.png");
    private static final Image ramkaImg = new Image("file:src/graphics/ramka.png");
    private static final Image gameNameImg = new Image("file:src/graphics/gameName.png");
    private static final Image rightArrowImg = new Image("file:src/graphics/arrowRight.png");
    private static final Image leftArrowImg = new Image("file:src/graphics/arrowLeft.png");
    private static int vechicleIndex = 0;
    private static int trackIndex = 0;
    private static boolean customCar = false;
    public static Scene renderMenu(AnchorPane root, final int WIDTH, final int HEIGHT) {//renderowanie menu
        VBox buttonBox = new VBox();//tworzenie VBoxa na przyciski
        //stworzenie obrazów
        ImageView background = new ImageView(menuImg);
        ImageView miata = new ImageView(miataImg);
        ImageView ramka = new ImageView(ramkaImg);
        ImageView gameName = new ImageView(gameNameImg);
        ImageView playButton = new ImageView(playButtonImg);
        ImageView customizeButton = new ImageView(customizeButtonImg);
        ImageView exitButton = new ImageView(exitButtonImg);
        root = new AnchorPane();
//        root.getChildren().add(background);//dodanie tła
//        //ustawienie współżędnych tła
//        background.setX(0);
//        background.setY(0);
        miata.setX(20);
        miata.setY(50);
        ramka.setX(550);
        ramka.setY(10);
        ramka.setFitHeight(900);
        ramka.setFitWidth(800);
        root.getChildren().addAll(miata,ramka);
        buttonBox.setSpacing(30);//ustwienie odstępów
        buttonBox.getChildren().addAll(gameName,playButton,customizeButton,exitButton);//dodanie przycisków
        playButton.setOnMouseClicked(e->Game.showTrackSelection());//przypisanie akcjii przyisku graj
        customizeButton.setOnMouseClicked(e->Game.showCustomization());//przypisanie akcjii przycisku dostosowanie pojazdu
        exitButton.setOnMouseClicked(event -> Game.exitGame());//przypisanie akcjii przycisku wyjscia
        //przypisanie kordynatów VBoxa
        buttonBox.setLayoutY(HEIGHT/2.0 - 300);
        buttonBox.setLayoutX(WIDTH/2.0 - 190);
        root.getChildren().add(buttonBox);//dodanie VBoxa do roota
        return new Scene(root, WIDTH, HEIGHT);//zwrócenie sceny
    }

    public static Scene renderTrackSelection(AnchorPane root, final int WIDTH, final int HEIGHT) {//renderowanie wyboru trasy
        // stworzenie Obrazów
        ImageView miata = new ImageView(miataImg);
        ImageView ramka = new ImageView(ramkaImg);
        ImageView displayedTrack = new ImageView(trackImgs[trackIndex]);//obraz z aktualnie wybranym pojazdem
        ImageView leftArrow = new ImageView(leftArrowImg);
        ImageView rightArrow = new ImageView(rightArrowImg);
        ImageView background = new ImageView(menuImg);
        ImageView backButton = new ImageView(backButtonImg);
        ImageView playButton = new ImageView(playButtonSmallImg);
        HBox buttonBox = new HBox();//stworzenie Hboxa na przyciski
        buttonBox.setSpacing(5);//ustwienie odstępów
        root = new AnchorPane();
//        root.getChildren().add(background);//dodanie tła
        //ustawienie współżędnych tłą
//        background.setX(0);
//        background.setY(0);
        miata.setX(20);
        miata.setY(50);
        ramka.setX(550);
        ramka.setY(10);
        ramka.setFitHeight(900);
        ramka.setFitWidth(800);
        root.getChildren().addAll(miata,ramka);
        backButton.setOnMouseClicked(event -> {
            if(customCar) Game.showCustomization();
            else Game.showMenu();
        });//ustawienie akcji przycisku wstecz
        playButton.setOnMouseClicked(e->{
            Game.setTrack(trackIndex);
            Game.startGame();
        });//ustawienie akcji przycisku graj
        //ustawienie współżędnych HBoxa
        buttonBox.setLayoutX(WIDTH/2.0 - 245);
        buttonBox.setLayoutY(HEIGHT/2.0 + 270);
        //dodanie przycisków
        buttonBox.getChildren().add(backButton);
        buttonBox.getChildren().add(playButton);
        root.getChildren().add(buttonBox);//dodanie HBoxa do roota
        HBox trackBox = new HBox();//stworzenie HBoxa na pojazd
        trackBox.setSpacing(50);//ustawienie odstępów
        displayedTrack.setFitWidth(300);
        displayedTrack.setFitHeight(150);
        trackBox.setAlignment(Pos.CENTER);//ustawienie pozycji
        leftArrow.setOnMouseClicked(e->{
            trackIndex--;
            if(trackIndex < 0) trackIndex = trackImgs.length - 1;
            displayedTrack.setImage(trackImgs[trackIndex]);
        });
        trackBox.getChildren().add(leftArrow);
        trackBox.getChildren().add(displayedTrack);
        rightArrow.setOnMouseClicked(e->{
            trackIndex++;
            if(trackIndex > trackImgs.length - 1) trackIndex = 0;
            displayedTrack.setImage(trackImgs[trackIndex]);
        });
        trackBox.getChildren().add(rightArrow);
        trackBox.setLayoutX((WIDTH/2.0) - 220);
        trackBox.setLayoutY((HEIGHT/2.0) - 150 );
        root.getChildren().add(trackBox);//dodanie HBoxa na pojazd do roota
        return new Scene(root, WIDTH, HEIGHT);//zwrócenie sceny
    }

    public static Scene renderVechicleCustomization(AnchorPane root, final int WIDTH, final int HEIGHT) {//renderowanie dostosowanie pojazdu
        //tworzenie obrazów
        ImageView miata = new ImageView(miataImg);
        ImageView ramka = new ImageView(ramkaImg);
        ImageView displayedVechicle = new ImageView(vechiclesImgs[vechicleIndex]);//obraz z aktualnie wybranym pojazdem
        ImageView leftArrow = new ImageView(leftArrowImg);
        ImageView rightArrow = new ImageView(rightArrowImg);
        ImageView background = new ImageView(menuImg);
        ImageView backButton = new ImageView(backButtonImg);
        ImageView playButton = new ImageView(playButtonSmallImg);
        root = new AnchorPane();
        HBox buttonBox = new HBox();//stworzenie HBoxa na przyciski
//        root.getChildren().add(background);//dodanie tła do roota
        //ustawienie pozycji tła
//        background.setX(0);
//        background.setY(0);
        miata.setX(20);
        miata.setY(50);
        ramka.setX(550);
        ramka.setY(10);
        ramka.setFitHeight(900);
        ramka.setFitWidth(800);
        root.getChildren().addAll(miata,ramka);
        //ustawienie współżędnych HBoxa
        buttonBox.setLayoutX(WIDTH/2.0 - 245);
        buttonBox.setLayoutY(HEIGHT/2.0 + 270);
        buttonBox.setSpacing(5);//ustawienie odstępów
        backButton.setOnMouseClicked(event -> Game.showMenu());//ustawienie akcji przycisku cofnij
        buttonBox.getChildren().add(backButton);//dodaie przycisku cofnij
        buttonBox.getChildren().add(playButton);//dodanie przycisku graj
        playButton.setOnMouseClicked(e-> {
            Game.setPlayerVechicle(vechicleIndex);
            customCar = true;
            Game.showTrackSelection();
        });//ustawienie akcji przycisku graj
        root.getChildren().add(buttonBox);//dodanie HBoxa do roota
        HBox vechicleBox = new HBox();//stworzenie HBoxa na pojazd
        vechicleBox.setSpacing(50);//ustawienie odstępów
        vechicleBox.setAlignment(Pos.CENTER);//ustawienie pozycji
        leftArrow.setOnMouseClicked(e->{
            vechicleIndex--;
            if(vechicleIndex < 0) vechicleIndex = vechiclesImgs.length - 1;
            displayedVechicle.setImage(vechiclesImgs[vechicleIndex]);
        });
        vechicleBox.getChildren().add(leftArrow);
        displayedVechicle.setFitHeight(110);
        displayedVechicle.setFitWidth(60);
        vechicleBox.getChildren().add(displayedVechicle);
        rightArrow.setOnMouseClicked(e->{
            vechicleIndex++;
            if(vechicleIndex > vechiclesImgs.length - 1) vechicleIndex = 0;
            displayedVechicle.setImage(vechiclesImgs[vechicleIndex]);
        });
        vechicleBox.getChildren().add(rightArrow);
        vechicleBox.setLayoutX((WIDTH/2.0) - 100);
        vechicleBox.setLayoutY((HEIGHT/2.0) - 150 );
        root.getChildren().add(vechicleBox);//dodanie HBoxa na pojazd do roota
        return new Scene(root, WIDTH, HEIGHT);//zwrócenie sceny
    }

    public static void renderUI() {//renderowanie interfejsu uytkownika
        //TODO stworz interfejs uzytkownika
    }
    public static void updateUI(int timeElapsed) {//aktualizacja elementów interfejsu użytkownika
        //TODO aktualizuj interfejs uzytkownika
    }

    public static Scene renderRace(Track track,Player player, AnchorPane racePane, int width, int height) {
        racePane = new AnchorPane();
        track.render(racePane);
        Obstacle ocstacle = new Obstacle(200, 100, 20, 30);
        ocstacle.render(racePane);
        player.getVehicle().render(racePane);
        return new Scene(racePane, width, height);
    }

    public static Scene renderScoreboard(AnchorPane scorePane, int width, int height) {
        scorePane = new AnchorPane();
        Scoreboard.localScores();
        ImageView imageView = new ImageView("");
        imageView.setX(width);
        imageView.setY(height);
        VBox score = new VBox();
        return new Scene(scorePane,width, height);
    }
}
