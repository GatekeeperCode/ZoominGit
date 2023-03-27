package edu.gcc.comp350.zoomin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    @Test
    void testDisplaySchedule() {
        String output = "| null | null | null" + " |\n" + "null | Credits: 2\n| COMP 350 | null | null" + " |\n" + "null | Credits: 3\n";
        Schedule s = new Schedule("testSchedule", "Fall");
//        Course c = new Course();
//        c.setCredits(2);
//        Course c2 = new Course();
//        c2.setCredits(3);
//        c2.setCourseCode("COMP 350");
//        s.addClass(c);
//        s.addClass(c2);
        s.addClassToSchedule(s.CourseList.get(0));
        s.addClassToSchedule(s.CourseList.get(1));
        assertEquals(s.displaySchedule(), output);
    }

    @Test
    void removeClass() {
    }

    @Test
    void addClass() {
    }

    @Test
    void addClassToSchedule() {
    }

    @Test
    void testRemoveClassFromSchedule() {
    }

    @Test
    void setCourseList() {
    }

    @Test
    void setScheduleName() {
    }

    @Test
    void setSemester() {
    }

    @Test
    void setTotalCredits() {
    }

    @Test
    void getScheduleName() {
    }

    @Test
    void getCourseList() {
    }

    @Test
    void getTotalCredits() {
    }

    @Test
    void getSemester() {
    }
}