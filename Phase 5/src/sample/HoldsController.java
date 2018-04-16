
    package sample;

import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

    public class HoldsController {
        private Connection connect = null;
        @FXML
        public Label holdInfo;

        ResultSet rs;
        ResultSet rs2;
        Statement st = null;
        int id;
        public static String username;

        public HoldsController() {
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
        public void setHoldInfo(String str){
            holdInfo.setText(str);
        }
    }

