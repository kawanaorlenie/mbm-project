package pl.mbm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.mbm.model.dto.UserJTable;
import pl.mbm.service.UserService;

@Controller
@RequestMapping("/user")
public class UserManagementController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public List<UserJTable> listUsers() {
		return userService.listUsers();
	}
}
