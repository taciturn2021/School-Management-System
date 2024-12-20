package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static int parseStudentID(String text) throws InvalidInputException {
        try {
            return Integer.parseInt(text);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Student ID must be a valid integer.");
        }
    }

    public static Date parseDateOfBirth(String text) throws InvalidInputException {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(text);
        } catch (java.text.ParseException e) {
            throw new NumberFormatException("Date of Birth must be a valid integer.");
        }
    }

    public static int parseTeacherID(String text) throws InvalidInputException {
        try {
            return Integer.parseInt(text);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Teacher ID must be a valid integer.");
        }
    }

    public static int parseStaffID(String text) throws InvalidInputException {
        try {
            return Integer.parseInt(text);
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Staff ID must be a valid integer.");
        }
    }

    public static boolean nullCheck(Object object) throws InvalidInputException {
        try{
            return object != null;
        }
        catch (Exception e){
            throw new InvalidInputException("Object is null");
        }
    }

}