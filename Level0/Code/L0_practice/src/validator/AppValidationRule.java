package validator;

import java.util.Scanner;

public class AppValidationRule {
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

    private static boolean printWrongFormatInput() {
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
    public static <T> T getInput(String message, InputValidator<T> validator) {
        // to validate String type
        Scanner scanner = new Scanner(System.in);
        T input;
        do {
            System.out.println(message);
            input = validator.validate((T) scanner.nextLine());
        } while (input == null && printWrongFormatInput());
        return input;
    }

    // validate Number Type
    public static <T> T getInput(String prompt, InputParser<T> parser, InputValidator<T> validator) {
        Scanner scanner = new Scanner(System.in);
        T input;
        do {
            System.out.print(prompt);
            try {
                input = validator.validate(parser.parse(scanner.nextLine()));
            } catch (Exception e) {
                input = null;
            }
        } while (input == null && printWrongFormatInput());
        return input;
    }

    public static String NameRule(String input) {
        return (input.length() < MAX_NAME_LENGTH) ? input : null;
    }

    public static Integer EnrollYearRule(int input) {
        return input > 1900 ? input : null;
    }
}