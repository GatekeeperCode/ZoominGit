package edu.gcc.comp350.zoomin;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import com.google.gson.stream.JsonWriter;

import static javafx.scene.Cursor.HAND;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;

import java.io.IOException;

public class Course {
    boolean isOnSchedule;
    private String days;
    private String time;
    private String professor;
    private int credits;
    private String courseCode;//Ex. The 350 of COMP350B
    private String department; //Ex. The COMP of COMP350B
    private String courseLetter; //Ex. The B of COMP350B
    private String description;
    private String corequisite;
    private String prerequisite;
    private String courseName;
    private String semester;
    private int year;
    //Constructor

    /**
     * NOTE:
     * THIS METHOD IS DEPRECATED. COURSE CONSTRUCTOR NEW VERSION BELOW.
     */
    public Course(String parseData) {
        //Add parameters here
        String[] data = parseData.split(",");
        department = data[2];
        courseCode = data[3];
        courseLetter = data[4];
        credits = Integer.parseInt(data[6]);
        time = data[14] + " - " + data[15];
        courseName = data[5].replace("`", ",");
        professor = data[17] + " " + data[16];
        if (data.length > 18 && !data[18].equals("")) {
            professor += "(" + data[18] + ")";
        }

        days = "";
        for (int i = 9; i <= 13; i++) {
            if (!data[i].equals("")) {
                days += data[i];
            }
        }
        isOnSchedule = false;
    }

    /**
     * Course constructor based on MongoDB JSON format document.
     * Will set up the course and prepare it for the schedule view.
     * @param c - The JSON document corresponding to the class.
     */
    public Course(Document c) {
        department = c.getString("coursePrefix");
        courseCode = "" + c.getInteger("courseNumber");
        courseLetter = c.getString("courseSection");
        credits = c.getInteger("creditHours");
        String startTime = c.getString("startTime");
        String endTime = c.getString("endTime");
        if (startTime != null && endTime != null) {
            time = c.getString("startTime") + " - " + c.getString("endTime");
        }
        else {
            time = "Online";
        }
        courseName = c.getString("courseTitle");
        professor = c.getString("firstName") + " " + c.getString("lastName");
        semester = c.getString("semester");
        year = c.getInteger("year");
        String preferred = c.getString("preferred_name");
        if (preferred != null) {
            professor += " (" + preferred + ")";
        }

        days = "";
        char[] weekchars = {'M', 'T', 'W', 'R', 'F'};
        boolean[] weekdays = {c.containsKey("onMonday"), c.containsKey("onTuesday"),
                              c.containsKey("onWednesday"), c.containsKey("onThursday"),
                              c.containsKey("onFriday")};
        for (int i=0; i<5; i++) {
            if (weekdays[i]) {
                days += weekchars[i];
            }
        }
    }


    public String expandInfo() {
        return "Course: " + this.courseName + "\n" +
                "Professor: " + this.professor + "\n" +
                "Credit hours: " + this.credits + "\n" +
                "Corequisites: " + this.corequisite + "\n" +
                "Prerequisites: " + this.prerequisite + "\n" +
                "Description: " + this.description;
    }

    public boolean isOnSchedule() {
        return isOnSchedule;
    }

    public void setOnSchedule(boolean onSchedule) {
        isOnSchedule = onSchedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCorequisite() {
        return corequisite;
    }

    public void setCorequisite(String corequisite) {
        this.corequisite = corequisite;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(String prequisite) {
        this.prerequisite = prequisite;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseLetter(){return this.courseLetter;}

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    /**
     * Mike Buriok
     * @return A string listing the course with the Full Course code (COMP350), Course Name, Professor, Days, Time, and Credits;
     */
    public String toString()
    {
        return department + courseCode + " " + courseName + " " + professor + " " + days + " " + time + " " +  credits;
    }
}
