package com.example.BasicProjectUsingVaadin.service;

import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;

public interface MasterService {
	public Iterable<SeasonEntity> findAllSeason();

	public Iterable<CountryEntity> findAllCountry();

	public Iterable<ClientEntity> findAllClient();
	
	public Iterable<SizeEntity> findAllSize();

	public SeasonEntity findSeasonById(Integer id);

	public CountryEntity findCountryById(Integer id);

	public ClientEntity findClientById(Integer id);
	
	public SizeEntity findSizeById(Integer id);

}
