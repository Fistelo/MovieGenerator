package Generators;

import Content.Resources;
import Model.Name;

import java.util.List;
import java.util.Random;

/**
 * Created by rados on 06.11.2017.
 */
public class NameGenerator {
    
    private List <String> names;
    private List <String> surnames;
    private Random random;
    
    public NameGenerator(){
        this.names = Resources.getInstance().getNames();
        this.surnames = Resources.getInstance().getSurnames();
        random = new Random();
    }
    
    private String getRandomName(){
        return names.get(random.nextInt(names.size()));
    }
    
    public String getRandomSurname(){
        return surnames.get(random.nextInt(surnames.size()));
    }
    
    public Name generateFullName(){
        return new Name(getRandomName(), getRandomSurname());
    }

   
}
