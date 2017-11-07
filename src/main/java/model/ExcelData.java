package model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Created by rados on 06.11.2017.
 */
public class ExcelData {
    String movieName;
    String directorName;
    UUID keyId;
    LocalDate dateFrom;
    LocalDate dateTo;
    String distributor;
    int pricePercent;
    
    public ExcelData(String movieName, String directorName,
                     LocalDate dateFrom, LocalDate dateTo,
                     String distributor, int pricePercent) {
        this.movieName = movieName;
        this.directorName = directorName;
        this.keyId = UUID.randomUUID();
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.distributor = distributor;
        this.pricePercent = pricePercent;
    }
    
    public String parseToExcel(){
        return movieName + "\t" + directorName + "\t" + keyId + "\t" + dateFrom +
                "\t" + dateTo + "\t" + distributor + "\t" + pricePercent;
    }
}
