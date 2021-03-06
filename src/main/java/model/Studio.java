package model;

/**
 * Created by rados on 05.11.2017.
 */
public class Studio implements DbParsable{

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
    
    @Override
    public String toString(){
        return "Studio: " + name +
                "\n Director: " + director +
                "\n capital: " + capital;
    }
    
    @Override
    public String parseToDb() {
        return "insert into Wytwornia (\"Nazwa\", \"Kapital\", \"Dyrektor\") values (\'"
                + name  + "\', \'" + capital +"\', \'" + director + "\');";
    }
}
