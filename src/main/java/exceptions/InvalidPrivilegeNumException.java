package exceptions;

public class InvalidPrivilegeNumException extends Exception{
    public InvalidPrivilegeNumException() {
    }

    public InvalidPrivilegeNumException(String message) {
        super(message);
    }
}
