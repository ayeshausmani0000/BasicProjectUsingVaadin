package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.StyleEntity;
import com.example.BasicProjectUsingVaadin.model.StyleOverFilter;
import com.vaadin.spring.annotation.SpringComponent;

@SpringComponent
public interface EMDao {
	// List<StyleEntity> checkByStyleNo(StyleEntity styleEntity, SeasonEntity
	// seasonEntity, ClientEntity clientEntity);
	List<StyleEntity> checkByStyleNo(StyleEntity styleEntity);

List<StyleEntity> filterByStyleNoAndCountry(StyleOverFilter filterEntity);
	
}
