package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Login_Controller;
import sample.Registration_Controller;

/*
 * The MainController class populates the values of the JavaFX controllers
 */
public class MainController implements Initializable {
	public ClassSelectionModel classSelectionModel = new ClassSelectionModel();
	
	public MainController() throws SQLException{}
	
	@FXML
	public ComboBox<String> termBox, degreeBox, subjectBox, 
							instructorBox, startHrBox,
							startMinBox, startPBox, endHrBox,
							endMinBox, endPBox;
	
	public TextField classSectionField, classTitleField, minCredField, maxCredField;
	public CheckBox cbMon, cbTues, cbWed, cbThurs, cbFri, cbSat, cbSun;
	
	@FXML
	private TableView<ClassDetails> classResultsTable;
	
	@FXML
	private TableColumn<ClassDetails, String> 	classIDCol, classNameCol,
												classSectionCol, classEmployeeIdCol, 
												classDaysCol, classTimeCol,
												classRoomCol, classDescriptionCol,
												classCreditHoursCol, classPrerequisitesCol;

	public String 	termStr = "", degStr = "", subStr = "", 
					instStr = "", secStr = "", titleStr = "", minCredStr = "", 
					maxCredStr = "", startHrStr = "", startMinStr = "", 
					startPStr = "", endHrStr = "", endMinStr = "", endPStr = "";
	
	//Populate comboboxes with values from database 
	
	ObservableList<String> termList = classSelectionModel.fillComboBox("Term", "TermTable");
	
	ObservableList<String> degList = classSelectionModel.fillComboBox("DegreeName", "DegreeTable");
			
	ObservableList<String> subList = classSelectionModel.fillComboBox("SubjectName", "SubjectTable");
			
	ObservableList<String> instList = classSelectionModel.fillComboBox("LastName", "EmployeeTable");
	
	ObservableList<String> hrList = FXCollections.observableArrayList(
			"01", "02", "03", "04", "05", "06", 
			"07", "08", "09", "10", "11", "12");
	ObservableList<String> minList = FXCollections.observableArrayList(
			"00", "05", "10", "15", "20", "25", 
			"30", "35", "40", "45", "50", "55");
	ObservableList<String> perList = FXCollections.observableArrayList("am", "pm");
	
	//set values for comboboxes on start
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		termBox.setItems(termList);
		degreeBox.setItems(degList);
		subjectBox.setItems(subList);
		subjectBox.setEditable(true);
		instructorBox.setItems(instList);
		instructorBox.setEditable(true);
		startHrBox.setItems(hrList);
		startMinBox.setItems(minList);
		startPBox.setItems(perList);
		endHrBox.setItems(hrList);
		endMinBox.setItems(minList);
		endPBox.setItems(perList);
	}
	
	/*
	 * *** (Unused)
	 * Postcondition: Resets the values of the comboboxes consistent with already selected fields
	 */
	public void repopCbs() throws SQLException {
		
		termList = classSelectionModel.fillComboBox("Term", "TermTable");
		termBox.setItems(termList);
		
		degList = classSelectionModel.fillComboBox("DegreeName", "DegreeTable");
		degreeBox.setItems(degList);
		
		subList = classSelectionModel.fillComboBox("SubjectName", "SubjectTable");
		subjectBox.setItems(subList);
		
		instList = classSelectionModel.fillComboBox("LastName", "EmployeeTable");
		instructorBox.setItems(instList);
	}

	/*
	 * Postcondition: Uses selection from termBox to alter final query
	 */
	public void termChanged(ActionEvent event) throws SQLException {
		termStr = termBox.getValue();
		System.out.println("termChanged to " + termStr);
		classSelectionModel.setQueryControl('a', termStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from degreeBox to alter final query
	 */
	public void degChanged(ActionEvent event) throws SQLException {
		System.out.println("degChanged");
		degStr = degreeBox.getValue();
		classSelectionModel.setQueryControl('b', degStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from subjectBox to alter final query
	 */
	public void subChanged(ActionEvent event) throws SQLException {
		System.out.println("subChanged");
		subStr = subjectBox.getValue();
		classSelectionModel.setQueryControl('c', subStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from instructorBox to alter final query
	 */
	public void instChanged(ActionEvent event) throws SQLException {
		System.out.println("instChanged");
		instStr = instructorBox.getValue();
		classSelectionModel.setQueryControl('d', instStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses input from classSectionField to alter final query
	 */
	public void sectionChanged(ActionEvent event) throws SQLException {
		secStr = classSectionField.getText();
		System.out.println("section changed to " + secStr);
		classSelectionModel.setQueryControl('e', secStr);
	}
	
	/*
	 * Postcondition: Uses input from classTitleField to alter final query
	 */
	public void titleChanged(ActionEvent event) throws SQLException {
		titleStr = "%" + classTitleField.getText() + "%";
		System.out.println("title changed to " + titleStr);
		classSelectionModel.setQueryControl('f', titleStr);
	}
	
	/*
	 * Postcondition: Uses input from minCredField to alter final query
	 */
	public void minCredChanged(ActionEvent event) throws SQLException {
		minCredStr = minCredField.getText();
		System.out.println("min cred changed to " + minCredStr);
		classSelectionModel.setQueryControl('g', minCredStr);
	}
	
	/*
	 * Postcondition: Uses input from maxCredField to alter final query
	 */
	public void maxCredChanged(ActionEvent event) throws SQLException {
		maxCredStr = maxCredField.getText();
		System.out.println("max cred changed to " + maxCredStr);
		classSelectionModel.setQueryControl('h', maxCredStr);
	}
	
	/*
	 * Postcondition: Uses selection from startHrBox to alter final query
	 */
	public void startHrChanged(ActionEvent event) throws SQLException {
		System.out.println("start Hour Changed");
		startHrStr = startHrBox.getValue();
		classSelectionModel.setQueryControl('i', startHrStr);
	}
	
	/*
	 * Postcondition: Uses selection from startMinBox to alter final query
	 */
	public void startMinChanged(ActionEvent event) throws SQLException {
		System.out.println("start Minute Changed");
		startMinStr = startMinBox.getValue();
		classSelectionModel.setQueryControl('j', startMinStr);
	}
	
	/*
	 * Postcondition: Uses selection from startPBox to alter final query
	 */
	public void startPChanged(ActionEvent event) throws SQLException {
		System.out.println("start period Changed");
		startPStr = startPBox.getValue();
		classSelectionModel.setQueryControl('k', startPStr);
	}
	/*
	 * Postcondition: Uses selection from endtHrBox to alter final query
	 */
	public void endHrChanged(ActionEvent event) throws SQLException {
		System.out.println("end Hour Changed");
		endHrStr = endHrBox.getValue();
		classSelectionModel.setQueryControl('l', endHrStr);
	}
	
	/*
	 * Postcondition: Uses selection from endMinBox to alter final query
	 */
	public void endMinChanged(ActionEvent event) throws SQLException {
		System.out.println("end Minute Changed");
		endMinStr = endMinBox.getValue();
		classSelectionModel.setQueryControl('m', endMinStr);
	}
	
	/*
	 * Postcondition: Uses selection from endPBox to alter final query
	 */
	public void endPChanged(ActionEvent event) throws SQLException {
		System.out.println("end period Changed");
		endPStr = endPBox.getValue();
		classSelectionModel.setQueryControl('n', endPStr);
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Monday to alter final query
	 */
	public void cbMonCheck(ActionEvent event) throws SQLException {
		System.out.println("Monday added");
		classSelectionModel.setQueryControl('o', "M");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Tuesday to alter final query
	 */
	public void cbTuesCheck(ActionEvent event) throws SQLException {
		System.out.println("Tuesday added");
		classSelectionModel.setQueryControl('o', "T");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Wednesday to alter final query
	 */
	public void cbWedCheck(ActionEvent event) throws SQLException {
		System.out.println("Wednesday added");
		classSelectionModel.setQueryControl('o', "W");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Thursday to alter final query
	 */
	public void cbThursCheck(ActionEvent event) throws SQLException {
		System.out.println("Thursday added");
		classSelectionModel.setQueryControl('o', "R");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Friday to alter final query
	 */
	public void cbFriCheck(ActionEvent event) throws SQLException {
		System.out.println("Friday added");
		classSelectionModel.setQueryControl('o', "F");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Saturday to alter final query
	 */
	public void cbSatCheck(ActionEvent event) throws SQLException {
		System.out.println("Saturday added");
		classSelectionModel.setQueryControl('o', "S");
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Sunday to alter final query
	 */
	public void cbSunCheck(ActionEvent event) throws SQLException {
		System.out.println("Sunday added");
		classSelectionModel.setQueryControl('o', "U");
	}
	
	/*
	 * Postcondition: Called after pressing of the 'reset' button, clears all query values.
	 */
	public void reset(ActionEvent event) throws SQLException {
		System.out.println("reset");
		termStr = ""; 
		degStr = ""; 
		subStr = ""; 
		instStr = "";
		secStr = "";
		titleStr = "";
		classSelectionModel.setQueryControl('z', "");
		//repopCbs();
		
	}
	
	/*
	 * Postcondition: Uses query value to execute class search query
	 */
	public void search(ActionEvent event) throws SQLException {
		System.out.println("search");
		classIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		classNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		classSectionCol.setCellValueFactory(new PropertyValueFactory<>("section"));
		classEmployeeIdCol.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
		classDaysCol.setCellValueFactory(new PropertyValueFactory<>("days"));
		classTimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
		classRoomCol.setCellValueFactory(new PropertyValueFactory<>("room"));
		classDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		classCreditHoursCol.setCellValueFactory(new PropertyValueFactory<>("creditHours"));
		classPrerequisitesCol.setCellValueFactory(new PropertyValueFactory<>("prerequisites"));
		classResultsTable.setItems(null);
		classResultsTable.setItems(classSelectionModel.search());
	}
	@FXML
	private void registrationBClicked(ActionEvent event) throws IOException, SQLException {       //Registration button is clicked
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/Login.fxml"));
		Parent great = (Parent) loader.load();

		Login_Controller login = loader.getController();
		String str = login.getUsername();

		Stage stage;
		Parent root;
		stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();

		FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/sample/Registration.fxml"));
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
}
