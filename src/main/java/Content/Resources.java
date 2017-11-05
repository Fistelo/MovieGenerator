package Content;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * Created by rados on 04.11.2017.
 */
public class Resources {
    private Properties genres = new Properties();
    private List<String> directors = new ArrayList<>();
    private List<String> studios = new ArrayList<>();
    
    public Resources(){
        setDirectors();
        setGenres();
        setStudios();
    }
    
    public String getRandomDirector(){
        Random rand = new Random();
        return directors.get(rand.nextInt(directors.size()));
    }
    
    public String getGenreById(int id){
        String genreName = genres.getProperty(Integer.toString(id));
        return genreName == null ? "No Genre" : genreName;
    }
    
    public List<String> getStudios(){
        return studios;
    }
    
    
    private void setDirectors(){
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource(Consts.DIRECTORS_SRC).
                    toURI())).forEach(p -> directors.add(p));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    private void setStudios(){
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource(Consts.STUDIOS_SRC).
                    toURI())).forEach(p -> studios.add(p));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    
    private void setGenres() {
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource(Consts.GENRES_SRC).
                    toURI())).forEach(
                            p -> {
                                String prop[] = p.split(" ");
                                genres.setProperty(prop[0], prop[1]);
                            }
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
