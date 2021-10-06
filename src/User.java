import Models.Driver;
import Models.Passenger;
import Models.Vehicle;

import java.sql.Date;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

public class User {
    protected static void mainMenu() {
        int choice;
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
            chosenItem(choice);
        } catch (InputMismatchException ime) {
            System.out.println("only integeres valid " + ime.getMessage());
        }
        mainMenu();
    }

    public static void chosenItem(int choice) {
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

    public static void addDrivers() throws SQLException {
        int driverNumbers = 0;
        System.out.println("enter number of drivers u wanna add: ");
        try {
            driverNumbers = scanner.nextInt();
            for (int i = 0; i < driverNumbers; i++) {
                String[] driverInputs = driverAndPassengerInfos();
                Driver driver = new Driver(driverInputs[0], driverInputs[1]
                        , Integer.parseInt(driverInputs[2]), Integer.parseInt(driverInputs[3])
                        , Date.valueOf(driverInputs[4]));
                driverService.save(driver);
                addDriverCarFeatures(driver);
                System.out.println("drivers added successfully ");
            }

        } catch (MyCustomException ime) {
            System.out.println(ime.getMessage());
        } finally {
            mainMenu();
        }
    }

    public static void addDriverCarFeatures(Driver driver) throws SQLException {
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
                vehicleService.save(vehicle);
            }
            //todo
        } catch (NumberFormatException | InputMismatchException excep) {
            System.out.println("not valid input try again" + excep.getMessage());
            addDriverCarFeatures(driver);
        }
    }

    public static String[] driverAndPassengerInfos() throws MyCustomException {
        String[] driverInputs = new String[5];
        System.out.println("enter firstName");
        scanner.nextLine();
        String firstName = scanner.nextLine().trim();
        System.out.println("enter lastName");
        String lastName = scanner.nextLine().trim();
        System.out.println("enter nationalCode");
        String nationalCode = scanner.nextLine().trim();
        System.out.println("enter phoneNumber");
        String phoneNumber = scanner.nextLine().trim();
        System.out.println("enter birthday in yyyy-mm-dd ");
        String date = scanner.nextLine().trim();

        if (firstName.matches("[a-zA-Z]+") && lastName.matches("[a-zA-Z]+")
                && isValidNationalCode(nationalCode) && isValidDate(date) && phoneNumber.matches("[0-9]+")) {
            driverInputs[0] = firstName;
            driverInputs[1] = lastName;
            driverInputs[2] = nationalCode;
            driverInputs[3] = phoneNumber;
            driverInputs[4] = date;
        }
        return driverInputs;
    }


    public static void addPassengers() {
        int passengerNumbers = 0;
        System.out.println("enter number of passengers u wanna add: ");
        try {
            passengerNumbers = scanner.nextInt();
            for (int i = 0; i < passengerNumbers; i++) {
                String[] driverInputs = driverAndPassengerInfos();
                Passenger passenger = new Passenger(driverInputs[0], driverInputs[1]
                        , Integer.parseInt(driverInputs[2]), Integer.parseInt(driverInputs[3])
                        , Date.valueOf(driverInputs[4]));
                passengerService.save(passenger);
            }
            System.out.println("passengers added successfully ");

        } catch (MyCustomException | SQLException ime) {
            System.out.println(ime.getMessage());
        }
        mainMenu();
    }

    public static void driverSignUpOrLogin() throws Exception {
        try {
            System.out.println("nationalCode: ");
            String nationalCode = scanner.nextLine().trim();
            List<Driver> driverList = driverService.getDriverList();
            for (Driver driver : driverList) {
                if (!nationalCode.equals(driver.getNationalCode())) {
                    String[] userInputInfo = driverAndPassengerInfos();
                    Driver newDriver = new Driver(userInputInfo[0], userInputInfo[1]
                            , Integer.parseInt(userInputInfo[2]), Integer.parseInt(userInputInfo[3])
                            , Date.valueOf(userInputInfo[4]));
                    driverService.save(newDriver);
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
        System.out.println("1) register \n" + "2) exit");
        try {
            choice = scanner.nextInt();
            if (choice == 1) {
                driverSignUpOrLogin();
            } else if (choice == 2) {
                mainMenu();
            } else {
                System.out.println("only enter 1 or 2 ");
                subMenuDriver();
            }
        } catch (Exception ime) {
            System.out.println("only integers valid " + ime.getMessage());
            subMenuDriver();
        }
    }

    public static void passengerSignUpOrLogin() throws Exception {
        try {
            System.out.println("nationalCode: ");
            String nationalCode = scanner.nextLine().trim();
            if (isValidNationalCode(nationalCode)) {
                Passenger passengerFound = passengerService.findByNationalCode(Integer.parseInt(nationalCode));
                if (passengerFound == null) {
                    String[] userInputInfo = driverAndPassengerInfos();
                    Passenger newPassenger = new Passenger(userInputInfo[0], userInputInfo[1]
                            , Integer.parseInt(userInputInfo[2]), Integer.parseInt(userInputInfo[3])
                            , Date.valueOf(userInputInfo[4]));
                    passengerService.save(newPassenger);
                }
               /* List<Passenger> passengerList = passengerService.showPassengerList();
                for (Passenger passenger : passengerList) {
                    if (!nationalCode.equals(passenger.getNationalCode())) {
                        String[] userInputInfo = driverAndPassengerInfos();
                        Passenger newPassenger = new Passenger(userInputInfo[0], userInputInfo[1]
                                , Integer.parseInt(userInputInfo[2]), Integer.parseInt(userInputInfo[3])
                                , Date.valueOf(userInputInfo[4]));
                        passengerService.save(newPassenger);
                        passengerService.addToPassengerList(passenger);
                    } else {
                        System.out.println("you are already registered ");
                    }
                }*/
            }
        } catch (Exception e) {
            throw new MyCustomException("try again", e);
        }
        subMenuPassenger();
    }

    private static boolean isValidNationalCode(String nationalCode) throws MyCustomException {
        if (nationalCode.matches("[0-9]+")) {
            return true;
        }
        throw new MyCustomException("national code is not valid");
    }

    private static boolean isValidDate(String dateStr) throws MyCustomException {
        try {
            Date date = Date.valueOf(dateStr);
        } catch (Exception e) {
            throw new MyCustomException("date format is incorrect");
        }
        return true;
    }

    public static void subMenuPassenger() throws Exception {
        int choice = 0;
        System.out.println("1) register \n" + "2) exit");
        try {
            choice = scanner.nextInt();
            if (choice == 1) {
                passengerSignUpOrLogin();
            } else if (choice == 2) {
                mainMenu();
            } else {
                System.out.println("only enter 1 or 2 ");
                subMenuPassenger();
            }
        } catch (InputMismatchException ime) {
            ime.getStackTrace();
            System.out.println("exception happens" + ime.getMessage());
            subMenuPassenger();
        }
    }

    public static void showDriverList() {
        try {
            List<Driver> driverList = driverService.getDriverList();
            for (Driver driver : driverList) {
                System.out.println(driver);
            }
        } catch (NullPointerException | SQLException excep) {
            System.out.println(" exception happens" + excep.getMessage());
        }
        mainMenu();
    }

    public static void showPassengerList() {
        try {
            List<Passenger> passengerList = passengerService.getPassengerList();
            for (Passenger passenger : passengerList) {
                System.out.println(passenger.toString());
            }
        } catch (SQLException excp) {
            System.out.println("exception happens" + excp.getMessage());
        }
        mainMenu();
    }
}
