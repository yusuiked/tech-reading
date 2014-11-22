package sample.customer.biz.service;

public class DataNotFoundException extends Exception {

    private static final long serialVersionUID = 4427765306184000294L;

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataNotFoundException(Throwable cause) {
        super(cause);
    }
}
