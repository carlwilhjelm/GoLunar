package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login_Controller {

    @FXML
    public TextField campusId, password;
    @FXML
    public Button loginB;
    @FXML
    public Label userPassWrong;

    public static String username;
    public static int count=0;

    ResultSet rs = null;
    Statement st;

    public Connection connect = SqliteConnection.Connector();


    @FXML
    public void Login(ActionEvent event) throws Exception{


        try {

            if(count < 3) {
                count++;


                if (!campusId.getText().isEmpty() && !password.getText().isEmpty()) {
                    String user = campusId.getText();
                    String pass = password.getText();


                    st = (Statement) connect.createStatement();
                    rs = st.executeQuery("SELECT * FROM StudentTable where Username = '" + user + "' COLLATE NOCASE AND Password_Plain = '" + pass + "'");

                    if (rs.next()) {
                        if (rs.getString(16).equalsIgnoreCase(user) && rs.getString(17).equals(pass)) {
                            Stage stage;
                            Parent root;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                            root = loader.load();

                            username = rs.getString(16);

                            String name = rs.getString(2);
                            String gpa = rs.getString(5);
                            int deg = rs.getInt(4);
                            rs = st.executeQuery("SELECT * from SubjectTable where SubjectId = '" + deg + "'");
                            String degree = rs.getString(2);


                            MainMenu_Controller menu = loader.getController();
                            menu.setNameL(name);
                            menu.setGpaText(gpa);
                            menu.setDegreeText(degree);

                            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Main Menu");
                            stage.setWidth(1125.0D);
                            stage.setHeight(700.0D);
                            stage.show();

                            rs.close();
                        }

                    } else {
                        userPassWrong.setText("The CampusId or Password is incorrect.");
                    }


                }
            }
            else{
                userPassWrong.setText("Your account is locked out. Please contact the administrator to unlock");
            }
        }
        catch (Exception e ){
            throw e;
        }
    }
public void setCount(int i){
        count = i;
}
public int getCount(){
        return count;
}

    public String getUsername(){
        return username;
    }
    public void setUsername(String n){
        username = n;
    }

}
