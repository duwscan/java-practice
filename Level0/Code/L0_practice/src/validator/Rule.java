package validator;

public interface Rule {
    int MAX_NAME_LENGTH = 100;
    int MAX_ADDRESS_LENGTH = 300;
    double MIN_HEIGHT = 50.0;
    double MAX_HEIGHT = 300.0;
    double MIN_WEIGHT = 5.0;
    double MAX_WEIGHT = 1000.0;
    int STUDENT_ID_LENGTH = 10;
    int MAX_SCHOOL_LENGTH = 200;
    int MIN_START_YEAR = 1900;
    double MIN_GPA = 0.0;
    double MAX_GPA = 10.0;
    String[] UPDATABLE_PROPERTIES = {"TEN", "NGAY_SINH", "DIA_CHI", "CHIEU_CAO", "CAN_NANG", "TEN_TRUONG", "NAM_HOC", "DIEM"};
    String[] RATE_TYPE = {"Kem", "Yeu", "Trung Binh", "Kha", "Gioi", "Xuat sac"};
}
