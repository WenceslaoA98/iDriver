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

import pe.edu.idriver.entity.Brand;
import pe.edu.idriver.entity.CarModel;
import pe.edu.idriver.service.IBrandService;
import pe.edu.idriver.service.ICarModelService;

@Controller
@RequestMapping("/carmodel")
public class CarModelController {

	@Autowired
	private ICarModelService cmService;
	
	@Autowired
	private IBrandService bService;
	
	@RequestMapping("/")
	public String irCarModel(Map<String, Object> model) {
		model.put("listaModelos", cmService.listar());
		return "listCarModel";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("listaMarcas", bService.listar());
		model.addAttribute("brand", new Brand());
		model.addAttribute("carmodel", new CarModel());
		return "carmodel";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute CarModel objCarModel, BindingResult binRes, Model model) 
	throws ParseException {
		
		if(binRes.hasErrors()) {
			model.addAttribute("listaMarcas", bService.listar());
			return "carmodel";
		} else {
			boolean flag = cmService.insertar(objCarModel);
			if(flag) {
				return "redirect:/carmodel/listar";
			} else {
				model.addAttribute("mensaje", "Ocurrio un rochetov");
				return "redirect:/carmodel/irRegistrar";
			}
		}
		
	}
	
	@RequestMapping("/modificar/{id}") // el pathvariable le dice que id modificara 
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) 
	throws ParseException {
		
		Optional<CarModel> objCarModel = cmService.listarId(id);
		
		if(objCarModel == null) {
			objRedir.addFlashAttribute("mensaje","Ocurrio un rochesin");
			return "redirect:/carmodel/listar";
		} else {
			model.addAttribute("listaMarcas", bService.listar());
			if(objCarModel.isPresent())
				objCarModel.ifPresent(o -> model.addAttribute("carmodel",o));
			return "carmodel";
		}
		
	}
	
	@RequestMapping("/eliminar")
	public String eliminar (Map<String, Object> model, @RequestParam(value="id") Integer id) {
		
		try {
			if(id != null && id>0) {
				cmService.eliminar(id);
				model.put("listaModelos", cmService.listar());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.put("mensaje", "sucedio un error");
			model.put("listaModelos", cmService.listar());
		}
		return "listCarModel";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		
		model.put("listaModelos", cmService.listar());
		return "listCarModel";
	}
	
	@RequestMapping("/listarId")
	public String listar(Map<String, Object> model, @ModelAttribute CarModel carmodel)
	throws ParseException
	{
		cmService.listarId(carmodel.getIdCarModel());
		return "listCarModel";
	}


	@RequestMapping("/buscar") 
	public String buscar(Map<String , Object> model, @ModelAttribute CarModel carmodel, @ModelAttribute Brand brand)
	throws ParseException 
	{
		List<CarModel> listaModelos;
		brand.setNameBrand(brand.getNameBrand());
		listaModelos = cmService.findByNameBrand(brand.getNameBrand());

	@RequestMapping("/buscar") /*se recupera el nombre de la raza. Del objeto race*/ 
	public String buscar(Map<String , Object> model, @ModelAttribute CarModel carmodel)
	throws ParseException 
	{
		List<CarModel> listaModelos;
		carmodel.setNameCarModel(carmodel.getNameCarModel());
		listaModelos = cmService.buscarNombre(carmodel.getNameCarModel());

		
		if(listaModelos.isEmpty()) /*si no encuentro es empty, y me devuelve el mensaje, sino me devuelve la lista con los valores cargados*/ 
		{
			model.put("mensaje", "No se encontro");
		}
		model.put("listaModelos", listaModelos);

		return "listCarModel";

		return "buscar";

	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) {
		model.addAttribute("carmodel", new CarModel());

		model.addAttribute("brand", new Brand());
		return "buscarCarModel";

		return "buscar";

	}
}
