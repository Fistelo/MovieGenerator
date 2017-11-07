package generators;

import content.Consts;
import content.Resources;
import generators.tools.NameGenerator;
import model.Studio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class StudioGenerator {
    private NameGenerator nameGenerator;
    private List <Studio> generatedStudios = new ArrayList<>();
    
    public StudioGenerator(){
        nameGenerator = new NameGenerator();
    }
    
    public List<Studio> generateStudios(int numberOfStudios) throws IOException {
        List <String> realStudios = Resources.getInstance().getStudios();
        
        
        for(int i =0;i<numberOfStudios;i++){
            
            String studioName;
            
            if(i>= realStudios.size())
                studioName = generateStudioName();
            else
                studioName = realStudios.get(i);
            
            generatedStudios.add(new Studio(studioName, generateCapital(), nameGenerator.generateFullName().toString()));
        }
        export();
        return generatedStudios;
    }
    
    private long generateCapital(){
        Random rand = new Random();
        return rand.nextInt(20) * 5387539;
    }
    
    private String generateStudioName(){
        return nameGenerator.getRandomSurname() + " " + "Pictures";
    }
    
    private void export() throws IOException {
        Path dir = Paths.get(Consts.OUTPUT_FILE);
        for (int i = 0; i < generatedStudios.size(); i++) {
            String data = generatedStudios.get(i).parseToDb() + "\n";
            Files.write(dir, data.getBytes(), StandardOpenOption.APPEND);
        }
    }
}
