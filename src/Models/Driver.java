package Models;


import java.sql.Date;

public class Driver extends PersonalInfo {
    public Driver(String firstName, String lastName, int nationalCode, int phoneNumber, Date birthday) {
        super(firstName, lastName, nationalCode, phoneNumber, birthday);
    }

    public Driver() {
        super();
    }

    @Override
    public String toString() {
        return "Driver{ "
                + getId()+", "
                + getFirstName()+", "
                + getLastName()+", "
                + getNationalCode()+", "
                + getPhoneNumber()+", "
                + getBirthday()+" }";
    }
}
