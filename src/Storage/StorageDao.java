package Storage;

import Passenger.models.Passenger;
import Vehicle.models.Vehicle;
import Driver.models.Driver;

import java.sql.SQLException;

public interface StorageDao {
    int savePassenger(Passenger passenger) throws SQLException, ClassNotFoundException;

    int saveDriver(Driver driver) throws SQLException, ClassNotFoundException;

    int saveVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException;


}
