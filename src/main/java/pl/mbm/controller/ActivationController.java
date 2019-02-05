package pl.mbm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.mbm.service.ActivationService;
import pl.mbm.service.dto.UserJTable;

@Controller
public class ActivationController {

	@Autowired
	private ActivationService activationService;

	@RequestMapping(value = "/activation", method = RequestMethod.GET)
	public ModelAndView activateUser(@RequestParam String name,
			@RequestParam String code) {
		UserJTable user = activationService.activateUser(name, code);
		return new ModelAndView("login", "activated", true);
	}
}
