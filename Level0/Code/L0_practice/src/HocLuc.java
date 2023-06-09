public enum HocLuc {
    KEM("Kem"),
    YEU("YEU"),
    TRUNGBINH("Trung Binh"),
    KHA("Kha"),
    GIOI("Gioi"),
    XUATSAC("Xuat sac");


    private String alias;

    HocLuc(String value) {
        this.alias = value;
    }

    public String getAlias() {
        return alias;
    }

    public static HocLuc getHocLuc(double gpa) {
        if (gpa < 3) {
            return KEM;
        } else if (gpa < 5) {
            return YEU;
        } else if (gpa < 6.5) {
            return TRUNGBINH;
        } else if (gpa < 7.5) {
            return KHA;
        } else if (gpa < 9) {
            return GIOI;
        } else {
            return XUATSAC;
        }
    }
}
