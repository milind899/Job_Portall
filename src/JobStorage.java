import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JobStorage {
    private static final String JOBS_FILE = "data/jobs.txt";

    public static void addJob(Job job) {
        try (FileWriter fw = new FileWriter(JOBS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(job.getCompany() + "|" + job.getSalary() + "|" + job.getDescription() + "|" + job.getSkills());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Job> getJobs() {
        List<Job> jobs = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(JOBS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    jobs.add(new Job(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
