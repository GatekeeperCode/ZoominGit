package edu.gcc.comp350.zoomin;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.apache.tika.parser.pdf.PDFParser;
import java.io.File;
import java.io.FileInputStream;
// Importing Apache POI classes
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

public class Minor {
    private ArrayList<Course> minorReqs;
    private String minorName;

    public Minor(ArrayList<Course> reqs){
        this.minorReqs = reqs;
    }

    public String getMinorName() {
        return minorName;
    }

    public void setMinorName(String minorName) {
        this.minorName = minorName;
    }

    public void addMinorReq(Course toAdd){
        minorReqs.add(toAdd);
    }

    public ArrayList<Course> getMinorReqs() {
        return this.minorReqs;
    }

    public void setMinorReqs(ArrayList<Course> minorReqs) throws Exception {
        this.minorReqs = minorReqs;

        BodyContentHandler contenthandler
                = new BodyContentHandler();

        File f = new File("src/main/resources/CSV/Minors2020-21.pdf");

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
        System.out.println("Extracting contents :"
                + contenthandler.toString());
    }
}
