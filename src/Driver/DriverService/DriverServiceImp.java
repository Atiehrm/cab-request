package Driver.DriverService;

import Driver.models.Driver;
import Storage.DataBaseAccess;
import Storage.StorageDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DriverServiceImp extends DataBaseAccess implements DriverService {
    private StorageDao storageDao;
    List<Driver> driverList = new ArrayList<>();

    public List<Driver> getDriverList() {
        return driverList;
    }

    public DriverServiceImp(StorageDao storageDao) throws SQLException, ClassNotFoundException {
        this.storageDao = storageDao;
    }


}
