package Vehicle.VehicleService;

import Storage.DataBaseAccess;
import Storage.StorageDao;
import Vehicle.models.Vehicle;

import java.sql.SQLException;

public class VehicleServiceImp extends DataBaseAccess implements VehicleService {
    private StorageDao storageDao;

    public VehicleServiceImp(StorageDao storageDao) throws SQLException,ClassNotFoundException {
        this.storageDao = storageDao;
    }

    @Override
    public void add(Vehicle vehicle) throws SQLException, ClassNotFoundException {
        this.storageDao.saveVehicle(vehicle);

    }
}
