import validator.AppValidationRule;

import static validator.AppValidationRule.getInput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SinhVien a = SinhVien.createSinhVienByConsole();
        System.out.println(a.toString());
    }
}