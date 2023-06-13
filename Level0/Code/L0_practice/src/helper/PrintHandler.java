package helper;

import java.util.ArrayList;
import java.util.List;

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
}
