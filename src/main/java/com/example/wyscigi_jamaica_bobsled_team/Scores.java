package com.example.wyscigi_jamaica_bobsled_team;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Scores {
    private LocalDateTime raceDate; // Unikalny identyfikator wyscigu - tworzony z daty i czasu
    private int trackID; // Id z obiektu klasy Track
    private Player player; // obiekt klasy player
    private double time; // wynik danego wyscigu

    public Scores(LocalDateTime inRaceDate, int inTrackId, Player inPlayer, double inTime) {
        raceDate = inRaceDate; //trzeba zawołać na początku wyścigu - racedate = new Date(System.currentTimeMillis());
        trackID = inTrackId;
        player = inPlayer;
        time = inTime;
    }

    public LocalDateTime getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(LocalDateTime raceDate) {
        this.raceDate = raceDate;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }


    public static Scores parse(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        String[] data = line.split(";");
        LocalDateTime raceDate = LocalDateTime.parse(data[0],formatter);
        int TrackID = Integer.valueOf(data[1]);
        int playerId = Integer.valueOf(data[2]);
        String playerName = data[3];
        Player localPlayer = new Player(playerId,Game.vechicleIndex,playerName);
        double time = Double.valueOf(data[4]);
        return new Scores(raceDate,TrackID, localPlayer, time);
    }
}
