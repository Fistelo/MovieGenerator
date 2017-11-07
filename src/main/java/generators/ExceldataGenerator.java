package generators;

import content.Consts;
import content.Resources;
import generators.tools.DateGenerator;
import model.Distributor;
import model.ExcelData;
import model.Movie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    
    public ExceldataGenerator(List<Movie> movies, List<Distributor> distributors) throws IOException {
        this.movies = movies;
        this.distributors = distributors;
        random = new Random();
        setExcelHeaders();
    }
    
    private void setExcelHeaders() throws IOException {
        String data = Resources.getInstance().getExcelHeaders();
        Path dir = Paths.get(Consts.OUTPUT_EXCEL_FILE);
        Files.write(dir, data.getBytes());
    }
    
    public List<ExcelData> generateExcelData(int numberOfLines) throws IOException {
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
        
        exportToFile();
        return excelData;
    }
    
    private void exportToFile() throws IOException {
        String data;
        Path dir = Paths.get(Consts.OUTPUT_EXCEL_FILE);
        
        for (int i = 0; i < excelData.size(); i++) {
            data = excelData.get(i).parseToExcel() + "\n";
            Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
        }
    
    
    }
}
