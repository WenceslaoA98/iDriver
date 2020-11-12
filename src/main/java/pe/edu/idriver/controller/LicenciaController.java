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

import pe.edu.idriver.entity.Licencia;
import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.service.ILicenciaService;
import pe.edu.idriver.service.IUsuarioService;

@Controller
@RequestMapping("/licencia")
public class LicenciaController {

	@Autowired
	private ILicenciaService lService;
	
	@Autowired
	private IUsuarioService uService;
	
	@RequestMapping("/")
	public String irLicencia(Map<String, Object> model) {
		model.put("listaLicencias", lService.listar());
		return "listLicencia";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaUsuarios", uService.listar());
		model.addAttribute("usuario", new Usuario());
		model.addAttribute("licencia", new Licencia());
		return "licencia";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Licencia objLicencia, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaUsuarios", uService.listar());
			return "licencia";
		} else {
			boolean flag = lService.insertar(objLicencia);
			if(flag) {
				return "redirect:/licencia/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/licencia/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable String id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Licencia> objLicencia = lService.listarId(id);
		
		if(objLicencia == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/licencia/listar";
		} else {
			model.addAttribute("listaUsuarios", uService.listar());
			if(objLicencia.isPresent())
				objLicencia.ifPresent(o -> model.addAttribute("licencia",o));
			return "licencia";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") String id) {
		
		try {
			if(id != null) {
				lService.eliminar(id);
				model.put("listaLicencias", lService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaLicencias", lService.listar());
		}
		return "listLicencia";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaLicencias", lService.listar());
		return "listLicencia";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Licencia licencia)
	throws ParseException
	{
		lService.listarId(licencia.getIdLicencia());
		return "listLicencia";
	}

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute Licencia licencia)
	throws ParseException 
	{
		List<Licencia> listaLicencias;
		licencia.setIdLicencia(licencia.getIdLicencia());
		listaLicencias = lService.findById(licencia.getIdLicencia());
		
		if(listaLicencias.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaLicencias", listaLicencias);
		return "listLicencia";
	}
	
	@RequestMapping("/buscarporUsuario") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscarporUsuario(Map<String , Object> model, @ModelAttribute Licencia licencia, @ModelAttribute Usuario usuario)
	throws ParseException 
	{
		List<Licencia> listaLicencias;
		usuario.setNameUsuario(usuario.getNameUsuario());
		listaLicencias = lService.findByNameUsuario(usuario.getNameUsuario());
		
		if(listaLicencias.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaLicencias", listaLicencias);
		return "listLicencia";
	}
	
	@RequestMapping("/comoBuscar")
	public String comoBuscar(Model model) {
		model.addAttribute("licencia", new Licencia());
		return "QuebuscaLicencia";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("licencia", new Licencia());
		return "buscarLicencia";
	}
	
	@RequestMapping("/irBuscarporUsuario")
	public String irBuscarporUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "buscarLicenciaporUsuario";
	}
}
