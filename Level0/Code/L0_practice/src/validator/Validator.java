package validator;

import java.time.LocalDate;
import java.util.Scanner;

import static helper.PrintHandler.printWrongFormatInput;

public class Validator implements Rule {


    public static <T> T getInput(String prompt, InputParser<T> parser, InputRule<T> validator) {
        Scanner scanner = new Scanner(System.in);
        Exception error = null;
        T input;
        do {
            System.out.println(prompt);
            try {
                input = parser.parse(scanner.nextLine());
            } catch (Exception e) {
                input = null;
                error = e;
            }
        } while ((input == null || !validator.rule(input)) && printWrongFormatInput(error));
        return input;
    }

    public static boolean nameRule(String input) {
        return input.length() < MAX_NAME_LENGTH;
    }

    public static boolean enrollYearRule(int input) {
        return input > MIN_START_YEAR;
    }


    public static boolean dateOfBirthRule(LocalDate input) {
        return input.getYear() > MIN_START_YEAR;
    }

    public static boolean addressRule(String input) {
        return input.length() < MAX_ADDRESS_LENGTH;
    }

    public static boolean heightRule(double input) {
        return input >= MIN_HEIGHT && input <= MAX_HEIGHT;
    }

    public static boolean weightRule(double input) {
        return input >= MIN_WEIGHT && input <= MAX_WEIGHT;
    }

    public static boolean schoolNameRule(String input) {
        return input.length() < MAX_SCHOOL_LENGTH;
    }

    public static boolean gpaRule(double input) {
        return input >= MIN_GPA && input <= MAX_GPA;
    }

    public static boolean updateStudentRule(int choice) {
        return choice > 0 && choice <= UPDATABLE_PROPERTIES.length;
    }

    public static boolean rateRule(int choice) {
        return choice > 0 && choice <= RATE_TYPE.length;
    }

    public static boolean studentIdRule(String input) {
        return input.length() < 10 && input.length() > 0;
    }
}
