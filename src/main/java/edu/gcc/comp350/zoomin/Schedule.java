package edu.gcc.comp350.zoomin;

import java.util.ArrayList;

public class Schedule {

    String ScheduleName;
    String Semester;
    int TotalCredits;
    ArrayList<Course> CourseList = new ArrayList<>();

    //Constructor
    Schedule(String SchedName, String Semest)
    {
        this.ScheduleName = SchedName;
        this.Semester = Semest;
    }

    public void setSchedule(){ } //what would this do?

    public Schedule getSchedule()
    {
        return null;
    }

    public void displaySchedule()
    {
        System.out.println("Schedule name: " + this.ScheduleName + " |  Semester: " + this.Semester);
        for (Course course: CourseList)
        {
            if(course.getCorequisite() != null){
                System.out.println("| " + course.getCourseCode() + " | " + course.getClass() + " | " + course.getTime() + "|\n" +
                        course.getProfessor() + " | " + course.getCredits() + " | " + course.getCorequisite() + " | " +
                        course.getPrequisite() + " |\n" + course.getDescription());
            }else{
                System.out.println("| " + course.getCourseCode() + " | " + " | " + course.getTime() + " |\n" +
                        course.getProfessor() + " | Credits" + course.getCredits());
            }
        }
    }

    public void removeClass()
    {

    }

    public void addClass(Course course)
    {
        getCourseList().add(course);
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
