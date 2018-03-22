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
	int controlN = 15;
	String[] queryControlArr = new String[controlN];
	// 0) term, 1) degree, 2) subject, 3) section, 4) title,
	// 5) Min Credits, 6) Max Credits
	// 7) Start Hour, 8) Start Minute, 9) Start Period,
	// 10) End Hour, 11) End Minute, 12) End Period,
	String qTerm, qDegreeName, qSubjectName, qLastName,
			qSection, qTitle, qStartHr, qStartMin, 
			qStartP, qEndHr, qEndMin, qEndP;
	int qMinCred, qMaxCred;
			//Inner Join
	String 	IJTermID = "inner join TermTable on ClassTable.termid = "
				+ "TermTable.termid and TermTable.Term = ",
			IJDegreeID = "inner join DegreeTable on DegreeTable.DegreeID = "
				+ "ClassTable.DegreeID and DegreeTable.DegreeName = ",
			IJSubjectID = "inner join SubjectTable on SubjectTable.SubjectID = "
					+ "ClassTable.SubjectID and SubjectTable.SubjectName = ",
			IJEmployeeID = "inner join EmployeeTable on EmployeeTable.EmployeeID = "
					+ "ClassTable.EmployeeID and EmployeeTable.LastName = ",
			//Where/And...
			waSection = "ClassTable.CourseSection = ",
			waTitle = "ClassTable.ClassName like ",
			waMinCred = "ClassTable.CreditHours >= ",
			waMaxCred = "ClassTable.CreditHours <= ",
			waStartHr = "ClassTable.TimeStart >= '",
			waStartMin = ":",
			waStartPAnd = "ClassTable.TimeStart like ",
			waStartPOr = "",
			waEndHr = "ClassTable.TimeEnd <= '",
			waEndMin = ":",
			waEndPAnd = "ClassTable.TimeStart like ",
			waEndPOr = "",
			waDays = "ClassTable.Days like ";
	
	StringBuilder queryControlBldr, daysBldr;
	boolean hasIJ = false;
	
	/*
	 * Precondition: Constructor evaluates DB location values from SqliteConnection
	 * Postcondition: Constructor initializes connection to database 
	 * or handles exceptions in the case of failure
	 */
	public ClassSelectionModel() {
		queryControlArr = new String[controlN];
		queryControlBldr = new StringBuilder();
		daysBldr = new StringBuilder();
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
		// 0) term, 1) degree, 2) subject, 3) instructor, 
		// 4) section, 5) title, 6) min credits, 7) max credits
		switch (ch) {
			//term
			case 'a': 
				qTerm = singleQuotes(key);
				queryControlArr[0] = IJTermID + qTerm + "\n";
				hasIJ = true;
				break;
			//degree
			case 'b':
				qDegreeName = singleQuotes(key);
				queryControlArr[1] = IJDegreeID + qDegreeName + "\n";
				hasIJ = true;
				break;
			//subject
			case 'c':
				qSubjectName = singleQuotes(key);
				queryControlArr[2] = IJSubjectID + qSubjectName + "\n";
				hasIJ = true;
				break;
			//instructor
			case 'd':
				qLastName = singleQuotes(key);
				queryControlArr[3] = IJEmployeeID + qLastName + "\n";
				hasIJ = true;
				break;
			//section
			case 'e':
				qSection = singleQuotes(key);
				queryControlArr[4] = waSection + qSection + "\n";
				break;
			//title
			case 'f':
				qTitle = singleQuotes(key);
				queryControlArr[5] = waTitle + qTitle + "\n";
				break;
			//minimum credits
			case 'g':
				qMinCred = Integer.parseInt(key);
				queryControlArr[6] = waMinCred + qMinCred + "\n";
				break;
			//maximum credits
			case 'h':
				qMaxCred = Integer.parseInt(key);
				queryControlArr[7] = waMaxCred + qMaxCred + "\n";
				break;
			//start hour
			case 'i':
				qStartHr = key;
				queryControlArr[8] = "\n" + waStartHr + qStartHr;
				break;
			//start minute
			case 'j':
				qStartMin = key;
				queryControlArr[9] = waStartMin + qStartMin + "'\n";
				break;
			//start period
			case 'k':
				qStartP = singleQuotes("%" + key);
				if (key.equals("am")) {
					waStartPOr = " or ClassTable.TimeStart like 'pm'\n";
				}
				//toothpicks and bubblegum, problem with query on string is 12pm makes more sense as 00pm
				if (key.equals("pm") && qStartHr.equals("12")) {
				queryControlArr[8] = "\n" + waStartHr + "00";
				}
				queryControlArr[10] = "\n" + waStartPAnd + qStartP + waStartPOr + "\n";
				break;
			//end hour
			case 'l':
				qEndHr = key;
				queryControlArr[11] = "\n" + waEndHr + qEndHr;
				break;	
			//end minute
			case 'm':
				qEndMin = key;
				queryControlArr[12] = waEndMin + qEndMin + "'\n";
				break;
			//end period
			case 'n':
				qEndP = singleQuotes("%" + key);
				if (key.equals("pm")) waEndPOr = " or ClassTable.TimeStart like 'am'";
				queryControlArr[13] = waEndPAnd + qEndP + waEndPOr + "\n";
				break;	
			//Monday
			case 'o':
				daysBldr.append(key);
				queryControlArr[14] = waDays + singleQuotes(daysBldr.toString());
				break;
			//reset
			case 'z':
				queryControlArr = new String[controlN];
				queryControlBldr = new StringBuilder();
				daysBldr = new StringBuilder();
				hasIJ = false;
		}
	}
	
	/**
	*Precondition: takes values from queryControlArr[] to build a single query string
	*				tends to rely heavily on input order in particular cases from MainController
	*Postcondition: produces query string from queryControlArr[]
	*/
	public String buildQuery() {
		queryControlBldr = new StringBuilder();
		for (int i = 0; i < controlN; i++) {
			if (queryControlArr[i] != null) {
				// if past the inner join statements and not start min or end min 
				// add a 'where' or 'and' to the statement
				if (i > 3 && i != 9 && i != 12) {
					if(!hasIJ) {
						queryControlBldr.append("where ");
						//change hasIJ to false purely for code simplicity, will append 'and' after first instance
						hasIJ = true;
					}
					else {
						queryControlBldr.append("and ");
					}
				}
				queryControlBldr.append(queryControlArr[i]);
			}
		}
		return queryControlBldr.toString();
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
			String query = "select " + field + " from " + table + " " + buildQuery();
			//print query value to system out for debugging
			//System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				//print field value to system out for debugging
				//System.out.println(resultSet.getString(field));
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
	
	/*
	 * Precondition: takes string value
	 * Postcondition: returns string value surrounded by single quotes for query purposes
	 */
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
			String query = "select * from ClassTable " + "\n" + buildQuery();
			//print query value to system out for debugging
			System.out.println(query);
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
		
			while (resultSet.next()) {
				//desired fields are 1,2,3,4,6,7,8,9,10,13
				//potentially could be changed to evaluate string values
				//System.out.println(resultSet.getString(1) + ", " + resultSet.getString(2)
				//+ ", " + resultSet.getString(3) + ", " + resultSet.getString(4));
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


