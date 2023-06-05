package validator;

public interface InputValidator<T> {
    //TODO: Return về giá trị validated hay boolean thì hợp lí hơn ??
    T validate(T input);

}
