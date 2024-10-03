import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JTextField companyField, salaryField, descriptionField, skillsField;
    private JTextArea jobListTextArea;

    public AdminDashboard() {
        setTitle("Admin Dashboard - Post Job");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for job posting
        JPanel postJobPanel = new JPanel(new GridLayout(5, 2));
        JLabel companyLabel = new JLabel("Company:");
        JLabel salaryLabel = new JLabel("Salary (INR):");
        JLabel descriptionLabel = new JLabel("Description:");
        JLabel skillsLabel = new JLabel("Skills Required:");

        companyField = new JTextField();
        salaryField = new JTextField();
        descriptionField = new JTextField();
        skillsField = new JTextField();

        JButton postJobButton = new JButton("Post Job");
        postJobButton.addActionListener(new PostJobAction());

        postJobPanel.add(companyLabel);
        postJobPanel.add(companyField);
        postJobPanel.add(salaryLabel);
        postJobPanel.add(salaryField);
        postJobPanel.add(descriptionLabel);
        postJobPanel.add(descriptionField);
        postJobPanel.add(skillsLabel);
        postJobPanel.add(skillsField);
        postJobPanel.add(postJobButton);

        add(postJobPanel, BorderLayout.NORTH);

        // Text area to display posted jobs
        jobListTextArea = new JTextArea(10, 40);
        jobListTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(jobListTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton viewJobsButton = new JButton("View Posted Jobs");
        viewJobsButton.addActionListener(new ViewPostedJobsAction());
        add(viewJobsButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Action listener for posting a job
    private class PostJobAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String company = companyField.getText();
            String salary = salaryField.getText();
            String description = descriptionField.getText();
            String skills = skillsField.getText();

            Job newJob = new Job(company, salary, description, skills);
            JobStorage.addJob(newJob);  // Store the job in jobs.txt
            JOptionPane.showMessageDialog(null, "Job posted successfully!");

            // Clear the fields after posting
            companyField.setText("");
            salaryField.setText("");
            descriptionField.setText("");
            skillsField.setText("");
        }
    }

    // Action listener for viewing posted jobs
    private class ViewPostedJobsAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Job> jobs = JobStorage.getJobs();
            jobListTextArea.setText("");  // Clear previous text

            for (Job job : jobs) {
                jobListTextArea.append(job.toString() + "\n");
            }
        }
    }
}
