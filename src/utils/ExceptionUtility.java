package utils;

import models.University;
import models.Student;
import models.Teacher;
import models.AdministrativeStaff;

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

    public static class DuplicateIDException extends InvalidInputException {
        public DuplicateIDException(String message) {
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
            int id = Integer.parseInt(text);
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Student ID must be a valid integer.");
        }
    }
    // Overloaded parseStudentID method for the case where the ID must be unique
    public static int parseStudentID(String text,boolean unique) throws InvalidInputException {
        try {
            int id = Integer.parseInt(text);
            for (Student student : University.studentRepository.getAll()) {
                if (student.getStudentID() == id) {
                    throw new DuplicateIDException("Student ID must be unique.");
                }
            }
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Student ID must be a valid integer.");
        }
    }

    public static Date parseDateOfBirth(String text) throws InvalidInputException {
        try {
            return new SimpleDateFormat("dd-MM-yyyy").parse(text);
        } catch (java.text.ParseException e) {
            throw new NumberFormatException("Date of Birth must be a valid date.");
        }
    }

    public static int parseTeacherID(String text) throws InvalidInputException {
        try {
            int id = Integer.parseInt(text);
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Teacher ID must be a valid integer.");
        }
    }

    // Overloaded parseTeacherID method for the case where the ID must be unique
    public static int parseTeacherID(String text, boolean unique) throws InvalidInputException {
        try {
            int id = Integer.parseInt(text);
            for (Teacher teacher : University.teacherRepository.getAll()) {
                if (teacher.getTeacherID() == id) {
                    throw new DuplicateIDException("Teacher ID must be unique.");
                }
            }
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Teacher ID must be a valid integer.");
        }
    }
    // Normal parseTeacherID method
    public static int parseStaffID(String text) throws InvalidInputException {
        try {
            int id = Integer.parseInt(text);
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Staff ID must be a valid integer.");
        }
    }

    // Overloaded parseStaffID method for the case where the ID must be unique
    public static int parseStaffID(String text, boolean unique) throws InvalidInputException {
        try {
            int id = Integer.parseInt(text);
            for (AdministrativeStaff staff : University.administrativeStaffRepository.getAll()) {
                if (staff.getStaffID() == id) {
                    throw new DuplicateIDException("Staff ID must be unique.");
                }
            }
            return id;
        } catch (java.lang.NumberFormatException e) {
            throw new NumberFormatException("Staff ID must be a valid integer.");
        }
    }

    public static boolean nullCheck(Object object) throws InvalidInputException {
        try {
            return object != null;
        } catch (Exception e) {
            throw new InvalidInputException("Object is null");
        }
    }
}