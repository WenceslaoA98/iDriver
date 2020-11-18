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

import pe.edu.login.idriver.entity.Person;
import pe.edu.login.idriver.service.PersonService;

@Controller
@RequestMapping("idriver/person")
@SessionAttributes("person")
public class PersonController {
     @Autowired PersonService personService;
     
     
     @GetMapping("/start")
 	public String listAll(Model model) {
 		try {
 			List<Person> persons = personService.readAll();
 			model.addAttribute("persons", persons);
 		} catch (Exception e) {
 		}
 		return "/person/start";
 	}

 	@GetMapping("/new")
 	public String newPerson(Model model) {
 		Person person = new Person();
 		model.addAttribute("person", person);
 		
 		try {
 		
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	
 		return "/person/login";
 	}
 	
 	@PostMapping("/save")
 	public String savePerson(@ModelAttribute("person") Person person, Model model, SessionStatus status) {
 		try {
 			personService.create(person);
 			status.setComplete();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/idriver/person/start";
 	}
 	
 	@GetMapping("/edit/{id}")
 	public String editPerson(@PathVariable("id") Integer id, Model model) {
 		try {
 			Optional<Person> optional = personService.findById(id);
 			if(optional.isPresent()) {
 				model.addAttribute("person", optional.get());	
 			} else {
 				return "redirect:/idriver/person/start";
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "/idriver/edit";
 	}
 	
 	@GetMapping("/del/{id}")
 	public String delPerson(@PathVariable("id") Integer id,  Model model) {
 		try {
 			Optional<Person> optional = personService.findById(id);
 			if (optional.isPresent()) {
 				personService.deleteById(id);
 			} else {
 				return "redirect:/idriver/person/start";
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		return "redirect:/karwas/idriver/start";
 	}	
}
