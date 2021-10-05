package DataBaseAccess;


import Models.Passenger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImp extends DataBaseAccess implements PassengerService {
    List<Passenger> passengerList = new ArrayList<>();

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public PassengerServiceImp() throws SQLException, ClassNotFoundException {
    }


    @Override
    public void save(Passenger passenger) throws SQLException {
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
