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
import pe.edu.idriver.entity.Province;
import pe.edu.idriver.service.IDepartmentService;
import pe.edu.idriver.service.IProvinceService;

@Controller
@RequestMapping("/province")
public class ProvinceController {

	@Autowired
	private IProvinceService pService;
	
	@Autowired
	private IDepartmentService dService;
	
	@RequestMapping("/")
	public String irProvince(Map<String, Object> model) {
		model.put("listaProvincias", pService.listar());
		return "listProvince";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaDepartamentos", dService.listar());
		model.addAttribute("department", new Department());
		model.addAttribute("province", new Province());
		return "province";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Province objProvince, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaDepartamentos", dService.listar());
			return "province";
		} else {
			boolean flag = pService.insertar(objProvince);
			if(flag) {
				return "redirect:/province/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/province/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Province> objProvince = pService.listarId(id);
		
		if(objProvince == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/province/listar";
		} else {
			model.addAttribute("listaDepartamentos", dService.listar());
			if(objProvince.isPresent())
				objProvince.ifPresent(o -> model.addAttribute("province",o));
			return "province";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				pService.eliminar(id);
				model.put("listaProvincias", pService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaProvincias", pService.listar());
		}
		return "listProvince";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaProvincias", pService.listar());
		return "listProvince";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Province province)
	throws ParseException
	{
		pService.listarId(province.getIdProvince());
		return "listProvince";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Province province)
	throws ParseException 
	{
		List<Province> listaProvincias;
		province.setNameProvince(province.getNameProvince());
		listaProvincias = pService.buscarNombre(province.getNameProvince());
		
		if(listaProvincias.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaProvincias", listaProvincias);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("province", new Province());
		return "buscar";
	}
}
