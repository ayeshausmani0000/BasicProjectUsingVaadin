package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.SeasonEntity;

@Repository
public interface SeasonRepository extends CrudRepository<SeasonEntity, Integer>{

}
