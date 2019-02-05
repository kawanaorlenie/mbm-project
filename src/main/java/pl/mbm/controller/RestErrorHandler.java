package pl.mbm.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import pl.mbm.service.dto.ValidationErrorDTO;

@ControllerAdvice
public class RestErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ValidationErrorDTO processValidationError(
			MethodArgumentNotValidException ex) {
		return processFieldErrors(ex.getBindingResult());
	}

	private ValidationErrorDTO processFieldErrors(BindingResult result) {
		ValidationErrorDTO dto = new ValidationErrorDTO();

		List<FieldError> fieldErrors = result.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			dto.addFieldError(fieldError.getField(), localizedErrorMessage);
		}
		
		List<ObjectError> globalErrors = result.getGlobalErrors();
		for (ObjectError objectError : globalErrors) {
			String localizedErrorMessage = resolveLocalizedErrorMessage(objectError);
			dto.addGlobalError(objectError.getObjectName(), localizedErrorMessage);
		}
		return dto;
	}

	private String resolveLocalizedErrorMessage(ObjectError objectError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(objectError,
				currentLocale);
		return localizedErrorMessage;
	}

	private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError,
				currentLocale);
		return localizedErrorMessage;
	}
}