import validator.AppValidationRule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static validator.AppValidationRule.*;

public class SinhVien extends Nguoi {
    private String studentId;
    private String universityName;
    private int enrollYear;
    private double gpa;

    public SinhVien(String name, LocalDate dateOfBirth, String address, Double height, Double weight, String universityName, int enrollYear, double gpa) {
        super(name, dateOfBirth, address, height, weight);
        this.studentId = "SV" + enrollYear + getId();
        this.universityName = universityName;
        this.enrollYear = enrollYear;
        this.gpa = gpa;
    }

    public SinhVien() {
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

    public static SinhVien createSinhVienByConsole() {
        String name = getInput("Nhap vao ten sinh vien(Ten <" + AppValidationRule.MAX_NAME_LENGTH + "):", String::trim, AppValidationRule::NameRule);
        LocalDate dob = getInput("Nhap vao ngay thang nam sinh(YYYY-MM-DD)(Y >" + AppValidationRule.MIN_START_YEAR + "):", input -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date;
            try {
                date = LocalDate.parse(input, formatter);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return date;
        }, AppValidationRule::DateOfBirthRule);
        String address = getInput("Nhap vao dia chi (Chuoi <" + AppValidationRule.MAX_ADDRESS_LENGTH + "):", String::trim, AppValidationRule::AddressRule);
        Double height = getInput("Nhap vao chieu cao don vi tu cm " + AppValidationRule.MIN_HEIGHT + "-" + AppValidationRule.MAX_HEIGHT + " :", Double::parseDouble, AppValidationRule::HeightRule);
        Double weight = getInput("Nhap vao can nang don vi tu kg  " + AppValidationRule.MIN_WEIGHT + "-" + AppValidationRule.MAX_WEIGHT + " :", Double::parseDouble, AppValidationRule::WeightRule);
        String school = getInput("Nhap vao truong hoc  (Chuoi <" + AppValidationRule.MAX_SCHOOL_LENGTH + "):", String::trim, AppValidationRule::SchoolNameRule);
        int enrollYear = getInput("Nhap vao can nam bat dau hoc Y > " + AppValidationRule.MIN_START_YEAR + " :", Integer::parseInt, AppValidationRule::EnrollYearRule);
        double gpa = getInput("Nhap vao diem trung binh tich luy " + MIN_GPA + "-" + MAX_GPA + ":", Double::parseDouble, AppValidationRule::GPARule);
        return new SinhVien(name, dob, address, height, weight, school, enrollYear, gpa);
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "studentId='" + studentId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", enrollYear=" + enrollYear +
                ", gpa=" + gpa +
                "} " + super.toString();
    }
}
