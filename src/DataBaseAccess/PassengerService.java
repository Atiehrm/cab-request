package DataBaseAccess;

import Models.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface PassengerService {
    void save(Passenger passenger) throws SQLException;

    void addToPassengerList(Passenger passenger);

    List<Passenger> showPassengerList();
}
