package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
	
	public TextField courseNum, title, credFrom, credTo;
	
	@FXML
	private TableView<ClassDetails> classResultsTable;
	
	@FXML
	private TableColumn<ClassDetails, String> 	classIDCol, classNameCol,
												classSectionCol, classEmployeeIdCol, 
												classDaysCol, classTimeCol,
												classRoomCol, classDescriptionCol,
												classCreditHoursCol, classPrerequisitesCol;

	public String termStr = "", degStr = "", subStr = "", instStr = "";
	
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
		classSelectionModel.setQueryControl('t', termStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from degreeBox to alter final query
	 */
	public void degChanged(ActionEvent event) throws SQLException {
		System.out.println("degChanged");
		degStr = degreeBox.getValue();
		classSelectionModel.setQueryControl('d', degStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from subjectBox to alter final query
	 */
	public void subChanged(ActionEvent event) throws SQLException {
		System.out.println("subChanged");
		subStr = subjectBox.getValue();
		classSelectionModel.setQueryControl('s', subStr);
		//repopCbs();
	}
	
	/*
	 * Postcondition: Uses selection from instructorBox to alter final query
	 */
	public void instChanged(ActionEvent event) throws SQLException {
		System.out.println("instChanged");
		instStr = instructorBox.getValue();
		classSelectionModel.setQueryControl('i', instStr);
		//repopCbs();
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
		classSelectionModel.setQueryControl('r', "");
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
}
