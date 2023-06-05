import validator.AppValidationRule;

import static validator.AppValidationRule.getInput;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        String name = getInput("Nhap", String::trim, AppValidationRule::NameRule);
        int enroll = getInput("Nhap nam", Integer::parseInt, AppValidationRule::EnrollYearRule);
        //custom
        int test = getInput("Nhap so:", Integer::parseInt, input -> input > 100 ? input : null);
    }
}