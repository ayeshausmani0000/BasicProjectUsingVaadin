package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.SizeEntity;
@Repository
public interface SizeRepository extends CrudRepository<SizeEntity, Integer>{

}
