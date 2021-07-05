package com.valerijovich.jsonpostgres.repositories;

import com.valerijovich.jsonpostgres.entities.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
