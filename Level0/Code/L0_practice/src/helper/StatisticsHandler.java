package helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StatisticsHandler {
    /**
     * param arr              : list we wanna statistics
     * param getterKeySymbol: which is mention below
     *
     * @type T, U
     * @method getKeySymbolic => override with the getter method of Object U type
     * to get the T which is property of U so that we can declare
     * that thing we wanna statistics by percent is that property
     */
    public interface KeySymbolic<T, U> {
        T getKeySymbolic(U item);
    }

    public static <T, U> Map<T, Double> percentageStatistics(ArrayList<U> arr, KeySymbolic<T, U> getterKeySymbol) {
        Map<T, Double> elFrequency = new HashMap<>();
        for (U el : arr) {
            T key = getterKeySymbol.getKeySymbolic(el);
            elFrequency.put(key, elFrequency.getOrDefault(key, 0.0) + 1);
        }
        int total = arr.size();
        Map<T, Double> percentageStatisticsBoard = new HashMap<>();
        for (T key : elFrequency.keySet()) {
            Double frequency = elFrequency.get(key);
            double percentage = frequency / total * 100;
            percentageStatisticsBoard.put(key, percentage);
        }
        return percentageStatisticsBoard;
    }

    public static <T, U> void printPercentageStatistics(ArrayList<U> arr, KeySymbolic<T, U> getterKeySymbol) {
        Map<T, Double> data = percentageStatistics(arr, getterKeySymbol);
        data.forEach((key, value) -> System.out.println(key + ": " + value + "%"));
    }
}
