package edu.gcc.comp350.zoomin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    @Test
    void testDisplaySchedule() {
        String output = "| null | null | null" + " |\n" + "null | Credits: 2\n| COMP 350 | null | null" + " |\n" + "null | Credits: 3\n";
        Schedule s = new Schedule("testSchedule", "Fall");
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