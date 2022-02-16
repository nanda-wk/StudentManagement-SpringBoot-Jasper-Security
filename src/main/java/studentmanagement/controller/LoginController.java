package studentmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

//	@GetMapping("/")
//	public ModelAndView index() {
//		return new ModelAndView("LGN001", "bean", new LoginBean());
//	}
	
	@GetMapping("/")
	public String login() {
		return "LGN001";
	}
}
