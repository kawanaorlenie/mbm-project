package pl.mbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mbm.model.entity.ActivationCode;
import pl.mbm.model.entity.User;
import pl.mbm.response.CorrectResponse;
import pl.mbm.response.Response;
import pl.mbm.service.ActivationService;

@Controller
public class ActivationController {

	@Autowired
	private ActivationService activationService;

	@RequestMapping(value = "/activation", method = RequestMethod.GET)
	@ResponseBody
	public Response activateUser(@ModelAttribute ActivationCode activationCode) {
		User user = activationService.activateUser(activationCode);
		return new CorrectResponse(200, user, "Account has been activated");

	}
}
