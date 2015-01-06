package pl.mbm.service.validator;

public interface Validator<T> {

	T validate(T object);

}
