package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuController {


    @FXML
    private void logoutClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
    }


    @FXML
    private void addDeleteClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Hyperlink)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddClasses.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Create or Delete Courses");
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
    }


    @FXML
    private void addRStudentsClicked(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Hyperlink)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddStudents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Add or Remove Students");
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
    }


}