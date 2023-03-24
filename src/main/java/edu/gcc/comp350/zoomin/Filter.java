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

    //public Boolean FilterStatus()
    //{
    //    return null;
    //}

    /**
     * Gets the filter for the time slot.
     * @return The time slot to filter the class list against
     */
    public String getTimeSlot()
    {
        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

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
}
