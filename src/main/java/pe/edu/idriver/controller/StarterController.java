package pe.edu.login.idriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/idriver")
public class StarterController {
	
	@GetMapping
	public String starter()
	{
		return "index";
	}
	
	@GetMapping("login")
	public String login()
	{
		return "login";
	}
	
	
	
	
}
