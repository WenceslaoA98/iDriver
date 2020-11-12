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

import pe.edu.idriver.entity.District;
import pe.edu.idriver.entity.Usuario;
import pe.edu.idriver.service.IDistrictService;
import pe.edu.idriver.service.IUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private IUsuarioService uService;
	
	@Autowired
	private IDistrictService diService;
	
	@RequestMapping("/")
	public String irUsuario(Map<String, Object> model) {
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaDistritos", diService.listar());
		model.addAttribute("district", new District());
		model.addAttribute("usuario", new Usuario());
		return "usuario";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute @Valid Usuario objUsuario, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaDistritos", diService.listar());
			return "usuario";
		} else {
			boolean flag = uService.insertar(objUsuario);
			if(flag) {
				return "redirect:/usuario/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/usuario/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable String id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<Usuario> objUsuario = uService.listarId(id);
		
		if(objUsuario == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/usuario/listar";
		} else {
			model.addAttribute("listaDistritos", diService.listar());
			if(objUsuario.isPresent())
				objUsuario.ifPresent(o -> model.addAttribute("usuario",o));
			return "usuario";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") String id) {
		
		try {
			if(id != null) {
				uService.eliminar(id);
				model.put("listaUsuarios", uService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaUsuarios", uService.listar());
		}
		return "listUsuario";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaUsuarios", uService.listar());
		return "listUsuario";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute Usuario usuario)
	throws ParseException
	{
		uService.listarId(usuario.getIdUsuario());
		return "listUsuario";
	}

	@RequestMapping("/buscar") 
	public String buscar(Map<String , Object> model, @ModelAttribute Usuario usuario)
	throws ParseException 
	{
		List<Usuario> listaUsuarios;
		usuario.setIdUsuario(usuario.getIdUsuario());
		listaUsuarios = uService.findById(usuario.getIdUsuario());
		
		if(listaUsuarios.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaUsuarios", listaUsuarios);
		return "listUsuario";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "buscarUsuario";
	}
}
