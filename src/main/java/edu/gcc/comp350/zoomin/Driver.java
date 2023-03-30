package edu.gcc.comp350.zoomin;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<Schedule> schedList = new ArrayList<Schedule>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static void main(String[] args)
    {
        boolean doStuff = true;
        Search searches = new Search("./src/main/java/edu/gcc/comp350/zoomin/CSV/2020-2021.csv");
        try {
            courseList = readInFile("./src/main/java/edu/gcc/comp350/zoomin/CSV/2020-2021.csv");
            //System.out.println(courseList.get(6).credits);
            //Schedule s = new Schedule("testSchedule", "Fall");
            //deleteSchedule();
        } catch(Exception e) {
            System.out.println(e);
            doStuff = false;
        }

        Schedule s = new Schedule("tempsch", "tempsem");
        Scanner scn = new Scanner(System.in);
        while (doStuff) {
            System.out.println("Enter a command:");
            String cmd = scn.nextLine().toUpperCase();
            Scanner lscan = new Scanner(cmd);

            String command = lscan.next();
            String tempName;
            String tempCode;
            String tempTime;
            String tempLetter;
            Course cAdd = null;
            switch (command) {
                case ("HELP"):
                    helpUser();
                    break;

                case ("ADDCOURSE"):
                    if (lscan.hasNext()) {
                        tempName = lscan.next();
                        tempCode = lscan.next();
                        tempLetter = lscan.next();
                        cAdd = findCourse(tempName, tempCode, tempLetter);
                    }

                    if (cAdd != null) {
                        s.addClassToSchedule(cAdd);
                    }
                    break;

                case ("REMOVE"):
                    if (lscan.hasNext()) {
                        tempName = lscan.next();
                        tempCode = lscan.next();
                        tempLetter = lscan.next();
                        cAdd = findCourse(tempName, tempCode, tempLetter);
                    }

                    if (cAdd != null) {
                        s.removeClassFromSchedule(cAdd);
                    }
                    break;

                case ("SEARCHNAME"):
                    tempName = "";
                    while (lscan.hasNext()) {
                        tempName += lscan.next();
                        if (lscan.hasNext()) {
                            tempName += " ";
                        }
                    }
                    searches.search(tempName, "", "");
                    searches.displayCourseSearch();
                    break;

                case ("SEARCHCODE"):
                    tempCode = "";
                    while (lscan.hasNext()) {
                        tempCode += lscan.next();
                        if (lscan.hasNext()) {
                            tempCode += " ";
                        }
                    }
                    searches.search("", tempCode, "");
                    searches.displayCourseSearch();
                    break;

                case ("SEARCHTIME"):
                    tempTime = "";
                    while (lscan.hasNext()) {
                        tempTime += lscan.next();
                        if (lscan.hasNext()) {
                            tempTime += " ";
                        }
                    }
                    searches.search("", "", tempTime);
                    searches.displayCourseSearch();
                    break;

                case ("SAVE"):
                    saveSchedule(s);
                    break;
                case ("LOAD"):
                    if (lscan.hasNext()){
                       String filename = lscan.next();
                       s = openSchedule(filename);
                    }else{
                        System.out.println("please type a filename after 'LOAD' ");
                    }
                    break;
                case ("DELETE"):
                    deleteSchedule();
                    break;

                case ("DISPLAY"):
                    s.displaySchedule();
                    break;

                case ("ADDFILTERS"):
                    searches.setFilters();
                    break;

                case ("NEWSCHEDULE"):

                    break;

                case ("QUIT"):
                    doStuff = false;
                    break;
            }
        }
    }

    public static ArrayList<Course> readInFile(String filename) throws Exception {
        Scanner scn = new Scanner(new File(filename));
        scn.nextLine(); //Throw the first line away, do not need column data.
        while (scn.hasNextLine()) {
            String iterLine = scn.nextLine();
            Scanner lnScan = new Scanner(iterLine);
            lnScan.useDelimiter(",");
            //When we get the CSV, proceed from here. This is how we read the CSV.
            courseList.add(new Course(iterLine));
        }
        return courseList;
    }

    public void translateToMongo(Course c) {
        //TODO: Need to set-up MongoDB and make a JSON translator for Course
    }

    public Schedule createSchedule(String schedName, String semester)
    {
        Schedule nSched = new Schedule(schedName, semester);
        return nSched;
    }

    public static void saveSchedule(Schedule s)
    {
        Gson gson = new Gson();
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter a schedule Name: ");
        String name = scan.nextLine();
        String json = gson.toJson(s);
        try {
            FileWriter file = new FileWriter("./src/main/java/edu/gcc/comp350/zoomin/Schedules/" + name + ".json");
            file.write(json);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void deleteSchedule() {
        //test
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the schedule Name you would like to delete: ");
        String toDelete = scan.nextLine();
        File file = new File("./src/main/java/edu/gcc/comp350/zoomin/Schedules/");
        for (File f : file.listFiles()) {
            if (f.getName().equals(toDelete)) {
                f.delete();
            }
        }
    }
    public String viewSchedule(Schedule s){
        return s.displaySchedule();
    }

    private static void helpUser() {
        System.out.println("Available Commands:\n" +
                "HELP: Shows all commands\n" +
                "ADDCOURSE <course code>: Adds course to schedule\n" +
                "REMOVE <course code>: Removes course (if applicable) from schedule\n" +
                "SAVE: Saves current schedule\n" +
                "LOAD <filename>: loads schedule with name <filename>\n" +
                "DELETE: Deletes specified schedule\n" +
                "SEARCHNAME <course name>: Searches for courses based on filters & course name\n" +
                "SEARCHCODE <course name>: Searches for courses based on filters & course code\n" +
                "SEARCHTIME <course name>: Searches for courses based on filters & course time\n" +
                "ADDFILTERS: Adds filters to the search tool\n" +
                "DISPLAY: Displays the current schedule\n" +
                "NEWSCHEDULE <name> <semester>: Creates a new, empty schedule\n" +
                "CLEARSEARCH: empties out search filters / values\n" +
                "QUIT: Stops the program");
    }

    private static Course findCourse(String dept, String code, String spec) {
        for(Course temp : courseList) {
            if (temp.department.equals(dept) && temp.courseCode.equals(code) && temp.courseLetter.equals(spec)) {
                return temp;
            }
        }
        return null;
    }

    private static Schedule openSchedule(String filename){
        Gson gson = new Gson();
        try {
            Schedule sched = gson.fromJson(new FileReader("./src/main/java/edu/gcc/comp350/zoomin/Schedules/"
                    + filename), Schedule.class);
            return sched;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
