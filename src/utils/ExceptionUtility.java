package utils;

public class ExceptionUtility {

    public static class InvalidInputException extends Exception {
        public InvalidInputException(String message) {
            super(message);
        }
    }

    public static class NumberFormatException extends InvalidInputException {
        public NumberFormatException(String message) {
            super(message);
        }
    }

    public static int parseCredits(String text) throws InvalidInputException {
        try {
            return Integer.parseInt(text);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Credits must be a valid integer.");
        }
    }
}