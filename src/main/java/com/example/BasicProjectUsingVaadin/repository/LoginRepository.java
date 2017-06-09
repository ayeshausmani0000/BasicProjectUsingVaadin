package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.BasicProjectUsingVaadin.model.LoginEntity;

public interface LoginRepository  extends CrudRepository<LoginEntity	, Integer>{

}
