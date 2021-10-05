package DataBaseAccess;

import Models.Vehicle;

import java.sql.SQLException;

public interface VehicleService {
    void save(Vehicle vehicle) throws SQLException, ClassNotFoundException, Exception;

}
