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

		listaDocumentos = doService.findById(documento.getIdDocumento());
		listaDocumentos = doService.buscarNombre(documento.getIdDocumento());

		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);

		return "listDocumento";
	}
	
	@RequestMapping("/buscarporUsuario") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporUsuario(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Usuario usuario)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		usuario.setNameUsuario(usuario.getNameUsuario());
		listaDocumentos = doService.findByNameUsuario(usuario.getNameUsuario());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporPlaca") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporPlaca(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Vehiculo vehiculo)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		vehiculo.setPlacaVehiculo(vehiculo.getPlacaVehiculo());
		listaDocumentos = doService.findByPlacaVehiculo(vehiculo.getPlacaVehiculo());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporLicencia") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporLicencia(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Licencia licencia)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		licencia.setIdLicencia(licencia.getIdLicencia());
		listaDocumentos = doService.findByIdLicencia(licencia.getIdLicencia());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporSeguro") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporSeguro(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Seguro seguro)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		seguro.setIdSeguro(seguro.getIdSeguro());
		listaDocumentos = doService.findByIdSeguro(seguro.getIdSeguro());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporSoat") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporSoat(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Soat soat)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		soat.setIdSoat(soat.getIdSoat());
		listaDocumentos = doService.findByIdSoat(soat.getIdSoat());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporTarjeta") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporTarjeta(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Tarjeta tarjeta)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		tarjeta.setIdTarjeta(tarjeta.getIdTarjeta());
		listaDocumentos = doService.findByIdTarjeta(tarjeta.getIdTarjeta());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/buscarporPolarizado") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporPolarizado(Map<String , Object> model, @ModelAttribute Documento documento, @ModelAttribute Polarizado polarizado)
	throws ParseException 
	{
		List<Documento> listaDocumentos;
		polarizado.setIdPolarizado(polarizado.getIdPolarizado());
		listaDocumentos = doService.findByIdPolarizado(polarizado.getIdPolarizado());
		
		if(listaDocumentos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaDocumentos", listaDocumentos);
		return "listDocumento";
	}
	
	@RequestMapping("/comoBuscar")
	public String comoBuscar(Model model) {
		model.addAttribute("documento", new Documento());
		return "QuebuscaDocumento";

		return "buscar";

	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("documento", new Documento());

		return "buscarDocumento";
	}
	
	@RequestMapping("/irBuscarporUsuario")
	public String irBuscarporUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "buscarDocumentoporUsuario";
	}
	
	@RequestMapping("/irBuscarporPlaca")
	public String irBuscarporPlaca(Model model) {
		model.addAttribute("vehiculo", new Vehiculo());
		return "buscarDocumentoporPlaca";
	}
	
	@RequestMapping("/irBuscarporLicencia")
	public String irBuscarporLicencia(Model model) {
		model.addAttribute("licencia", new Licencia());
		return "buscarDocumentoporLicencia";
	}
	
	@RequestMapping("/irBuscarporSeguro")
	public String irBuscarporSeguro(Model model) {
		model.addAttribute("seguro", new Seguro());
		return "buscarDocumentoporSeguro";
	}
	
	@RequestMapping("/irBuscarporSoat")
	public String irBuscarporSoat(Model model) {
		model.addAttribute("soat", new Soat());
		return "buscarDocumentoporSoat";
	}
	
	@RequestMapping("/irBuscarporTarjeta")
	public String irBuscarporTarjeta(Model model) {
		model.addAttribute("tarjeta", new Tarjeta());
		return "buscarDocumentoporTarjeta";
	}
	
	@RequestMapping("/irBuscarporPolarizado")
	public String irBuscarporPolarizado(Model model) {
		model.addAttribute("polarizado", new Polarizado());
		return "buscarDocumentoporPolarizado";

		return "buscar";

	}
}
