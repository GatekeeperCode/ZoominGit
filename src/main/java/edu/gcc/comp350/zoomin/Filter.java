package edu.gcc.comp350.zoomin;
import java.util.ArrayList;

public class Filter {
    private String startTime;
    private String endTime;
    private String daysOffered;
    private String professor;
    private int creditHours;
    private String department;
    private String CourseCode;
    private String courseName;
    private String primaryTimeCheck;
    private ArrayList<Course> taken;

    Filter()
    {
        startTime = "";
        endTime = "";
        professor = "";
        creditHours = 0;
        department = "";
        CourseCode = "";
        courseName = "";
        daysOffered = "";
        primaryTimeCheck = "";
    }

    //public Boolean FilterStatus()
    //{
    //    return null;
    //}

    /**
     * Gets the filter for the time slot.
     * @return The time slot to filter the class list against
     */
    public String getStartTime()
    {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime(){return endTime;}
    public void setEndTime(String endTime) {this.endTime =  endTime;}

    /**
     * Gets the filter for the professor.
     * @return The professor to filter the class list against
     */
    public String getProfessor()
    {
        return professor;
    }

    /**
     * Sets professor filter
     * @param professor The desired professor.
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /**
     * Gets the filter for the credit hours.
     * @return The credit hours to filter the class list against
     */
    public int getCreditHours()
    {
        return creditHours;
    }

    /**
     * Sets credit hours filter
     * @param creditHours The desired credit hours.
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /**
     *  Gets the filter for the class's department.
     * @return The department to filter the class list against
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Sets department filter
     * @param department The desired class department.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the filter for the class's course code.
     * @return The course code to filter the class list against
     */
    public String getCourseCode()
    {
        return CourseCode;
    }

    /**
     * Sets courseCode filter
     * @param courseCode The desired Course Code.
     */
    public void setCourseCode(String courseCode) {
        CourseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDaysOffered(String daysOffered)
    {
        this.daysOffered = daysOffered;
    }

    public String getDaysOffered() {
        return daysOffered;
    }

    public void setPrimaryTimeCheck(String primaryTimeCheck)
    {
        this.primaryTimeCheck = primaryTimeCheck;
    }

    public String getPrimaryTimeCheck()
    {
        return primaryTimeCheck;
    }
}
