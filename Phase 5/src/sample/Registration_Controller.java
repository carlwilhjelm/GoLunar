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
import sun.applet.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Registration_Controller {

    ResultSet rs = null;
    Statement st = null;

    public static String username;
    private Connection connect = null;

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

    @FXML
    private void classScheduleSearchClicked(ActionEvent event) throws IOException {     //Hyper Link for class Schedule Search is clicked
        Stage stage;
        Parent root;
        stage=(Stage) ((Hyperlink)(event.getSource())).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Schedule Search");

        stage.setWidth(1100.0D);            stage.setHeight(900.0D);             stage.show();
    }
    @FXML
    private void addDropClicked(ActionEvent event) throws IOException, SQLException {     //Hyper Link for class Schedule Search is clicked
        try {
            connect = SqliteConnection.Connector();
            Stage stage;
            Parent root;
            stage = (Stage) ((Hyperlink) (event.getSource())).getScene().getWindow();

            st = (Statement) connect.createStatement();
            rs = st.executeQuery("select * from StudentTable where username = '" + username + "'");

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AddDrop.fxml"));

            root = (Parent) loader1.load();

            AddDrop_Controller addDrop_controller = loader1.getController();

            int studentId = rs.getInt(1);
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
    private void conciseStudentScheduleClicked(ActionEvent event) throws SQLException, IOException {     //Hyper Link for class Schedule Search is clicked
        try {
            connect = SqliteConnection.Connector();
            Stage stage;
            Parent root;
            stage = (Stage) ((Hyperlink) (event.getSource())).getScene().getWindow();

            st = (Statement) connect.createStatement();
            rs = st.executeQuery("select * from StudentTable where username = '" + username + "'");

            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ConciseSchedule.fxml"));

            root = (Parent) loader1.load();

            ConciseSchedule_controller conciseSchedule_controller = loader1.getController();

            int studentId = rs.getInt(1);
            conciseSchedule_controller.setStudentID(studentId);


            conciseSchedule_controller.setUsername(username);
            conciseSchedule_controller.setScheduleDetails(studentId);

            conciseSchedule_controller.setEverything();

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("View Concise Schedule");
            stage.setWidth(1125.0D);
            stage.setHeight(700.0D);
            stage.show();
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

    public void setUsername(String name){
        username = name;
    }
    public String getUsername(){
        return username;
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
    private void registrationTimes(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;

        stage=(Stage) ((Hyperlink)(event.getSource())).getScene().getWindow();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegistrationDates.fxml"));
        root = loader.load();

        RegistrationDates_Controller dates_controller = loader.getController();
        dates_controller.initialize();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Registration Dates");
        stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
    }



}
