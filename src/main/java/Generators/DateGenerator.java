package Generators;

import java.time.LocalDate;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class DateGenerator {
    
    public LocalDate generateDate(int yearFrom, int yearTo){
        Random rand = new Random();
       
        int minDay = (int) LocalDate.of(yearFrom, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(yearTo, 1, 1).toEpochDay();
        long randomDay = minDay + rand.nextInt(maxDay - minDay);
        
        return LocalDate.ofEpochDay(randomDay);
        
    }
}
