package com.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.peliculas.entities.Pelicula;
import com.peliculas.repository.PeliculaRepository;
@Service
public class PeliculaServiceImpl implements PeliculaService {

	@Autowired
	private PeliculaRepository repo;
	@Override
	public void save(Pelicula pelicula) {
		// TODO Auto-generated method stub
		repo.save(pelicula);
	}

	@Override
	public Pelicula findById(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	}

	@Override
	public List<Pelicula> findAll() {
		// TODO Auto-generated method stub
		return (List<Pelicula>) repo.findAll();
	}

	@Override
	public Page<Pelicula> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findAll(pageable);
	}

}
