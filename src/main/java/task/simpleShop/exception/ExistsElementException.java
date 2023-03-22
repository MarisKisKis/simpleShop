package task.simpleShop.exception;

public class ExistsElementException extends RuntimeException {
    public ExistsElementException(String error) {
        super(error);
    }
}
