package Model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by rados on 06.11.2017.
 */
public class Distributor {

    Name fullname;
    String SSN;
    String phoneNumber;
    String email;
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
    
    @Override
    public String toString(){
        return "Distributor: " + fullname +
                "\n SSN: " + SSN +
                "\n phoneNumber: " + phoneNumber +
                "\n email: " + email +
                "\n birthDate: " + birthDate +
                "\n description: " + description;
    }
    
}
