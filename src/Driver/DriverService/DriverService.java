package Driver.DriverService;

import Driver.models.Driver;

import java.sql.SQLException;
import java.util.List;

public interface DriverService {
    void register(Driver driver) throws SQLException, ClassNotFoundException;
    void addToDriverList(Driver driver) throws SQLException, ClassNotFoundException;
    List<Driver> showDriverList() throws SQLException, ClassNotFoundException;
}
