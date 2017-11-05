package Generators;

import Content.Resources;
import Model.Studio;

import java.util.List;
import java.util.Random;

/**
 * Created by rados on 05.11.2017.
 */
public class StudioHandler {
    private List<Studio> studios;
    private Resources resources;
    
    public StudioHandler(Resources resources){
        this.resources = resources;
    }
    
    public void generateStudios(int numberOfStudios){
        List <String> realStudios = resources.getStudios();
        
        for(int i =0;i<numberOfStudios;i++){
            
            String name;
            
            if(i>= realStudios.size())
                name = generateStudioName();
            else
                name = realStudios.get(i);
            //TODO
            studios.add(new Studio(name, generateCapital(), ""));
        }
        
    }
    
    private long generateCapital(){
        Random rand = new Random();
        return rand.nextInt(20) * 5387539;
    }
    
    private String generateStudioName(){
    
        return null;
    }
    
}
