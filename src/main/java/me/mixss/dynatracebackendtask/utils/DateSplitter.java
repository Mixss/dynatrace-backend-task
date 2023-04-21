package me.mixss.dynatracebackendtask.utils;

import me.mixss.dynatracebackendtask.exceptions.BadDateFormatException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class DateSplitter {
    // this class splits date in format yyyy-mm-dd

    public String getYear(String date) {
        try{
            return String.valueOf(LocalDate.parse(date).getYear());
        }
        catch (DateTimeParseException e){
            throw new BadDateFormatException();
        }
    }

    public String getMonth(String date){
        try{
            LocalDate localDate = LocalDate.parse(date);
            DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
            return monthFormatter.format(localDate);
        }
        catch (DateTimeParseException e) {
            throw new BadDateFormatException();
        }
    }

    public String getDay(String date){
        try{
            LocalDate localDate = LocalDate.parse(date);
            DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
            return dayFormatter.format(localDate);
        }
        catch (DateTimeParseException e) {
            throw new BadDateFormatException();
        }
    }
}
