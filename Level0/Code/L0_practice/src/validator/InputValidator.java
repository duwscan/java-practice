package validator;

public interface InputValidator<T> {
    boolean validate(T input);

}
