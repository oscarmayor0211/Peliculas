package com.peliculas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.peliculas.entities.Genero;
import com.peliculas.services.IGeneroService;

@RestController
public class GeneroController {

	private IGeneroService service;

	public GeneroController(IGeneroService service) {
		this.service = service;
	}

	@PostMapping("genero")
	public Long guardar(@RequestParam String nombre) {
		Genero genero = new Genero();
		genero.setNombre(nombre);
		return genero.getId();
	}

	@GetMapping("generos/{id}")
	public String buscarPorId(@PathVariable(name = "id") Long id) {
		return service.findById(id).getNombre();
	}
}
