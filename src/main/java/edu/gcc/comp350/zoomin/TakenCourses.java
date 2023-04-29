package edu.gcc.comp350.zoomin;

import java.util.ArrayList;

public class TakenCourses {
    public static ArrayList<Course> taken;

    public static ArrayList<Course> getTaken() {
        return taken;
    }

    public void addToTaken(Course a) {
        taken.add(a);
    }

    public static void setTaken(ArrayList<Course> taken) {
        TakenCourses.taken = taken;
    }

    public TakenCourses(ArrayList<Course> take){
        this.taken = take;
    }
}
