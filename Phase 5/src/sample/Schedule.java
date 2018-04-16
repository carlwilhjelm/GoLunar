package sample;

import javafx.beans.property.SimpleStringProperty;

//for javaFX tableView Data Model
public class Schedule {

    private final SimpleStringProperty CRN, subjectName, courseTitle, credits;
    private final SimpleStringProperty days, time, location, instructor,
            description;


    public Schedule(String crn, String subjectName,
             String courseTitle, String credits,
             String days, String time,
             String location, String instructor, String description) {

        this.CRN = new SimpleStringProperty(crn);
        this.subjectName = new SimpleStringProperty(subjectName);
        this.courseTitle = new SimpleStringProperty(courseTitle);
        this.credits = new SimpleStringProperty(credits);
        this.days = new SimpleStringProperty(days);
        this.time = new SimpleStringProperty(time);
        this.location = new SimpleStringProperty(location);
        this.instructor = new SimpleStringProperty(instructor);
        this.description = new SimpleStringProperty(description);



    }

    public Schedule()
    {
        this.CRN = new SimpleStringProperty("");
        this.subjectName = new SimpleStringProperty("");
        this.courseTitle = new SimpleStringProperty("");
        this.credits = new SimpleStringProperty("");
        this.days = new SimpleStringProperty("");
        this.time = new SimpleStringProperty("");
        this.location = new SimpleStringProperty("");
        this.instructor = new SimpleStringProperty("");
        this.description = new SimpleStringProperty("");


    }



    public String getCRN() {
        return CRN.get();
    }

    public void setCRN(String CRN) {
        this.CRN.set(CRN);
    }

    public String getSubjectName() {
        return subjectName.get();
    }

    public void setSubjectName(String subjectName) {
        this.subjectName.set(subjectName);
    }

    public String getCourseTitle() {
        return courseTitle.get();
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle.set(courseTitle);
    }

    public String getCredits() {
        return credits.get();
    }

    public void setCredits(String credits) {
        this.credits.set(credits);
    }

    public String getDays() {
        return days.get();
    }

    public void setDays(String days) {
        this.days.set(days);
    }

    public String getTime() {
        return time.get();
    }


    public void setTime(String time) {
        this.time.set(time);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getInstructor() {
        return instructor.get();
    }

    public void setInstructor(String instructor) {
        this.instructor.set(instructor);
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}
