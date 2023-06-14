package type;

public enum Rate {
    POOR("Kem"),
    WEAK("Yeu"),
    AVG("Trung Binh"),
    GOOD("Kha"),
    EXCELLENT("Gioi"),
    OUTSTANDING("Xuat sac");


    private String alias;

    Rate(String value) {
        this.alias = value;
    }

    public String getAlias() {
        return alias;
    }

    public static Rate getRate(double gpa) {
        if (gpa < 3) {
            return POOR;
        } else if (gpa < 5) {
            return WEAK;
        } else if (gpa < 6.5) {
            return AVG;
        } else if (gpa < 7.5) {
            return GOOD;
        } else if (gpa < 9) {
            return EXCELLENT;
        } else {
            return OUTSTANDING;
        }
    }
}
