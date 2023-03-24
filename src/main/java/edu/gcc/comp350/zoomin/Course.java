package edu.gcc.comp350.zoomin;

public class Course {
    boolean isOnSchedule;

    String days;
    String time;
    String professor;
    int credits;
    String courseCode;
    String courseHandle;
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
        //TODO: CANNOT READ ENTRIES WITH "," WITHIN ITS NAME, ASK FOR A FIX TOMORROW
        String[] data = parseData.split(",");
        courseHandle = data[2];
        courseCode = data[3];
        credits = Integer.parseInt(data[6]);
        time = data[14] + " - " + data[15];
        courseName = data[5].replace("`", ",");
        professor = data[17] + " " + data[16];
        if (data.length > 18 && !data[18].equals("")) {
            professor += "(" + data[18] + ")";
        }
        days = "";
        for(int i = 9; i<=13; i++) {
            if (!data[i].equals("")) {
                days += data[i];
            }
        }
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
