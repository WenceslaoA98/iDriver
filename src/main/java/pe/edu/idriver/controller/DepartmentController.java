package pe.edu.idriver.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.idriver.entity.Department;
import pe.edu.idriver.service.IDepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private IDepartmentService dService;
	
	@RequestMapping("/bienvenido")
	public String irDepartmentBienvenido() {
		return "bienvenido";
	}
	
	@RequestMapping("/")
	public String irDepartment(Map<String, Object> model) {
		model.put("listaDepartamentos", dService.listar());
		return "listDepartment";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("department", new Department());
		return "department";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Department objDepartment, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			return "department";
		} else {
			boolean flag = dService.insertar(objDepartment);
			if(flag) {
				return "redirect:/department/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/department/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Department> objDepartment = dService.listarId(id);
		
		if(objDepartment == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/department/listar";
		} else {
			model.addAttribute("department", objDepartment);
			return "department";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				dService.eliminar(id);
				model.put("listaDepartamentos", dService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaDepartamentos", dService.listar());
		}
		return "listDepartment";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaDepartamentos", dService.listar());
		return "listDepartment";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Department department)
	throws ParseException
	{
		dService.listarId(department.getIdDepartment());
		return "listDepartment";
	}
	
	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Department department)
	throws ParseException 
	{
		List<Department> listaDepartamentos;
		department.setNameDepartment(department.getNameDepartment());
		listaDepartamentos = dService.findByName(department.getNameDepartment());
		
		if(listaDepartamentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDepartamentos", listaDepartamentos);
		return "listDepartment";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("department", new Department());
		return "buscarDepartamento";
	}
	
}
