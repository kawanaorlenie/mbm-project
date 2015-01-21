package pl.mbm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mbm.response.CorrectResponse;
import pl.mbm.response.Response;
import pl.mbm.service.UserService;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.service.dto.UserRegistrationForm;

@Controller
public class RegistrationController {

	private static final String USER_CREATED_MESSAGE = "User has been created";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public Response register(@Valid	@RequestBody UserRegistrationForm userRegistrationForm) {
		UserJTable user = userService.registerUser(userRegistrationForm);
		return new CorrectResponse(200, user, USER_CREATED_MESSAGE);
	}
	
	@RequestMapping(value = "/validate/registerForm", method = RequestMethod.POST)
	@ResponseBody
	public Response validateRegisterForm(@Valid	@RequestBody UserRegistrationForm userRegistrationForm) {
		return new CorrectResponse(200, userRegistrationForm, "All data are valid");
	}
}
