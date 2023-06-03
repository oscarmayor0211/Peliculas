package com.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.entities.Genero;
import com.peliculas.repository.IGeneroRepository;
@Service
public class GeneroServiceImple implements IGeneroService {

	@Autowired
	private IGeneroRepository repo;
	@Override
	public void save(Genero genero) {
		// TODO Auto-generated method stub
		repo.save(genero);
	}

	@Override
	public Genero findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<Genero> findAll() {
		// TODO Auto-generated method stub
		return (List<Genero>) repo.findAll();
	}

}
