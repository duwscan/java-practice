package helper;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PrintHandler {
    public interface ListByCondition<T> {
        boolean condition(T nodeInList);
    }

    public static <T> void printList(List<T> list) {
        for (T obj : list) {
            System.out.println(obj);
        }
    }

    public static <T> void printByCondition(ListByCondition<T> condition, ArrayList<T> arr) {
        for (T el : arr) {
            if (condition.condition(el)) {
                System.out.println(el);
            }
        }
    }

    public static boolean printWrongFormatInput(Exception e) {
        if (e == null) {
            if (e instanceof NumberFormatException) {
                System.out.println("Ban can nhap so!");
            }
            // write more if it needed
        }

        System.out.println("Your input is inappropriate!!");
        return true;
    }

    public static void printLine() {
        System.out.println("=============================================================================");
    }
}
