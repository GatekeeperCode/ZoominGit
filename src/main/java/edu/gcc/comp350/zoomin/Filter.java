package edu.gcc.comp350.zoomin;

public class Filter {
    private String timeSlot;
    private String professor;
    private int creditHours;
    private String department;
    private String CourseCode;

    Filter()
    {
        timeSlot = "";
        professor = "";
        creditHours = 0;
        department = "";
        CourseCode = "";
    }

    public Boolean FilterStatus()
    {
        return null;
    }

    public String getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getProfessor() {
        return professor;
    }
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public int getCreditHours() {
        return creditHours;
    }
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCourseCode() {
        return CourseCode;
    }
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }
}
