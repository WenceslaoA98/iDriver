package pe.edu.login.idriver.controller;

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

import pe.edu.login.idriver.entity.Tarjeta;
import pe.edu.login.idriver.entity.Vehiculo;
import pe.edu.login.idriver.service.ITarjetaService;
import pe.edu.login.idriver.service.IVehiculoService;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {

	@Autowired
	private ITarjetaService tService;
	
	@Autowired
	private IVehiculoService vService;
	
	@RequestMapping("/")
	public String irTarjeta(Map<String, Object> model) {
		model.put("listaTarjetas", tService.listar());
		return "listTarjeta";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaVehiculos", vService.listar());
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("tarjeta", new Tarjeta());
		return "tarjeta";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Tarjeta objTarjeta, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaVehiculos", vService.listar());
			return "tarjeta";
		} else {
			boolean flag = tService.insertar(objTarjeta);
			if(flag) {
				return "redirect:/tarjeta/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/tarjeta/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable String id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Tarjeta> objTarjeta = tService.listarId(id);
		
		if(objTarjeta == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/tarjeta/listar";
		} else {
			model.addAttribute("listaVehiculos", vService.listar());
			if(objTarjeta.isPresent())
				objTarjeta.ifPresent(o -> model.addAttribute("tarjeta",o));
			return "tarjeta";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") String id) {
		
		try {
			if(id != null) {
				tService.eliminar(id);
				model.put("listaTarjetas", tService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaTarjetas", tService.listar());
		}
		return "listTarjeta";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaTarjetas", tService.listar());
		return "listTarjeta";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Tarjeta tarjeta)
	throws ParseException
	{
		tService.listarId(tarjeta.getIdTarjeta());
		return "listTarjeta";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Tarjeta tarjeta)
	throws ParseException 
	{
		List<Tarjeta> listaTarjetas;
		tarjeta.setIdTarjeta(tarjeta.getIdTarjeta());
		listaTarjetas = tService.findById(tarjeta.getIdTarjeta());
		
		if(listaTarjetas.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaTarjetas", listaTarjetas);
		return "listTarjeta";
	}
	
	@RequestMapping("/buscarporPlaca") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporPlaca(Map<String , Object> model, @ModelAttribute Tarjeta tarjeta, @ModelAttribute Vehiculo vehiculo)
	throws ParseException 
	{
		List<Tarjeta> listaTarjetas;
		vehiculo.setPlacaVehiculo(vehiculo.getPlacaVehiculo());
		listaTarjetas = tService.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
		
		if(listaTarjetas.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaTarjetas", listaTarjetas);
		return "listTarjeta";
	}
	
	@RequestMapping("/comoBuscar")
	public String comoBuscar(Model model) {
		model.addAttribute("tarjeta", new Tarjeta());
		return "QuebuscaTarjeta";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("tarjeta", new Tarjeta());
		return "buscarTarjeta";
	}
	
	@RequestMapping("/irBuscarporPlaca")
	public String irBuscarporPlaca(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		return "buscarTarjetaporPlaca";
	}
}
