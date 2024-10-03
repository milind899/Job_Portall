import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class UserDashboard extends JFrame {
    private JList<String> jobList;
    private JComboBox<String> companyFilter;
    private JTextField salaryFilter;
    private JCheckBox javaCheckBox, pythonCheckBox, reactCheckBox;
    private DefaultListModel<String> jobListModel;
    private JTextArea jobDetailsArea;  // Area to display selected job details
    private JButton applyButton;         // Button to apply for the selected job

    public UserDashboard() {
        setTitle("User Dashboard - Apply for Jobs");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Filters
        JPanel filterPanel = new JPanel(new GridLayout(3, 2));
        filterPanel.add(new JLabel("Filter by Company:"));

        companyFilter = new JComboBox<>(new String[]{"All", "Google", "Apple", "Microsoft", "TCS"});
        filterPanel.add(companyFilter);

        filterPanel.add(new JLabel("Filter by Minimum Salary:"));
        salaryFilter = new JTextField();
        filterPanel.add(salaryFilter);

        filterPanel.add(new JLabel("Filter by Skills:"));
        JPanel skillsPanel = new JPanel();
        javaCheckBox = new JCheckBox("Java");
        pythonCheckBox = new JCheckBox("Python");
        reactCheckBox = new JCheckBox("React");
        skillsPanel.add(javaCheckBox);
        skillsPanel.add(pythonCheckBox);
        skillsPanel.add(reactCheckBox);
        filterPanel.add(skillsPanel);

        add(filterPanel, BorderLayout.NORTH);

        // Job List
        jobListModel = new DefaultListModel<>();
        jobList = new JList<>(jobListModel);
        jobList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jobList.addListSelectionListener(e -> loadJobDetails()); // Load details on selection
        add(new JScrollPane(jobList), BorderLayout.WEST);

        // Job Details Area
        jobDetailsArea = new JTextArea();
        jobDetailsArea.setEditable(false);
        add(new JScrollPane(jobDetailsArea), BorderLayout.CENTER);

        // Apply Button
        applyButton = new JButton("Apply for Selected Job");
        applyButton.addActionListener(new ApplyAction());
        add(applyButton, BorderLayout.SOUTH);

        // Load and display all jobs
        loadAndDisplayJobs();

        // Add filters action
        companyFilter.addActionListener(e -> loadAndDisplayJobs());
        salaryFilter.addActionListener(e -> loadAndDisplayJobs());
        javaCheckBox.addActionListener(e -> loadAndDisplayJobs());
        pythonCheckBox.addActionListener(e -> loadAndDisplayJobs());
        reactCheckBox.addActionListener(e -> loadAndDisplayJobs());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Load and display jobs based on filters
    private void loadAndDisplayJobs() {
        jobListModel.clear();
        List<Job> jobs = JobStorage.getJobs();
        List<Job> filteredJobs = jobs;

        // Filter by company
        String selectedCompany = companyFilter.getSelectedItem().toString();
        if (!selectedCompany.equals("All")) {
            filteredJobs = filteredJobs.stream()
                    .filter(job -> job.getCompany().equals(selectedCompany))
                    .collect(Collectors.toList());
        }

        // Filter by salary
        String minSalaryStr = salaryFilter.getText();
        if (!minSalaryStr.isEmpty()) {
            try {
                int minSalary = Integer.parseInt(minSalaryStr);
                filteredJobs = filteredJobs.stream()
                        .filter(job -> Integer.parseInt(job.getSalary()) >= minSalary)
                        .collect(Collectors.toList());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid salary input", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Filter by skills
        if (javaCheckBox.isSelected()) {
            filteredJobs = filteredJobs.stream()
                    .filter(job -> job.getSkills().contains("Java"))
                    .collect(Collectors.toList());
        }
        if (pythonCheckBox.isSelected()) {
            filteredJobs = filteredJobs.stream()
                    .filter(job -> job.getSkills().contains("Python"))
                    .collect(Collectors.toList());
        }
        if (reactCheckBox.isSelected()) {
            filteredJobs = filteredJobs.stream()
                    .filter(job -> job.getSkills().contains("React"))
                    .collect(Collectors.toList());
        }

        for (Job job : filteredJobs) {
            jobListModel.addElement(job.toString());
        }
    }

    // Load job details for the selected job
    private void loadJobDetails() {
        String selectedJobString = jobList.getSelectedValue();
        if (selectedJobString != null) {
            List<Job> jobs = JobStorage.getJobs();
            for (Job job : jobs) {
                if (job.toString().equals(selectedJobString)) {
                    jobDetailsArea.setText("Company: " + job.getCompany() +
                            "\nSalary: " + job.getSalary() + " INR" +
                            "\nDescription: " + job.getDescription() +
                            "\nSkills Required: " + job.getSkills());
                }
            }
        }
    }

    // Handle job application
    private class ApplyAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedJob = jobList.getSelectedValue();
            if (selectedJob != null) {
                new JobApplicationForm(UserDashboard.this, selectedJob); // Open application form
            } else {
                JOptionPane.showMessageDialog(null, "No job selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
