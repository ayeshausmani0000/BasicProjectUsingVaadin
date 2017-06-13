package com.example.BasicProjectUsingVaadin.dao.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.BasicProjectUsingVaadin.dao.MasterRestService;
import com.example.BasicProjectUsingVaadin.dto.ClientDto;
import com.example.BasicProjectUsingVaadin.dto.CountryDto;
import com.example.BasicProjectUsingVaadin.dto.SeasonDto;
import com.example.BasicProjectUsingVaadin.dto.SizeDto;
import com.example.BasicProjectUsingVaadin.impl.MasterServiceImpl;
import com.example.BasicProjectUsingVaadin.model.CountryEntity;


@RestController
public class MasterRestServiceImpl implements MasterRestService{

	@Autowired
	private MasterServiceImpl masterServiceImpl;
	
	@Override
	public List<SeasonDto> findAllSeason() {
		return null;
	}

	@Override
	public List<CountryDto> findAllCountry() {
		Iterable<CountryEntity> countryEntities=masterServiceImpl.findAllCountry();
		List<CountryDto> countryDTOs=new ArrayList<CountryDto>();
		for (CountryEntity countryEntity : countryEntities) {
			CountryDto countryDTO=new CountryDto();
			countryDTO.setName(countryEntity.getName());
			countryDTO.setIsoCode(countryEntity.getIsoCode());
			countryDTOs.add(countryDTO);
		}
		return countryDTOs;
	}

	@Override
	public List<ClientDto> findAllClient() {
		return null;
	}

	@Override
	public List<SizeDto> findAllSize() {
		return null;
	}

	@Override
	public SeasonDto findSeasonById(Integer id) {
		return null;
	}

	@Override
	public CountryDto findCountryById(Integer id) {
		return null;
	}

	@Override
	public ClientDto findClientById(Integer id) {
		return null;
	}

	@Override
	public SizeDto findSizeById(Integer id) {
		return null;
	}

}
