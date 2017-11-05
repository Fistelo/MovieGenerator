package Model;

public class Movie {
    
    private String name;
    private String director;
    private String description;
    private String genre;
    private long profit;
    
    public Movie(String name, String director, String description, String genre, long profit) {
        this.name = name;
        this.director = director;
        this.description = description;
        this.genre = genre;
        this.profit = profit;
    }
    
    @Override
    public String toString(){
        return "Movie: " + name +
                "\n Director: " + director +
                "\n Genre: " + genre +
                "\n Profit: " + profit +
                "\n Description: " + description.substring(0, 15) + "...";
    }
    
    public long getProfit() {
        return profit;
    }
    
    public void setProfit(long profit) {
        this.profit = profit;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
