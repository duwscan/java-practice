package validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Validation {
    public static final int MAX_NAME_LENGTH = 100;
    public static final int MAX_ADDRESS_LENGTH = 300;
    public static final double MIN_HEIGHT = 50.0;
    public static final double MAX_HEIGHT = 300.0;
    public static final double MIN_WEIGHT = 5.0;
    public static final double MAX_WEIGHT = 1000.0;
    public static final int STUDENT_ID_LENGTH = 10;
    public static final int MAX_SCHOOL_LENGTH = 200;
    public static final int MIN_START_YEAR = 1900;
    public static final double MIN_GPA = 0.0;
    public static final double MAX_GPA = 10.0;
    public static final String[] UPDATABLE_PROPERTIES = {"TEN", "NGAY_SINH", "DIA_CHI", "CHIEU_CAO", "CAN_NANG", "TEN_TRUONG", "NAM_HOC", "DIEM"};
    public static final String[] HOC_LUC = {"Kem", "Yeu", "Trung Binh", "Kha", "Gioi", "Xuat sac"};

    static boolean printWrongFormatInput() {
        System.out.println("Your input is inappropriate!!");
        // return true vì chỉ khi có lỗi thì hàm này mới được call
        return true;
    }

    /**
     * This method is deprecated and should not be used anymore.
     * Use the getInput(String prompt, InputParser<T> parser, InputValidator<T> validator) instead.
     * And pass the parser as String::trim ( or other static method from String Wrapper Classes ) when in use or custom functional interface  that modify string input or just input -> input
     *
     * @deprecated This method is no longer have right logic when it potentially cause
     * a runtime exception if the type casting is not valid . it will result in a ClassCastException at runtime
     */
    @Deprecated
    public static <T> T getInput(String message, InputRule<T> validator) {
        // to validate String type
        Scanner scanner = new Scanner(System.in);
        T input;
        do {
            System.out.println(message);
            input = (T) scanner.nextLine();
        } while ((input == null || !validator.rule(input)) && printWrongFormatInput());
        return input;
    }

    // validate Number Type
    public static <T> T getInput(String prompt, InputParser<T> parser, InputRule<T> validator) {
        Scanner scanner = new Scanner(System.in);
        T input;
        do {
            System.out.println(prompt);
            try {
                input = parser.parse(scanner.nextLine());
            } catch (Exception e) {
                input = null;
            }
        } while ((input == null || !validator.rule(input)) && printWrongFormatInput());
        return input;
    }

    public static boolean NameRule(String input) {
        return input.length() < MAX_NAME_LENGTH;
    }

    public static boolean EnrollYearRule(int input) {
        return input > MIN_START_YEAR;
    }


    public static boolean DateOfBirthRule(LocalDate input) {
        return input.getYear() > MIN_START_YEAR;
    }

    public static boolean AddressRule(String input) {
        return input.length() < MAX_ADDRESS_LENGTH;
    }

    public static boolean HeightRule(double input) {
        return input >= MIN_HEIGHT && input <= MAX_HEIGHT;
    }

    public static boolean WeightRule(double input) {
        return input >= MIN_WEIGHT && input <= MAX_WEIGHT;
    }

    public static boolean SchoolNameRule(String input) {
        return input.length() < MAX_SCHOOL_LENGTH;
    }

    public static boolean GPARule(double input) {
        return input >= MIN_GPA && input <= MAX_GPA;
    }

    public static boolean UpdateSinhVienRule(int choice) {
        return choice > 0 && choice <= UPDATABLE_PROPERTIES.length;
    }

    public static boolean getChoiceHocLucRule(int choice) {
        return choice > 0 && choice <= HOC_LUC.length;
    }
}
