import Generators.DistributorsGenerator;
import Generators.MovieGenerator;
import Generators.StudioGenerator;

import java.io.IOException;

/**
 * Created by rados on 04.11.2017.
 */
public class GeneratorApplication {
    
    public static void main(String[] args) {
    
        MovieGenerator movieHandler = new MovieGenerator();
        StudioGenerator studioGenerator = new StudioGenerator();
        DistributorsGenerator distributorsGenerator = new DistributorsGenerator();
        
        try {
            movieHandler.generateMoviesFromApi(1);
            
            distributorsGenerator.generateDistributors(1);
            studioGenerator.generateStudios(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
