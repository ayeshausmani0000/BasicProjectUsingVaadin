package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.ItemEntity;
@Repository
public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {

}
