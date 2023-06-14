package model;

import type.Rate;

import java.io.Serializable;
import java.time.LocalDate;

public class Student extends Person implements Serializable {
    private String studentId;
    private String universityName;
    private int enrollYear;
    private double gpa;
    private Rate rate;

    public Rate getRate() {
        return rate;
    }

    public void setRate() {
        this.rate = Rate.getRate(this.gpa);
    }

    public Student(String name, LocalDate dateOfBirth, String address, Double height, Double weight, String universityName, int enrollYear, double gpa) {
        super(name, dateOfBirth, address, height, weight);
        this.studentId = "SV" + getId();
        this.universityName = universityName;
        this.enrollYear = enrollYear;
        this.gpa = gpa;
        setRate();
    }

    public Student() {
        super();
    }

    public String getStudentId() {
        return studentId;
    }


    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getEnrollYear() {
        return enrollYear;
    }

    public void setEnrollYear(int enrollYear) {
        this.enrollYear = enrollYear;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "SinhVien{" + "studentId='" + studentId + '\'' + ", universityName='" + universityName + '\'' + ", enrollYear=" + enrollYear + ", gpa=" + gpa + "," + " hocluc=" + this.rate.getAlias() + "} " + super.toString();
    }
}
