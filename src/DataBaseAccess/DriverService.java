package DataBaseAccess;

import Models.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void save(Driver driver) throws SQLException;

    void addToDriverList(Driver driver);

    List<Driver> showDriverList() throws NullPointerException;
}
