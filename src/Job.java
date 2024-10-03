public class Job {
    private String company;
    private String salary;
    private String description;
    private String skills;

    public Job(String company, String salary, String description, String skills) {
        this.company = company;
        this.salary = salary;
        this.description = description;
        this.skills = skills;
    }

    public String getCompany() {
        return company;
    }

    public String getSalary() {
        return salary;
    }

    public String getDescription() {
        return description;
    }

    public String getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return company + " | " + salary + " INR | " + description + " | Skills: " + skills;
    }
}
