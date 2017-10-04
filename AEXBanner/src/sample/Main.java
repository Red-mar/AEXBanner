package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Classes.AEXBanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AEXBanner aexBanner = new AEXBanner();
        aexBanner.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
