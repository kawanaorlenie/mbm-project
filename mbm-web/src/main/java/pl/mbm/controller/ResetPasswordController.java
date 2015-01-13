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
		//TODO: ta funkcja nizej moglaby zwracac passwordRecoveryForm
		resetPasswordService.beginProcedure(passwordRecoveryForm.getEmail());
		return new CorrectResponse(200, null,
				"Email has been sent to: "+passwordRecoveryForm.getEmail());
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ModelAndView showForm(@RequestParam String email,
			@RequestParam String uuid) {
		return new ModelAndView("resetPassword", "passwordsForm", new PasswordsForm(email, uuid));
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	@ResponseBody
	public Response resetPassword(@Valid @RequestBody PasswordsForm passwordsForm) {
		resetPasswordService.changePassword(passwordsForm);
		return new CorrectResponse(200, null,"Password has been changed successfully");
	}
}
