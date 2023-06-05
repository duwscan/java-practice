import java.util.Date;

public class SinhVien extends Nguoi {
    private String studentId;
    private String universityName;
    private int enrollYear;
    private int gpa;

    public SinhVien(String name, Date dateOfBirth, String address, Double height, Double weight, String studentId, String universityName, int enrollYear, int gpa) {
        super(name, dateOfBirth, address, height, weight);
        this.studentId = "SV" + enrollYear + this.getId();
        this.universityName = universityName;
        this.enrollYear = enrollYear;
        this.gpa = gpa;
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

    @Override
    public String toString() {
        return "SinhVien{" +
                "studentId='" + studentId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", enrollYear=" + enrollYear +
                ", gpa=" + gpa +
                '}';
    }

    public void setEnrollYear(int enrollYear) {
        this.enrollYear = enrollYear;
    }

    public int getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }
}
