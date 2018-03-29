package sample;

import javafx.beans.property.SimpleStringProperty;

//for tableView Data Model in Registration status and times, do not edit
public class Dates {

    private final SimpleStringProperty Term, dates, dates2;

    public Dates() {

        this.Term = new SimpleStringProperty("");
        this.dates = new SimpleStringProperty("");
        this.dates2 = new SimpleStringProperty("");
    }

    public Dates(String term, String dates, String seconddate) {

        this.Term = new SimpleStringProperty(term);
        this.dates = new SimpleStringProperty(dates);
        this.dates2 = new SimpleStringProperty(seconddate);
    }

    public String getTerm() {
        return Term.get();
    }

    public void setTerm(String term) {
        this.Term.set(term);
    }

    public String getDates() {
        return dates.get();
    }

    public void setDates(String dates) {
        this.dates.set(dates);
    }

    public void setDates2(String dates2) {
        this.dates2.set(dates2);
    }

    public String getDates2() {
        return dates2.get();
    }

}
