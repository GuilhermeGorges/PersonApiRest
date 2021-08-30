package com.guilherme.personapi.repository;

import com.guilherme.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
