package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

//This is the main method to run!


public class Start extends Application {
	public static String holdPath = "", searchPath = "";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(new Image("file:bluemoon.png"));
        primaryStage.setWidth(1125.0D);            primaryStage.setHeight(700.0D);             primaryStage.show();

    }


    public static void main(String[] args) {
    	holdPath = args[0];
    	searchPath = args[1];
        launch(args);
    }
}
