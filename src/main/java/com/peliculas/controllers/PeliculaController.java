package com.peliculas.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.peliculas.entities.Actor;
import com.peliculas.entities.Pelicula;
import com.peliculas.services.ActorService;
import com.peliculas.services.ArchivoService;
import com.peliculas.services.IGeneroService;
import com.peliculas.services.PeliculaService;

import jakarta.validation.Valid;

@RestController
public class PeliculaController {
	private PeliculaService service;
	private IGeneroService generoService;
	private ActorService actorService;
	private ArchivoService archivoService;

	public PeliculaController(PeliculaService service, IGeneroService generoService, ActorService actorService,
			ArchivoService archivoService) {
		this.service = service;
		this.generoService = generoService;
		this.actorService = actorService;
		this.archivoService = archivoService;
	}

	@PostMapping("/pelicula")
	// BindingResulto es donde se almacena el resultado de la validaciones de los
	// formiularios
	public String guardar(@Valid Pelicula pelicula, BindingResult br, @ModelAttribute(name = "ids") String ids,
			Model model, @RequestParam("archivo") MultipartFile imagen) {

		if (br.hasErrors()) {
			model.addAttribute("generos", generoService.findAll());
			model.addAttribute("actores", actorService.findAll());
			return "pelicula";
		}

		if (!imagen.isEmpty()) {
			String archivo = pelicula.getNombre() + getExtension(imagen.getOriginalFilename());
			pelicula.setImagen(archivo);
			try {
				archivoService.guardar(archivo, imagen.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			pelicula.setImagen("_default.jpg");
		}

		if (ids != null && !"".equals(ids)) {
			List<Long> idsProtagonista = Arrays.stream(ids.split(",")).map(Long::parseLong)
					.collect(Collectors.toList());
			List<Actor> protagonistas = actorService.findAllById(idsProtagonista);
			pelicula.setProtagonistas(protagonistas);
		}

		service.save(pelicula);
		return "redirect:home";
	}

	private String getExtension(String archivo) {
		return archivo.substring(archivo.lastIndexOf("."));
	}

	@GetMapping("pelicula")
	public String crear(Model model) {
		Pelicula pelicula = new Pelicula();
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("actores", actorService.findAll());
		model.addAttribute("titulo", "Nueva Pelicula");
		return "pelicula";
	}

	@GetMapping("pelicula/{id}")
	public String editar(@PathVariable(name = "id") Long id, Model model) {
		Pelicula pelicula = service.findById(id);
		String ids = "";
		
		for (Actor actor : pelicula.getProtagonistas()) {
			if ("".equals(ids)) {
				ids= actor.getId().toString();
			}else {
				ids= ids + "," + actor.getId().toString();
			}
		}
		model.addAttribute("pelicula", pelicula);
		model.addAttribute("ids", ids);
		model.addAttribute("generos", generoService.findAll());
		model.addAttribute("actores", actorService.findAll());
		model.addAttribute("titulo", "Editar Pelicula");
		return "pelicula";
	}

	@GetMapping({ "/", "/home", "/index" })
	public String home(Model model, @RequestParam(name="pagina", required = false, defaultValue ="0") Integer pagina) {
		PageRequest pr = PageRequest.of(pagina, 6);
		Page<Pelicula> page = service.findAll(pr);
		
		model.addAttribute("peliculas", page.getContent());
		
		
		model.addAttribute("msj", "Catalogo Actualizado a 2023");
		model.addAttribute("tipoMsj", "success");
		return "home";
	}

	@GetMapping({ "/listado" })
	public String listado(Model model , @RequestParam(required = false) String msj,  @RequestParam(required = false) String tipoMsj) {
		model.addAttribute("titulo", "Listado de  Peliculas");
		model.addAttribute("peliculas", service.findAll());

		if (!"".equals(tipoMsj) && !"".equals(msj)) {
			model.addAttribute("tipoMsj", tipoMsj);
			model.addAttribute("msj", msj);

		}
		return "listado";
	}
	
	@GetMapping("pelicula/{id}/delete")
	public String eliminar(@PathVariable(name = "id") Long id, Model model, RedirectAttributes attributes) {
		service.delete(id);
		attributes.addAttribute("msj", "La Pelicula Fue Eliminada");
		attributes.addAttribute("msj" , "success");
		return "redirect:listado";
	}

}
