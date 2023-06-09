package validator;

public interface InputRule<T> {
    boolean rule(T input);

}
