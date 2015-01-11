package pl.mbm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import pl.mbm.response.CorrectResponse;
import pl.mbm.response.Response;
import pl.mbm.service.ResetPasswordService;
import pl.mbm.service.dto.PasswordRecoveryForm;
import pl.mbm.service.dto.PasswordsForm;

@Controller
public class ResetPasswordController {

	@Autowired
	private ResetPasswordService resetPasswordService;

	@ResponseBody
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public Response sendMailWithCode(
			@Valid @RequestBody PasswordRecoveryForm passwordRecoveryForm) {
		System.out.println("AAAAAAAAAAAAAAAAAAAA: "
				+ passwordRecoveryForm.getEmail());
		resetPasswordService.beginProcedure(passwordRecoveryForm.getEmail());
		return new CorrectResponse(200, null,
				"Procedure has been initialized successfully");
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView showForm(@RequestParam String email,
			@RequestParam String uuid) {
		PasswordsForm passwordsForm = resetPasswordService
				.generatePasswordsForm(email, uuid);
		return new ModelAndView("resetPassword", "passwords", passwordsForm);
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Response resetPassword(@Valid PasswordsForm passwordsForm) {
		resetPasswordService.changePassword(passwordsForm);
		// TODO dokoncz to
		return new CorrectResponse(200, null,
				"Password has been changed successfully");
	}
}
