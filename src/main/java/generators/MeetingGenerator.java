package generators;

import content.Consts;
import generators.tools.DateGenerator;
import model.Meeting;
import model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class MeetingGenerator{
    private List<Meeting> generatedMeetings = new ArrayList<>();
    private List<Movie> movies;
    
    public  MeetingGenerator(List<Movie> movies){
        this.movies = movies;
    }
    
    
    public List<Meeting> generateMeetings(int numberOfMeetings) throws IOException {
        DateGenerator dateGenerator = new DateGenerator();
        
        for(int i =0;i<numberOfMeetings;i++){
            generatedMeetings.add(new Meeting(dateGenerator.generateDate(2017,2018),
                    dateGenerator.generateTime(), movies.get(new Random().nextInt(movies.size()))));
        }
        export();
        System.out.println(Consts.MEETINGS_GENERATED);
        return generatedMeetings;
    }
    
    private void export() throws IOException {
        Path dir = Paths.get(Consts.OUTPUT_SQL_FILE);
        for (int i = 0; i < generatedMeetings.size(); i++) {
            String data = generatedMeetings.get(i).parseToDb() + "\n";
            Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
