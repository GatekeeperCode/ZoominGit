package edu.gcc.comp350.zoomin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {
    @Test
    void testExpandInfo() {
        Course one = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");
        assertEquals(one.expandInfo(), "Course: PRINCIPLES OF ACCOUNTING I\n" +
                                        "Professor: Richard Snyder\n" +
                                        "Credit hours: 3\n" +
                                        "Corequisites: null\n" +
                                        "Prerequisites: null\n" +
                                        "Description: null");
    }

    @Test
    void courseCreate() {

    }
}
