package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;


public class Registration_Controller {
    @FXML
    private void mainBClicked(ActionEvent event) throws IOException {       //Main Menu button is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void recordsBClicked(ActionEvent event) throws IOException {        //Records button is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Records.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Records Menu");
        stage.show();
    }
    @FXML
    private void infoBClicked(ActionEvent event) throws IOException {       //Personal Info button is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("PersonalInfo.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Personal Information Menu");
        stage.show();
    }
    @FXML
    private void registrationBClicked(ActionEvent event) throws IOException {       //Registration button is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Menu");
        stage.show();
    }
    @FXML
    private void classScheduleSearchClicked(ActionEvent event) throws IOException {     //Hyper Link for class Schedule Search is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Hyperlink)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Search");
        stage.show();
    }






}
