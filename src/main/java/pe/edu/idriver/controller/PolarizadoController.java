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

import pe.edu.idriver.entity.Polarizado;
import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.service.IPolarizadoService;
import pe.edu.idriver.service.IVehiculoService;

@Controller
@RequestMapping("/polarizado")
public class PolarizadoController {

	@Autowired
	private IPolarizadoService poService;
	
	@Autowired
	private IVehiculoService vService;
	
	@RequestMapping("/")
	public String irPolarizado(Map<String, Object> model) {
		model.put("listaPolarizados", poService.listar());
		return "listPolarizado";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaVehiculos", vService.listar());
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("polarizado", new Polarizado());
		return "polarizado";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Polarizado objPolarizado, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaVehiculos", vService.listar());
			return "polarizado";
		} else {
			boolean flag = poService.insertar(objPolarizado);
			if(flag) {
				return "redirect:/polarizado/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/polarizado/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 

	public String modificar (@PathVariable String id, Model model, RedirectAttributes objRedir) 

	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 

	throws ParseException {
		
		Optional<Polarizado> objPolarizado = poService.listarId(id);
		
		if(objPolarizado == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/polarizado/listar";
		} else {
			model.addAttribute("listaVehiculos", vService.listar());
			if(objPolarizado.isPresent())
				objPolarizado.ifPresent(o -> model.addAttribute("polarizado",o));
			return "polarizado";
		}
		
	}
	
	@RequestMapping("/eliminar")

	public String eliminar (Map<String, Object> model, @RequestParam(value="id") String id) {
		
		try {
			if(id != null ) {

	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {

				poService.eliminar(id);
				model.put("listaPolarizados", poService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaPolarizados", poService.listar());
		}
		return "listPolarizado";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaPolarizados", poService.listar());
		return "listPolarizado";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Polarizado polarizado)
	throws ParseException
	{
		poService.listarId(polarizado.getIdPolarizado());
		return "listPolarizado";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Polarizado polarizado)
	throws ParseException 
	{
		List<Polarizado> listaPolarizados;
		polarizado.setIdPolarizado(polarizado.getIdPolarizado());

		listaPolarizados = poService.findById(polarizado.getIdPolarizado());

		listaPolarizados = poService.buscarNombre(polarizado.getIdPolarizado());

		
		if(listaPolarizados.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPolarizados", listaPolarizados);

		return "listPolarizado";
	}
	
	@RequestMapping("/buscarporPlaca") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporPlaca(Map<String , Object> model, @ModelAttribute Polarizado polarizado, @ModelAttribute Vehiculo vehiculo)
	throws ParseException 
	{
		List<Polarizado> listaPolarizados;
		vehiculo.setPlacaVehiculo(vehiculo.getPlacaVehiculo());
		listaPolarizados = poService.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
		
		if(listaPolarizados.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaPolarizados", listaPolarizados);
		return "listPolarizado";
	}
	
	@RequestMapping("/comoBuscar")
	public String comoBuscar(Model model) {
		model.addAttribute("polarizado", new Polarizado());
		return "QuebuscaPolarizado";

		return "buscar";

	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("polarizado", new Polarizado());

		return "buscarPolarizado";
	}
	
	@RequestMapping("/irBuscarporPlaca")
	public String irBuscarporPlaca(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		return "buscarPolarizadoporPlaca";

		return "buscar";

	}
}
