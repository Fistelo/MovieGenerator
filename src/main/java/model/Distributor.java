package model;

import java.time.LocalDate;

/**
 * Created by rados on 06.11.2017.
 */
public class Distributor implements DbParsable{

    Name fullname;
    String SSN;
    String phoneNumber;
    String email;
    
    public String getSSN() {
        return SSN;
    }
    
    LocalDate birthDate;
    String description;
    
    public Distributor(Name fullname, String SSN,
                       String phoneNumber, String email, LocalDate birthDate, String description) {
        this.fullname = fullname;
        this.SSN = SSN;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;
        this.description = description;
    }
    
    public Name getFullname() {
        return fullname;
    }
    
    public void setFullname(Name fullname) {
        this.fullname = fullname;
    }
    
    @Override
    public String toString(){
        return "Distributor: " + fullname +
                "\n SSN: " + SSN +
                "\n phoneNumber: " + phoneNumber +
                "\n email: " + email +
                "\n birthDate: " + birthDate +
                "\n description: " + description;
    }
    
    @Override
    public String parseToDb() {
       return "insert into Dystrybutor (\"imie\", \"nazwisko\", \"SSN\", \"telefon\", \"email\", \"Data_urodzenia\", \"opis\") values (\'"
                + fullname.getFirstName()  + "\', \'" + fullname.getSurname() +"\', \'" + SSN +"\', \'" + phoneNumber +"\' , \'"+ email+
               "\', \'" + birthDate + "\', \'" + description + "\');";
    }
}
