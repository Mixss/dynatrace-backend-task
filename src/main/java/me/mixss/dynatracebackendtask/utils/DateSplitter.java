package me.mixss.dynatracebackendtask.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateSplitter {
    // this class splits LocalDate to its elements in string format of
    // yyyy for years
    // mm for months
    // dd for days

    public String getYear(LocalDate date) {
        return String.valueOf(date.getYear());
    }

    public String getMonth(LocalDate date){
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        return monthFormatter.format(date);
    }

    public String getDay(LocalDate date){
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
        return dayFormatter.format(date);
    }
}
