package edu.gcc.comp350.zoomin;

public class Course {
    boolean isOnSchedule;
    String time;
    String professor;
    int credits;
    String courseCode; //Ex. The 350 of COMP350
    String department; //Ex. The COMP of COMP350
    String description;
    String corequisite;
    String prequisite;

    //Constructor
    Course()
    {

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

    public String getPrequisite() {
        return prequisite;
    }

    public void setPrequisite(String prequisite) {
        this.prequisite = prequisite;
    }
}
