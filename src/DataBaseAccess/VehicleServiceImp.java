package DataBaseAccess;

import Models.Vehicle;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleServiceImp extends DataBaseAccess implements VehicleService {

    public VehicleServiceImp() throws SQLException, ClassNotFoundException {
    }
    
    @Override
    public void save(Vehicle vehicle) throws SQLException {
        if (getConnection() != null) {
            PreparedStatement preparedStatement = getConnection().prepareStatement(
                    "insert into vehicle ('vehicle_number','vehicle_color','type_vehicle','driverId')" +
                            "values (?,?,?,?); ");
            preparedStatement.setString(1, String.valueOf(vehicle.getCarNumber()));
            preparedStatement.setString(2, vehicle.getCarColor());
            preparedStatement.setString(3, vehicle.getTypeVehicle());
            preparedStatement.setString(4, String.valueOf(vehicle.getDriverId()));
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                vehicle.setId(generatedKey);
            }
        }
    }
}
