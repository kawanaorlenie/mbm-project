package pl.mbm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.mbm.model.entity.User;

@Controller
public class LogInController {

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView login(ModelMap model) {
		if (!model.containsAttribute("signUpModel"))
			model.addAttribute("signUpModel", new User());
		return new ModelAndView("login", model);
	}
}
