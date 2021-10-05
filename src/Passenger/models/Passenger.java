package Passenger.models;
import java.sql.Date;

public class Passenger extends PersonalInfo {
    public Passenger(String firstName, String lastName, int nationalCode, int phoneNumber, Date birthday) {
        super(firstName, lastName, nationalCode, phoneNumber, birthday);
    }
}
