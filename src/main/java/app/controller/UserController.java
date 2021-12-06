package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/app")
public class UserController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public String admin() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "/qlkh/dashboard", method = RequestMethod.GET)
	public String qlkh() {
		return "qlkh/dashboard";
	}

	@RequestMapping(value = "/cskh/dashboard", method = RequestMethod.GET)
	public String cskh() {
		return "cskh/dashboard";
	}

	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public String client() {
		return "client/dashboard";
	}
}
