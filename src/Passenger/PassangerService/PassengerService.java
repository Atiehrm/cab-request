package Passenger.PassangerService;

import Driver.models.Driver;
import Passenger.models.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface PassengerService {
   void register(Passenger passenger) throws SQLException, ClassNotFoundException;
   void addToPassengerList(Passenger passenger) throws SQLException, ClassNotFoundException;
   List<Passenger> showPassengerList() throws SQLException, ClassNotFoundException;
}
