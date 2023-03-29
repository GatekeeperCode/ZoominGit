package edu.gcc.comp350.zoomin;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<Schedule> schedList = new ArrayList<Schedule>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static void main(String[] args)
    {
        try {
            readInFile("./src/main/java/edu/gcc/comp350/zoomin/CSV/2020-2021.csv");
            System.out.println(courseList.get(6).credits);
            Schedule s = new Schedule("testSchedule", "Fall");
            deleteSchedule();
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

    public static void deleteSchedule()
    {
        //test
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the schedule Name you would like to delete: ");
        System.out.print("(without the extension)");
        String toDelete = scan.nextLine();
        File file = new File("./src/main/java/edu/gcc/comp350/zoomin/Schedules/");
        for(File f: file.listFiles()) {
            System.out.println(f.getName());
            if(f.getName().equals(toDelete)){
                f.delete();
            }
        }
    }
}
