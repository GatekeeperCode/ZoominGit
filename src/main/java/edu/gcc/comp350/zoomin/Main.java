package edu.gcc.comp350.zoomin;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.mongodb.*;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class Main {
    public static ArrayList<Schedule> schedList = new ArrayList<Schedule>();
    public static ArrayList<Course> courseList = new ArrayList<Course>();
    public static void main(String[] args)
    {
        //Use the access pass here:
        String atlasPass = "";

        // Construct a ServerApi instance using the ServerApi.builder() method
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(atlasPass))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        MongoClient mongoClient = null;
        MongoDatabase database = null;
        MongoCollection collection = null;
        try {
            mongoClient = MongoClients.create(settings);
            database = mongoClient.getDatabase("ZMMN");
            collection = database.getCollection("Courses");
            try {
                // Send a ping to confirm a successful connection
                Bson command = new BsonDocument("ping", new BsonInt64(1));
                Document commandResult = database.runCommand(command);
                System.out.println("MongoDB connection has been successfully established.\n" + commandResult);
            } catch (MongoException e) {
                System.err.println(e + " - First Catch.");
            }
        }
        catch (Exception e) {
            System.err.println(e + " - Second Catch.");
        }

        //Old method of importing courses
        boolean doStuff = true;
        Search searches = new Search("src/main/resources/CSV/2020-2021.csv");
        try {
            courseList = readInFile("src/main/resources/CSV/2020-2021.csv");
            //System.out.println(courseList.get(6).credits);
            //Schedule s = new Schedule("testSchedule", "Fall");
            //deleteSchedule();
        } catch(Exception e) {
            System.out.println(e);
            doStuff = false;
        }

        //Setting all courses (new method through database)
        FindIterable<Document> allCourses = collection.find();
        allCourses.forEach(doc -> courseList.add(new Course(doc)));

        Schedule s = new Schedule("tempsch", "tempsem");
        Scanner scn = new Scanner(System.in);
        while (doStuff) {
            System.out.println("Enter a command:");
            String cmd = scn.nextLine().toUpperCase();
            Scanner lscan = new Scanner(cmd);
            String command = "";

            //Command separation
            if (lscan.hasNext())
                command = lscan.next();

            //Usage variable formation
            String tempName;
            String tempCode;
            String tempTime;
            String[] parseList;
            Course cAdd = null;
            ArrayList<Course> minorReqs = new ArrayList<>();
            Minor minor;
            try {
                minor = new Minor(minorReqs);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            ArrayList<Minor> minors = new ArrayList<>();

            //Checks for proper commands
            switch (command) {
                case ("HELP"):
                    helpUser();
                    break;


                case ("ADDMINORREQ"):
                    if (lscan.hasNext() && (s != null)) {
                        parseList = lscan.nextLine().split(" ");
                        if (parseList.length == 4)
                            cAdd = findCourse(parseList[1], parseList[2], parseList[3]);
                        else
                            System.out.println("Please use the command ADDMINORREQ <name> <code> <letter>");
                    }

                    if (cAdd != null) {
                        try {
                            minor = new Minor(minorReqs);
                            minor.getMinorReqs().add(cAdd);
                        }
                        catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    }

                    break;

                case ("TAKENCLASS"):
                    if (lscan.hasNext() && (s != null)) {
                        parseList = lscan.nextLine().split(" ");
                        if (parseList.length == 4)
                            cAdd = findCourse(parseList[1], parseList[2], parseList[3]);
                        else
                            System.out.println("Please use the command TAKENCLASS <name> <code> <letter>");
                    }

                    if (cAdd != null) {
                        TakenCourses.taken.add(cAdd);
                    }


                    break;

                case ("ADDCOURSE"):
                    if (lscan.hasNext() && (s != null)) {
                        parseList = lscan.nextLine().split(" ");
                        if (parseList.length == 4)
                            cAdd = findCourse(parseList[1], parseList[2], parseList[3]);
                        else
                            System.out.println("Please use the command ADDCOURSE <name> <code> <letter>");
                    }

                    if (cAdd != null) {
                        s.addClassToSchedule(cAdd);
                    }
                    break;

                case ("REMOVE"):
                    if (lscan.hasNext() && (s != null)) {
                        parseList = lscan.nextLine().split(" ");
                        if (parseList.length == 4)
                            cAdd = findCourse(parseList[1], parseList[2], parseList[3]);
                        else
                            System.out.println("Please use the command REMOVE <name> <code> <letter>");
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
                    if (lscan.hasNext()) {
                        String filename = lscan.nextLine();
                        filename = filename.replaceFirst("\\s+", "");
                        s = openSchedule(filename);
                    } else {
                        System.out.println("Please type LOAD <filename>");
                    }
                    break;

                case ("DELETE"):
                    deleteSchedule();
                    break;

                case ("DISPLAY"):
                    if (s != null)
                        s.displaySchedule();
                    else
                        System.out.println("File is empty, create a new schedule.");
                    break;

                case ("ADDFILTERS"):
                    searches.setFilters();
                    break;

                case ("NEWSCHEDULE"):
                    if (lscan.hasNext()) {
                        parseList = lscan.nextLine().split("\\s+");
                        if (parseList.length != 3) {
                            System.out.println("Please enter NEWSCHEDULE <name> <semester>");
                            break;
                        }
                        s = new Schedule(parseList[1], parseList[2]);
                    }
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
            FileWriter file = new FileWriter("src/main/resources/Schedules/" + name + ".json");
            file.write(json);
            file.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //deletes specified schedule
    public static void deleteSchedule() {
        //test
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the schedule Name you would like to delete: ");
        String toDelete = scan.nextLine();
        File file = new File("src/main/resources/Schedules/");
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
                "ADDMINORREQ <course code>: Adds minor requirement to a list\n" +
                "TAKENCLASS <course code>: Removes the (previously taken) class from all search results\n" +
                "syntax should be <course code> <class section> ex: COMM 101 A" +
                "REMOVE <course code>: Removes course (if applicable) from schedule\n" +
                "syntax should be <course code> <class section> ex: COMM 101 A" +
                "SAVE: Saves current schedule\n" +
                "LOAD <filename>: loads schedule with name <filename>\n" +
                "DELETE: Deletes specified schedule\n" +
                "SEARCHNAME <course name>: Searches for courses based on filters & course name\n" +
                "SEARCHCODE <course name>: Searches for courses based on filters & course code\n" +
                "SEARCHTIME <course name>: Searches for courses based on filters & course time\n" +
                "ADDFILTERS: Adds filters to the search tool\n" +
                "DISPLAY: Displays the current schedule\n" +
                "CLEARSEARCH: empties out search filters / values\n" +
                "QUIT: Stops the program\n");
    }

    private static Course findCourse(String dept, String code, String spec) {
        for(Course temp : courseList) {
            if (temp.getDepartment().equals(dept) && temp.getCourseCode().equals(code) && temp.getCourseLetter().equals(spec)) {
                return temp;
            }
        }
        return null;
    }

    private static Schedule openSchedule(String filename) {
        Gson gson = new Gson();
        try {
            Schedule sched = gson.fromJson(new FileReader("src/main/resources/Schedules/"
                    + filename), Schedule.class);
            return sched;
        } catch (FileNotFoundException e) {
            System.out.println("There was a problem loading the file: " + e.getMessage());
            return null;
        }
    }
}
