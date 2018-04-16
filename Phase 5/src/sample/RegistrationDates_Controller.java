package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;

public class RegistrationDates_Controller {
    @FXML
    public TableView<Dates> table;
    @FXML
    public TableColumn termCol;
    @FXML
    public Button submitBt;
    @FXML
    public Button backBT;

    @FXML
    public void initialize() {

        submitBt.setText("Submit");


        final ObservableList<Dates> data = FXCollections.observableArrayList();

        data.add(new Dates("Fall 2018", "Mar 29, 2018   09:00 am     Aug " +
                "08," + " 2018 05:00pm", "Aug 10, 2018   01:00 pm     Aug " +
                "24, 2018 " + "05:00 " + "pm"));
        data.add(new Dates("Spring  2018", "Oct 26, 2017   09:00 am     Jan" +
                " " + "07, 2018 11:59 pm","Jan 08, 2018   07:00 am    Jan " +
                "12, " + "2018 " + " 05:00 pm"));
        data.add(new Dates("Fall 2017", "Mar 31, 2017  09:00 am      Aug " +
                "08," +
                " " + "2017 05:00 pm", "Aug 09, 2017   12:00 pm    Aug 25, " +
                "2017 05:00 " + "pm"));

        termCol.setCellValueFactory(new PropertyValueFactory<Dates, String>
                ("Term"));


        table.setItems(data);
    }

    @FXML
    public void clickSubmitBT(MouseEvent event) {

        if ((table.getSelectionModel()
                .isSelected(0) || table.getSelectionModel()
                .isSelected(1) || table.getSelectionModel()
                .isSelected(2))) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Go Lunar 1.0");     //change stage title if wanted
            alert.setHeaderText("Registration Time Tickets");

            //do not edit below
            alert.setContentText("From             Begin Time      To        " +
                    "           End Time\n" +table.getSelectionModel().getSelectedItem().getDates()
                    + "\n" + table.getSelectionModel()
                    .getSelectedItem().getDates2());

            alert.showAndWait();
        }

    }

    @FXML
    public void backBTClicked(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Menu");
        stage.show();

    }

    }






