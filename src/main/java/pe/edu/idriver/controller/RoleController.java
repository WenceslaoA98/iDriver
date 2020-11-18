package pe.edu.login.idriver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.login.idriver.entity.Role;
import pe.edu.login.idriver.entity.User;
import pe.edu.login.idriver.service.RoleService;
import pe.edu.login.idriver.service.UserService;

@Controller
@RequestMapping("idriver/role")
@SessionAttributes("role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;
	
	@GetMapping("/start")
	public String listAll(Model model) {
		try {
			List<Role> roles = roleService.readAll();
			model.addAttribute("roles", roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/role/start";
	}
	
	@GetMapping("/new")
	public String newRole(Model model) {
		Role role = new Role();
		model.addAttribute("role", role);	
		try {
			List<User> users = userService.readAll();
			model.addAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/role/login";
	}
	
	@PostMapping("/save")
 	public String saveRole(@ModelAttribute("role") Role role, Model model, SessionStatus status) {
 		try {
 			roleService.create(role);
 			status.setComplete();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/idriver/role/start";
 	}
	
	@GetMapping("/edit/{id}")
	public String editRole(@PathVariable("id") Long id, Model model) {
		try {
			Optional<Role> optional = roleService.findById(id);
			if (optional.isPresent()) {
				model.addAttribute("role", optional.get());
				List<User> users = userService.readAll();
				model.addAttribute("users", users);
			} else {
				return "redirect:/idriver/role/start";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/role/edit";
	}
	
	@GetMapping("/del/{id}")
	public String delRole(@PathVariable("id") Long id,  Model model) {
		try {
			Optional<Role> optional = roleService.findById(id);
			if (optional.isPresent()) {
				roleService.deleteById(id);
			} else {
				return "redirect:/idriver/role/start";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/idriver/role/start";
	}
}
