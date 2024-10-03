import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JobApplicationForm extends JDialog {
    private JTextField nameField, educationField, skillsField;
    private JTextArea additionalInfoField;
    private JButton submitButton;

    public JobApplicationForm(JFrame parent, String jobTitle) {
        super(parent, "Apply for " + jobTitle, true);
        setLayout(new GridLayout(5, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Education:"));
        educationField = new JTextField();
        add(educationField);

        add(new JLabel("Skills:"));
        skillsField = new JTextField();
        add(skillsField);

        add(new JLabel("Additional Information:"));
        additionalInfoField = new JTextArea(3, 20);
        add(new JScrollPane(additionalInfoField));

        submitButton = new JButton("Submit Application");
        submitButton.addActionListener(new SubmitAction(jobTitle));
        add(new JLabel()); // Empty label for spacing
        add(submitButton);

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private class SubmitAction implements ActionListener {
        private String jobTitle;

        public SubmitAction(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String education = educationField.getText();
            String skills = skillsField.getText();
            String additionalInfo = additionalInfoField.getText();

            // Here you can handle the submitted application details
            String applicationDetails = "Job Title: " + jobTitle +
                    "\nName: " + name +
                    "\nEducation: " + education +
                    "\nSkills: " + skills +
                    "\nAdditional Info: " + additionalInfo;

            // Display confirmation
            JOptionPane.showMessageDialog(JobApplicationForm.this, 
                    "Application Submitted Successfully!\n\n" + applicationDetails, 
                    "Confirmation", JOptionPane.INFORMATION_MESSAGE);

            dispose(); // Close the dialog
        }
    }
}
