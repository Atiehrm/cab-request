package DataBaseAccess;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImp extends DataBaseAccess implements Passenger.PassangerService.PassengerService {
    private StorageDao storageDao;
    List<Passenger> passengerList = new ArrayList<>();

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public PassengerServiceImp(StorageDao storageDao) throws SQLException, ClassNotFoundException {
        this.storageDao = storageDao;
    }

    @Override
    public void register(Passenger passenger) throws SQLException, ClassNotFoundException {
        this.storageDao.savePassenger(passenger);

    }

    @Override
    public void addToPassengerList(Passenger passenger) {
        passengerList.add(passenger);

    }

    @Override
    public List<Passenger> showPassengerList() {
        return getPassengerList();
    }
}
