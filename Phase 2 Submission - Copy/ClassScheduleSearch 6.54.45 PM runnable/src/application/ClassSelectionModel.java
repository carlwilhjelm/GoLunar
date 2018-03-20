package application;
import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;


/*
 * ClassSelectionModel manages the connection to the SQLite database and 
 * handles all queries therefrom
 */
public class ClassSelectionModel {
	Connection connection;
	String queryControlStr, qTerm, qDegreeName, qSubjectName, qLastName;
	String 	IJTermID = "inner join TermTable on ClassTable.termid = "
				+ "TermTable.termid and TermTable.Term = ",
			IJDegreeID = "inner join DegreeTable on DegreeTable.DegreeID = "
				+ "ClassTable.DegreeID and DegreeTable.DegreeName = ",
			IJSubjectID = "inner join SubjectTable on SubjectTable.SubjectID = "
					+ "ClassTable.SubjectID and SubjectTable.SubjectName = ",
			IJEmployeeID = "inner join EmployeeTable on EmployeeTable.EmployeeID = "
					+ "ClassTable.EmployeeID and EmployeeTable.LastName = ";
	StringBuilder queryControl;
	
	/*
	 * Precondition: Constructor evaluates DB location values from SqliteConnection
	 * Postcondition: Constructor initializes connection to database 
	 * or handles exceptions in the case of failure
	 */
	public ClassSelectionModel() {
		queryControlStr = "";
		queryControl = new StringBuilder();
		connection = SqliteConnection.Connector();
		if (connection == null) {
			System.out.println("DB Connection failed");
			System.exit(1);
		}
	}
	
	/*
	 * Postcondition: returns boolean value for connection to DB
	 */
	public boolean isDbConnected() {
		try {
			return !connection.isClosed();
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Precondition: takes arguments from MainController to generate query
	 * Postcondition: appends to queryControl then sets queryControlStr to new value
	 */
	public void setQueryControl(char ch, String key) throws SQLException {
	
		switch (ch) {
			//term
			case 't': 
				qTerm = singleQuotes(key);
				queryControl.append(IJTermID + qTerm + "\n");
				break;
			//degree
			case 'd':
				qDegreeName = singleQuotes(key);
				queryControl.append(IJDegreeID + qDegreeName + "\n");
				break;
			//subject
			case 's':
				qSubjectName = singleQuotes(key);
				queryControl.append(IJSubjectID + qSubjectName + "\n");
				break;
			//instructor
			case 'i':
				qLastName = singleQuotes(key);
				queryControl.append(IJEmployeeID + qLastName + "\n");
				break;
			//reset
			case 'r':
				queryControl = new StringBuilder();
		}
			
	
	queryControlStr = queryControl.toString();
	System.out.println(queryControlStr);
	}
	
	/*
	 * Precondition: takes params field and table from MainController
	 * Postcondition: fills comboboxes with appropriate values from DB
	 */
	public ObservableList<String> fillComboBox(String field, String table) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ObservableList<String> options = FXCollections.observableArrayList();
		try {
			String query = "select " + field + " from " + table + " " + queryControlStr;
			//print query value to system out for debugging
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				//print field value to system out for debugging
				System.out.println(resultSet.getString(field));
				options.add(resultSet.getString(field));
				
			}
			return options;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		finally {
		preparedStatement.close();
		resultSet.close();
		}
	}
	
	public String singleQuotes(String x) {
		return "\'" + x + "\'";
	}
	
	/*
	 * Precondition: 	Called from MainController to populate classSearchTable with an
	 * 					ObservableList<ClassDetails>
	 * 
	 * Postcondition: 	Takes field values returned from DB query to create ClassDetail objects
	 * 					and add them to the returned ObservableList<ClassDetails> to then be displayed in table
	 */
	public ObservableList<ClassDetails> search() throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ObservableList<ClassDetails> options = FXCollections.observableArrayList();
		try {
			String query = "select * from ClassTable " + "\n" + queryControlStr;
			//print query value to system out for debugging
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				//desired fields are 1,2,3,4,6,7,8,9,10,13
				//potentially could be changed to evaluate string values
				System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2)
				+ ", " + resultSet.getString(3) + ", " + resultSet.getString(4));
				options.add(new ClassDetails(
						resultSet.getString(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(6),
						resultSet.getString(7),
						resultSet.getString(8),
						resultSet.getString(9),
						resultSet.getString(10),
						resultSet.getString(13)
						));
				
			}
			return options;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
		finally {
		preparedStatement.close();
		resultSet.close();
		}
	}
	
	
	
}


