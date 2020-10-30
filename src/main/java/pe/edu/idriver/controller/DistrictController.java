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

import pe.edu.idriver.entity.District;
import pe.edu.idriver.entity.Province;
import pe.edu.idriver.service.IDistrictService;
import pe.edu.idriver.service.IProvinceService;

@Controller
@RequestMapping("/district")
public class DistrictController {

	@Autowired
	private IDistrictService diService;
	
	@Autowired
	private IProvinceService pService;
	
	@RequestMapping("/")
	public String irDistrict(Map<String, Object> model) {
		model.put("listaDistritos", diService.listar());
		return "listDistrict";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaProvincias", pService.listar());
		model.addAttribute("province", new Province());
		model.addAttribute("district", new District());
		return "district";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute District objDistrict, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaProvincias", pService.listar());
			return "district";
		} else {
			boolean flag = diService.insertar(objDistrict);
			if(flag) {
				return "redirect:/district/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/district/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<District> objDistrict = diService.listarId(id);
		
		if(objDistrict == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/district/listar";
		} else {
			model.addAttribute("listaProvincias", pService.listar());
			if(objDistrict.isPresent())
				objDistrict.ifPresent(o -> model.addAttribute("district",o));
			return "district";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				diService.eliminar(id);
				model.put("listaDistritos", diService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaDistritos", diService.listar());
		}
		return "listDistrict";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaDistritos", diService.listar());
		return "listDistrict";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute District district)
	throws ParseException
	{
		pService.listarId(district.getIdDistrict());
		return "listDistrict";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute District district)
	throws ParseException 
	{
		List<District> listaDistritos;
		district.setNameDistrict(district.getNameDistrict());
		listaDistritos = diService.buscarNombre(district.getNameDistrict());
		
		if(listaDistritos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDistritos", listaDistritos);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("district", new District());
		return "buscar";
	}
}
