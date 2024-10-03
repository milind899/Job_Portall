import javax.swing.*;
import java.awt.*;

public class Theme {
    public static void applyDarkTheme() {
        try {
            // Set the Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Customize Nimbus colors for a dark theme
            UIManager.put("control", new Color(50, 50, 50));              // Background of components
            UIManager.put("info", new Color(50, 50, 50));                 // Background of tooltips
            UIManager.put("nimbusBase", new Color(35, 35, 35));           // Base color for controls
            UIManager.put("nimbusAlertYellow", new Color(255, 255, 0));   // Warning colors
            UIManager.put("nimbusDisabledText", new Color(100, 100, 100)); // Disabled text
            UIManager.put("nimbusFocus", new Color(115, 164, 209));       // Focus ring color
            UIManager.put("nimbusGreen", new Color(176, 179, 50));        // Success color
            UIManager.put("nimbusInfoBlue", new Color(38, 139, 210));     // Info color
            UIManager.put("nimbusLightBackground", new Color(40, 40, 40)); // Background of panels
            UIManager.put("nimbusOrange", new Color(191, 98, 4));         // Orange highlight
            UIManager.put("nimbusRed", new Color(169, 46, 34));           // Error color
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255)); // Selected text
            UIManager.put("nimbusSelectionBackground", new Color(75, 110, 175)); // Highlight color

            // Customize default text colors
            UIManager.put("text", new Color(230, 230, 230)); // Main text color

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
