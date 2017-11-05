import Content.Resources;
import Generators.MovieHandler;

import java.io.IOException;

/**
 * Created by rados on 04.11.2017.
 */
public class GeneratorApplication {
    
    public static void main(String[] args) {
    
        MovieHandler movieHandler = new MovieHandler(new Resources());
        
        try {
            movieHandler.getMoviesFromApi(10);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
