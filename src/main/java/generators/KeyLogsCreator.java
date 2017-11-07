package generators;

import generators.tools.DateGenerator;
import model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/**
 * Created by rados on 06.11.2017.
 */
public class KeyLogsCreator {

    Movie movie;
    UUID keyId;
    LocalDate date;
    Random rand = new Random();
    int filmsPerDay;
    
    public KeyLogsCreator(LocalDate date, Movie movie){
        filmsPerDay = rand.nextInt(5)+1;
        this.movie = movie;
        this.date = date;
    }
    
    public void createKeylogs(int numberOfLines){
        try {
            keyId = UUID.randomUUID();
            String dir = createDirectory();
            createKeydata(numberOfLines, dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String createDirectory() throws IOException {
        String folderName ="logs/" + movie.getName() + "_" + movie.getDirector();
        Path dir = Paths.get( folderName);
        if(!Files.exists(dir))
            Files.createDirectory(dir);
        return folderName;
    }
    
    private void createKeydata(int numberOfLines, String directory) throws IOException {
        Path dir = Paths.get(directory + "/" + keyId + ".txt");
        LocalDate keyDate = date;
        int movieDuration = rand.nextInt(77)+90;
        for(int i =0;i<numberOfLines;i++){
            if(i % filmsPerDay ==0){
                keyDate=keyDate.plusDays(1);
            }
    
            String keyTime = new DateGenerator().generateTime();
            movieDuration += rand.nextInt(15)-7;
            int seatsNumber = rand.nextInt(170)+50;
            int ticketsSold = seatsNumber - rand.nextInt(seatsNumber);
            
            String data = keyDate + "\t" + keyTime + "\t" + movieDuration + "\t" + ticketsSold + "\t" + seatsNumber + "\n";
            if(!Files.exists(dir))
                Files.write(dir, data.getBytes());
            else
                Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
        }
    }
    
}
