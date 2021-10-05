package Storage;

import Passenger.models.Passenger;
import Vehicle.models.Vehicle;
import Driver.models.Driver;

import java.sql.*;

public class StorageDaoImpl extends DataBaseAccess implements StorageDao {
    public StorageDaoImpl() throws SQLException,ClassNotFoundException {
    }

    @Override
    public int savePassenger(Passenger passenger) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into passenger (first_name" +
                            ",last_name,national_code,phone_num,birthday) values ('%s','%s','%s','%s','%s')"
                    , passenger.getFirstName(), passenger.getLastName(), passenger.getNationalCode()
                    , passenger.getPhoneNumber(), passenger.getBirthday());
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                passenger.setId(generatedKey);
            }
        }
        return 0;
    }

    @Override
    public int saveDriver(Driver driver) throws SQLException {
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("insert into driver (first_name" +
                            ",last_name,national_code,phone_num,birthday) values ('%s','%s','%s','%s','%s')"
                    , driver.getFirstName(), driver.getLastName(), driver.getNationalCode()
                    , driver.getPhoneNumber(), driver.getBirthday());
            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlQuery,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            int generatedKey = 0;
            if (rs.next()) {
                generatedKey = rs.getInt(1);
                driver.setId(generatedKey);
            }
        }
        return 0;
    }


    @Override
    public int saveVehicle(Vehicle vehicle) throws SQLException {
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
        return 0;
    }
}
