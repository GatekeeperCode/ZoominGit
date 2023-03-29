package edu.gcc.comp350.zoomin;

import com.google.gson.Gson;

import javax.swing.text.html.HTMLDocument;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Schedule {

    String ScheduleName;
    String Semester;
    int TotalCredits;
    ArrayList<Course> CourseList = new ArrayList<>();
    HashMap<String, Course> Schedule = new HashMap<String, Course>();

    //Constructor
    Schedule(String SchedName, String Semest)
    {
        this.ScheduleName = SchedName;
        this.Semester = Semest;
    }

    public String displaySchedule()
    {
        Iterator iter = Schedule.entrySet().iterator();
        String toOutput = "";
        System.out.println("Schedule name: " + this.ScheduleName + " |  Semester: " + this.Semester);
        while(iter.hasNext())
        {
            HashMap.Entry pair = (HashMap.Entry)iter.next();
            if(((Course) pair.getValue()).getCorequisite() != null){
                toOutput += "| " + ((Course) pair.getValue()).getCourseCode() + " | " + ((Course) pair.getValue()).getCourseName() + " | "
                        + ((Course) pair.getValue()).getTime() + "|\n" + ((Course) pair.getValue()).getProfessor() + " | "
                        + ((Course) pair.getValue()).getCredits() + " | " + ((Course) pair.getValue()).getCorequisite() + " | " +
                        ((Course) pair.getValue()).getPrerequisite() + " |\n" + ((Course) pair.getValue()).getDescription() + "\n";
            }else{
                toOutput += "| " + ((Course) pair.getValue()).getCourseCode() + " | " + ((Course) pair.getValue()).getCourseName() + " | "
                        + ((Course) pair.getValue()).getTime() + " |\n" +
                        ((Course) pair.getValue()).getProfessor() + " | Credits: " + ((Course) pair.getValue()).getCredits() + "\n";
            }
        }
        System.out.println(toOutput);
        return toOutput;
    }

    public void removeClass(Course course) throws Exception
    {
        for(Course temp : CourseList) {
            if (temp.courseCode == course.courseCode && temp.professor == course.professor && temp.time == course.time) {
                CourseList.remove(temp);
                temp.setOnSchedule(false);
            }
        }
        throw new Exception("Could not find course in schedule.");
    }

    public void addClass(Course course)
    {
        getCourseList().add(course);
    }

    public void addClassToSchedule(Course course)
    {
        Schedule.put(course.courseCode, course);
    }

    public void removeClassFromSchedule(Course course)
    {
        Schedule.remove(course.courseCode, course);
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
