package sample;

import javafx.beans.property.SimpleStringProperty;

//for javaFX tableView Data Model
public class Transcript {

    private final SimpleStringProperty subject, crn, title, grade, credit;


    public Transcript(String subject, String crn, String title, String grade, String credit) {

        this.subject = new SimpleStringProperty(subject);
        this.crn = new SimpleStringProperty(crn);
        this.title = new SimpleStringProperty(title);
        this.grade = new SimpleStringProperty(grade);
        this.credit = new SimpleStringProperty(credit);
    }
public String getcrn(){
        return crn.get();
}
public String getsubject(){
        return subject.get();
}
public String gettitle(){
        return title.get();
    }
public String getgrade(){
        return grade.get();
}
public String getcredit(){
        return credit.get();
}
}