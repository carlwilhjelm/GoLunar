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


import javax.swing.text.AsyncBoxView;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


/**
 * Controls the Behavior and state of the concise schedule page
 */
public class AddDrop_Controller {
    @FXML
    public TableColumn crnCol;
    @FXML
    public TableColumn subjectCol;
    @FXML
    public TableColumn courseCol;
    @FXML
    public TableColumn creditsCol;
    @FXML
    public TableColumn daysCol;
    @FXML
    public TableColumn timeCol;
    @FXML
    public TableColumn locationCol;
    @FXML
    public TableColumn instructorCol;
    //create object to retrieve student's schedule details from the database
    // with input parameter being the "name" of the student
    private ScheduleDetails schedule;

    @FXML
    public ComboBox action1;
    @FXML
    public ComboBox action2;
    @FXML
    public ComboBox action3;
    @FXML
    public ComboBox action4;

    @FXML
    public TextField addClass1, addClass2, addClass3, addClass4;
    public static String[] addClassesStr = {"","","",""};

    @FXML
    public Label errorORNOT;


    //^^studentID
    // variable
    @FXML
    public Button bMain, bRegistration, bPerInfo, bRecords, submitButton;
    @FXML
    private TableView<Schedule> table = new TableView<>();


    public static String username;
    public static int studentID;
    private Connection connect = null;
    ResultSet rs = null;
    Statement st = null;


    public void setScheduleDetails(int studentIDin) throws SQLException{
        schedule = new ScheduleDetails(studentIDin);
    }

    @FXML
    public void setEverything() {
    	if(!addClassesStr[0].isEmpty()) addClass1.setText(addClassesStr[0]);
    	if(!addClassesStr[1].isEmpty()) addClass2.setText(addClassesStr[1]);
    	if(!addClassesStr[2].isEmpty()) addClass3.setText(addClassesStr[2]);
    	if(!addClassesStr[3].isEmpty()) addClass4.setText(addClassesStr[3]);
        String str = ""+studentID;
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


        courseCol.setCellValueFactory(new PropertyValueFactory<Schedule, String>
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
        action1.getItems().addAll(
                "Web Drop/Delete");
        action2.getItems().addAll(
                "Web Drop/Delete");
        action3.getItems().addAll(
                "Web Drop/Delete");
        action4.getItems().addAll(
                "Web Drop/Delete");
        if(data.size() == 1){
            action1.setVisible(true);
        }
        else if(data.size() == 2){
            action1.setVisible(true);
            action2.setVisible(true);
        }
        else if(data.size() == 3){
            action1.setVisible(true);
            action2.setVisible(true);
            action3.setVisible(true);
        }
        else if(data.size() == 4){
            action1.setVisible(true);
            action2.setVisible(true);
            action3.setVisible(true);
            action4.setVisible(true);
        }



    }

    //double click on a row shows the class description
    @FXML
    public void clickItem(MouseEvent event)
    {
        if (event.getClickCount() == 2)  {    //Checking double click

            //for debugging purpose below to print to console
            //System.out.println(table.getSelectionModel().getSelectedItem()
            //        .getDescription());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Go Lunar 1.0");     //change stage title if wanted
            alert.setHeaderText(table.getSelectionModel().getSelectedItem()
                    .getCourseTitle());

            if (table.getSelectionModel().getSelectedItem().getDescription()
                    .isEmpty()) {
                alert.setContentText("No Description Available");
            } else {

                alert.setContentText(table.getSelectionModel().getSelectedItem()
                        .getDescription());
            }

            alert.showAndWait();
        }
    }


    @FXML
    private void mainBClicked(ActionEvent event) throws IOException, SQLException {       //Main Menu button is clicked
        try {
            connect = SqliteConnection.Connector();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent great = (Parent) loader.load();

            Login_Controller login = loader.getController();
            String str = login.getUsername();
            st = (Statement) connect.createStatement();
            rs = st.executeQuery("SELECT * FROM StudentTable where Username = '" + username + "'");

            String name = rs.getString(2);
            String gpa = rs.getString(5);
            int deg = rs.getInt(4);
            rs = st.executeQuery("SELECT * from SubjectTable where SubjectId = '" + deg + "'");
            String degree = rs.getString(2);

            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
            root = (Parent) loader1.load();

            MainMenu_Controller menu = loader1.getController();
            menu.setUsername(str);
            menu.setNameL(name);
            menu.setGpaText(gpa);
            menu.setDegreeText(degree);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Main Menu");
            stage.setWidth(1125.0D);
            stage.setHeight(700.0D);
            stage.show();
        }
        catch(Exception e){

        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                }
            }

            if (st != null) {
                try {
                    st.close();
                    st = null;
                } catch (SQLException e) {
                }
            }

            try {
                if (connect!= null && !connect.isClosed()){
                    if (!connect.getAutoCommit()) {
                        connect.commit();
                        connect.setAutoCommit(true);
                    }
                    connect.close();
                    connect= null;
                }
            } catch (SQLException sqle) {
            }
        }

    }
    @FXML
    private void recordsBClicked(ActionEvent event) throws IOException {        //Records button is clicked
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent great = (Parent) loader.load();

        Login_Controller login = loader.getController();
        String str = login.getUsername();

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Records.fxml"));
        root = (Parent) loader1.load();

        Records_Controller records = loader1.getController();
        records.setUsername(str);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Records Menu");
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.show();

    }
    @FXML
    private void infoBClicked(ActionEvent event) throws IOException {       //Personal Info button is clicked
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent great = (Parent) loader.load();

        Login_Controller login = loader.getController();
        String str = login.getUsername();

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PersonalInfo.fxml"));
        root = (Parent) loader1.load();

        Personal_Info_Controller registration = loader1.getController();
        registration.setUsername(str);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Personal Information Menu");
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.show();

    }
    @FXML
    private void registrationBClicked(ActionEvent event) throws IOException {       //Registration button is clicked
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent great = (Parent) loader.load();

        Login_Controller login = loader.getController();
        String str = login.getUsername();

        Stage stage;
        Parent root;
        stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Registration.fxml"));
        root = (Parent) loader1.load();

        Registration_Controller registration = loader1.getController();
        registration.setUsername(str);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Menu");
        stage.setWidth(1125.0D);
        stage.setHeight(700.0D);
        stage.show();
    }

    public void setUsername(String name){
        username = name;
    }

    public String getUsername(){
        return username;
    }

    public void setStudentID(int i){
        studentID = i;
    }

    public int getStudentID(){
        return studentID;
    }
    @FXML
    private void logoutClicked(ActionEvent event) throws IOException{
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
    private void submitButtonClicked(ActionEvent event) throws IOException, SQLException {
        try {

            connect = SqliteConnection.Connector();

            TableColumn<Schedule, String> column = crnCol; // column you want

            ArrayList<String> columnData = new ArrayList<>();

            for (Schedule item : table.getItems()) {
                columnData.add(column.getCellObservableValue(item).getValue());
            }
            st = (Statement) connect.createStatement();
            rs = st.executeQuery("SELECT * FROM StudentTable Where Username = '" + username + "'");

            String class1 = rs.getString(8);
            String class2 = rs.getString(10);
            String class3 = rs.getString(12);
            String class4 = rs.getString(14);
            String ac1 = action1.getSelectionModel().getSelectedItem().toString();
            String ac2 = action2.getSelectionModel().getSelectedItem().toString();
            String ac3 = action3.getSelectionModel().getSelectedItem().toString();
            String ac4 = action4.getSelectionModel().getSelectedItem().toString();

            rs.close();
            st.close();

            //all check to see if the classes want to be dropped
            PreparedStatement pstclass1 = connect.prepareStatement("UPDATE StudentTable set Class1Id= null where UserName = '" + username + "'");
            PreparedStatement pstclass2 = connect.prepareStatement("UPDATE StudentTable set Class2Id= null where UserName = '" + username + "'");
            PreparedStatement pstclass3 = connect.prepareStatement("UPDATE StudentTable set Class3Id= null where UserName = '" + username + "'");
            PreparedStatement pstclass4 = connect.prepareStatement("UPDATE StudentTable set Class4Id= null where UserName = '" + username + "'");



            if (ac1.equalsIgnoreCase("Web Drop/Delete")) {
                String val1 = columnData.get(0);
                if (val1.equalsIgnoreCase(class1)) {
                    pstclass1.executeUpdate();
                } else if (val1.equalsIgnoreCase(class2)) {
                    pstclass2.executeUpdate();
                } else if (val1.equalsIgnoreCase(class3)) {
                    pstclass3.executeUpdate();
                } else if (val1.equalsIgnoreCase(class4)) {
                    pstclass4.executeUpdate();
                }

            }
            if (ac2.equalsIgnoreCase("Web Drop/Delete")) {
                String val1 = columnData.get(1);
                if (val1.equalsIgnoreCase(class1)) {
                    pstclass1.executeUpdate();
                } else if (val1.equalsIgnoreCase(class2)) {
                    pstclass2.executeUpdate();
                } else if (val1.equalsIgnoreCase(class3)) {
                    pstclass3.executeUpdate();
                } else if (val1.equalsIgnoreCase(class4)) {
                    pstclass4.executeUpdate();
                }

            }
            if (ac3.equalsIgnoreCase("Web Drop/Delete")) {
                String val1 = columnData.get(2);
                if (val1.equalsIgnoreCase(class1)) {
                    pstclass1.executeUpdate();
                } else if (val1.equalsIgnoreCase(class2)) {
                    pstclass2.executeUpdate();
                } else if (val1.equalsIgnoreCase(class3)) {
                    pstclass3.executeUpdate();
                } else if (val1.equalsIgnoreCase(class4)) {
                    pstclass4.executeUpdate();
                }

            }
            if (ac4.equalsIgnoreCase("Web Drop/Delete")) {
                String val1 = columnData.get(3);
                if (val1.equalsIgnoreCase(class1)) {
                    pstclass1.executeUpdate();
                } else if (val1.equalsIgnoreCase(class2)) {
                    pstclass2.executeUpdate();
                } else if (val1.equalsIgnoreCase(class3)) {
                    pstclass3.executeUpdate();
                } else if (val1.equalsIgnoreCase(class4)) {
                    pstclass4.executeUpdate();
                }

            }
            pstclass1.close();
            pstclass2.close();
            pstclass3.close();
            pstclass4.close();

            //need to add to check what classes they want to add and check if there is empty space
//            rs = st.executeQuery("select * from ClassTable");
//            int[] classes = new int[1000];
//            int i =0;
//            while(rs.next()){
//                classes[i] = rs.getInt(1);
//            }
            //classes wanting to add
             pstclass1 = connect.prepareStatement("UPDATE StudentTable set Class1Id= ? where UserName = '" + username + "'");
             pstclass2=connect.prepareStatement("UPDATE StudentTable set Class2Id = ? where UserName = '" + username + "'");
             pstclass3=connect.prepareStatement("UPDATE StudentTable set Class3Id = ? where UserName = '" + username + "'");
             pstclass4=connect.prepareStatement("UPDATE StudentTable set Class4Id = ? where UserName = '" + username + "'");

            String aclass1 = addClass1.getText(), aclass2 = addClass2.getText(), aclass3 = addClass3.getText(), aclass4 = addClass4.getText();
            st = (Statement) connect.createStatement();
            rs = st.executeQuery("SELECT * FROM StudentTable Where Username = '" + username + "'");
             class1 = rs.getString(8);//old classes
             class2 = rs.getString(10);
             class3 = rs.getString(12);
             class4 = rs.getString(14);

             if(!aclass1.isEmpty()) {
                 if (class1 == null || class1.isEmpty()) {
                     pstclass1.setString(1,aclass1);
                     pstclass1.executeUpdate();
                 }
                 else if (class2 == null || class2.isEmpty()) {
                     pstclass2.setString(1,aclass1);
                     pstclass2.executeUpdate();
                     class2="filled";
                 }
                 else if (class3 == null || class3.isEmpty()) {
                     pstclass3.setString(1,aclass1);
                     pstclass3.executeUpdate();
                     class3="filled";
                 }
                 else if (class4 == null || class4.isEmpty()) {
                     pstclass4.setString(1,aclass1);
                     pstclass4.executeUpdate();
                     class3="filled";
                 }
                 else{
                     errorORNOT.setVisible(true);
                     errorORNOT.setText("Could not add "+addClass1+" because you have exceeded 4 classes");
                 }

             }
            if(!aclass2.isEmpty()) {
                if (class1 == null || class1.isEmpty()) {
                    pstclass1.setString(1,aclass2);
                    pstclass1.executeUpdate();
                }
                else if (class2 == null || class2.isEmpty()) {
                    pstclass2.setString(1,aclass2);
                    pstclass2.executeUpdate();
                    class2="filled";
                }
                else if (class3 == null || class3.isEmpty()) {
                    pstclass3.setString(1,aclass2);
                    pstclass3.executeUpdate();
                    class3="filled";
                }
                else if (class4 == null || class4.isEmpty()) {
                    pstclass4.setString(1,aclass2);
                    pstclass4.executeUpdate();
                    class3="filled";
                }
                else{
                    errorORNOT.setVisible(true);
                    errorORNOT.setText("Could not add "+addClass1+" because you have exceeded 4 classes");
                }

            }
            if(!aclass3.isEmpty()) {
                if (class1 == null || class1.isEmpty()) {
                    pstclass1.setString(1,aclass3);
                    pstclass1.executeUpdate();
                }
                else if (class2 == null || class2.isEmpty()) {
                    pstclass2.setString(1,aclass3);
                    pstclass2.executeUpdate();
                    class2="filled";
                }
                else if (class3 == null || class3.isEmpty()) {
                    pstclass3.setString(1,aclass3);
                    pstclass3.executeUpdate();
                    class3="filled";
                }
                else if (class4 == null || class4.isEmpty()) {
                    pstclass4.setString(1,aclass3);
                    pstclass4.executeUpdate();
                    class3="filled";
                }
                else{
                    errorORNOT.setVisible(true);
                    errorORNOT.setText("Could not add "+addClass3+" because you have exceeded 4 classes");
                }

            }
            if(!aclass4.isEmpty()) {
                if (class1 == null || class1.isEmpty()) {
                    pstclass1.setString(1,aclass4);
                    pstclass1.executeUpdate();
                }
                else if (class2 == null || class2.isEmpty()) {
                    pstclass2.setString(1,aclass4);
                    pstclass2.executeUpdate();
                    class2="filled";
                }
                else if (class3 == null || class3.isEmpty()) {
                    pstclass3.setString(1,aclass4);
                    pstclass3.executeUpdate();
                    class3="filled";
                }
                else if (class4 == null || class4.isEmpty()) {
                    pstclass4.setString(1,aclass4);
                    pstclass4.executeUpdate();
                    class3="filled";
                }
                else{
                    errorORNOT.setVisible(true);
                    errorORNOT.setText("Could not add "+addClass4+" because you have exceeded 4 classes");
                }

            }
            pstclass1.close();
             pstclass2.close();
             pstclass3.close();
             pstclass4.close();



            //refresh page by reopening the adddrop page
            Stage stage;
            Parent root;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

            rs = st.executeQuery("select * from StudentTable where username = '" + username + "'");

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AddDrop.fxml"));

            root = (Parent) loader1.load();

            AddDrop_Controller addDrop_controller = loader1.getController();

            int studentId = rs.getInt(1);
            rs.close();
            st.close();
            connect.close();
            addDrop_controller.setStudentID(studentId);


            addDrop_controller.setUsername(username);
            addDrop_controller.setScheduleDetails(studentId);

            addDrop_controller.setEverything();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Concise Schedule");
            stage.setWidth(1120);
            stage.setHeight(760);
            stage.show();


        }
        catch(Exception e){
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                }
            }

            if (st != null) {
                try {
                    st.close();
                    st = null;
                } catch (SQLException e) {
                }
            }

            try {
                if (connect!= null && !connect.isClosed()){
                    if (!connect.getAutoCommit()) {
                        connect.commit();
                        connect.setAutoCommit(true);
                    }
                    connect.close();
                    connect= null;
                }
            } catch (SQLException sqle) {
            }
        }
    }

}





