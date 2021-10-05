import Driver.DriverService.DriverService;
import Driver.DriverService.DriverServiceImp;
import Driver.models.Driver;
import Passenger.PassangerService.PassengerService;
import Passenger.PassangerService.PassengerServiceImp;
import Passenger.models.Passenger;
import Storage.StorageDao;
import Storage.StorageDaoImpl;
import Vehicle.VehicleService.VehicleService;
import Vehicle.VehicleService.VehicleServiceImp;
import Vehicle.models.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    private static StorageDao storageDao;
    private static DriverService driverService;
    private static PassengerService passengerService;
    private static VehicleService vehicleService;

    static {
        try {
            storageDao = new StorageDaoImpl();
            driverService = new DriverServiceImp(storageDao);
            passengerService = new PassengerServiceImp(storageDao);
            vehicleService = new VehicleServiceImp(storageDao);
        } catch (SQLException | ClassNotFoundException exp) {
            /*exp.printStackTrace();*/
        }
    }

    public static void main(String[] args) throws Exception {
        mainMenu();
    }

    protected static void mainMenu() throws Exception {
        int choice;
        boolean checkException;
        System.out.println("***Cab Request System*** \n" +
                "1) Add a group of drivers \n" +
                "2) Add a group of passengers \n" +
                "3) Driver signup or login \n" +
                "4) Passenger signup or login \n" +
                "5) Show ongoing travels \n" +
                "6) Show a list of drivers \n" +
                "7) Show a list of passengers \n" +
                "8) exit");
        try {
            choice = scanner.nextInt();
            checkException = true;
            chosenItem(choice);

        } catch (InputMismatchException ime) {
            ime.getStackTrace();
            throw new MyCustomException("only integeres valid ", ime);
        }
        if (checkException = false) {
            mainMenu();
        }
    }

    public static void chosenItem(int choice) throws Exception {
        if (choice == 1) {
            addDrivers();
        } else if (choice == 2) {
            addPassengers();

        } else if (choice == 3) {
            driverSignUpOrLogin();

        } else if (choice == 4) {
            passengerSignUpOrLogin();

        } else if (choice == 5) {
            System.out.println("not completed yet ");

        } else if (choice == 6) {
            showDriverList();

        } else if (choice == 7) {
            showPassengerList();

        } else if (choice == 8) {
            System.out.println("bye bye");

        } else {
            System.out.println("input only in [1-8], try again ");
        }
    }

    public static void addDrivers() throws Exception {
        boolean checkException;
        int driverNumbers = 0;
        System.out.println("enter number of drivers u wanna add: ");
        try {
            driverNumbers = scanner.nextInt();

        } catch (InputMismatchException | NumberFormatException ime) {
            System.out.println("enter only integers ");
            driverNumbers = scanner.nextInt();
        }
        for (int i = 0; i < driverNumbers; i++) {
            String[] driverInputs = driverAndPassengerInfos();
            try {
                Driver driver = new Driver(driverInputs[0], driverInputs[1]
                        , Integer.parseInt(driverInputs[2]), Integer.parseInt(driverInputs[3])
                        , Date.valueOf(driverInputs[4]));
                driverService.register(driver);
                driverService.addToDriverList(driver);
                addDriverCarFeatures(driver);
                checkException = true;
            } catch (NumberFormatException | InputMismatchException excp) {
                excp.getStackTrace();
                throw new MyCustomException("wrong input,tryagain ", excp);
            }
        }
        if (checkException = false) {
            addDrivers();
        }
        System.out.println("drivers added successfully ");
        mainMenu();

    }

    public static void addDriverCarFeatures(Driver driver) throws Exception {
        boolean checkException;
        System.out.println("enter your car info: ");
        System.out.println("Vehicle number: ");
        String carNum = scanner.nextLine().trim();
        System.out.println("type Of Vehicle: ");
        String typeVehicle = scanner.nextLine().trim();
        System.out.println("color of Vehicle: ");
        String vehicleColor = scanner.nextLine().trim();
        try {
            if (carNum.matches("[0-9]+") && typeVehicle.matches("[a-zA-Z]+")
                    && vehicleColor.matches("[a-zA-Z]+")) {
                Vehicle vehicle = new Vehicle(Integer.parseInt(carNum), typeVehicle, vehicleColor, driver.getId());
                vehicleService.add(vehicle);
                checkException = true;
            }
        } catch (NumberFormatException | InputMismatchException excp) {
            excp.getStackTrace();
            throw new MyCustomException("not valid input try again", excp);
        }
        if (checkException = false) {
            addDriverCarFeatures(driver);
        }
    }


    public static String[] driverAndPassengerInfos() throws Exception {
        boolean checkException;
        String[] driverInputs = new String[5];
        System.out.println("enter firstName");
        String firstName = scanner.nextLine().trim();
        System.out.println("enter lastName");
        String lastName = scanner.nextLine().trim();
        System.out.println("enter nationalCode");
        String nationalCode = scanner.nextLine().trim();
        System.out.println("enter phoneNumber");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("enter birthday in yyyy-mm-dd ");
        String date = scanner.nextLine().trim();
        try {
            if (firstName.matches("[a-zA-Z]+") && lastName.matches("[a-zA-Z]+")
                    && nationalCode.matches("[0-9]+") && phoneNumber.matches("[0-9]+")) {
                driverInputs[0] = firstName;
                driverInputs[1] = lastName;
                driverInputs[2] = nationalCode;
                driverInputs[3] = phoneNumber;
                driverInputs[4] = date;
                checkException = true;
            }
        } catch (NumberFormatException | InputMismatchException exp) {
            exp.getStackTrace();
            throw new MyCustomException("not valid input try again", exp);
        }
        if (checkException = false) {
            driverAndPassengerInfos();
        }
        return driverInputs;

    }


    public static void addPassengers() throws Exception {
        boolean checkException;
        int passengerNumbers = 0;
        System.out.println("enter number of passengers u wanna add: ");
        try {
            passengerNumbers = scanner.nextInt();
            checkException = true;

        } catch (InputMismatchException ime) {
            ime.getStackTrace();
            throw new MyCustomException("only integers valid ", ime);
        }
        if (checkException = false) {
            passengerNumbers = scanner.nextInt();
        }
        for (int i = 0; i < passengerNumbers; i++) {
            String[] driverInputs = driverAndPassengerInfos();
            Passenger passenger = new Passenger(driverInputs[0], driverInputs[1]
                    , Integer.parseInt(driverInputs[2]), Integer.parseInt(driverInputs[3])
                    , Date.valueOf(driverInputs[4]));
            passengerService.register(passenger);
            passengerService.addToPassengerList(passenger);
        }
        System.out.println("passengers added successfully ");
        mainMenu();
    }

    public static void driverSignUpOrLogin() throws Exception {
        try {
            System.out.println("nationalCode: ");
            String nationalCode = scanner.nextLine().trim();
            List<Driver> driverList = driverService.showDriverList();
            for (Driver driver : driverList) {
                if (!nationalCode.equals(driver.getNationalCode())) {
                    String[] userInputInfo = driverAndPassengerInfos();
                    Driver newDriver = new Driver(userInputInfo[0], userInputInfo[1]
                            , Integer.parseInt(userInputInfo[2]), Integer.parseInt(userInputInfo[3])
                            , Date.valueOf(userInputInfo[4]));
                    driverService.register(newDriver);
                    driverService.addToDriverList(driver);
                    addDriverCarFeatures(driver);
                    System.out.println("driver and vehicle successfully registered ");
                } else {
                    System.out.println("you are already registered ");
                }
            }
        } catch (Exception exception) {
            exception.getStackTrace();
            throw new MyCustomException("try again cause exception", exception);

        }

        subMenuDriver();
    }

    public static void subMenuDriver() throws Exception {
        int choice = 0;
        boolean checkException;
        System.out.println("1) register \n" + "2) exit");
        try {
            choice = scanner.nextInt();
            checkException = true;
        } catch (Exception ime) {
            throw new MyCustomException("only integers valid ", ime);
        }
        if (checkException = false) {
            subMenuDriver();
        }
        if (choice == 1) {
            driverSignUpOrLogin();
        } else if (choice == 2) {
            mainMenu();
        } else {
            System.out.println("only enter 1 or 2 ");
            subMenuDriver();

        }
    }

    public static void passengerSignUpOrLogin() throws Exception {
        try {
            System.out.println("nationalCode: ");
            String nationalCode = scanner.nextLine().trim();
            List<Passenger> passengerList = passengerService.showPassengerList();
            for (Passenger passenger : passengerList) {
                if (!nationalCode.equals(passenger.getNationalCode())) {
                    String[] userInputInfo = driverAndPassengerInfos();
                    Passenger newPassenger = new Passenger(userInputInfo[0], userInputInfo[1]
                            , Integer.parseInt(userInputInfo[2]), Integer.parseInt(userInputInfo[3])
                            , Date.valueOf(userInputInfo[4]));
                    passengerService.register(newPassenger);
                    passengerService.addToPassengerList(passenger);
                } else {
                    System.out.println("you are already registered ");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
            throw new MyCustomException("try again", e);
        }
        subMenuPassenger();

    }

    public static void subMenuPassenger() throws Exception {
        int choice = 0;
        boolean checkException;
        System.out.println("1) register \n" + "2) exit");
        try {
            choice = scanner.nextInt();
            checkException = true;
        } catch (InputMismatchException ime) {
            ime.getStackTrace();
            throw new MyCustomException("only integers valid ", ime);
        }
        if (checkException = false) {
            subMenuPassenger();
        }
        if (choice == 1) {
            passengerSignUpOrLogin();
        } else if (choice == 2) {
            mainMenu();
        } else {
            System.out.println("only enter 1 or 2 ");
            subMenuPassenger();

        }
    }

    public static void showDriverList() throws Exception {
        boolean checkException;
        try {
            List<Driver> driverList = driverService.showDriverList();
            for (Driver driver : driverList) {
                System.out.println(driver.toString());
            }
            checkException = true;
        } catch (SQLException | ClassNotFoundException | NullPointerException excp) {
            excp.getStackTrace();
            throw new MyCustomException("sql | class not found | null point exception, try again ", excp);
        }
        if (checkException = false) {
            mainMenu();
        }
    }

    public static void showPassengerList() throws Exception {
        boolean checkException;
        try {
            List<Passenger> passengerList = passengerService.showPassengerList();
            for (Passenger passenger : passengerList) {
                System.out.println(passenger.toString());
            }
            checkException = true;
        } catch (SQLException | ClassNotFoundException | NullPointerException excp) {
            excp.getStackTrace();
            throw new MyCustomException("sql | class not found | null point exception, try again ", excp);
        }
        if (checkException = false) {
            mainMenu();
        }
    }
}