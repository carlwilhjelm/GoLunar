package sample;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class TranscriptDetails {
    ResultSet rs;
    Statement st;
    private static int studentID;
    int count;
    public static Connection connect = SqliteConnection.Connector();

    //ID1 -> class1ID properties, ID2 -> class2ID properties, etc
    private StringProperty ID1subject, ID1crn, ID1title, ID1grade,
            ID1credit, ID2subject, ID2crn, ID2title, ID2grade,
    ID2credit,ID3subject, ID3crn, ID3title, ID3grade,
    ID3credit,ID4subject, ID4crn, ID4title, ID4grade,
    ID4credit;

    public TranscriptDetails(int StudentIDin) throws SQLException{
        studentID = StudentIDin;
        queryDB(StudentIDin);
    }

    private void queryDB(int studentIDin) throws SQLException {
    setClass1and2properties(studentIDin);
    }
    private void setClass1and2properties(int studentIDin) throws SQLException{
        st = connect.createStatement();
        rs = st.executeQuery("SELECT * FROM StudentTable where StudentID = '" + studentIDin + "'");

        if(rs.getObject(4) != null){
            int subjectid1 = rs.getInt(4);
            ID1subject = new SimpleStringProperty(getSubjectDB(subjectid1) + " " + rs.getString(3)); //sets subject of class
            int classid1 = rs.getInt(8);
            rs = getClassInfoDB(classid1);

            ID1crn = new SimpleStringProperty("" + rs.getInt(1)); //sets the CRN of the first class in transcript
            ID1title = new SimpleStringProperty(rs.getString(2));   //sets title of the first class in transcript
            ID1credit = new SimpleStringProperty(rs.getString(10)); //sets the credit hours of the first class in transcript
            rs = st.executeQuery("SELECT * FROM StudentTable where StudentID = '" + studentIDin + "'");
            int gradeid1 = rs.getInt(9);
            ID1grade = new SimpleStringProperty(getGradeDB(gradeid1));



        }



    }
    private ResultSet getClassInfoDB(int classid) throws SQLException {

        ResultSet temp;
        Statement statement = connect.createStatement();
        //retrieve first class result set
        temp = statement.executeQuery("SELECT * FROM ClassTable where ClassID" +
                " = '" + classid + "'");

        return temp;

    }
    private String getSubjectDB(int subjectID) throws SQLException{
        ResultSet temp;
        Statement statement = connect.createStatement();

        temp = statement.executeQuery("SELECT SubjectName FROM " +
                "SubjectTable " + "where " + "subjectID = '" + subjectID + "'");
        if(temp.next()){
            return temp.getString(1);
        }
        return null;
    }
    private String getGradeDB(int gradeID) throws SQLException{
        ResultSet temp;
        Statement statement = connect.createStatement();
System.out.println(gradeID);
        temp = statement.executeQuery("SELECT FinalLetterGrade_For_Class FROM " + "FinalLetterGrade_for_class " + "where "
                                        + "FinalGrade_ID = '" + gradeID + "'");
        if(temp.next()){
            return temp.getString(1);
        }
        return null;
    }
    public String getID1subject() {
        return ID1subject.get();
    }
    public String getID1crn(){
        return String.valueOf(ID1crn);
    }
    public String getID1title(){
        return String.valueOf(ID1title);
    }
    public String getID1grade(){
        return String.valueOf(ID1grade);
    }
    public String getID1credit(){
        return String.valueOf(ID1credit);
    }
    public StringProperty getID2subject(){
        return ID2subject;
    }
    public StringProperty getID2crn(){
        return ID2crn;
    }
    public StringProperty getID2title(){
        return ID2title;
    }
    public StringProperty getID2grade(){
        return ID2grade;
    }
    public StringProperty getID2credit(){
        return ID2credit;
    }
    public StringProperty getID3subject(){
        return ID3subject;
    }
    public StringProperty getID3crn(){
        return ID3crn;
    }
    public StringProperty getID3title(){
        return ID3title;
    }
    public StringProperty getID3grade(){
        return ID3grade;
    }
    public StringProperty getID3credit(){
        return ID3credit;
    }
    public StringProperty getID4subject(){
        return ID4subject;
    }
    public StringProperty getID4crn(){
        return ID4crn;
    }
    public StringProperty getID4title(){
        return ID4title;
    }
    public StringProperty getID4grade(){
        return ID4grade;
    }
    public StringProperty getID4credit(){
        return ID4credit;
    }




}
