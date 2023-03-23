package edu.gcc.comp350.zoomin;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<Schedule> schedList = new ArrayList<Schedule>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static void main(String[] args)
    {
        try {
            readInFile("C:\\Users\\BALLMD20\\IdeaProjects\\ZoominProject\\2020-2021.csv");
            System.out.println(courseList.get(6).credits);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public static void readInFile(String filename) throws Exception {
        Scanner scn = new Scanner(new File(filename));
        scn.nextLine(); //Throw the first line away, do not need column data.
        while (scn.hasNextLine()) {
            String iterLine = scn.nextLine();
            Scanner lnScan = new Scanner(iterLine);
            lnScan.useDelimiter(",");
            //When we get the CSV, proceed from here. This is how we read the CSV.
            courseList.add(new Course(iterLine));
        }
    }

    public void translateToMongo(Course c) {
        //TODO: Need to set-up MongoDB and make a JSON translator for Course
    }

    public Schedule createSchedule()
    {
        return null;
    }

    public void saveSchedule(Schedule s)
    {

    }

    public void deleteSchedule(Schedule s)
    {

    }
}
