package DataBaseAccess;

import Models.Driver;
import Models.Passenger;
import Models.PersonalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DriverServiceImp extends DataBaseAccess implements DriverService {

    public DriverServiceImp() throws SQLException, ClassNotFoundException {

    }


    @Override
    public void save(Driver driver) throws SQLException {
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
    }



    @Override
    public List<Driver> getDriverList() throws SQLException {
        List<Driver> driverList = new ArrayList<>();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from driver ");
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getInt("id"));
                driver.setFirstName(resultSet.getString("first_name"));
                driver.setLastName(resultSet.getString("last_name"));
                driver.setNationalCode(resultSet.getInt("national_code"));
                driver.setPhoneNumber(resultSet.getInt("phone_num"));
                driver.setBirthday(resultSet.getDate("birthday"));
                driverList.add(driver);
            }
        }
        return driverList;
    }
    @Override
    public Driver findByNationalCode(int nationalCode) throws SQLException {
        Driver driver = new Driver();
        if (getConnection() != null) {
            Statement statement = getConnection().createStatement();
            String sqlQuery = String.format("select * from driver where national_code='%d'", nationalCode);
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                driver.setId(resultSet.getInt("id"));
                driver.setFirstName(resultSet.getString("first_name"));
                driver.setLastName(resultSet.getString("last_name"));
                driver.setNationalCode(resultSet.getInt("national_code"));
                driver.setPhoneNumber(resultSet.getInt("phone_num"));
                driver.setBirthday(resultSet.getDate("birthday"));
            }

        }
        return driver;
    }

}
