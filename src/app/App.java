package app;

import com.sun.source.tree.NewArrayTree;
import gui.*;

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
