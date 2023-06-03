package com.peliculas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.peliculas.entities.Actor;
@Repository
public interface ActorRepository extends CrudRepository<Actor, Long> {

}
