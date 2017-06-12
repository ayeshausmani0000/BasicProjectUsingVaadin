package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import com.example.BasicProjectUsingVaadin.dto.ClientDTO;
import com.example.BasicProjectUsingVaadin.dto.CountryDTO;
import com.example.BasicProjectUsingVaadin.dto.SeasonDTO;
import com.example.BasicProjectUsingVaadin.dto.SizeDTO;
import com.example.BasicProjectUsingVaadin.model.ClientEntity;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;
import com.example.BasicProjectUsingVaadin.model.SeasonEntity;
import com.example.BasicProjectUsingVaadin.model.SizeEntity;

public interface MasterRestService 
{
	public List<SeasonDTO> findAllSeason();

	public List<CountryDTO> findAllCountry();

	public List<ClientDTO> findAllClient();
	
	public List<SizeDTO> findAllSize();

	public SeasonDTO findSeasonById(Integer id);

	public CountryDTO findCountryById(Integer id);

	public ClientDTO findClientById(Integer id);
	
	public SizeDTO findSizeById(Integer id);
}
