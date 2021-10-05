package DataBaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccess {
    private Connection connection = null;

    public DataBaseAccess() throws SQLException , ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hw6_cab_req"
                    , "root", "root");

    }

    public Connection getConnection() {
        return connection;
    }


}
