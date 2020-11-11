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

import pe.edu.idriver.entity.Color;
import pe.edu.idriver.service.IColorService;


@Controller
@RequestMapping("/color")
public class ColorController {

	@Autowired
	private IColorService coService;
	
	@RequestMapping("/")
	public String irColor(Map<String, Object> model) {
		model.put("listaColores", coService.listar());
		return "listColor";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("color", new Color());
		return "color";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Color objColor, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			return "color";
		} else {
			boolean flag = coService.insertar(objColor);
			if(flag) {
				return "redirect:/color/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/color/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Color> objColor = coService.listarId(id);
		
		if(objColor == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/color/listar";
		} else {
			model.addAttribute("color", objColor);
			return "color";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				coService.eliminar(id);
				model.put("listaColores", coService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaColores", coService.listar());
		}
		return "listColor";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaColores", coService.listar());
		return "listColor";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Color color)
	throws ParseException
	{
		coService.listarId(color.getIdColor());
		return "listColor";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Color color)
	throws ParseException 
	{
		List<Color> listaColores;
		color.setNameColor(color.getNameColor());
		listaColores = coService.findByName(color.getNameColor());
		
		if(listaColores.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaColores", listaColores);
		return "listColor";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("color", new Color());
		return "buscarColor";
	}
}
