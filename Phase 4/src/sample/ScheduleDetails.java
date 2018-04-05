package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** READ ME first!
 * When creating Object of this class, NEED StudentID as constructor arg
 * This class the relevant information of the student's schedule
 * and stores it as globally defined variables, accessed through getter()
 * methods, studentID must be argument to the constructor to instantiate class
 * if at anytime you need the schedule info properties, then instantiate an
 * object of this class and always pass the corrent studentID in, and then
 * access the information you want through the getter() methods.
 *
 * IMPORTANT: if the student drops/adds/withdraw, need to check if up to 4
 * classID properties (the variables below) are null),
 * NOTE: the way this class checks if the classID# exits in the ResultSet
 * row, is if the corresponding column Index of the ClassID# != null, so it is
 * really important when a student drops a
 * class that the value inside the sql table corresponding to the CLASSID# is
 * set to null.
 *
 * ALWAYS check for null value in the global variables, USE "getID#crn()"
 * method to check for nullity, using any other method will throw a
 * NullPointerException; all getID#crn use ternary expressions and return
 * null if the object is not instantiated.
 */
public class ScheduleDetails {

    ResultSet rs = null;
    Statement st;
    private int studentID;
    int count;
    public static Connection connect = SqliteConnection.Connector();

    //ID1 -> class1ID properties, ID2 -> class2ID properties, etc
    private StringProperty ID1crn, ID1subjectName, ID1coursetitle, ID1credits,
            ID1days, ID1time, ID1location, ID1instructor, ID1Desc, ID2crn,
            ID2subjectName, ID2coursetitle, ID2credits,
            ID2days, ID2time, ID2location, ID2instructor, ID2Desc, ID3crn,
            ID3subjectName, ID3coursetitle, ID3credits,
            ID3days, ID3time, ID3location, ID3instructor, ID3Desc, ID4crn,
            ID4subjectName, ID4coursetitle, ID4credits,
            ID4days, ID4time, ID4location, ID4instructor, ID4Desc;



    //name constructor important for deriving all other important information
    // in this class
    public ScheduleDetails(int studentIDin) throws SQLException {

        studentID = studentIDin;
        //query all class schedule information and store results in global
        // variables
        System.out.println("student ID: " + studentIDin);
        queryDB(studentIDin);

    }

    private void queryDB(int studentIDin) throws SQLException {

        setClass1and2properties(studentIDin);
        setClass3and4properties(studentIDin);

        //prints to console for debugging purposes
        if (ID1crn != null) {
            System.out.println("Test results: class1 properties: " + ID1crn.get() +
                    ", " +
                    "" + ID1subjectName.get() + ". " + ID1coursetitle.get() + ", " +
                    ID1credits.get() + ", " + ID1days.get() + ", " + ID1time.get() +
                    ", " + ID1location.get() + ", " + ID1instructor.get() +
                    ", Description: " + ID1Desc.get());
        }

        if (ID2crn != null) {
            System.out.println("Test results: class2 properties: " + ID2crn.get() +
                    ", " + ID2subjectName.get() +
                    "" + ", " + ID2coursetitle.get() + ", " +
                    ID2credits.get() + ", " + ID2days.get() + ", " + ID2time.get()
                    + ", " + ID2location.get() + ", " + ID2instructor.get()  +
                    ", Description: " + ID2Desc.get());

        }
        if (ID3crn != null) {
            System.out.println("Test results: class3 properties: " + ID3crn.get() +
                    ", " + ID3subjectName.get() +
                    "" + ", " + ID3coursetitle.get() + ", " +
                    ID3credits.get() + ", " + ID3days.get() + ", " + ID3time.get()
                    + ", " + ID3location.get() + ", " + ID3instructor.get() +
                    ", Description: " + ID3Desc.get());

        }

        if (ID4crn != null) {
            System.out.println("Test results: class4 properties: " + ID4crn.get() +
                    ", " + ID4subjectName.get() +
                    "" + ", " + ID4coursetitle.get() + ", " +
                    ID4credits.get() + ", " + ID4days.get() + ", " + ID4time.get()
                    + ", " + ID4location.get() + ", " + ID4instructor.get() +
                    ", Description: " + ID4Desc.get());
        }

}


    /**
     * @param studentIDin the studentID to set class1ID properties to make
     *                    life easier and make code more concise
     * @throws SQLException
     */
    private void setClass1and2properties(int studentIDin) throws SQLException {

        st = connect.createStatement();

        //get results set of student, then find class names, set variables etc
        rs = st.executeQuery("SELECT * FROM StudentTable where StudentID = '"
                + studentIDin + "'");

        //check if the DB Class1ID field is null, (then no
        // class1 exist, student doesnt have a class1, checks for null return)
        if (rs.getObject(8) != null) {

            int classid1 = rs.getInt(8);

            //retrieve first class result set
            rs = getClassInfoDB(classid1);

            ID1crn = new SimpleStringProperty("" + rs.getInt(1));
            ID1coursetitle = new SimpleStringProperty(rs.getString(2));
            ID1days = new SimpleStringProperty(rs.getString(6));
            ID1time = new SimpleStringProperty(rs.getString(7));
            ID1location = new SimpleStringProperty(rs.getString(8));
            ID1credits = new SimpleStringProperty(rs.getString(10));


            //retrieve Subject from SubjectTable
            int classID1subjectID = rs.getInt(12);
            ID1subjectName = new SimpleStringProperty(getSubjectDB(classID1subjectID)
                    + " " + rs.getString(3));


            //retrieve Employee name from EmployeeTable
            int classID1employeeID = rs.getInt(4);
            ID1instructor = new SimpleStringProperty(getInstructorName
                    (classID1employeeID));

            //retrieve ClassDescription from ClassTable
            ID1Desc = new SimpleStringProperty(getClassDesc(classid1));
        }

        /*
        * ClassID2: Second class of student-set up properties
        **Important**: must set up new statement connection and ResultSet, or
        *  else SQL will throw exception "ResultSet closed".
        */

        Statement statement = connect.createStatement();
        ResultSet result2 = null;
        //get results set of student, then find class names, set variables etc
        result2 = statement.executeQuery("SELECT * FROM StudentTable where " +
                "StudentID = '" + studentIDin + "'");

        if(result2.getObject(10) != null) {

            //if it exists in the database
            int classid2 = result2.getInt(10);
            result2 = getClassInfoDB(classid2);

            ID2crn = new SimpleStringProperty("" + result2.getInt(1));
            ID2coursetitle = new SimpleStringProperty(result2.getString(2));
            ID2days = new SimpleStringProperty(result2.getString(6));
            ID2time = new SimpleStringProperty(result2.getString(7));
            ID2location = new SimpleStringProperty(result2.getString(8));
            ID2credits = new SimpleStringProperty(result2.getString(10));


            //retrieve Subject from SubjectTable
            int classID2subjectID = result2.getInt(12);
            ID2subjectName = new SimpleStringProperty(getSubjectDB
                    (classID2subjectID) + " " + result2.getString(3));


            //retrieve Employee name from EmployeeTable
            int classID2employeeID = result2.getInt(4);
            ID2instructor = new SimpleStringProperty(getInstructorName
                    (classID2employeeID));

            //retrieve ClassDescription from ClassTable
            ID2Desc = new SimpleStringProperty(getClassDesc(classid2));
        }
    }


    private void setClass3and4properties(int studentIDin) throws SQLException {

        Statement st = connect.createStatement();

        //get results set of student, then find class names, set variables etc
        rs = st.executeQuery("SELECT * FROM StudentTable where StudentID = '"
                + studentIDin + "'");

        //check if classID3 field in DB is null (i.e. student dropped a class
        // and value is null
        if (rs.getObject(12) != null) {

            int classid3 = rs.getInt(12);

            //retrieve first class result set
            rs = getClassInfoDB(classid3);

            //set class variables to database result set
            ID3crn = new SimpleStringProperty("" + rs.getInt(1));
            ID3coursetitle = new SimpleStringProperty(rs.getString(2));
            ID3days = new SimpleStringProperty(rs.getString(6));
            ID3time = new SimpleStringProperty(rs.getString(7));
            ID3location = new SimpleStringProperty(rs.getString(8));
            ID3credits = new SimpleStringProperty(rs.getString(10));

            //retrieve Subject from SubjectTable
            int classID3subjectID = rs.getInt(12);
            ID3subjectName = new SimpleStringProperty(getSubjectDB
                    (classID3subjectID) + " " + rs.getString(3));

            //retrieve Employee name from EmployeeTable
            int classID3employeeID = rs.getInt(4);
            ID3instructor = new SimpleStringProperty(getInstructorName
                    (classID3employeeID));

            //retrieve ClassDescription from ClassTable
            ID3Desc = new SimpleStringProperty(getClassDesc(classid3));
        }

        /* Class4 properties, check if student has dropped ClassID4 in DB
         *
         */
        Statement statement4 = connect.createStatement();
        ResultSet result4 = null;
        //get results set of student, then find class names, set variables etc
        result4 = statement4.executeQuery("SELECT * FROM StudentTable" +
                " " + "where " + "StudentID = '" + studentIDin + "'");

        //null check in DB
        if (result4.getObject(14) != null) {

            int classid4 = result4.getInt(14);

            //retrieve first class result set
            result4 = getClassInfoDB(classid4);

            //set class variables to database result set
            ID4crn = new SimpleStringProperty("" + result4.getInt(1));
            ID4coursetitle = new SimpleStringProperty(result4.getString(2));
            ID4days = new SimpleStringProperty(result4.getString(6));
            ID4time = new SimpleStringProperty(result4.getString(7));
            ID4location = new SimpleStringProperty(result4.getString(8));
            ID4credits = new SimpleStringProperty(result4.getString(10));


            //retrieve Subject from SubjectTable
            int classID4subjectID = result4.getInt(12);
            ID4subjectName = new SimpleStringProperty(getSubjectDB
                    (classID4subjectID) + " " + result4.getString(3));


            //retrieve Employee name from EmployeeTable
            int classID4employeeID = result4.getInt(4);
            ID4instructor = new SimpleStringProperty(getInstructorName
                    (classID4employeeID));

            //retrieve ClassDescription from ClassTable
            ID4Desc = new SimpleStringProperty(getClassDesc(classid4));
        }
    }


    /**
     * @param employeeID the instructor's employeeID to grab from DB
     * @return return's the instructor's first name and last name
     * @throws SQLException
     */
    private String getInstructorName(int employeeID) throws SQLException {

        ResultSet temp;
        Statement statement = connect.createStatement();

        temp = statement.executeQuery("SELECT FirstName, LastName FROM " +
                "EmployeeTable " + "where EmployeeID = '" + employeeID + "'");

        String firstname= temp.getString(1);
        String lastname = temp.getString(2);

        return (firstname + " " + lastname);

    }

    /**
     * @param classid the classID to pull infromation from Database
     * @return  returns the resultSet of the query
     * @throws SQLException
     */
    private ResultSet getClassInfoDB(int classid) throws SQLException {

        ResultSet temp;
        Statement statement = connect.createStatement();
        //retrieve first class result set
        temp = statement.executeQuery("SELECT * FROM ClassTable where ClassID" +
                " = '" + classid + "'");

        return temp;

    }

    /**
     * @param subjectID the subjectID we want to query to database
     * @return returns the name of the subject
     */
    private String getSubjectDB(int subjectID) throws SQLException {

        ResultSet temp;
        Statement statement = connect.createStatement();

        temp = statement.executeQuery("SELECT SubjectName FROM " +
                "SubjectTable " + "where " + "subjectID = '" + subjectID + "'");

        if (temp.next()) {
            return temp.getString(1);
        }

        return null;

    }

    /**
     * @param classid the classID in the DB
     * @return return's the class description
     * @throws SQLException
     */
    private String getClassDesc(int classid) throws SQLException {

        ResultSet temp;
        Statement statement = connect.createStatement();

        temp = statement.executeQuery("SELECT ClassDescription FROM " +
                "ClassTable where ClassID = '" + classid + "'");

        if (temp.next()) {
            return temp.getString(1);
        }

        return null;


    }


    public String getID1crn() {
        return ID1crn != null ? ID1crn.get() : null;
    }

    public StringProperty ID1crnProperty() {
        return ID1crn != null ? ID1crn : null;
    }

    public String getID1subjectName() {
        return ID1subjectName.get();
    }

    public StringProperty ID1subjectIDProperty() {
        return ID1subjectName;
    }

    public String getID1coursetitle() {
        return ID1coursetitle.get();
    }

    public StringProperty ID1coursetitleProperty() {
        return ID1coursetitle;
    }

    public String getID1credits() {
        return ID1credits.get();
    }

    public StringProperty ID1creditsProperty() {
        return ID1credits;
    }

    public String getID1days() {
        return ID1days.get();
    }

    public StringProperty ID1daysProperty() {
        return ID1days;
    }

    public String getID1time() {
        return ID1time.get();
    }

    public StringProperty ID1timeProperty() {
        return ID1time;
    }

    public String getID1location() {
        return ID1location.get();
    }

    public StringProperty ID1locationProperty() {
        return ID1location;
    }

    public String getID1instructor() {
        return ID1instructor.get();
    }

    public StringProperty ID1instructorProperty() {
        return ID1instructor;
    }

    public String getID2crn() {
        return ID2crn != null ? ID2crn.get() : null;
    }

    public StringProperty ID2crnProperty() {
        return ID2crn != null ? ID2crn : null;
    }

    public String getID2subjectName() {
        return ID2subjectName.get();
    }

    public StringProperty ID2subjectNameProperty() {
        return ID2subjectName;
    }

    public String getID2coursetitle() {
        return ID2coursetitle.get();
    }

    public StringProperty ID2coursetitleProperty() {
        return ID2coursetitle;
    }

    public String getID2credits() {
        return ID2credits.get();
    }

    public StringProperty ID2creditsProperty() {
        return ID2credits;
    }

    public String getID2days() {
        return ID2days.get();
    }

    public StringProperty ID2daysProperty() {
        return ID2days;
    }

    public String getID2time() {
        return ID2time.get();
    }

    public StringProperty ID2timeProperty() {
        return ID2time;
    }

    public String getID2location() {
        return ID2location.get();
    }

    public StringProperty ID2locationProperty() {
        return ID2location;
    }

    public String getID2instructor() {
        return ID2instructor.get();
    }

    public StringProperty ID2instructorProperty() {
        return ID2instructor;
    }

    public String getID3crn() {
        return ID3crn != null ? ID3crn.get() : null;
    }

    //use this value to check for null value
    public StringProperty ID3crnProperty() {
        return ID3crn != null ? ID3crn : null;
    }

    public String getID3subjectName() {
        return ID3subjectName.get();
    }

    public StringProperty ID3subjectNameProperty() {
        return ID3subjectName;
    }

    public String getID3coursetitle() {
        return ID3coursetitle.get();
    }

    public StringProperty ID3coursetitleProperty() {
        return ID3coursetitle;
    }

    public String getID3credits() {
        return ID3credits.get();
    }

    public StringProperty ID3creditsProperty() {
        return ID3credits;
    }

    public String getID3days() {
        return ID3days.get();
    }

    public StringProperty ID3daysProperty() {
        return ID3days;
    }

    public String getID3time() {
        return ID3time.get();
    }

    public StringProperty ID3timeProperty() {
        return ID3time;
    }

    public String getID3location() {
        return ID3location.get();
    }

    public StringProperty ID3locationProperty() {
        return ID3location;
    }

    public String getID3instructor() {
        return ID3instructor.get();
    }

    public StringProperty ID3instructorProperty() {
        return ID3instructor;
    }

    public String getID4crn() {
        return ID4crn != null ? ID4crn.get() : null;
    }

    //Use this method to check for null value
    public StringProperty ID4crnProperty() {
        return ID4crn != null ? ID4crn : null;
    }

    public String getID4subjectName() {
        return ID4subjectName.get();
    }

    public StringProperty ID4subjectNameProperty() {
        return ID4subjectName;
    }

    public String getID4coursetitle() {
        return ID4coursetitle.get();
    }

    public StringProperty ID4coursetitleProperty() {
        return ID4coursetitle;
    }

    public String getID4credits() {
        return ID4credits.get();
    }

    public StringProperty ID4creditsProperty() {
        return ID4credits;
    }

    public String getID4days() {
        return ID4days.get();
    }

    public StringProperty ID4daysProperty() {
        return ID4days;
    }

    public String getID4time() {
        return ID4time.get();
    }

    public StringProperty ID4timeProperty() {
        return ID4time;
    }

    public String getID4location() {
        return ID4location.get();
    }

    public StringProperty ID4locationProperty() {
        return ID4location;
    }

    public String getID4instructor() {
        return ID4instructor.get();
    }

    public StringProperty ID4instructorProperty() {
        return ID4instructor;
    }

    //get studentID from class
    public String getStudentID() {
        return "" + studentID;
    }

    //sets the studentsID variable
    public void setStudentID(int studentIDin) {
        studentID = studentIDin;
    }

    public String getID1Desc() {
        return ID1Desc.get();
    }

    public StringProperty ID1DescProperty() {
        return ID1Desc;
    }

    public String getID2Desc() {
        return ID2Desc.get();
    }

    public StringProperty ID2DescProperty() {
        return ID2Desc;
    }

    public String getID3Desc() {
        return ID3Desc.get();
    }

    public StringProperty ID3DescProperty() {
        return ID3Desc;
    }

    public String getID4Desc() {
        return ID4Desc.get();
    }

    public StringProperty ID4DescProperty() {
        return ID4Desc;
    }
}
