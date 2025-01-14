package com.maingame.Service;

import com.maingame.Data.CareerFaker;
import com.maingame.Data.SchoolFaker;
import com.maingame.Model.Career.Student;

import java.util.List;

public class CareerService {
    private final CareerFaker careerRepository;
    private final SchoolFaker schoolRepository;

    public CareerService() {
        this.careerRepository = new CareerFaker();
        this.schoolRepository = new SchoolFaker();
    }

    public List<String> getAvailableDegrees() {
        return schoolRepository.schoolProvider().getAllDegrees();
    }

    public String getSchoolName(Student.StudentType studentType) {
        return schoolRepository.schoolProvider().getSchoolName(studentType);
    }

    public List<String> getAvailableJobs() {
        return careerRepository.careerProvider().getAllCareers();
    }

    public Integer getSalary(String jobName) {
        return careerRepository.careerProvider().getSalary(jobName);
    }

    public String getCompany(String jobName) {
        return careerRepository.careerProvider().getCompanyName(jobName);
    }
}
