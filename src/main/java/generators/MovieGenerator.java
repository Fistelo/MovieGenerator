package generators;

import content.Consts;
import content.Resources;
import model.Distributor;
import model.Studio;
import utils.HttpOperations;
import model.Movie;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rados on 04.11.2017.
 */
public class MovieGenerator {
    
    private List<Movie> movies = new ArrayList<Movie>();
    private List<Movie> moviesToDistribution = new ArrayList<Movie>();
    private List<Movie> moviesDistributed = new ArrayList<Movie>();
    
    private int pagesNumber;
    private int recordsNumber;
    private String baseURL;
    private List<Studio> studios;
    private List<Distributor> distributors;
    
    public MovieGenerator(List<Studio> studios, List<Distributor> distributors){
        baseURL = Consts.API_LOCATION + "/discover/movie?" + Consts.API_KEY + "&sort_by=popularity.desc";
        this.studios = studios;
        this.distributors = distributors;
        init();
    }
    
    public void init(){
    
        String response = null;
        try {
            response = HttpOperations.GetRequest(baseURL);
            JSONObject jsonResponse = new JSONObject(response);
            pagesNumber = jsonResponse.getInt("total_pages");
            recordsNumber = jsonResponse.getInt("total_results");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Movie> generateMoviesFromApi(int numberOfMovies) throws IOException {
        
        String url;
        
        for(int page =1 ; page < pagesNumber ; page++){
            url = baseURL + "&page=" + page;
            JSONObject moviesPage = new JSONObject(HttpOperations.GetRequest(url));
            JSONArray moviesArray = moviesPage.getJSONArray("results");
            makeMovieFromJSON(moviesArray);
            
            if(movies.size() >= numberOfMovies )
                break;
        }
        divide();
        exportToFile();
        return moviesToDistribution;
    }
    
    private void divide(){
        for (int i = 0; i < movies.size(); i++){
            if(Consts.NUMBEROF_MOVIES_TO_DISTRIBUTE < i)
                moviesToDistribution.add(movies.get(i));
            else
                moviesDistributed.add(movies.get(i));
        }
    }
    
    private void makeMovieFromJSON(JSONArray moviesArray){
        
        for(int i=0 ; i<moviesArray.length() ; i++){
            JSONObject singleMovie = moviesArray.getJSONObject(i);
            String genre = getGenre(singleMovie.getJSONArray("genre_ids"));
            String director = Resources.getInstance().getRandomDirector();
            long profit = generateProfitFromVote(singleMovie.getDouble("vote_average"));
            
            Movie movie = new Movie(singleMovie.getString("original_title"),
                    director,
                    singleMovie.getString("overview"),
                    genre,
                    profit,
                    studios.get(new Random().nextInt(studios.size())),
                    distributors.get(new Random().nextInt(distributors.size())));
            movies.add(movie);
        }
    }
    
    private String getGenre(JSONArray genreIDs){
        
        int genreID = genreIDs.getInt(0);
        return Resources.getInstance().getGenreById(genreID);
    }
    
    private long generateProfitFromVote(double vote){
        double profit = Math.pow(2, vote) * 5000000;
        return (long) profit;
    }
    
    private void exportToFile() throws IOException {
    
        Path dir = Paths.get(Consts.OUTPUT_FILE);
            for (int i = 0; i < movies.size(); i++) {
                String data = movies.get(i).parseToDb() + "\n";
                if (!Files.exists(dir))
                    Files.write(dir, data.getBytes());
                else
                    Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
            }
            
            for (int i = 0; i < moviesToDistribution.size(); i++) {
            String data = moviesToDistribution.get(i).parseToDistribution() + "\n";
            Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
             }
    
            for (int i = 0; i < moviesDistributed.size(); i++) {
                String data = moviesDistributed.get(i).parsePreviousFilms() + "\n";
                Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
            }
    }
}