package Generators;

import Model.Distributor;
import Model.Name;

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
    
    public void generateDistributors(int numberOfDistributors){
        NameGenerator nameGenerator = new NameGenerator();
        DateGenerator dateGenerator = new DateGenerator();
        
        for(int i =0 ; i<numberOfDistributors ;i++){
            Name name = nameGenerator.generateFullName();
            generatedDistributors.add(new Distributor(name, generateSSN(), generateTelNumber(),
                    generateEmail(name), dateGenerator.generateDate(1950, 2000), "" ));
        }
        showList();
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
    
    private void showList(){
        for(int i=0;i<generatedDistributors.size();i++)
            System.out.println(i + ":   " + generatedDistributors.get(i).toString());
    }
 
}
