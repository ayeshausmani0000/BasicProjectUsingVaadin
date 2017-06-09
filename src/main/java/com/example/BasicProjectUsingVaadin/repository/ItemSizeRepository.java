package com.example.BasicProjectUsingVaadin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.BasicProjectUsingVaadin.model.ItemSizeEntity;

@Repository
public interface ItemSizeRepository extends CrudRepository<ItemSizeEntity, Integer>{

}
