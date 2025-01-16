package com.maingame.model.career;

public class Job extends Career {
    private int salary;
    private final String jobName;
    private final String institutionName;

    public Job(String title, int salary, String institutionName) {
        super(title);
        this.salary = salary;
        this.jobName = title;
        this.institutionName = institutionName;
    }

    public String getJobName() {
        return this.jobName;
    }
    public int getSalary() {
        return this.salary;
    }

    public void updateSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String getInstitutionName() {
        return this.institutionName ;
    }

    @Override
    public String getDetails() {
        return getTitle() + " | Salary: $" + this.salary + " | Hours: " + 40 + " per week";
    }
}
