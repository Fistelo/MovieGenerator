package model;

import java.time.LocalDate;

/**
 * Created by rados on 06.11.2017.
 */
public class Meeting implements DbParsable{
    LocalDate date;
    String time;
    Movie movie;
    
    public Meeting(LocalDate date, String time,  Movie movie) {
        this.date = date;
        this.time = time;
        this.movie = movie;
    }
    
    @Override
    public String toString(){
        return "Meeting: " +
                "\n Date: " + date +
                "\n Time: " + time;
    }
    
    @Override
    public String parseToDb() {
        return "insert into Spotkanie (\"data_spotkania\", \"FK_NazwaFilmu\", \"FK_RezyserFilmu\", \"FK_Dystrybutor\") values (\'"
                + date  + "\',  \'" + movie.getName() +"\', \'" + movie.getDirector() +"\' , \'"+ movie.getDistributor().getSSN()
                + "\');";
    }
}
