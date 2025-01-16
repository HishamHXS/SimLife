package com.maingame.service;

import com.maingame.data.CareerFaker;
import com.maingame.data.SchoolFaker;
import com.maingame.model.career.Student;

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
