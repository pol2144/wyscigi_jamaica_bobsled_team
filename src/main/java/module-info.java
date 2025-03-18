module com.example.wyscigi_jamaica_bobsled_team {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.wyscigi_jamaica_bobsled_team to javafx.fxml;
    exports com.example.wyscigi_jamaica_bobsled_team;
}