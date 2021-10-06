package DataBaseAccess;


import Models.Passenger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassengerServiceImp extends DataBaseAccess implements PassengerService {

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
    public List<Passenger> getPassengerList() throws SQLException {
        List<Passenger> passengerList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from passenger ");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Passenger passenger = new Passenger();
                passenger.setId(resultSet.getInt("id"));
                passenger.setFirstName(resultSet.getString("first_name"));
                passenger.setLastName(resultSet.getString("last_name"));
                passenger.setNationalCode(resultSet.getInt("national_code"));
                passenger.setPhoneNumber(resultSet.getInt("phone_num"));
                passenger.setBirthday(resultSet.getDate("birthday"));
                passengerList.add(passenger);
            }
        }
        return passengerList;
    }

    @Override
    public Passenger findByNationalCode(int nationalCode) throws SQLException {
        Passenger passenger = new Passenger();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from passenger where national_code='%d'", nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                passenger.setId(resultSet.getInt("id"));
                passenger.setFirstName(resultSet.getString("first_name"));
                passenger.setLastName(resultSet.getString("last_name"));
                passenger.setNationalCode(resultSet.getInt("national_code"));
                passenger.setPhoneNumber(resultSet.getInt("phone_num"));
                passenger.setBirthday(resultSet.getDate("birthday"));
            }

        }
        return passenger;
    }
}
