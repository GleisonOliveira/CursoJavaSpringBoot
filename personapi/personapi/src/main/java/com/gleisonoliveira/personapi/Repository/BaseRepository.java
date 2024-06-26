package com.gleisonoliveira.personapi.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends Serializable, ID extends Serializable> extends JpaRepository<T, ID> {}
