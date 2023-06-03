package com.peliculas.services;

import java.util.List;

import com.peliculas.entities.Actor;

public interface ActorService {
	public List<Actor> findAllById(List<Long> ids);
	public List<Actor> findAll();
}
