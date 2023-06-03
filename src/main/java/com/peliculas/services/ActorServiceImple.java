package com.peliculas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peliculas.entities.Actor;
import com.peliculas.repository.ActorRepository;
@Service
public class ActorServiceImple implements ActorService {

	@Autowired
	private ActorRepository repo;
	@Override
	public List<Actor> findAllById(List<Long> ids) {
		// TODO Auto-generated method stub
		return (List<Actor>) repo.findAllById(ids);
	}

	//si aparece error eliminar los override
	@Override
	public List<Actor> findAll() {
		// TODO Auto-generated method stub
		return (List<Actor>) repo.findAll();
	}

}
