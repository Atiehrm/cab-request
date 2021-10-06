package Exception;

public class MyCustomException extends Exception{

    public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyCustomException(String message) {
        super(message);
    }
}
