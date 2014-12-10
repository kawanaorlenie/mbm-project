package pl.mbm.validator;

public interface Validator<T> {

	boolean validate(T object);

}
