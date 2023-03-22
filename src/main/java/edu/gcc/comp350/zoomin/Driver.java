package edu.gcc.comp350.zoomin;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static ArrayList<Schedule> schedList = new ArrayList<Schedule>();
    public static void main(String[] args)
    {


    }

    public void readInFile(String filename) throws Exception {
        Scanner scn = new Scanner(new File(""/*Insert File Name Here*/));
        while (scn.hasNextLine()) {
            String iterLine = scn.nextLine();
            Scanner lnScan = new Scanner(iterLine);
            lnScan.useDelimiter(",");
            //When we get the CSV, proceed from here. This is how we read the CSV.
        }
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
