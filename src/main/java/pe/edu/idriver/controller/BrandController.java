package pe.edu.login.idriver.controller;

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

import pe.edu.login.idriver.entity.Brand;
import pe.edu.login.idriver.service.IBrandService;

@Controller
@RequestMapping("/brand")
public class BrandController {

	@Autowired
	private IBrandService bService;
	
	@RequestMapping("/")
	public String irBrand(Map<String, Object> model) {
		model.put("listaMarcas", bService.listar());
		return "listBrand";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("brand", new Brand());
		return "brand";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Brand objBrand, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			return "brand";
		} else {
			boolean flag = bService.insertar(objBrand);
			if(flag) {
				return "redirect:/brand/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/brand/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Brand> objBrand = bService.listarId(id);
		
		if(objBrand == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/brand/listar";
		} else {
			model.addAttribute("brand", objBrand);
			return "brand";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				bService.eliminar(id);
				model.put("listaMarcas", bService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaMarcas", bService.listar());
		}
		return "listBrand";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaMarcas", bService.listar());
		return "listBrand";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Brand brand)
	throws ParseException
	{
		bService.listarId(brand.getIdBrand());
		return "listBrand";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Brand brand)
	throws ParseException 
	{
		List<Brand> listaMarcas;
		brand.setNameBrand(brand.getNameBrand());
		listaMarcas = bService.findByName(brand.getNameBrand());
		
		if(listaMarcas.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaMarcas", listaMarcas);
		return "listBrand";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("brand", new Brand());
		return "buscarMarca";
	}
}
