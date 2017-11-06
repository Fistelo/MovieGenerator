package Generators;

import Content.Resources;
import Model.Studio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StudioGenerator {
    private NameGenerator nameGenerator;
    private List <Studio> generatedStudios = new ArrayList<>();
    
    public StudioGenerator(){
        nameGenerator = new NameGenerator();
    }
    
    public List<Studio> generateStudios(int numberOfStudios){
        List <String> realStudios = Resources.getInstance().getStudios();
        
        
        for(int i =0;i<numberOfStudios;i++){
            
            String studioName;
            
            if(i>= realStudios.size())
                studioName = generateStudioName();
            else
                studioName = realStudios.get(i);
            
            generatedStudios.add(new Studio(studioName, generateCapital(), nameGenerator.generateFullName().toString()));
        }
        showList();
        return generatedStudios;
    }
    
    private long generateCapital(){
        Random rand = new Random();
        return rand.nextInt(20) * 5387539;
    }
    
    private String generateStudioName(){
        return nameGenerator.getRandomSurname() + " " + "Pictures";
    }
    
    private void showList(){
        for(int i=0;i<generatedStudios.size();i++)
            System.out.println(i + ":   " + generatedStudios.get(i).toString());
    }
}
