package pl.mbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mbm.exception.ValidationException;
import pl.mbm.model.dto.UserJTable;
import pl.mbm.model.dto.UserRegistrationForm;
import pl.mbm.response.CorrectResponse;
import pl.mbm.response.ErrorResponse;
import pl.mbm.response.Response;
import pl.mbm.service.UserService;

@Controller
public class RegistrationController {

	private static final String USER_CREATED_MESSAGE = "User has been created";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Response register(
			@RequestBody UserRegistrationForm userRegistrationForm) {
		UserJTable user = userService.registerUser(userRegistrationForm);
		return new CorrectResponse(200, user, USER_CREATED_MESSAGE);
	}

	@ExceptionHandler(ValidationException.class)
	@ResponseBody
	public ErrorResponse handleBadRequest(ValidationException e) {
		return new ErrorResponse(e.getCode(), e.getCustomMessage());
	}

}
