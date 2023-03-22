package edu.gcc.comp350.zoomin;

public class Course {
    boolean isOnSchedule;
    String time;
    String professor;
    int credits;
    String courseCode;
    String description;
    String corequisite;
    String prequisite;
    String courseName;

    //Constructor
    Course()
    {

    }

    public Course(String parseData) {
        //Add parameters here
    }

    public void expandInfo()
    {
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

    public String getCorequisite() {
        return corequisite;
    }

    public void setCorequisite(String corequisite) {
        this.corequisite = corequisite;
    }

    public String getPrequisite() {
        return prequisite;
    }

    public void setPrequisite(String prequisite) {
        this.prequisite = prequisite;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
