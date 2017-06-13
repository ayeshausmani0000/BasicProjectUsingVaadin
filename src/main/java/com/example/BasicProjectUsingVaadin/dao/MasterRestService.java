package com.example.BasicProjectUsingVaadin.dao;

import java.util.List;

import com.example.BasicProjectUsingVaadin.dto.ClientDTO;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.SeasonDto;
import com.example.BasicProjectUsingVaadin.dto.SizeDto;


public interface MasterRestService 
{
	public List<SeasonDto> findAllSeason();

	public List<CountryDto> findAllCountry();

	public List<ClientDTO> findAllClient();
	
	public List<SizeDto> findAllSize();

	public SeasonDto findSeasonById(Integer id);

	public CountryDto findCountryById(Integer id);

	public ClientDTO findClientById(Integer id);
	
	public SizeDto findSizeById(Integer id);
}
