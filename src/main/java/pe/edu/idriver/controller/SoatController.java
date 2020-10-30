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

import pe.edu.idriver.entity.Soat;
import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.service.ISoatService;
import pe.edu.idriver.service.IUsuarioService;
import pe.edu.idriver.service.IVehiculoService;

@Controller
@RequestMapping("/soat")
public class SoatController {

	@Autowired
	private ISoatService sService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IVehiculoService vService;
	
	@RequestMapping("/")
	public String irSoat(Map<String, Object> model) {
		model.put("listaSoats", sService.listar());
		return "listSoat";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaVehiculos", vService.listar());
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("soat", new Soat());
		return "soat";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Soat objSoat, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			return "soat";
		} else {
			boolean flag = sService.insertar(objSoat);
			if(flag) {
				return "redirect:/soat/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/soat/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Soat> objSoat = sService.listarId(id);
		
		if(objSoat == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/soat/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			if(objSoat.isPresent())
				objSoat.ifPresent(o -> model.addAttribute("soat",o));
			return "soat";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				sService.eliminar(id);
				model.put("listaSoats", sService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaSoats", sService.listar());
		}
		return "listSoat";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaSoats", sService.listar());
		return "listSoat";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Soat soat)
	throws ParseException
	{
		sService.listarId(soat.getIdSoat());
		return "listSoat";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Soat soat)
	throws ParseException 
	{
		List<Soat> listaSoats;
		soat.setIdSoat(soat.getIdSoat());
		listaSoats = sService.buscarNombre(soat.getIdSoat());
		
		if(listaSoats.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaSoats", listaSoats);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("soat", new Soat());
		return "buscar";
	}
}
