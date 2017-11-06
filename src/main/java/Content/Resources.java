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
    
    private static Resources instance = null;
    
    private Properties genres = new Properties();
    private List<String> directors = new ArrayList<>();
    private List<String> studios = new ArrayList<>();
    private List<String> names = new ArrayList<>();
    private List<String> surnames = new ArrayList<>();
    
    private Resources(){
        getStringsFromTxt(names, Consts.NAMES_SRC);
        getStringsFromTxt(surnames, Consts.SURNAMES_SRC);
        getStringsFromTxt(directors, Consts.DIRECTORS_SRC);
        getStringsFromTxt(studios, Consts.STUDIOS_SRC);
        setGenres();
    }
    
    public static Resources getInstance(){
        if(instance == null)
            instance = new Resources();
        return instance;
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
    
    public List<String> getNames(){
        return names;
    }
    
    public List<String> getSurnames() {
        return surnames;
    }
    
    private void getStringsFromTxt(List <String> destination, String src){
        try {
            Files.lines(Paths.get(ClassLoader.getSystemResource(src).
                    toURI())).forEach(p -> destination.add(p));
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
