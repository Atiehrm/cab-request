package Passenger.PassangerService;

import Passenger.models.Passenger;
import Storage.DataBaseAccess;
import Storage.StorageDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImp extends DataBaseAccess implements PassengerService {
    private StorageDao storageDao;
    List<Passenger> passengerList = new ArrayList<>();

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public PassengerServiceImp(StorageDao storageDao) throws SQLException, ClassNotFoundException {
        this.storageDao = storageDao;
    }

}
