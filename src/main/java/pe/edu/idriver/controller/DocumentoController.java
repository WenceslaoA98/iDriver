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

import pe.edu.idriver.entity.Documento;
import pe.edu.idriver.entity.Licencia;
import pe.edu.idriver.entity.Polarizado;
import pe.edu.idriver.entity.Seguro;
import pe.edu.idriver.entity.Soat;
import pe.edu.idriver.entity.Tarjeta;
import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.entity.Vehiculo;
import pe.edu.idriver.service.IDocumentoService;
import pe.edu.idriver.service.ILicenciaService;
import pe.edu.idriver.service.IPolarizadoService;
import pe.edu.idriver.service.ISeguroService;
import pe.edu.idriver.service.ISoatService;
import pe.edu.idriver.service.ITarjetaService;
import pe.edu.idriver.service.IUsuarioService;
import pe.edu.idriver.service.IVehiculoService;


@Controller
@RequestMapping("/documento")
public class DocumentoController {

	@Autowired
	private IDocumentoService doService;
	
	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IVehiculoService vService;
	
	@Autowired
	private ILicenciaService lService;
	
	@Autowired
	private ISeguroService sService;
	
	@Autowired
	private ISoatService soService;
	
	@Autowired
	private ITarjetaService tService;
	
	@Autowired
	private IPolarizadoService poService;
	
	@RequestMapping("/")
	public String irDocumento(Map<String, Object> model) {
		model.put("listaDocumentos", doService.listar());
		return "listDocumento";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("listaVehiculos", vService.listar());
		model.addAttribute("vehiculo", new Vehiculo());
		model.addAttribute("listaLicencias", lService.listar());
		model.addAttribute("licencia", new Licencia());
		model.addAttribute("listaSeguros", sService.listar());
		model.addAttribute("seguro", new Seguro());
		model.addAttribute("listaSoats", soService.listar());
		model.addAttribute("soat", new Soat());
		model.addAttribute("listaTarjetas", tService.listar());
		model.addAttribute("tarjeta", new Tarjeta());
		model.addAttribute("listaPolarizados", poService.listar());
		model.addAttribute("polarizado", new Polarizado());
		model.addAttribute("documento", new Documento());
		return "documento";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Documento objDocumento, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			model.addAttribute("listaLicencias", lService.listar());
			model.addAttribute("listaSeguros", sService.listar());
			model.addAttribute("listaSoats", soService.listar());
			model.addAttribute("listaTarjetas", tService.listar());
			model.addAttribute("listaPolarizados", poService.listar());
			return "documento";
		} else {
			boolean flag = doService.insertar(objDocumento);
			if(flag) {
				return "redirect:/documento/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/documento/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Documento> objDocumento = doService.listarId(id);
		
		if(objDocumento == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/documento/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			model.addAttribute("listaVehiculos", vService.listar());
			model.addAttribute("listaLicencias", lService.listar());
			model.addAttribute("listaSeguros", sService.listar());
			model.addAttribute("listaSoats", soService.listar());
			model.addAttribute("listaTarjetas", tService.listar());
			model.addAttribute("listaPolarizados", poService.listar());
			if(objDocumento.isPresent())
				objDocumento.ifPresent(o -> model.addAttribute("documento",o));
			return "documento";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				doService.eliminar(id);
				model.put("listaDocumentos", doService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaDocumentos", doService.listar());
		}
		return "listDocumento";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaDocumentos", doService.listar());
		return "listDocumento";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Documento documento)
	throws ParseException
	{
		doService.listarId(documento.getIdDocumento());
		return "listDocumento";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Documento documento)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		documento.setIdDocumento(documento.getIdDocumento());
		listaDocumentos = doService.buscarNombre(documento.getIdDocumento());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "buscar";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("documento", new Documento());
		return "buscar";
	}
}
