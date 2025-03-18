package com.example.wyscigi_jamaica_bobsled_team;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;

import java.util.Optional;
import java.util.stream.Collectors;

public class Scoreboard {
    private static String FILE_NAME = "src\\times.csv";
    static List <Scores> results = new ArrayList<>();
    private GridPane gridPane;
    private Scene scene;
    private HBox hbox;
    private VBox mainVBox;
    private HBox headerHBox;
    private VBox contentVBox;
    private ScrollPane scrollPane;
    private static final int MAX_ROWS = 20;
    private static final double ROW_HEIGHT = 25.0;



    public static void addScore(LocalDateTime raceDate, int trackID, Player player, double time) {
        Scores newRecord = new Scores(raceDate,trackID,player,time);
        results.add(newRecord);
    }

    public static void saveScores() {
        for (int i = 0; i < results.size(); i++) {
            Scores element = results.get(i);
            String newLine = element.getPlayer().getId() + ";" + element.getRaceDate().toString() + ";" + element.getTrackID() + ";"
                    + element.getPlayer().getId() + ";" + element.getPlayer().getName().toString() + ";" + element.getTime();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
                writer.newLine();
                writer.write(newLine);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Scores> loadScoresLocal() {
        List<Scores> myResults = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME));
            while(scanner.hasNext()) {
                String line = scanner.next();
                myResults.add(Scores.parse(line));
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return myResults;
    }

    public static void localScores() {
        results = loadScoresLocal();
    }

    public Scene getScene() {
        return scene;
    }

    private Scene renderScoreboardLocal(AnchorPane root, final int WIDTH, final int HEIGHT, List<Scores> scoresList) {
        ImageView background = new ImageView("file:src/graphics/menu.png");
        ImageView frame = new ImageView("file:src/graphics/ramka.png");
        root = new AnchorPane();
        root.getChildren().add(background);
        frame.setX(WIDTH/2.0-400);
        frame.setY(HEIGHT/2.0-420);
        root.getChildren().add(frame);
        background.setX(0);
        background.setY(0);

        mainVBox = new VBox(); // Main container VBox
        mainVBox.setPadding(new Insets(200, 10, 10, 40));
        mainVBox.setSpacing(10);

        headerHBox = new HBox(10); // Header HBox
        headerHBox.setPadding(new Insets(20));
        headerHBox.setAlignment(Pos.CENTER);
        // Add a semi-transparent background to the headerHBox
        Color semiTransparentGray = new Color(0.827, 0.827, 0.827, 0.5);
        headerHBox.setBackground(new Background(new BackgroundFill(semiTransparentGray, CornerRadii.EMPTY, Insets.EMPTY)));

        contentVBox = new VBox(10); // Content VBox
        contentVBox.setPadding(new Insets(10, 10, 10, 10));
        contentVBox.setAlignment(Pos.TOP_CENTER);
        // Set preferred width for contentVBox
        // Set a preferred and maximum width for the contentVBox
        contentVBox.setMaxWidth(400); // Set your desired max width
        contentVBox.setPrefWidth(400);
        contentVBox.setMaxHeight(300); // Set your desired max height
        contentVBox.setPrefHeight(300);
        // Set a preferred and maximum width for the scrollPane
        headerHBox.setMaxWidth(400);
        headerHBox.setPrefWidth(400);

        scrollPane = new ScrollPane(contentVBox);
        scrollPane.setFitToWidth(true);

        scrollPane.setMaxWidth(400); // Set your desired max width
        scrollPane.setPrefWidth(400);

        GridPane headerGrid = new GridPane();
        headerGrid.setHgap(25); // Set horizontal gap between columns
        headerGrid.setVgap(10); // Set vertical gap between rows

        // Add headers
        headerGrid.add(new Label("Race Date"), 0, 0);
        headerGrid.add(new Label("Track ID"), 1, 0);
        headerGrid.add(new Label("Player"), 2, 0);
        headerGrid.add(new Label("Time"), 3, 0);
        headerHBox.getChildren().add(headerGrid);

        // Update ContentVBox with data from scoreList
        updateContentVBox(scoresList);

        // Add headers and ScrollPane to the main VBox
        mainVBox.getChildren().addAll(headerHBox, scrollPane);
        // Set alignment for mainVBox to center
        mainVBox.setAlignment(Pos.CENTER);

        // Set the position of mainVBox within the frame
        mainVBox.setLayoutX(WIDTH / 2.0 - 220); // Adjust values as needed
        mainVBox.setLayoutY(HEIGHT / 2.0 - 190); // Adjust values as needed

        root.getChildren().addAll(mainVBox);

        // Set specific margins for the headerHBox to adjust its position within the image
        StackPane.setMargin(headerHBox, new Insets(10, 0, 0, 10)); // Adjust values as needed

        return new Scene(root, WIDTH, HEIGHT);
    }

    private void updateContentVBox(List<Scores> scoresList) {
        contentVBox.getChildren().clear(); // Clear previous content

        GridPane rowGrid = new GridPane();
        rowGrid.setHgap(30); // Set horizontal gap between columns
        rowGrid.setVgap(10); // Set vertical gap between rows

        for (int i = 0; i < scoresList.size(); i++) {
            Scores element = scoresList.get(i);
            String date = element.getRaceDate().toString().replace('T', ' ');
            String name = element.getPlayer().getName();
            String track = Integer.toString(element.getTrackID());
            String score = Double.toString(element.getTime());

            rowGrid.add(new Label(date), 0, i + 1);
            rowGrid.add(new Label(track), 1, i + 1);
            rowGrid.add(new Label(name), 2, i + 1);
            rowGrid.add(new Label(score), 3, i + 1);
        }
        contentVBox.getChildren().add(rowGrid);
    }

    // This function finds results of latest recorded race and
    // return list of results.
    private List<Scores> GetLatestRaceResults() {
        // Find the latest date
        Optional<LocalDateTime> latestDate = results.stream()
                .map(Scores::getRaceDate)
                .max(LocalDateTime::compareTo);

        // Filter the list to include only entries with the latest date
        List<Scores> latestScores = results.stream()
                .filter(score -> score.getRaceDate().equals(latestDate.orElse(null)))
                .collect(Collectors.toList());

        return latestScores;
    }

    // This function finds result for selected track and sort them
    // from best (shortest) time.
    private List<Scores> GetBestResultsForTrack(int trackId) {
        // Filter and sort the list
        List<Scores> filteredAndSortedScores = results.stream()
                .filter(score -> score.getTrackID() == trackId)
                .sorted((s1, s2) -> Double.compare(s1.getTime(), s2.getTime()))
                .collect(Collectors.toList());

        return filteredAndSortedScores;
    }

    // This function finds result for selected player and track and sort them
    // from best (shortest) time.
    private List<Scores> GetBestResultsForPlayerAndTrack(int trackId, String playerName) {
        // Filter and sort the list
        List<Scores> filteredAndSortedScores = results.stream()
                .filter(score -> score.getTrackID() == trackId && score.getPlayer().getName().toString().equals(playerName))
                .sorted((s1, s2) -> Double.compare(s1.getTime(), s2.getTime()))
                .collect(Collectors.toList());

        return filteredAndSortedScores;
    }

    // Show all results
    public Scene renderScoreboard(AnchorPane root, final int WIDTH, final int HEIGHT) {
        return renderScoreboardLocal(root, WIDTH, HEIGHT, results);
    }

    // Show best results for given track
    public Scene renderScoreboard(AnchorPane root, final int WIDTH, final int HEIGHT, int trackId) {
        List<Scores> latestScores = GetBestResultsForTrack(trackId);
        return renderScoreboardLocal(root, WIDTH, HEIGHT, latestScores);
    }

    // Show best results for given track and player
    public Scene renderScoreboard(AnchorPane root, final int WIDTH, final int HEIGHT, int trackId, String playerName) {
        List<Scores> latestScores = GetBestResultsForPlayerAndTrack(trackId, playerName);
        return renderScoreboardLocal(root, WIDTH, HEIGHT, latestScores);
    }
}
