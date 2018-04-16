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
import sun.rmi.server.InactiveGroupException;

import java.io.IOException;
import java.sql.*;


public class Records_Controller {

    ResultSet rs = null;
    Statement st = null;

    private Connection connect = null;
    public static String username;
   @FXML
    public Label name;

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
    public void viewHolds(ActionEvent event) throws Exception {
        try {
            connect = SqliteConnection.Connector();
            Stage stage;
            Parent root;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Holds.fxml"));
            root = loader.load();

            HoldsController transcript_controller = loader.getController();
            transcript_controller.setUsername(username);

            st = (Statement) connect.createStatement();
            rs = st.executeQuery("SELECT * FROM StudentTable where Username = '" + username + "'");

            if (rs.next()) {
                int holdnum = rs.getInt(19);
                if (holdnum != 0) {
                    rs = st.executeQuery("SELECT * FROM Holds where HoldID = '" + holdnum + "'");
                    String description = rs.getString(2);
                    HoldsController holdsController = loader.getController();
                    holdsController.setHoldInfo(description);

                } else {
                    HoldsController holdsController = loader.getController();
                    holdsController.setHoldInfo("There are no holds for this user.");
                }

            }
            stage = (Stage) ((Hyperlink) (event.getSource())).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Holds");
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
    private void TranscriptHClicked(ActionEvent event) throws Exception {        //Hyper Link for Personal Info is clicked

        try {
            connect = SqliteConnection.Connector();
        st = (Statement) connect.createStatement();
        rs = st.executeQuery("SELECT * FROM StudentTable where Username = '"+username+"'" );

            if (rs.next()) {
                Stage stage;
                Parent root;


                FXMLLoader loader = new FXMLLoader(getClass().getResource("Transcript.fxml"));
                root = loader.load();
                username = rs.getString(16);
                int studentId = rs.getInt(1);

                System.out.println(studentId);

                String name = rs.getString(2);
                String gpa = rs.getString(5);

                int deg = rs.getInt(4);

                rs = st.executeQuery("SELECT * from SubjectTable where SubjectId = '"+deg+"'");

                String degree = rs.getString(2);
                Transcript_Controller transcript = loader.getController();
                transcript.setUsername(username);
                transcript.setName(name);
                transcript.setDegree(degree);
                transcript.setStudentID(studentId);
                transcript.setTranscriptDetails(studentId);
                transcript.set();

                stage = (Stage) ((Hyperlink) (event.getSource())).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Transcript");
                stage.setWidth(1125.0D);            stage.setHeight(700.0D);             stage.show();
                rs.close();
                st.close();
                connect.close();

            }

        }catch (Exception e ){
            throw e;
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
    public void setUsername(String name){
        username = name;
    }
    public String getUsername(){
        return username;
    }

}
