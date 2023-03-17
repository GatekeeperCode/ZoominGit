package edu.gcc.comp350.zoomin;

import java.util.ArrayList;

public class Schedule {

    String ScheduleName;
    String Semester;
    int TotalCredits;
    ArrayList<Course> CourseList;

    //Constructor
    Schedule(String SchedName, String Semest)
    {

    }

    public void setSchedule()
    {

    }

    public Schedule getSchedule()
    {
        return null;
    }

    public void displaySchedule()
    {

    }

    public void removeClass()
    {

    }

    public void addClass(Course course)
    {

    }

    public void addClassToSchedule()
    {

    }

    public void removeClassFromSchedule()
    {

    }

    public void setCourseList(ArrayList<Course> courseList) {
        CourseList = courseList;
    }

    public void setScheduleName(String scheduleName) {
        ScheduleName = scheduleName;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public void setTotalCredits(int totalCredits) {
        TotalCredits = totalCredits;
    }

    public String getScheduleName() {
        return ScheduleName;
    }

    public ArrayList<Course> getCourseList() {
        return CourseList;
    }

    public int getTotalCredits() {
        return TotalCredits;
    }

    public String getSemester() {
        return Semester;
    }
}
