package Model;

/**
 * Created by rados on 06.11.2017.
 */
public class Name {
    
    private String firstName;
    private String surname;
    
    public Name(String firstName, String surname){
        this.firstName = firstName;
        this.surname = surname;
    }
    
    @Override
    public String toString(){
        return firstName + " " + surname;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
}
