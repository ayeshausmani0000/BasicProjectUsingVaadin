package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {

}
