package model;

public class Movie implements DbParsable{
    
    private String name;
    private String director;
    private String description;
    private String genre;
    private long profit;
    private Studio studio;
    private Distributor distributor;
    
    public Movie(String name, String director, String description, String genre, long profit, Studio studio, Distributor distributor) {
        this.name = parseText(name);
        this.director = director;
        this.description = parseText(description);
        this.genre = genre;
        this.profit = profit;
        this.studio = studio;
        this.distributor = distributor;
    }
    
    private String parseText(String description) {
       String newString = description.replace("\'"," ").replace(":", " ");
       if(newString.length() > 300){
           newString = newString.substring(0,290);
           newString += "...";
       }
       return newString;
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
    
    public Distributor getDistributor() {
        return distributor;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String parseToDb() {
        return "insert into Film (\"Nazwa\", \"Rezyser\", \"Gatunek\", \"Opis\", \"FK_Wytwornia\") values (\'"
                + name  + "\', \'" + director +"\', \'" + genre +"\', \'" + description +"\' , \'"+ studio.getName() + "\');";
    }
    
    public String parsePreviousFilms() {
        return "insert into Wczesniejsze_filmy (\"FK_NazwaFilmu\", \"FK_RezyserFilmu\", \"Boxoffice\") values (\'"
                + name  + "\', \'" + director +"\', \'" + profit + "\');";
    }
    
    public String parseToDistribution() {
        return "insert into Do_dystrybucji (\"FK_NazwaFilmu\", \"FK_RezyserFilmu\", \"FK_Dystrybutor\", \"prognozowany_zysk\") values (\'"
                + name  + "\', \'" + director +"\', \'" + distributor.getSSN()  +"\', \'" + profit + "\');";
    }
}
