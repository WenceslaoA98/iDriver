package pe.edu.idriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/users")
	public String irUsers() {
		return "users";
	}
	
	@RequestMapping("/bienvenidoadmin")
	public String irBienvenidoAdmin() {
		return "bienvenidoadmin";
	}
	
	
}
