package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController implements Initializable {
	
	@FXML
	public Label myLabel;
	@FXML
	public ComboBox<String> term, degree, subject, 
						 	campus, partOfTerm,
							instructor, type, startHr,
							startMin, startP, endHr,
							endMin, endP;
	
	public TextField courseNum, title, credFrom, credTo;
	
	ObservableList<String> termList = FXCollections.observableArrayList(
			"Spring Semester 2018", 
			"Fall Semester 2017 (View only)", 
			"Summer Semester 2017 (View only)", 
			"Spring Semester 2017 (View only)");
	ObservableList<String> degList = FXCollections.observableArrayList(
			"Associates (2-year undergraduate, Perimeter College students)", 
			"Bachelors (4-year undergraduate students)", 
			"Graduate (Graduate level students)");
	ObservableList<String> subList = FXCollections.observableArrayList(
			"ACCOUNTING",
			"ACTUARIAL SCIENCE",
			"AFRICAN-AMERICAN STUDIES",
			"AMERICAN SIGN LANGUAGE",
			"ANALYTICS",
			"ANTHROPOLOGY",
			"APPLIED BASS",
			"APPLIED BASSOON",
			"APPLIED CELLO",
			"APPLIED CLARINET",
			"APPLIED COMPOSITION",
			"APPLIED CONDUCTING",
			"APPLIED EUPHONIUM",
			"APPLIED FLUTE",
			"APPLIED GUITAR",
			"APPLIED HARP",
			"APPLIED HORN",
			"APPLIED JAZZ BASS",
			"APPLIED JAZZ GUITAR",
			"APPLIED LINGUISTICS",
			"APPLIED OBOE",
			"APPLIED ORGAN",
			"APPLIED PERCUSSION",
			"APPLIED PIANO",
			"APPLIED PIANO JAZZ PIANO",
			"APPLIED SAXOPHONE",
			"APPLIED TROMBONE",
			"APPLIED TRUMPET",
			"APPLIED TUBA",
			"APPLIED VIOLA",
			"APPLIED VIOLIN",
			"APPLIED VOICE",
			"ARABIC",
			"ART",
			"ART EDUCATION",
			"ART HISTORY",
			"ASTRONOMY",
			"BIOLOGY",
			"BIOMEDICAL SCIENCE",
			"BIRTH THROUGH FIVE",
			"BUSINESS ADMINISTRATION--BA",
			"BUSINESS ADMINISTRATION--BUSA",
			"BUSINESS COMMUNICATION",
			"CENTER PROCESS INNOVATION",
			"CERAMICS",
			"CHEMISTRY",
			"CHINESE",
			"COMMUNICATION",
			"COMMUNICATION SCI & DISORDERS",
			"COMPUTER INFORMATION SYSTEMS",
			"COMPUTER SCIENCE",
			"COOPERATIVE EDUCATION",
			"COUNSELING & PSYCH SERVICES",
			"CREATIVE MEDIA INDUSTRY STUDY",
			"CRIMINAL JUSTICE",
			"CURRICULUM & INSTRUCTION",
			"DENTAL HYGIENE",
			"DRAWING AND PAINTING",
			"DRAWING, PAINTING, PRINTMAKING",
			"EARLY CHILDHOOD EDUCATION",
			"ECONOMICS",
			"ED, BUSINESS, AND TECHNOLOGY",
			"EDUCATION",
			"EDUCATIONAL POLICY STUDIES",
			"EDUCATIONAL PSYCHOLOGY",
			"ENGINEERING",
			"ENGLISH",
			"ENGLISH AS A SECOND LANG (UG)",
			"ENGLISH SECOND LANGUAGE (GRAD)",
			"ENTREPRENEURSHIP",
			"ENVIRONMENTAL STUDIES",
			"EPS/EDUCATIONAL LEADERSHIP",
			"EPS/RESEARCH",
			"EPS/SOCIAL FOUNDATIONS",
			"EUROPEAN UNION STUDIES",
			"EXCEPTIONAL CHILDREN",
			"EXEC DOCTORATE IN BUSINESS",
			"EXECUTIVE MBA",
			"FILM AND MEDIA",
			"FINANCE",
			"FOREIGN LANGUAGE",
			"FRENCH",
			"GEOGRAPHY",
			"GEOLOGY",
			"GEOSCIENCES",
			"GERMAN",
			"GERONTOLOGY",
			"GLOBAL STUDIES",
			"GRAPHIC DESIGN",
			"GSU NEW STUDENT ORIENTATION",
			"HEALTH ADMINISTRATION",
			"HEBREW - MODERN",
			"HISTORY",
			"HOMELAND SECURITY & EMERG MGMT",
			"HONORS",
			"HOSPITALITY ADMINISTRATION",
			"HUMANITIES",
			"INTEGRATED SCIENCES",
			"INTENSIVE ENGLISH PROGRAM",
			"INTERIOR DESIGN",
			"INTERNATIONAL BUSINESS",
			"INTERNATIONAL EXCHANGE",
			"ITALIAN",
			"JAPANESE",
			"JEWISH STUDIES",
			"JOURNALISM",
			"KINESIOLOGY & HEALTH",
			"KOREAN",
			"LANGUAGE ARTS EDUCATION",
			"LATIN",
			"LAW",
			"LEARNING TECHNOLOGIES",
			"LEGAL STUDIES",
			"MANAGERIAL SCIENCES",
			"MARKETING",
			"MASTER OF BUSINESS ADMIN",
			"MATHEMATICAL RISK MANAGEMENT",
			"MATHEMATICS",
			"MATHEMATICS EDUCATION",
			"MIDDLE EAST STUDIES",
			"MILITARY SCIENCE LEADERSHIP",
			"MUSIC",
			"MUSIC APPRECIATION",
			"MUSIC TECHNOLOGY MANAGEMENT",
			"NATURAL SCIENCES",
			"NEUROSCIENCE",
			"NURSING",
			"NUTRITION",
			"OCCUPATIONAL THERAPY",
			"PERIMETER COLLEGE ORIENTATION",
			"PERSPECTIVES",
			"PHILOSOPHY",
			"PHOTOGRAPHY",
			"PHYSICAL EDUCATION",
			"PHYSICAL THERAPY",
			"PHYSICS",
			"POLITICAL SCIENCE",
			"PORTUGUESE",
			"PRINTMAKING",
			"PROFESSIONAL MASTER BUS ADMIN",
			"PSYCHOLOGY",
			"PUBLIC HEALTH",
			"PUBLIC MANAGEMENT POLICY",
			"READING EDUCATION",
			"REAL ESTATE",
			"RELIGIOUS STUDIES",
			"RESEARCH STRATEGIES",
			"RESPIRATORY THERAPY",
			"RISK MANAGEMENT AND INSURANCE",
			"RUSSIAN",
			"SCH OF NURSING & HEALTH PROF.",
			"SCIENCE EDUCATION",
			"SCULPTURE",
			"SIGN LANGUAGE INTERPRETING",
			"SOCIAL STUDIES EDUCATION",
			"SOCIAL WORK",
			"SOCIOLOGY",
			"SPANISH",
			"SPEECH COMMUNICATION",
			"STATISTICS",
			"SWAHILI",
			"TAXATION",
			"TEACHING ESL/COLLEGE OF ED",
			"TEXTILES",
			"THEATRE",
			"THREE-DIMENSIONAL STUDIES",
			"WOMEN'S GENDER & SEXUALITY STU");
	ObservableList<String> instList = FXCollections.observableArrayList("All",
			"Aamer, Nadiyah ",
			"Abalmazova, Yelena ",
			"Abbott, Emory Reginald",
			"Abbott, Laura Anne",
			"Abdelaziz, Sarah ",
			"Abdus-Saboor, Kinda ",
			"Abebe, Gebeyehu ",
			"Abrahao, Thalita Balsamo",
			"Abron, Clara Elizabeth",
			"Acharya, Ananta Raj",
			"Acosta, Katie Linette",
			"Adams, Ellis Adjei",
			"Adams, Francis Scott",
			"Adams, Marcus Paul",
			"Adams, M Hiuko Ngari",
			"Adams, Nicholas L",
			"Adams, Teresa B",
			"Adams, Windsor ",
			"Adamson, Andrew N",
			"Zou, Ming-Hui ",
			"Zuk, Tanya Dolores");
	ObservableList<String> hrList = FXCollections.observableArrayList(
			"01", 
			"02", 
			"03", 
			"04", 
			"05", 
			"06",
			"07",
			"08",
			"09",
			"10",
			"11",
			"12");
	ObservableList<String> minList = FXCollections.observableArrayList(
			"00", 
			"05", 
			"10", 
			"15", 
			"20", 
			"25", 
			"30",
			"35",
			"40",
			"45",
			"50",
			"55");
	ObservableList<String> perList = FXCollections.observableArrayList("am", "pm");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		term.setItems(termList);
		degree.setItems(degList);
		subject.setItems(subList);
		instructor.setItems(instList);
		startHr.setItems(hrList);
		startMin.setItems(minList);
		startP.setItems(perList);
		endHr.setItems(hrList);
		endMin.setItems(minList);
		endP.setItems(perList);
	}

	public void comboChanged(ActionEvent event) {
		//myLabel.setText(combobox.getValue());
		
	}
}
