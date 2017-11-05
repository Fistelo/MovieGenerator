package Generators;

import Content.Consts;
import Content.Resources;
import Utils.HttpOperations;
import Model.Movie;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rados on 04.11.2017.
 */
public class MovieHandler {
    
    private List<Movie> movies = new ArrayList<Movie>();
    
    private Resources resources;
    private int pagesNumber;
    private int recordsNumber;
    private String baseURL;
    
    public MovieHandler(Resources resources){
        this.resources = resources;
        baseURL = Consts.API_LOCATION + "/discover/movie?" + Consts.API_KEY + "&sort_by=popularity.desc";
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
    
    
    public void getMoviesFromApi(int numberOfMovies) throws IOException {
        
        String url;
        
        for(int page =1 ; page < pagesNumber ; page++){
            url = baseURL + "&page=" + page;
            JSONObject moviesPage = new JSONObject(HttpOperations.GetRequest(url));
            JSONArray moviesArray = moviesPage.getJSONArray("results");
            makeMovieFromJSON(moviesArray);
            
            if(movies.size() >= numberOfMovies )
                break;
        }
        showList();
    }
    
    private void makeMovieFromJSON(JSONArray moviesArray){
        
        for(int i=0 ; i<moviesArray.length() ; i++){
            JSONObject singleMovie = moviesArray.getJSONObject(i);
            String genre = getGenre(singleMovie.getJSONArray("genre_ids"));
            String director = resources.getRandomDirector();
            long profit = generateProfitFromVote(singleMovie.getDouble("vote_average"));
            
            Movie movie = new Movie(singleMovie.getString("original_title"),
                    director,
                    singleMovie.getString("overview"),
                    genre,
                    profit);
            movies.add(movie);
        }
    }
    
    private String getGenre(JSONArray genreIDs){
        
        int genreID = genreIDs.getInt(0);
        return resources.getGenreById(genreID);
    }
    
    private long generateProfitFromVote(double vote){
        double profit = Math.pow(2, vote) * 5000000;
        return (long) profit;
    }
    
    private void showList(){
          for(int i=0;i<movies.size();i++)
             System.out.println(i + ":   " + movies.get(i).toString());
    }
}