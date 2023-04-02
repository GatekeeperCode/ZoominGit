package edu.gcc.comp350.zoomin;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {

    @Test
    void removeClass() {
        Schedule s = new Schedule("schedule1", "Fall2020");
        Course one = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");
        Course two = new Course("2020,10,ACCT,301,B,INTERMEDIATE ACCOUNTING I,3,26,21,,,,R,,4:00:00 PM,5:50:00 PM,Stone,Jennifer,Nicole,");
        s.addClass(one);
        s.addClass(two);

        try {
            s.removeClass(one);
            assertEquals(s.CourseList.size(), 1);
        }
        catch (Exception e) {
            fail("Course was not removed due to an Exception.");
        }

    }

    @Test
    void addClass() {
        Schedule s = new Schedule("schedule1", "Fall2020");
        Course one = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");

        s.addClass(one);

        assertEquals(s.CourseList.size(), 1);
    }

    @Test
    void addClassToSchedule() {
        Schedule s = new Schedule("schedule1", "Fall2020");
        Course one = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");

        s.addClassToSchedule(one);

        assertEquals(s.Schedule.size(), 1);
    }

    @Test
    void testRemoveClassFromSchedule() {
        Schedule s = new Schedule("schedule1", "Fall2020");
        Course one = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");

        s.addClassToSchedule(one);

        s.removeClassFromSchedule(one);
        assertEquals(s.Schedule.size(), 0);
    }

    @Test
    void setCourseList() {
        Schedule s = new Schedule("sched1", "Fall2020");
        ArrayList<Course> cList = new ArrayList<Course>();
        Course cOne = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");
        Course cTwo = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");
        Course cThree = new Course("2020,10,ACCT,201,B,PRINCIPLES OF ACCOUNTING I,3,30,32,M,,W,,F,11:00:00 AM,11:50:00 AM,Snyder,Richard,,Online materials fee");

        cList.add(cOne);
        cList.add(cTwo);
        cList.add(cThree);

        s.setCourseList(cList);
        assertEquals(s.getCourseList().size(), 3);
    }

    @Test
    void setScheduleName() {
        Schedule s = new Schedule("falsename", "Fall2020");
        s.setScheduleName("truename");

        assertEquals(s.getScheduleName(), "truename");
    }

    @Test
    void setSemester() {
        Schedule s = new Schedule("sched1", "Fall2020");
        s.setSemester("Fall2021");

        assertEquals(s.getSemester(), "Fall2021");
    }

    @Test
    void setTotalCredits() {
        Schedule s = new Schedule("sched1", "Fall2020");
        s.setTotalCredits(10);

        assertEquals(s.getTotalCredits(), 10);
    }

    @Test
    void getScheduleName() {
        Schedule s = new Schedule("sched1", "Fall2020");
        assertEquals(s.getScheduleName(), "sched1");
    }
}