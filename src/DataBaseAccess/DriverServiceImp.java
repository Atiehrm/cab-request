package DataBaseAccess;

import Models.Driver;
import Models.PersonalInfo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DriverServiceImp extends DataBaseAccess implements DriverService {
    List<Driver> driverList = new ArrayList<>();

    public List<Driver> getDriverList() {
        return driverList;
    }

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
    public void addToDriverList(Driver driver) {
        driverList.add(driver);

    }

    @Override
    public List<Driver> showDriverList() {
        return getDriverList();
    }
}
