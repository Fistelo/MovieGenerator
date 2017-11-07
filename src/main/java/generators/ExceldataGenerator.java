package generators;

import content.Consts;
import generators.tools.DateGenerator;
import model.Distributor;
import model.ExcelData;
import model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class ExceldataGenerator {
    private List<ExcelData> excelData = new ArrayList<>();
    private List<Movie> movies;
    private List<Distributor> distributors;
    private Random random;
    
    public ExceldataGenerator(List<Movie> movies, List<Distributor> distributors){
        this.movies = movies;
        this.distributors = distributors;
        random = new Random();
    }
    
    public List<ExcelData> generateExcelData(int numberOfLines){
        DateGenerator dateGenerator = new DateGenerator();
        LocalDate startDate = dateGenerator.generateDate(2015,2017);
        
        Movie randomMovie = movies.get(random.nextInt(movies.size()));
        for (int i =0 ;i<numberOfLines;i++){
            LocalDate nextStartDate = startDate.plusDays(7*i);
            LocalDate nextEndDate = startDate.plusDays(7*(i+1));
            excelData.add(new ExcelData(randomMovie.getName(), randomMovie.getDirector(),
                    nextStartDate, nextEndDate, randomMovie.getDistributor().getFullname().toString(), random.nextInt(30)+60));
            
            new KeyLogsCreator(nextStartDate, randomMovie).createKeylogs(Consts.NUMBEROF_EXCEL_LINES);
        }
        
        return excelData;
    }
    
}
