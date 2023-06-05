package validator;

public interface InputParser<T> {
    T parse(String input);
}
