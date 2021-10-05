package Vehicle.VehicleService;

import Vehicle.models.Vehicle;

import java.sql.SQLException;

public interface VehicleService {
    void add(Vehicle vehicle) throws SQLException, ClassNotFoundException, Exception;

}
