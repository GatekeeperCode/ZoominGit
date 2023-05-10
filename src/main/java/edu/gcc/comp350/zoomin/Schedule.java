package edu.gcc.comp350.zoomin;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Schedule {

    private String ScheduleName;
    private String Semester;
    private int TotalCredits;
    private ArrayList<Course> CourseList = new ArrayList<>();
    private HashMap<String, Course> Schedule = new HashMap<String, Course>();

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
            if (temp.getCourseCode().equals(course.getCourseCode()) && temp.getProfessor().equals(course.getProfessor()) && temp.getTime().equals(course.getTime())) {
                CourseList.remove(temp);
                temp.setOnSchedule(false);
                return;
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
        boolean addTo = true;
        for(String key : this.Schedule.keySet()) {
            Course ctest = this.Schedule.get(key);
//            if (ctest.getTime().equals(course.getTime()) && !ctest.getTime().equals("Online") && !course.getTime().equals("Online")) {
//                addTo = false;
//                System.out.println("Cannot add that course as it conflicts with: " + ctest.getCourseCode() + ",\nwhich is also at " +
//                        ctest.getTime());
//                break;
//            }
            if (ctest.getCourseName().equals(course.getCourseName())) {
                addTo = false;
                System.out.println("Cannot add a duplicate course");
                break;
            }
        }

        if (addTo) {
            Schedule.put(course.getCourseCode(), course);
        }
    }

    public void removeClassFromSchedule(Course course)
    {
        Schedule.remove(course.getCourseCode(), course);
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

    public HashMap<String, Course> getSchedule(){return this.Schedule;}
}
