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

import pe.edu.idriver.entity.CarModel;
import pe.edu.idriver.entity.Color;
import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.service.ICarModelService;
import pe.edu.idriver.service.IColorService;
import pe.edu.idriver.service.IVehiculoService;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {

	@Autowired
	private IVehiculoService vService;
	
	@Autowired
	private ICarModelService cmService;
	
	@Autowired
	private IColorService coService;
	
	@RequestMapping("/")
	public String irVehiculo(Map<String, Object> model) {
		model.put("listaVehiculos", vService.listar());
		return "listVehiculo";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaModelos", cmService.listar());
		model.addAttribute("carmodel", new CarModel());
		model.addAttribute("listaColores", coService.listar());
		model.addAttribute("color", new Color());
		model.addAttribute("vehiculo", new Vehiculo());
		return "vehiculo";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Vehiculo objVehiculo, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaModelos", cmService.listar());
			model.addAttribute("listaColores", coService.listar());
			return "vehiculo";
		} else {
			boolean flag = vService.insertar(objVehiculo);
			if(flag) {
				return "redirect:/vehiculo/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/vehiculo/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Vehiculo> objVehiculo = vService.listarId(id);
		
		if(objVehiculo == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/vehiculo/listar";
		} else {
			model.addAttribute("listaModelos", cmService.listar());
			if(objVehiculo.isPresent())
				objVehiculo.ifPresent(o -> model.addAttribute("vehiculo",o));
			return "vehiculo";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				vService.eliminar(id);
				model.put("listaVehiculos", vService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaVehiculos", vService.listar());
		}
		return "listVehiculo";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaVehiculos", vService.listar());
		return "listVehiculo";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Vehiculo vehiculo)
	throws ParseException
	{
		vService.listarId(vehiculo.getIdVehiculo());
		return "listVehiculo";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Vehiculo vehiculo)
	throws ParseException 
	{
		List<Vehiculo> listaVehiculos;
		vehiculo.setPlacaVehiculo(vehiculo.getPlacaVehiculo());

		listaVehiculos = vService.findByName(vehiculo.getPlacaVehiculo());

		listaVehiculos = vService.buscarNombre(vehiculo.getPlacaVehiculo());

		
		if(listaVehiculos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaVehiculos", listaVehiculos);

		return "listVehiculo";

		return "buscar";

	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());

		return "buscarVehiculo";

		return "buscar";

	}
}
