package Model;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Created by rados on 06.11.2017.
 */
public class Meeting {
    LocalDate date;
    Time time;
    String description;
    
    public Meeting(LocalDate date, Time time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }
    
    @Override
    public String toString(){
        return "Meeting: " +
                "\n Date: " + date +
                "\n Time: " + time +
                "\n description: " + description;
    }
}
