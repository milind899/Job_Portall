import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String role;
    private String correctUsername;
    private String correctPassword;

    public LoginForm(String role, String correctUsername, String correctPassword) {
        this.role = role;
        this.correctUsername = correctUsername;
        this.correctPassword = correctPassword;

        setTitle(role + " Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Labels and fields
        JLabel userLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        // Add components
        add(userLabel);
        add(usernameField);
        add(passLabel);
        add(passwordField);
        add(loginButton);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Handle login
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals(correctUsername) && password.equals(correctPassword)) {
                JOptionPane.showMessageDialog(null, role + " logged in successfully!");
                if (role.equals("Admin")) {
                    new AdminDashboard();  // Open Admin Dashboard
                } else {
                    new UserDashboard();   // Open User Dashboard
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
