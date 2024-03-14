package com.gleisonoliveira.personapi.Repository.Person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gleisonoliveira.personapi.Models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryBase {}
