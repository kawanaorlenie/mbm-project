package pl.mbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mbm.constant.URLs;
import pl.mbm.response.CorrectResponse;
import pl.mbm.response.ErrorResponse;
import pl.mbm.response.Response;
import pl.mbm.service.dto.UserRegistrationForm;
import pl.mbm.service.validator.RegistrationValidator;

@Controller
@RequestMapping(URLs.VALIDATE_REGISTER_FORM)
public class RegistrationValidationController {

	@Autowired
	private RegistrationValidator registrationValidator;

	@RequestMapping(value = "/name", method = RequestMethod.POST)
	@ResponseBody
	public Response validateName(@RequestBody UserRegistrationForm user) {
		if (registrationValidator.nameFormatCorrect(user.getName()))
			if (registrationValidator.nameExists(user.getName()))
				return new ErrorResponse(1234, "Name already exists");
			else
				return new CorrectResponse(200, null, "Name format is correct");
		else
			return new ErrorResponse(1234, "Name format is not correct");
	}

	@RequestMapping("/password")
	@ResponseBody
	public Response validatePassword(@RequestBody UserRegistrationForm user) {
		if (registrationValidator.passwordFormatCorrect(user.getPassword()))
			return new CorrectResponse(200, null, "Password format is correct");
		else
			return new ErrorResponse(1234, "Wrong password format");
	}

	@RequestMapping("/confirmPassword")
	@ResponseBody
	public Response validateConfirmPassword(
			@RequestBody UserRegistrationForm user) {
		if (registrationValidator.passwordsMismatch(user.getPassword(),
				user.getConfirmPassword()))
			return new ErrorResponse(1234, "Passwords must be tha same");
		return new CorrectResponse(200, null, "bla bla");

	}

	@RequestMapping("/email")
	@ResponseBody
	public Response validateEmail(@RequestBody UserRegistrationForm user) {
		if (registrationValidator.emailFormatCorrect(user.getEmail()))
			if (registrationValidator.emailExists(user.getEmail()))
				return new ErrorResponse(1234, "email already exists");
			else
				return new CorrectResponse(200, null, "email format is correct");
		else
			return new ErrorResponse(1234, "email format is not correct");
	}

}
