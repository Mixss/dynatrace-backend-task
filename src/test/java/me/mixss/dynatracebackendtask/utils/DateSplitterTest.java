package me.mixss.dynatracebackendtask.utils;

import me.mixss.dynatracebackendtask.exceptions.BadDateFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateSplitterTest {

    DateSplitter dateSplitter;

    @BeforeEach
    void setUp() {
        dateSplitter = new DateSplitter();
    }

    @Test
    void getYear_2001_CorrectStringDate() {
        assertEquals("2001", dateSplitter.getYear("2001-05-26"));
    }

    @Test
    void getMonth_05_CorrectStringDate() {
        assertEquals("05", dateSplitter.getMonth("2001-05-26"));
    }

    @Test
    void getDay_26_CorrectStringDate() {
        assertEquals("26", dateSplitter.getDay("2001-05-26"));
    }

    @Test
    void getDay_throwsBadDateFormatException_WrongFormatDate() {
        Assertions.assertThrows(BadDateFormatException.class, () -> {
            dateSplitter.getDay("2001-05-5");
        }, "Should throw BadDateFormatException when 2001-05-5");
    }

    @Test
    void getMonth_throwsBadDateFormatException_WrongFormatDate() {
        Assertions.assertThrows(BadDateFormatException.class, () -> {
            dateSplitter.getMonth("2001-05-5");
        }, "Should throw BadDateFormatException when 2001-05-5");
    }

    @Test
    void getYear_throwsBadDateFormatException_WrongFormatDate() {
        Assertions.assertThrows(BadDateFormatException.class, () -> {
            dateSplitter.getYear("2001-05-5");
        }, "Should throw BadDateFormatException when 2001-05-5");
    }
}