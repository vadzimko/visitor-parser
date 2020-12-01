package exceptions;

public class ParseException extends Exception {

    public ParseException(String message, int position) {
        super("Error at position  " + position + ": " + message);
    }

    public ParseException(String message) {
        super("Invalid tokens order:  " + message);
    }
}
