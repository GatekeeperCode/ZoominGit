package edu.gcc.comp350.zoomin;
import java.util.ArrayList;

public class Minor {
    private ArrayList<Course> minorReqs;


    public Minor(ArrayList<Course> reqs){
        this.minorReqs = reqs;
    }

    public void addMinorReq(Course toAdd){
        this.minorReqs.add(toAdd);
    }

    public ArrayList<Course> getMinorReqs() {
        return minorReqs;
    }

    public void setMinorReqs(ArrayList<Course> minorReqs) {
        this.minorReqs = minorReqs;
    }
}
