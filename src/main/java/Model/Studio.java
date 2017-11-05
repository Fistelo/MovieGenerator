package Model;

/**
 * Created by rados on 05.11.2017.
 */
public class Studio {

    private String name;
    private long capital;
    private String director;
    
    public Studio(String name, long capital, String director) {
        this.name = name;
        this.capital = capital;
        this.director = director;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getCapital() {
        return capital;
    }
    
    public void setCapital(long capital) {
        this.capital = capital;
    }
    
    public String getDirector() {
        return director;
    }
    
    public void setDirector(String director) {
        this.director = director;
    }
}
