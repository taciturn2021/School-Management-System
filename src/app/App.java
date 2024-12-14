package app;

import gui.CourseAddForm;
import gui.CourseSearchForm;
import gui.GUIWindow;
import gui.StudentAdmissionForm;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              new StudentAdmissionForm();
            }
        });
    }
}
