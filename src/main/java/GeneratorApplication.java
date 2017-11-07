import content.Consts;
import generators.*;
import model.Distributor;
import model.Movie;
import model.Studio;

import java.io.IOException;
import java.util.List;

/**
 * Created by rados on 04.11.2017.
 */
public class GeneratorApplication {
    
    public static void main(String[] args) {

        StudioGenerator studioGenerator = new StudioGenerator();
        DistributorsGenerator distributorsGenerator = new DistributorsGenerator();
      
        try {
            List<Distributor> distributors = distributorsGenerator.generateDistributors(Consts.NUMBEROF_DISTRIBUTORS);
            List <Studio> studios = studioGenerator.generateStudios(Consts.NUMBEROF_STUDIOS);
            MovieGenerator movieHandler = new MovieGenerator(studios, distributors);
            List<Movie> movies = movieHandler.generateMoviesFromApi(Consts.NUMBEROF_MOVIES);
            MeetingGenerator meetingGenerator = new MeetingGenerator(movies);
            meetingGenerator.generateMeetings(Consts.NUMBEROF_MEETINGS);
            
            ExceldataGenerator exceldataGenerator = new ExceldataGenerator(movies,distributors);
            
            for(int i = 0; i< Consts.NUMBEROF_EXCEL_FILMS; i++){
                exceldataGenerator.generateExcelData(Consts.NUMBEROF_EXCEL_KEYS);
                System.out.println("Excel data " + i  + "/" + Consts.NUMBEROF_EXCEL_FILMS);
            }
            System.out.println(Consts.EXCEL_GENERATED);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
