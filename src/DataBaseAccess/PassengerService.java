package DataBaseAccess;

import Models.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface PassengerService {
    void save(Passenger passenger) throws SQLException;

    List<Passenger> getPassengerList() throws SQLException;

    Passenger findByNationalCode(int nationalCode) throws SQLException;
}
