import java.time.LocalDate;

public class SinhVien extends Nguoi {
    private String studentId;
    private String universityName;
    private int enrollYear;
    private double gpa;
    private HocLuc hocLuc;

    public HocLuc getHocLuc() {
        return hocLuc;
    }

    public void setHocLuc() {
        this.hocLuc = HocLuc.getHocLuc(this.gpa);
    }

    public SinhVien(String name, LocalDate dateOfBirth, String address, Double height, Double weight, String universityName, int enrollYear, double gpa) {
        super(name, dateOfBirth, address, height, weight);
        this.studentId = "SV" + getId();
        this.universityName = universityName;
        this.enrollYear = enrollYear;
        this.gpa = gpa;
        setHocLuc();
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


    @Override
    public String toString() {
        return "SinhVien{" + "studentId='" + studentId + '\'' + ", universityName='" + universityName + '\'' + ", enrollYear=" + enrollYear + ", gpa=" + gpa + "," + " hocluc=" + this.hocLuc.getAlias() + "} " + super.toString();
    }

    public double getPercentage() {
        return gpa * 10.0;
    }

}
