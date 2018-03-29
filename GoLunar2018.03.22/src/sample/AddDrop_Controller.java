package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AddDrop_Controller {
    public static String username;

    @FXML
    public Label addDropLabel;

    @FXML
    public TreeTableColumn statusCol;
    @FXML
    public TreeTableColumn actionCol;
    @FXML
    public TreeTableColumn crnCol;
    @FXML
    public TreeTableColumn subjectCol;
    @FXML
    public TreeTableColumn titleCol;
    @FXML
    public TreeTableColumn creditsCol;
    @FXML
    public TreeTableColumn daysCol;
    @FXML
    public TreeTableColumn timeCol;
    @FXML
    public TreeTableColumn locationCol;
    @FXML
    public TreeTableColumn instructorCol;
    //create object to retrieve student's schedule details from the database
    // with input parameter being the "name" of the student
    private ScheduleDetails schedule;

    //^^studentID
    // variable
    @FXML
    public Button bMain, bRegistration, bPerInfo, bRecords;
    @FXML
    private TableView<Schedule> table = new TableView<>();
    @FXML
    public Label studentIDlabel;

    public static int studentID;

    public void setScheduleDetails(int studentIDin) throws SQLException {
        schedule = new ScheduleDetails(studentIDin);
    }

    @FXML
    public void setEverything() {

        String str = ""+studentID;
        studentIDlabel.setText(str);
        //table = new TableView<Schedule>();
        //rows of the table corresponding to each class
        final ObservableList<Schedule> data = FXCollections.observableArrayList();

        //always check classid# crn if null, CRN methods return null if
        // classID# does not exist
        if (schedule.getID1crn() != null) {
            System.out.println("adding to data list: ID1");
            data.add(new Schedule(schedule.getID1crn(), schedule
                    .getID1subjectName(), schedule.getID1coursetitle(),
                    schedule.getID1credits(), schedule.getID1days(), schedule
                    .getID1time(), schedule.getID1location(), schedule
                    .getID1instructor(), schedule.getID1Desc()));
        }
        if (schedule.getID2crn() != null) {
            System.out.println("adding to data list: ID2");
            data.add(new Schedule(schedule.getID2crn(), schedule
                    .getID2subjectName(), schedule.getID2coursetitle(),
                    schedule.getID2credits(), schedule.getID2days(), schedule
                    .getID2time(), schedule.getID2location(), schedule
                    .getID2instructor(), schedule.getID2Desc()));

        }
        if (schedule.getID3crn() != null) {
            System.out.println("adding to data list: ID3");
            data.add(new Schedule(schedule.getID3crn(), schedule
                    .getID3subjectName(), schedule.getID3coursetitle(),
                    schedule.getID3credits(), schedule.getID3days(), schedule
                    .getID3time(), schedule.getID3location(), schedule
                    .getID3instructor(), schedule.getID3Desc()));
        }
        if (schedule.getID4crn() != null) {
            System.out.println("adding to data list: ID4");
            data.add(new Schedule(schedule.getID4crn(), schedule
                    .getID4subjectName(), schedule.getID4coursetitle(),
                    schedule.getID4credits(), schedule.getID4days(), schedule
                    .getID4time(), schedule.getID4location(), schedule
                    .getID4instructor(), schedule.getID4Desc()));
        }

        crnCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("CRN"));


        subjectCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("subjectName"));


        titleCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("courseTitle"));


        creditsCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("credits"));


        daysCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("days"));


        timeCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
                ("time"));


        locationCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("location"));


        instructorCol.setCellValueFactory(new PropertyValueFactory<Schedule,
                String>("instructor"));

        table.setItems(data);

    }




















    @FXML
    private void mainBClicked(ActionEvent event) throws IOException {       //Main Menu button is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
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
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
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
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
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
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
    }

    @FXML
    public void fillName(String name){
        addDropLabel.setText(name);
    }
    public void setUsername(String name){
        username = name;
    }
    public String getUsername(){
        return username;
    }

}
