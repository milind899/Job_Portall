import java.io.*;
import java.util.*;

public class JobData {
    private static final String JOB_FILE = "data/jobs.txt";

    // Save job to file
    public static void saveJob(Job job) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOB_FILE, true))) {
            writer.write(job.getCompany() + "|" + job.getSalary() + "|" + job.getDescription() + "|" + job.getSkills());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load jobs from file
    public static List<Job> loadJobs() {
        List<Job> jobs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(JOB_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Job job = new Job(parts[0], parts[1], parts[2], parts[3]);
                jobs.add(job);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
