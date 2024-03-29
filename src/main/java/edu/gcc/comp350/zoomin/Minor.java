package edu.gcc.comp350.zoomin;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.tika.parser.pdf.PDFParser;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
// Importing Apache POI classes
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

public class Minor {
    //private ArrayList<Course> minorReqs;
    private String minorName;
    public static Map<String, String> minorSheet = new TreeMap<>();

    /**
     * Loads the HashMap using the .pdf with the keys being the Minor names (all caps)
     * and the values are a String description
     */
    public Minor() throws Exception {

        //this.minorReqs = reqs;

        // Create a content handler
        BodyContentHandler contenthandler
                = new BodyContentHandler();

        // Create a file in local directory
        URI uri = getClass().getResource("Minors2020-21.pdf").toURI();
        String path = Paths.get(uri).toString();
        File f = new File(path);

        // Create a file input stream
        // on specified path with the created file
        FileInputStream fstream = new FileInputStream(f);

        // Create an object of type Metadata to use
        Metadata data = new Metadata();

        // Create a context parser for the pdf document
        ParseContext context = new ParseContext();

        // PDF document can be parsed using the PDFparser
        // class
        PDFParser pdfparser = new PDFParser();

        // Method parse invoked on PDFParser class
        pdfparser.parse(fstream, contenthandler, data,
                context);

        // Printing the contents of the pdf document
        // using toString() method in java
        //System.out.println("Extracting contents :"
        //+ contenthandler.toString());
        String contentString = contenthandler.toString();
        Scanner scan = new Scanner(contentString);
        Scanner nScanner = new Scanner(contentString);
//        HashMap hashing = new HashMap();

        int check = 0;
        String marker = "";
        String val = "";
        scan.useDelimiter("\\.");
        String key = scan.next();
        String newStuff = scan.next();
        while(true) {
            newStuff = newStuff.strip();
            newStuff = newStuff.replaceAll("[\r\n]+", "\r\n");
            String holdStuff = newStuff.replace("and", "AND");
            if(marker.equals(key) && !marker.equals("") && (!holdStuff.equals(holdStuff.toUpperCase()) || newStuff.matches("\\d+"))){
                if(newStuff.matches("\\d+")){
                    val = val + "\n" + newStuff + ". ";
                }
                else {
                    val = val + newStuff + ". ";
                }
            }

            newStuff = newStuff.replace("\n", "");
            newStuff = newStuff.replace("and", "AND");



            if(newStuff.equals(newStuff.toUpperCase()) && !newStuff.matches("\\d+")) {
                if(key.equals(key.toUpperCase()) && !key.matches("\\d+")) {
                    minorSheet.put(key, val);
                }
                check = check + 1;
                key = newStuff;
                marker = key;
                //System.out.println(val);
                //System.out.println("STARTS HERE! " + key + "! ENDS HERE!");
                val = "";
            }
            if(newStuff.equals("")){
                break;
            }
            newStuff = scan.next();

        }

//        System.out.println(check);
//        System.out.println(minorSheet.get("INTERNATIONAL STUDIES"));
        fstream.close();


        
    }

    public String getMinorDesc(String minName) {
        return minorSheet.get(minName);
    }

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }

//    public void addMinorReq(Course toAdd){
//        minorReqs.add(toAdd);
//    }
//
//    public ArrayList<Course> getMinorReqs() {
//        return this.minorReqs;
//    }
//
//    public void setMinorReqs(ArrayList<Course> minorReqs){
//        this.minorReqs = minorReqs;
//    }
}
