package pe.edu.login.idriver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visualizar")
public class VisualizacionesController {
	
	@GetMapping("permisos")
	public String permisos()
	{
		return "permisos";
	}
	
	@GetMapping("permisosespeciales")
	public String permisosespeciales()
	{
		return "permisosespeciales";
	}
	
	@GetMapping("centrosseguros")
	public String centrosseguros()
	{
		return "centrosseguros";
	}
	
	@GetMapping("centrossoat")
	public String centrossoat()
	{
		return "centrossoat";
	}
	
	
}