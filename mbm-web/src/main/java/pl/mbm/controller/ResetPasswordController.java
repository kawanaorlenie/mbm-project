package pl.mbm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.mbm.response.Response;

@Controller
public class ResetPasswordController {

	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public Response sendMailWithCode(@RequestParam String email) {
		return null;

	}
}
