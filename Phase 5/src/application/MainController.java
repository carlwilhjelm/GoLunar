package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.AddDrop_Controller;
import sample.Login_Controller;
import sample.Registration_Controller;
import sample.SqliteConnection;

/*
 * The MainController class populates the values of the JavaFX controllers
 */
public class MainController implements Initializable {
    ResultSet rs = null;
    Statement st = null;

    public static String username;
    private Connection connect = null;

	public ClassSelectionModel classSelectionModel = new ClassSelectionModel();
	
	public MainController() throws SQLException{}
	
	@FXML
	public ComboBox<String> termBox, degreeBox, subjectBox, 
							instructorBox, startHrBox,
							startMinBox, startPBox, endHrBox,
							endMinBox, endPBox;
	
	public TextField classSectionField, classTitleField, minCredField, maxCredField,
					classSelection1, classSelection2, classSelection3, classSelection4;
	
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
	
	public static boolean monBool, tuesBool, wedBool, thursBool, friBool, satBool, sunBool;
	
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
	String[] classes;
	
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
		monBool = false; 
		tuesBool = false; 
		wedBool = false; 
		thursBool = false; 
		friBool = false; 
		satBool = false;  
		sunBool = false;
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
		if(!termStr.equals(termBox.getPromptText())) {
		classSelectionModel.setQueryControl('a', termStr);
		}
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from degreeBox to alter final query
	 */
	public void degChanged(ActionEvent event) throws SQLException {
		System.out.println("degChanged");
		degStr = degreeBox.getValue();
		if(!degStr.equals(degreeBox.getPromptText())) {
		classSelectionModel.setQueryControl('b', degStr);
		}
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from subjectBox to alter final query
	 */
	public void subChanged() throws SQLException {
		System.out.println("subChanged");
		if (subjectBox.getValue() == null || subjectBox.getValue().toString().isEmpty()) return;
		subStr = subjectBox.getValue();
		classSelectionModel.setQueryControl('c', subStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from instructorBox to alter final query
	 */
	public void instChanged() throws SQLException {
		System.out.println("instChanged");
		if (instructorBox.getValue() == null) return;
		instStr = instructorBox.getValue();
		if (!instStr.isEmpty()) {
			classSelectionModel.setQueryControl('d', instStr);
		}
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses input from classSectionField to alter final query
	 */
	public void sectionChanged() throws SQLException {
		secStr = classSectionField.getText();
		if (!secStr.isEmpty()) {
			System.out.println("section changed to " + secStr);
			classSelectionModel.setQueryControl('e', secStr);
		}
	}
	
	/*
	 * Precondition: called from search method
	 * Postcondition: Uses input from classTitleField to alter final query
	*/
	
	public void titleChanged() throws SQLException {
		if (!classTitleField.getText().isEmpty()) {
			titleStr = "%" + classTitleField.getText() + "%";
			System.out.println("title changed to " + titleStr);
			classSelectionModel.setQueryControl('f', titleStr);
		}
	}
	
	/*
	 * Postcondition: Uses input from minCredField to alter final query
	 */
	public void minCredChanged() throws SQLException {
		minCredStr = minCredField.getText();
		if (!minCredStr.isEmpty()) {
			System.out.println("min cred changed to " + minCredStr);
			classSelectionModel.setQueryControl('g', minCredStr);
		}
	}
	
	/*
	 * Postcondition: Uses input from maxCredField to alter final query
	 */
	public void maxCredChanged() throws SQLException {
		maxCredStr = maxCredField.getText();
		if (!maxCredStr.isEmpty()) {
			System.out.println("max cred changed to " + maxCredStr);
			classSelectionModel.setQueryControl('h', maxCredStr);
		}
	}
	
	/*
	 * Postcondition: Uses selection from startHrBox to alter final query
	 */
	public void startHrChanged(ActionEvent event) throws SQLException {
		if (startHrBox.getValue().equals(startHrBox.getPromptText())) return;
		System.out.println("start Hour Changed");
		startHrStr = startHrBox.getValue();
		classSelectionModel.setQueryControl('i', startHrStr);
	}
	
	/*
	 * Postcondition: Uses selection from startMinBox to alter final query
	 */
	public void startMinChanged(ActionEvent event) throws SQLException {
		if (startMinBox.getValue().equals(startMinBox.getPromptText())) return;
		System.out.println("start Minute Changed");
		startMinStr = startMinBox.getValue();
		classSelectionModel.setQueryControl('j', startMinStr);
	}
	
	/*
	 * Postcondition: Uses selection from startPBox to alter final query
	 */
	public void startPChanged(ActionEvent event) throws SQLException {
		if (startPBox.getValue().equals(startPBox.getPromptText())) return;
		System.out.println("start period Changed");
		startPStr = startPBox.getValue();
		classSelectionModel.setQueryControl('k', startPStr);
	}
	/*
	 * Postcondition: Uses selection from endtHrBox to alter final query
	 */
	public void endHrChanged(ActionEvent event) throws SQLException {
		if (endHrBox.getValue().equals(endHrBox.getPromptText())) return;
		System.out.println("end Hour Changed");
		endHrStr = endHrBox.getValue();
		classSelectionModel.setQueryControl('l', endHrStr);
	}
	
	/*
	 * Postcondition: Uses selection from endMinBox to alter final query
	 */
	public void endMinChanged(ActionEvent event) throws SQLException {
		if (endMinBox.getValue().equals(endMinBox.getPromptText())) return;
		System.out.println("end Minute Changed");
		endMinStr = endMinBox.getValue();
		classSelectionModel.setQueryControl('m', endMinStr);
	}
	
	/*
	 * Postcondition: Uses selection from endPBox to alter final query
	 */
	public void endPChanged(ActionEvent event) throws SQLException {
		if (endPBox.getValue().equals(endPBox.getPromptText())) return;
		System.out.println("end period Changed");
		endPStr = endPBox.getValue();
		classSelectionModel.setQueryControl('n', endPStr);
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Monday to alter final query
	 */
	public void cbMonCheck(ActionEvent event) throws SQLException {
		System.out.println("monbool =  " + monBool);
		if(!monBool) {
			monBool = true;
			System.out.println("Monday added");
			classSelectionModel.setQueryControl('o', "M");
		}
		else {
			monBool = false;
			System.out.println("Monday removed");
			classSelectionModel.setQueryControl('p', "M");
		}
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Tuesday to alter final query
	 */
	public void cbTuesCheck(ActionEvent event) throws SQLException {
		if(!tuesBool) {
			tuesBool = true;
			System.out.println("Tuesday added");
			classSelectionModel.setQueryControl('o', "T");
		}
		else {
			tuesBool = false;
			System.out.println("Tuesday removed");
			classSelectionModel.setQueryControl('p', "T");
		}
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Wednesday to alter final query
	 */
	public void cbWedCheck(ActionEvent event) throws SQLException {
		
		if(!wedBool) {
			wedBool = true;
			System.out.println("Wednesday added");
			classSelectionModel.setQueryControl('o', "W");
		}
		else {
			wedBool = false;
			System.out.println("Wednesday removed");
			classSelectionModel.setQueryControl('p', "W");
		}
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Thursday to alter final query
	 */
	public void cbThursCheck(ActionEvent event) throws SQLException {

		if(!thursBool) {
			thursBool = true;
			System.out.println("Thursday added");
			classSelectionModel.setQueryControl('o', "R");
		}
		else {
			thursBool = false;
			System.out.println("Thursday removed");
			classSelectionModel.setQueryControl('p', "R");
		}
	}
	
	
	/*
	 * Postcondition: Uses selection from checkbox Friday to alter final query
	 */
	public void cbFriCheck(ActionEvent event) throws SQLException {
		
		if(!friBool) {
			friBool = true;
			System.out.println("Friday added");
			classSelectionModel.setQueryControl('o', "F");
		}
		else {
			friBool = false;
			System.out.println("Friday removed");
			classSelectionModel.setQueryControl('p', "F");
		}
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Saturday to alter final query
	 */
	public void cbSatCheck(ActionEvent event) throws SQLException {
		
		if(!satBool) {
			satBool = true;
			System.out.println("Saturday added");
			classSelectionModel.setQueryControl('o', "S");
		}
		else {
			satBool = false;
			System.out.println("Saturday removed");
			classSelectionModel.setQueryControl('p', "S");
		}
	}
	
	/*
	 * Postcondition: Uses selection from checkbox Sunday to alter final query
	 */
	public void cbSunCheck(ActionEvent event) throws SQLException {
		
		if(!sunBool) {
			sunBool = true;
			System.out.println("Sunday added");
			classSelectionModel.setQueryControl('o', "U");
		}
		else {
			sunBool = false;
			System.out.println("Sunday removed");
			classSelectionModel.setQueryControl('p', "U");
		}
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
		termBox.setValue(termBox.getPromptText()); 
		degreeBox.setValue(degreeBox.getPromptText());
		subjectBox.setValue(""); 
		instructorBox.setValue("");
		startHrBox.setValue(startHrBox.getPromptText());
		startMinBox.setValue(startMinBox.getPromptText());
		startPBox.setValue(startPBox.getPromptText());
		endHrBox.setValue(endHrBox.getPromptText());
		endMinBox.setValue(endMinBox.getPromptText());
		endPBox.setValue(endPBox.getPromptText());
		classSectionField.setText("");
		classTitleField.setText("");
		minCredField.setText("");
		maxCredField.setText("");
		if(monBool) cbMon.fire();
		if(tuesBool) cbTues.fire();
		if(wedBool) cbWed.fire(); 
		if(thursBool) cbThurs.fire();
		if(friBool) cbFri.fire(); 
		if(satBool) cbSat.fire();
		if(sunBool) cbSun.fire();
		//repopCbs();
		
	}
	
	/*
	 * Postcondition: Uses query value to execute class search query
	 */
	public void search(ActionEvent event) throws SQLException {
		System.out.println("search");
		subChanged();
		sectionChanged();
		titleChanged();
		minCredChanged();
		maxCredChanged();
		instChanged();
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
	
	/*
	 * Postcondition: loads the registration menu
	 */
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

	@FXML
	private void addDropClicked(ActionEvent event) throws IOException, SQLException {       //Registration button is clicked
		String[] classes = {classSelection1.getText(), classSelection2.getText(),
				classSelection3.getText(), classSelection4.getText()};  
		sample.AddDrop_Controller.addClassesStr = classes;
		}
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	        if(classSelection1.getText().isEmpty()) {
	        	classSelection1.setText(
	        			classResultsTable.getSelectionModel().getSelectedItem().getSection());
	        }
	        else if(classSelection2.getText().isEmpty()) {
	        	classSelection2.setText(
	        			classResultsTable.getSelectionModel().getSelectedItem().getSection());
	        }
        	else if(classSelection3.getText().isEmpty()) {
        		classSelection3.setText(
        				classResultsTable.getSelectionModel().getSelectedItem().getSection());
        	}
	        else if(classSelection4.getText().isEmpty()) {
	        	classSelection4.setText(
	        			classResultsTable.getSelectionModel().getSelectedItem().getSection());
	        }
	    }
	}
	
}
