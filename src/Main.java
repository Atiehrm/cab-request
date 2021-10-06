
import DataBaseAccess.*;
import Exception.MyCustomException;
import Models.Driver;
import Models.Passenger;
import Models.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    private static DriverService driverService;
    private static PassengerService passengerService;
    private static VehicleService vehicleService;

    public static void main(String[] args) {

        try {
            driverService = new DriverServiceImp();
            passengerService = new PassengerServiceImp();
            vehicleService = new VehicleServiceImp();
            mainMenu();
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println("cannot connect to db ");
        } catch (Exception e) {
        }

    }
}
