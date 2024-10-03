import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Theme.applyDarkTheme(); // Apply dark theme
            new LoginScreen();      // Show login screen
        });
    }
}
