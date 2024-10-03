import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoginScreen extends JFrame {

    public LoginScreen() {
        setTitle("Job Portal - Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a custom panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel("src/images/bg.png"); // Adjust the path as necessary
        backgroundPanel.setLayout(new GridLayout(3, 1));

        // Create and style buttons for Admin and User login with icons
        JButton adminLoginButton = createStyledButton("Admin Login", "src/images/admin.png");
        JButton userLoginButton = createStyledButton("User Login", "src/images/user.png");

        // Action listeners for buttons
        adminLoginButton.addActionListener(new AdminLoginAction());
        userLoginButton.addActionListener(new UserLoginAction());

        backgroundPanel.add(adminLoginButton);
        backgroundPanel.add(userLoginButton);

        add(backgroundPanel);
        setVisible(true);
    }

    // Method to create a styled button with an icon
    private JButton createStyledButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(iconPath)); // Set icon for the button
        button.setBackground(new Color(0, 150, 215));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 30));
        return button;
    }

    // Admin login
    private class AdminLoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showLoginForm("Admin", "admin", "admin123");
        }
    }

    // User login
    private class UserLoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showLoginForm("User", "user", "user123");
        }
    }

    // Show the login form for the specified role
    private void showLoginForm(String role, String username, String password) {
        // Create the login form
        JFrame loginForm = new JFrame(role + " Login");
        loginForm.setSize(300, 150);
        loginForm.setLayout(new GridLayout(3, 2));
        loginForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginForm.setLocationRelativeTo(null);

        // Username and password fields
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        // Labels
        loginForm.add(new JLabel("Username:"));
        loginForm.add(usernameField);
        loginForm.add(new JLabel("Password:"));
        loginForm.add(passwordField);

        // Login button with icon
        JButton loginButton = createStyledButton("Login", "src/images/login.png");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredUsername = usernameField.getText().trim();
                String enteredPassword = new String(passwordField.getPassword()).trim();
                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    JOptionPane.showMessageDialog(loginForm, role + " login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loginForm.dispose(); // Close the login form on success
                    // Open respective dashboard
                    if (role.equals("Admin")) {
                        new AdminDashboard().setVisible(true); // Assuming you have an AdminDashboard class
                    } else {
                        new UserDashboard().setVisible(true); // Assuming you have a UserDashboard class
                    }
                } else {
                    JOptionPane.showMessageDialog(loginForm, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loginForm.add(loginButton);
        loginForm.setVisible(true);
    }

    // Custom panel class to paint a background image
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            // Load the background image
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginScreen::new);
    }
}
