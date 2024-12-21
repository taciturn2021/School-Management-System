package app;

import gui.*;
import utils.FileHandler;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
              new GUIWindow();
            }
        });
    }
}
