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

    /** Mike Buriok
     * Gets the filter for the time slot.
     * @return The time slot to filter the class list against
     * @throws Exception If there is not a timeslot filter determined.
     */
    public String getTimeSlot() throws Exception
    {
        if(timeSlot == "")
        {
            throw new Exception("No Filter Present");
        }

        return timeSlot;
    }
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    /** Mike Buriok
     * Gets the filter for the professor.
     * @return The professor to filter the class list against
     * @throws Exception If there is not a timeslot filter determined.
     */
    public String getProfessor() throws Exception
    {
        if(professor == "")
        {
            throw new Exception("No Filter Present");
        }

        return professor;
    }

    /**
     * Sets professor filter
     * @param professor The desired professor.
     */
    public void setProfessor(String professor) {
        this.professor = professor;
    }

    /** Mike Buriok
     * Gets the filter for the credit hours.
     * @return The credit hours to filter the class list against
     * @throws Exception If there is not a credit hours filter determined.
     */
    public int getCreditHours() throws Exception
    {
        if(creditHours==0)
        {
            throw new Exception("No Filter Present");
        }

        return creditHours;
    }

    /**
     * Sets credit hours filter
     * @param creditHours The desired credit hours.
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    /** Mike Buriok
     *  Gets the filter for the class's department.
     * @return The department to filter the class list against
     * @throws Exception If there is not a department filter determined.
     */
    public String getDepartment() throws Exception
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

    /** Mike Buriok
     * Gets the filter for the class's course code.
     * @return The course code to filter the class list against
     * @throws Exception If there is not a Course Code filter determined.
     */
    public String getCourseCode() throws Exception
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
