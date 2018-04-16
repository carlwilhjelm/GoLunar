package sample;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.security.auth.Subject;

public class StudentDetails {
    private StringProperty StudentID, FirstName, LastName, SubjectID, GPA,
            DegreeID, CreditsEarned, Class1ID, Class1_FinalGradeID, Class2ID,
            Class2_FinalGrade, Class3ID, Class3_FinalGrade, Class4ID, CLass4_FinalGrade,
            Username, Password_Plain, SHA1_HASH_Password;
    public StudentDetails(String StudentIDIn, String FirstNameIn,
                        String LastNameIn, String SubjectIDIn,
                        String GPAIn, String DegreeIDIn,
                        String CreditsEarnedIn, String Class1IDIn,
                        String Class1_FinalGradeIDIn, String Class2IDIn,
                          String Class2_FinalGradeIn, String Class3IDIn,
                          String Class3_FinalGradeIn, String Class4IDIn,
                          String CLass4_FinalGradeIn, String UsernameIn,
                          String Password_PlainIn, String SHA1_HASH_PasswordIn) {
        StudentID = new SimpleStringProperty(StudentIDIn);
        FirstName = new SimpleStringProperty(FirstNameIn);
        LastName = new SimpleStringProperty(LastNameIn);
        SubjectID = new SimpleStringProperty(SubjectIDIn);
        GPA = new SimpleStringProperty(GPAIn);
        DegreeID = new SimpleStringProperty(DegreeIDIn);
        CreditsEarned = new SimpleStringProperty(CreditsEarnedIn);
        Class1ID = new SimpleStringProperty(Class1IDIn);
        Class1_FinalGradeID = new SimpleStringProperty(Class1_FinalGradeIDIn);
        Class2ID = new SimpleStringProperty(Class2IDIn);
        Class2_FinalGrade = new SimpleStringProperty(Class2_FinalGradeIn);
        Class3ID = new SimpleStringProperty(Class3IDIn);
        Class3_FinalGrade = new SimpleStringProperty(Class3_FinalGradeIn);
        Class4ID = new SimpleStringProperty(Class4IDIn);
        CLass4_FinalGrade = new SimpleStringProperty(CLass4_FinalGradeIn);
        Username = new SimpleStringProperty(UsernameIn);
        Password_Plain = new SimpleStringProperty(Password_PlainIn);
        SHA1_HASH_Password = new SimpleStringProperty(SHA1_HASH_PasswordIn);

    }
    public String getStudentID(){
        return StudentID.get();
    }
    public void setStudentID(String StudentIdIn){
        StudentID.set(StudentIdIn);
    }

    public String getFirstName(){
        return FirstName.get();
    }
    public void setFirstName(String FirstNameIn){
        FirstName.set(FirstNameIn);
    }

    public String getLastName(){
        return LastName.get();
    }
    public void setLastName(String LastNameIn){
        LastName.set(LastNameIn);
    }

    public String getSubjectId(){
        return SubjectID.get();
    }
    public void setSubjectID(String SubjectIdIn){
        SubjectID.set(SubjectIdIn);
    }

    public String getGPA(){
        return GPA.get();
    }
    public void setGPA(String GPAIn){
        GPA.set(GPAIn);
    }

    public String getDegreeID(){
        return DegreeID.get();
    }
    public void setDegreeID(String DegreeIDIn){
        DegreeID.set(DegreeIDIn);
    }

    public String getCreditsEarned(){
        return CreditsEarned.get();
    }
    public void setCreditsEarned(String CreditsEarnedIn){
        CreditsEarned.set(CreditsEarnedIn);
    }

    public String getClass1ID(){
        return Class1ID.get();
    }
    public void setClass1ID(String Class1IDIn){
        Class1ID.set(Class1IDIn);
    }

    public String getClass1_FinalGradeID(){
        return Class1_FinalGradeID.get();
    }
    public void setClass1_FinalGradeID(String Class1_FinalGradeIDIn){
        Class1_FinalGradeID.set(Class1_FinalGradeIDIn);
    }

    public String getClass2ID(){
        return Class2ID.get();
    }
    public void setClass2ID(String Class2IDIn){
        Class2ID.set(Class2IDIn);
    }

    public String getClass2_FinalGradeID(){
        return Class2_FinalGrade.get();
    }
    public void setClass2_FinalGradeID(String Class2_FinalGradeIn){
        Class2_FinalGrade.set(Class2_FinalGradeIn);
    }

    public String getClass3ID(){
        return Class3ID.get();
    }
    public void setClass3ID(String Class3IDIn){
        Class3ID.set(Class3IDIn);
    }

    public String getClass3_FinalGradeID(){
        return Class3_FinalGrade.get();
    }
    public void setClass3_FinalGradeID(String Class3_FinalGradeIn){
        Class3_FinalGrade.set(Class3_FinalGradeIn);
    }

    public String getClass4ID(){
        return Class4ID.get();
    }
    public void setClass4ID(String Class4IDIn){
        Class4ID.set(Class4IDIn);
    }

    public String getClass4_FinalGrade(){
        return CLass4_FinalGrade.get();
    }
    public void setCLass4_FinalGrade(String Class4_FinalGradeIn){
        CLass4_FinalGrade.set(Class4_FinalGradeIn);
    }

    public String getUsername(){
        return Username.get();
    }
    public void setUsername(String UsernameIn){
        Username.set(UsernameIn);
    }

    public String getPassword_Plain(){
        return Password_Plain.get();
    }
    public void setPassword_Plain(String Password_PlainIn){
        Password_Plain.set(Password_PlainIn);
    }

    public String getSHA1_HASH_Password(){
        return SHA1_HASH_Password.get();
    }
    public void setSHA1_HASH_Password(String SHA1_HASH_PasswordIn){
        SHA1_HASH_Password.set(SHA1_HASH_PasswordIn);
    }


}
