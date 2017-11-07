package generators.tools;

import java.time.LocalDate;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class DateGenerator {
    
    Random rand = new Random();
    
    public LocalDate generateDate(int yearFrom, int yearTo){
       
        int minDay = (int) LocalDate.of(yearFrom, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(yearTo, 1, 1).toEpochDay();
        long randomDay = minDay + rand.nextInt(maxDay - minDay);
        
        return LocalDate.ofEpochDay(randomDay);
        
    }
    
    public String generateTime(){
        StringBuilder sb = new StringBuilder();
        sb.append(rand.nextInt(24));
        sb.append(":");
        int minutes = rand.nextInt(6)*10;
        sb.append(String.format("%02d", minutes));
        return sb.toString();
    }
}
