package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/*
 * ClassDetails class creates an object for the purpose of populating the search
 * table with values returned from search() in ClassSelectionModel
 * Getters and setters below are necessary for library calls from
 * javafx.scene.control.cell.PropertyValueFactory
 * when populating the values of the table in MainController.search().
 */
public class ClassDetails {
	private StringProperty 	id, name, section, employeeId,
							days, time, room, description,
							creditHours, prerequisites;
	
	/*
	 * Precondition: Constructor takes arguments from ClassSelection.search() to populate class columns.
	 * It thus relies heavily on ClassSelection.setQueryControl() for the order of the parameters.
	 * If the query format is changed, order must be preserved.
	 */
	public ClassDetails(String idIn, String nameIn, 
						String sectionIn, String employeeIdIn, 
						String daysIn, String timeIn,
						String roomIn, String descriptionIn,
						String creditHoursIn, String prerequisitesIn) {
		id = new SimpleStringProperty(idIn);
		name = new SimpleStringProperty(nameIn);
		section = new SimpleStringProperty(sectionIn);
		employeeId = new SimpleStringProperty(employeeIdIn);
		days = new SimpleStringProperty(daysIn);
		time = new SimpleStringProperty(timeIn);
		room = new SimpleStringProperty(roomIn);
		description = new SimpleStringProperty(descriptionIn);
		creditHours = new SimpleStringProperty(creditHoursIn);
		prerequisites = new SimpleStringProperty(prerequisitesIn);
	}
	
	public String getId() {
		return id.get();
	}
	public void setId(String idIn) {
		id.set(idIn);
	}
	
	public String getName() {
		return name.get();
	}
	public void setName(String nameIn) {
		name.set(nameIn);
	}
	
	public String getSection() {
		return section.get();
	}
	public void setSection(String sectionIn) {
		section.set(sectionIn);
	}
	
	public String getEmployeeId() {
		return employeeId.get();
	}
	public void setEmployeeId(String employeeIdIn) {
		employeeId.set(employeeIdIn);
	}
	
	public String getDays() {
		return days.get();
	}
	public void setDays(String daysIn) {
		days.set(daysIn);
	}
	
	public String getTime() {
		return time.get();
	}
	public void setTime(String timeIn) {
		time.set(timeIn);
	}
	
	public String getRoom() {
		return room.get();
	}
	public void setRoom(String roomIn) {
		room.set(roomIn);
	}
	
	public String getDescription() {
		return description.get();
	}
	public void setDescription(String descriptionIn) {
		description.set(descriptionIn);
	}
	
	public String getCreditHours() {
		return creditHours.get();
	}
	public void setCreditHours(String creditHoursIn) {
		creditHours.set(creditHoursIn);
	}
	
	public String getPrerequisites() {
		return prerequisites.get();
	}
	public void setPrerequisites(String prerequisitesIn) {
		prerequisites.set(prerequisitesIn);
	}
	
}