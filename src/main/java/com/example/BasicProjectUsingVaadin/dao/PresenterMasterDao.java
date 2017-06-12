package com.example.BasicProjectUsingVaadin.dao;

import com.example.BasicProjectUsingVaadin.dto.ClientDto;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.SeasonDto;
import com.example.BasicProjectUsingVaadin.dto.SizeDto;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;

public interface PresenterMasterDao {
	public Iterable<SeasonDto> findAllSeason();

	public Iterable<CountryDto> findAllCountry();

	public Iterable<ClientDto> findAllClient();

	public Iterable<SizeDto> findAllSize();

	public SeasonEntity findSeasonById(Integer id);

	public CountryEntity findCountryById(Integer id);

	public ClientEntity findClientById(Integer id);

	public SizeEntity findSizeById(Integer id);
}
