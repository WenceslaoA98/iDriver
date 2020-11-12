package pe.edu.idriver.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.idriver.entity.Seguro;
import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.service.ISeguroService;
import pe.edu.idriver.service.IUsuarioService;
import pe.edu.idriver.service.IVehiculoService;

@Controller
@RequestMapping("/seguro")
public class SeguroController {

	@Autowired
	private ISeguroService seService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IVehiculoService vService;
	
	@RequestMapping("/")
	public String irSeguro(Map<String, Object> model) {
		model.put("listaSeguros", seService.listar());
		return "listSeguro";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaVehiculos", vService.listar());
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("seguro", new Seguro());
		return "seguro";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Seguro objSeguro, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			return "seguro";
		} else {
			boolean flag = seService.insertar(objSeguro);
			if(flag) {
				return "redirect:/seguro/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/seguro/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable String id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Seguro> objSeguro = seService.listarId(id);
		
		if(objSeguro == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/seguro/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			if(objSeguro.isPresent())
				objSeguro.ifPresent(o -> model.addAttribute("seguro",o));
			return "seguro";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") String id) {
		
		try {
			if(id != null) {
				seService.eliminar(id);
				model.put("listaSeguros", seService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaSeguros", seService.listar());
		}
		return "listSeguro";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaSeguros", seService.listar());
		return "listSeguro";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Seguro seguro)
	throws ParseException
	{
		seService.listarId(seguro.getIdSeguro());
		return "listSeguro";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Seguro seguro)
	throws ParseException 
	{
		List<Seguro> listaSeguros;
		seguro.setIdSeguro(seguro.getIdSeguro());
		listaSeguros = seService.findById(seguro.getIdSeguro());
		
		if(listaSeguros.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaSeguros", listaSeguros);
		return "listSeguro";
	}
	
	@RequestMapping("/buscarporUsuario") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporUsuario(Map<String , Object> model, @ModelAttribute Seguro seguro, @ModelAttribute Usuario usuario)
	throws ParseException 
	{
		List<Seguro> listaSeguros;
		usuario.setNameUsuario(usuario.getNameUsuario());
		listaSeguros = seService.findByNombreUsuario(usuario.getNameUsuario());
		
		if(listaSeguros.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaSeguros", listaSeguros);
		return "listSeguro";
	}
	
	@RequestMapping("/buscarporPlaca") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporPlaca(Map<String , Object> model, @ModelAttribute Seguro seguro, @ModelAttribute Vehiculo vehiculo)
	throws ParseException 
	{
		List<Seguro> listaSeguros;
		vehiculo.setPlacaVehiculo(vehiculo.getPlacaVehiculo());
		listaSeguros = seService.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
		
		if(listaSeguros.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaSeguros", listaSeguros);
		return "listSeguro";
	}
	
	@RequestMapping("/comoBuscar")
	public String comoBuscar(Model model) {
		model.addAttribute("seguro", new Seguro());
		return "QuebuscaSeguro";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("seguro", new Seguro());
		return "buscarSeguro";
	}
	
	@RequestMapping("/irBuscarporUsuario")
	public String irBuscarporUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "buscarSeguroporUsuario";
	}
	
	@RequestMapping("/irBuscarporPlaca")
	public String irBuscarporPlaca(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		return "buscarSeguroporPlaca";
	}
}
