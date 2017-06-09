package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.CountryEntity;

@Repository
public interface CountryRepository extends CrudRepository<CountryEntity	, Integer>{

}
