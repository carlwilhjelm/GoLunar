package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class AddStudentsController {


    public static String username;
    public static int studentID;
    private Connection connect = null;
    ResultSet rs = null;
    Statement st = null;

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
    private void addStudentClicked(ActionEvent event)  throws IOException, SQLException {
        connect = SqliteConnection.Connector();
        st = connect.createStatement();


    }

    @FXML
    private void deleteStudentClicked(ActionEvent event)  throws IOException, SQLException{

    }

}
