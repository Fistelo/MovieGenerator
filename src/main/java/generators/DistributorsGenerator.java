package generators;

import content.Consts;
import generators.tools.DateGenerator;
import generators.tools.NameGenerator;
import model.Distributor;
import model.Name;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class DistributorsGenerator {
    
    private Random rand = new Random();
    private String[] emailDomains = {"gmail.com", "msn.com", "yahoo.com", "hotmail.com"};
    private List<Distributor> generatedDistributors = new ArrayList<>();
    
    public List<Distributor> generateDistributors(int numberOfDistributors) throws IOException {
        NameGenerator nameGenerator = new NameGenerator();
        DateGenerator dateGenerator = new DateGenerator();
        
        for(int i =0 ; i<numberOfDistributors ;i++){
            Name name = nameGenerator.generateFullName();
            generatedDistributors.add(new Distributor(name, generateSSN(), generateTelNumber(),
                    generateEmail(name), dateGenerator.generateDate(1950, 2000), "" ));
        }
    
        export();
        return generatedDistributors;
    }
    
    private String generateSSN(){
    
        int randomNumber;
        StringBuilder stringBuilder = new StringBuilder();
        
        randomNumber = rand.nextInt(898) + 1;
        stringBuilder.append(String.format("%03d", randomNumber));
        stringBuilder.append("-");
        randomNumber = rand.nextInt(98) + 1;
        stringBuilder.append(String.format("%02d", randomNumber));
        stringBuilder.append("-");
        randomNumber = rand.nextInt(9998) + 1;
        stringBuilder.append(String.format("%04d", randomNumber));
        return stringBuilder.toString();
    }
    
    private String generateEmail(Name name){
        String domain = emailDomains[rand.nextInt(emailDomains.length)];
        return name.getFirstName() + "." + name.getSurname() + "@" + domain;
    }
    
    private String generateTelNumber(){
        StringBuilder sb = new StringBuilder();
        int digits = rand.nextInt(899) + 100;
        sb.append(digits);
        digits = rand.nextInt(999999);
        sb.append(String.format("%06d", digits));
        return sb.toString();
    }
    
    private void export() throws IOException {
        Path dir = Paths.get(Consts.OUTPUT_FILE);
        for (int i = 0; i < generatedDistributors.size(); i++) {
            String data = generatedDistributors.get(i).parseToDb() + "\n";
            if (!Files.exists(dir))
                Files.write(dir, data.getBytes());
            else
                Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
        }
    }
 
}
