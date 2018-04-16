package sample;


import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



import java.io.IOException;
import java.sql.SQLException;



public class Transcript_Controller {


    @FXML
    public Label name, age, major;
    @FXML
    public TableColumn subject;
    @FXML
    public TableColumn crn;
    @FXML
    public TableColumn title;
    @FXML
    public TableColumn grade;
    @FXML
    public TableColumn credit;
    public static int studentID;
    public static String username;
    private TranscriptDetails transcript;
    @FXML
    private TableView<Transcript> table = new TableView<>();



    public void setTranscriptDetails(int studentIDin) throws SQLException {
        transcript = new TranscriptDetails(studentIDin);
   }
    @FXML
    public void set() {
        final ObservableList<Transcript> data = FXCollections.observableArrayList();
        if (transcript.getID1subject() != null) {
            System.out.println("adding to data list: ID1");
            data.add(new Transcript(transcript.getID1subject(), transcript.getID1crn(), transcript.getID1title(),
                    transcript.getID1grade(), transcript.getID1credit()));
        }


        subject.setCellValueFactory(new PropertyValueFactory<Transcript, String>("subject"));


        crn.setCellValueFactory(new PropertyValueFactory<Transcript, String>
                ("crn"));


        title.setCellValueFactory(new PropertyValueFactory<Transcript, String>
                ("title"));
        grade.setCellValueFactory(new PropertyValueFactory<Transcript,  String>
                ("grade"));
        credit.setCellValueFactory(new PropertyValueFactory<Transcript,  String>
                ("credit"));
            table.setItems(data);

    }
    public void setName(String str){
        name.setText(str);

    }
    public void setAge(String Age){
        age.setText(Age);
    }
    public void setDegree(String maj){
        major.setText(maj);
        }
        public void setSubject(String subject){

        }
    public void setStudentID(int i){
        studentID = i;
    }
    public void setUsername(String name){
        username = name;
    }
}
