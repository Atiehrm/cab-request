package DataBaseAccess;

import Models.Driver;
import Models.Passenger;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void save(Driver driver) throws SQLException;

    Driver findByNationalCode(int nationalCode) throws SQLException;


    List<Driver> getDriverList() throws SQLException;
}
